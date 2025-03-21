package com.hoo.aoo.aar.adapter.in.web.user;

import com.hoo.aoo.aar.adapter.out.api.KakaoLoginApi;
import com.hoo.aoo.aar.adapter.out.api.KakaoProperties;
import com.hoo.aoo.aar.adapter.out.persistence.repository.UserJpaRepository;
import com.hoo.aoo.common.adapter.in.web.config.AbstractControllerTest;
import com.hoo.aoo.common.adapter.out.persistence.repository.DeletedUserJpaRepository;
import com.hoo.aoo.common.adapter.out.persistence.repository.HomeJpaRepository;
import com.hoo.aoo.common.adapter.out.persistence.repository.ItemJpaRepository;
import com.hoo.aoo.common.adapter.out.persistence.repository.SoundSourceJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DeleteMyAccountControllerTest extends AbstractControllerTest {

    @Autowired
    UserJpaRepository userJpaRepository;

    @Autowired
    HomeJpaRepository homeJpaRepository;

    @Autowired
    ItemJpaRepository itemJpaRepository;

    @Autowired
    SoundSourceJpaRepository soundSourceJpaRepository;

    @Autowired
    DeletedUserJpaRepository deletedUserJpaRepository;

    @LocalServerPort
    private int port;

    @Autowired
    KakaoProperties kakaoProperties;

    @BeforeEach
    void init() {
        ReflectionTestUtils.setField(kakaoProperties, "unlinkUrl", "http://localhost:" + port + "/unlink");
    }

    @Test
    @Sql("DeleteMyAccountControllerTest.sql")
    @DisplayName("회원탈퇴 API")
    void testDeleteMyAccountAPI() throws Exception {

        //language=JSON
        String content = """
                {
                  "termsOfDeletionAgreement" : true,
                  "personalInformationDeletionAgreement" : true
                }
                """;

        mockMvc.perform(delete("/aar/users")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(jwt().jwt(jwt -> jwt.claim("userId", 10L))
                                .authorities(new SimpleGrantedAuthority("ROLE_USER"))
                        ))
                .andExpect(status().is(200))
                .andDo(MockMvcRestDocumentation.document("aar-user-delete",
                        requestFields(
                                fieldWithPath("termsOfDeletionAgreement").description("삭제 약관 동의여부입니다."),
                                fieldWithPath("personalInformationDeletionAgreement").description("개인정보 삭제 동의여부입니다.")
                        ),
                        responseFields(
                                fieldWithPath("message").description("탈퇴 완료 메시지 : 회원탈퇴가 완료되었습니다.")
                        )
                ));

        assertThat(userJpaRepository.findById(10L)).isEmpty();
        assertThat(homeJpaRepository.findAllByUserId(10L)).isEmpty();
        assertThat(itemJpaRepository.findAllByUserId(10L)).isEmpty();
        assertThat(soundSourceJpaRepository.findAllByUserId(10L)).isEmpty();
        assertThat(deletedUserJpaRepository.findByMaskedRealName("남*엽")).isNotEmpty();
    }
}