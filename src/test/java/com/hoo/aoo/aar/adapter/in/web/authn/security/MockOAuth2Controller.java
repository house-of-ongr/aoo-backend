package com.hoo.aoo.aar.adapter.in.web.authn.security;

import com.hoo.aoo.aar.adapter.in.web.authn.security.jwt.JwtUtil;
import com.hoo.aoo.aar.domain.SnsAccount;
import com.hoo.aoo.aar.domain.SnsAccountF;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequiredArgsConstructor
public class MockOAuth2Controller {

    @Value("${security.frontend-redirect-uri}")
    private String redirectUri;

    private final JwtUtil jwtUtil;

    @GetMapping("/mock/authn/login/{snsAccountId}")
    void mockLogin(HttpServletResponse response, @PathVariable Long snsAccountId) throws IOException {

        if (snsAccountId == 1L) {
            SnsAccount snsAccount = SnsAccountF.KAKAO_NOT_REGISTERED.get();
            String redirectUrl = UriComponentsBuilder.fromUriString(redirectUri)
                    .queryParam("nickname", URLEncoder.encode("남상엽", StandardCharsets.UTF_8))
                    .queryParam("accessToken", jwtUtil.getAccessToken(snsAccount))
                    .queryParam("provider", "kakao")
                    .queryParam("isFirstLogin", "true")
                    .build().toUriString();

            response.sendRedirect(redirectUrl);
        }
        else if (snsAccountId == 2L) {
            SnsAccount snsAccount = SnsAccountF.KAKAO_NOT_REGISTERED_2.get();
            String redirectUrl = UriComponentsBuilder.fromUriString(redirectUri)
                    .queryParam("nickname", URLEncoder.encode("남엽돌", StandardCharsets.UTF_8))
                    .queryParam("accessToken", jwtUtil.getAccessToken(snsAccount))
                    .queryParam("provider", "kakao")
                    .queryParam("isFirstLogin", "true")
                    .build().toUriString();

            response.sendRedirect(redirectUrl);
        }
        else throw new UnsupportedOperationException("SNS Account Id 허용범위 초과");
    }
}
