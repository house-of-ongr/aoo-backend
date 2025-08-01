package com.aoo.admin.application.port.in.user;

import com.aoo.admin.domain.user.snsaccount.SnsDomain;
import com.aoo.common.application.port.in.Pagination;

import java.util.List;

public record SearchUserResultV2(
        List<UserInfo> users,
        Pagination pagination
) {
    public record UserInfo(
            Long id,
            String name,
            String nickname,
            String phoneNumber,
            String email,
            Long registeredDate,
            Boolean termsOfUseAgreement,
            Boolean personalInformationAgreement,
            List<SnsAccountInfo> snsAccounts
    ) {

    }

    public record SnsAccountInfo(
            SnsDomain domain,
            String email
    ) {

    }
}
