package com.hoo.aoo.admin.application.service.universe;

import com.hoo.aoo.admin.application.port.in.universe.CreateUniverseCommand;
import com.hoo.aoo.admin.application.port.out.universe.CreateUniversePort;
import com.hoo.aoo.admin.application.port.out.universe.SaveUniversePort;
import com.hoo.aoo.admin.application.service.AdminErrorCode;
import com.hoo.aoo.admin.domain.universe.Category;
import com.hoo.aoo.admin.domain.universe.PublicStatus;
import com.hoo.aoo.common.application.port.in.MessageDto;
import com.hoo.aoo.common.domain.Authority;
import com.hoo.aoo.file.application.port.in.UploadFileResult;
import com.hoo.aoo.file.application.port.in.UploadPublicAudioUseCase;
import com.hoo.aoo.file.application.port.in.UploadPublicImageUseCase;
import com.hoo.aoo.file.domain.FileSize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateUniverseServiceTest {

    CreateUniverseService sut;

    CreateUniversePort createUniversePort = mock();
    UploadPublicImageUseCase uploadPublicImageUseCase = mock();
    UploadPublicAudioUseCase uploadPublicAudioUseCase = mock();
    SaveUniversePort saveUniversePort = mock();

    @BeforeEach
    void init() {
        sut = new CreateUniverseService(createUniversePort, uploadPublicImageUseCase, uploadPublicAudioUseCase, saveUniversePort);
    }

    @Test
    @DisplayName("유니버스 생성 서비스")
    void testUniverseCreateService() {
        // given
        Map<String, MultipartFile> map = new HashMap<>();
        map.put("thumbnail", new MockMultipartFile("thumbnail", "universe_thumb.png", "image/png", "universe file".getBytes()));
        map.put("thumbMusic", new MockMultipartFile("thumbMusic", "universe_music.mp3", "audio/mpeg", "music file".getBytes()));

        CreateUniverseCommand command = new CreateUniverseCommand("우주", "유니버스는 우주입니다.", Category.GOVERNMENT_AND_PUBLIC_INSTITUTION, PublicStatus.PUBLIC, List.of("우주", "행성", "지구", "별"), map);

        // when
        when(uploadPublicImageUseCase.publicUpload(any())).thenReturn(new UploadFileResult(List.of(new UploadFileResult.FileInfo(1L, null, "universe_music.mp3", "test1235.mp3", new FileSize(1234L, 10000L).getUnitSize(), Authority.PUBLIC_FILE_ACCESS))));
        when(uploadPublicAudioUseCase.publicUpload(any())).thenReturn(new UploadFileResult(List.of(new UploadFileResult.FileInfo(1L, null, "universe_thumb.png", "test1234.png", new FileSize(1234L, 10000L).getUnitSize(), Authority.PUBLIC_FILE_ACCESS))));
        MessageDto messageDto = sut.create(command);

        // then
        verify(saveUniversePort, times(1)).save(any());
        assertThat(messageDto.message()).contains("번 유니버스가 생성되었습니다.");
    }

}