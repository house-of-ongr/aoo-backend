package com.aoo.aar.application.port.in.authn;

public interface LoginBusinessUserUseCase {
    LoginBusinessUserResult login(String email, String password);
}
