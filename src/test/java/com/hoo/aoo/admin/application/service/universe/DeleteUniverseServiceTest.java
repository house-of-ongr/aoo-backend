package com.hoo.aoo.admin.application.service.universe;

import com.hoo.aoo.admin.application.port.out.universe.DeleteUniversePort;
import com.hoo.aoo.admin.application.port.out.universe.FindUniversePort;
import com.hoo.aoo.admin.domain.universe.Universe;
import com.hoo.aoo.common.application.port.in.MessageDto;
import com.hoo.aoo.common.application.service.MockEntityFactoryService;
import com.hoo.aoo.file.application.port.in.DeleteFileUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class DeleteUniverseServiceTest {

    DeleteUniverseService sut;

    FindUniversePort findUniversePort;
    DeleteFileUseCase deleteFileUseCase;
    DeleteUniversePort deleteUniversePort;

    @BeforeEach
    void init() {
        findUniversePort = mock();
        deleteFileUseCase = mock();
        deleteUniversePort = mock();
        sut = new DeleteUniverseService(findUniversePort, deleteFileUseCase, deleteUniversePort);
    }

    @Test
    @DisplayName("썸네일 및 썸뮤직 파일 삭제요청, DB 삭제요청")
    void testDeleteUniverse() {
        // given
        Long id = 1L;
        Universe universe = MockEntityFactoryService.getUniverse();

        // when
        when(findUniversePort.load(id)).thenReturn(Optional.of(universe));
        MessageDto message = sut.delete(id);

        // then
        verify(deleteFileUseCase, times(2)).deleteFile(anyLong());
        verify(deleteUniversePort, times(1)).delete(universe);
        assertThat(message.message()).contains("번 유니버스가 삭제되었습니다.");
    }

}