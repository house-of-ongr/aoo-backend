package com.aoo.admin.application.service.user;

import com.aoo.admin.application.port.in.user.UpdateUserInfoCommand;
import com.aoo.admin.application.port.in.user.UpdateUserInfoUseCase;
import com.aoo.admin.application.port.out.user.FindUserPort;
import com.aoo.admin.application.port.out.user.UpdateUserPort;
import com.aoo.admin.application.service.AdminErrorCode;
import com.aoo.admin.application.service.AdminException;
import com.aoo.admin.domain.user.User;
import com.aoo.common.application.port.in.MessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UpdateUserService implements UpdateUserInfoUseCase {

    private final FindUserPort findUserPort;
    private final UpdateUserPort updateUserPort;

    @Override
    public MessageDto updateUserInfo(Long userId, UpdateUserInfoCommand command) {

        User user = findUserPort.loadUser(userId)
                .orElseThrow(() -> new AdminException(AdminErrorCode.USER_NOT_FOUND));

        user.updateNickname(command.nickname());

        updateUserPort.updateUser(user);

        return new MessageDto("본인정보가 수정되었습니다.");
    }
}
