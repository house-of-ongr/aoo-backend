package com.aoo.admin.adapter.in.web.soundsource;

import com.aoo.admin.application.port.in.soundsource.DeleteSoundSourceUseCase;
import com.aoo.common.application.port.in.MessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DeleteSoundSourceController {

    private final DeleteSoundSourceUseCase deleteSoundSourceUseCase;

    @DeleteMapping("/admin/sound-sources/{soundSourceId}")
    public ResponseEntity<MessageDto> deleteSoundSource(@PathVariable Long soundSourceId) {
        return ResponseEntity.ok(deleteSoundSourceUseCase.deleteSoundSource(soundSourceId));
    }
}
