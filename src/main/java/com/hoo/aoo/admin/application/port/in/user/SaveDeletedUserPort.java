package com.hoo.aoo.admin.application.port.in.user;

import com.hoo.aoo.admin.domain.user.DeletedUser;

public interface SaveDeletedUserPort {
    Long saveDeletedUser(DeletedUser deletedUser);
}
