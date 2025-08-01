package com.aoo.common.adapter.in.web;

import com.aoo.common.domain.Authority;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public class GrantedAuthorityAdapter implements GrantedAuthority {

    private final Authority authority;

    @Override
    public String getAuthority() {
        return authority.name();
    }
}
