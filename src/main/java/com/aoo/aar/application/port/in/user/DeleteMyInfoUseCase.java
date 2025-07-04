package com.aoo.aar.application.port.in.user;

import com.aoo.admin.application.port.in.user.DeleteUserCommand;
import com.aoo.common.application.port.in.MessageDto;

public interface DeleteMyInfoUseCase {
    MessageDto deleteMyInfo(Long userId, DeleteUserCommand command);
}
