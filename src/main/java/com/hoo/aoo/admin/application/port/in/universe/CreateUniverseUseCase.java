package com.hoo.aoo.admin.application.port.in.universe;

import com.hoo.aoo.common.application.port.in.MessageDto;

public interface CreateUniverseUseCase {
    MessageDto create(CreateUniverseCommand command);
}
