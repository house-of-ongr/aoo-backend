package com.aoo.admin.application.port.in.user;

public interface SearchUserUseCaseV2 {
    SearchUserResult query(SearchUserCommand command);
}
