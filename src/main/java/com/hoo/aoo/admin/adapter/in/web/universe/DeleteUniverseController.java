package com.hoo.aoo.admin.adapter.in.web.universe;

import com.hoo.aoo.admin.application.port.in.universe.DeleteUniverseUseCase;
import com.hoo.aoo.common.application.port.in.MessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DeleteUniverseController {

    private final DeleteUniverseUseCase useCase;

    @DeleteMapping("/admin/universes/{id}")
    public ResponseEntity<MessageDto> delete(@PathVariable Long id) {
        return ResponseEntity.ok(useCase.delete(id));
    }
}
