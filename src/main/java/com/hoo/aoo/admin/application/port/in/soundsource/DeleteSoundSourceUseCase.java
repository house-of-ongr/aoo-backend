package com.hoo.aoo.admin.application.port.in.soundsource;

import com.hoo.aoo.common.application.port.in.MessageDto;

public interface DeleteSoundSourceUseCase {
    MessageDto deleteSoundSource(Long id);
}
