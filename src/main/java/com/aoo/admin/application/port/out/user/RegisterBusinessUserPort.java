package com.aoo.admin.application.port.out.user;

import com.aoo.admin.domain.user.User;

public interface RegisterBusinessUserPort {
    User registerBusinessUser(Long businessUserId);
}
