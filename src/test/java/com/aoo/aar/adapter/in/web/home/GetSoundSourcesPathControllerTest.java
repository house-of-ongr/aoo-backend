package com.aoo.aar.adapter.in.web.home;

import com.aoo.common.adapter.in.web.config.AbstractControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.jdbc.Sql;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class GetSoundSourcesPathControllerTest extends AbstractControllerTest {

    @Test
    @Sql("GetSoundSourcesPathControllerTest.sql")
    @DisplayName("전체 음원 경로조회 API")
    void testGetAllMySoundSourceAPI() throws Exception {
        mockMvc.perform(get("/aar/sound-sources/path")
                        .param("page", "1")
                        .param("size", "3")
                        .with(jwt().jwt(jwt -> jwt.claim("userId", 10L))
                                .authorities(new SimpleGrantedAuthority("ROLE_USER"))
                        ))
                .andExpect(status().is(200))
                .andDo(document("aar-home-get-soundsource-path",
                        pathParameters(
                                parameterWithName("page").description("보여줄 페이지 번호입니다. +" + "\n" + "* 기본값 : 1").optional(),
                                parameterWithName("size").description("한 페이지에 보여줄 데이터 개수입니다. +" + "\n" + "* 기본값 : 10").optional()
                        ),
                        responseFields(
                                fieldWithPath("soundSources[].name").description("음원의 이름입니다."),
                                fieldWithPath("soundSources[].description").description("음원의 상세설명입니다."),
                                fieldWithPath("soundSources[].createdDate").description("음원의 생성일입니다."),
                                fieldWithPath("soundSources[].updatedDate").description("음원의 수정일입니다."),
                                fieldWithPath("soundSources[].audioFileId").description("음원이 보유한 음악 파일 ID입니다."),
                                fieldWithPath("soundSources[].homeName").description("음원이 위치한 홈 이름입니다."),
                                fieldWithPath("soundSources[].homeId").description("음원이 위치한 홈 ID입니다."),
                                fieldWithPath("soundSources[].roomName").description("음원이 위치한 방 이름입니다."),
                                fieldWithPath("soundSources[].roomId").description("음원이 위치한 룸 ID입니다."),
                                fieldWithPath("soundSources[].itemName").description("음원이 위치한 아이템 이름입니다."),
                                fieldWithPath("soundSources[].itemId").description("음원이 위치한 아이템 ID입니다."),

                                fieldWithPath("pagination.pageNumber").description("현재 페이지 번호입니다."),
                                fieldWithPath("pagination.size").description("한 페이지의 항목 수입니다."),
                                fieldWithPath("pagination.totalElements").description("조회된 전체 개수입니다."),
                                fieldWithPath("pagination.totalPages").description("조회된 전체 페이지 개수입니다.")
                        )
                ));

    }
}