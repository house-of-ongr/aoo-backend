package com.hoo.aoo.aar.adapter.in.web.authn.security.service;

import com.hoo.aoo.aar.adapter.in.web.authn.security.SNSLoginResponse;
import com.hoo.aoo.aar.adapter.in.web.authn.security.dto.OAuth2Dto;
import com.hoo.aoo.aar.adapter.in.web.authn.security.jwt.JwtUtil;
import com.hoo.aoo.aar.adapter.out.persistence.mapper.UserMapper;
import com.hoo.aoo.aar.application.port.out.database.LoadSnsAccountPort;
import com.hoo.aoo.aar.application.port.out.database.SaveSnsAccountPort;
import com.hoo.aoo.aar.domain.SnsAccount;
import com.nimbusds.jose.shaded.gson.Gson;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@AllArgsConstructor
public class KakaoLoadUserService implements LoadUserService {

    private final Gson gson = new Gson();
    private final LoadSnsAccountPort loadSnsAccountPort;
    private final SaveSnsAccountPort saveSnsAccountPort;
    private final UserMapper userMapper;
    private final JwtUtil jwtUtil;

    @Override
    @Transactional
    public OAuth2User load(OAuth2User user) {

        OAuth2Dto.KakaoUserInfo userInfo = gson.fromJson(gson.toJsonTree(user.getAttributes()), OAuth2Dto.KakaoUserInfo.class);

        SnsAccount snsAccountInDB = loadSnsAccountPort.loadWithUser(userInfo.id())
                .orElseGet(() -> save(userInfo));

        SNSLoginResponse response = snsAccountInDB.getUser() == null?
                SNSLoginResponse.of(snsAccountInDB, jwtUtil.getAccessToken(snsAccountInDB), true):
                SNSLoginResponse.of(snsAccountInDB, jwtUtil.getAccessToken(snsAccountInDB), false);

        return new DefaultOAuth2User(user.getAuthorities(), response.getAttributes(), "nickname");
    }

    private SnsAccount save(OAuth2Dto.KakaoUserInfo userInfo) {
        return saveSnsAccountPort.save(userMapper.kakaoUserToSnsAccount(userInfo));
    }
}
