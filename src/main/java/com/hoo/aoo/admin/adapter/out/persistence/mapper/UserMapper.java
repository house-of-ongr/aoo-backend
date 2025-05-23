package com.hoo.aoo.admin.adapter.out.persistence.mapper;

import com.hoo.aoo.admin.application.port.in.user.QueryUserInfoResult;
import com.hoo.aoo.admin.domain.user.User;
import com.hoo.aoo.admin.domain.user.snsaccount.SnsAccount;
import com.hoo.aoo.common.adapter.in.web.DateTimeFormatters;
import com.hoo.aoo.common.adapter.out.persistence.entity.SnsAccountJpaEntity;
import com.hoo.aoo.common.adapter.out.persistence.entity.UserJpaEntity;
import com.hoo.aoo.common.application.port.in.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final SnsAccountMapper snsAccountMapper;

    public QueryUserInfoResult mapToQueryResults(Page<UserJpaEntity> userJpaEntities) {
        Page<QueryUserInfoResult.UserInfo> userInfosPages = userJpaEntities.map(this::mapToQueryResult);
        return new QueryUserInfoResult(userInfosPages.getContent(), Pagination.of(userInfosPages));

    }

    private QueryUserInfoResult.UserInfo mapToQueryResult(UserJpaEntity userJpaEntity) {
        return new QueryUserInfoResult.UserInfo(
                userJpaEntity.getId(),
                userJpaEntity.getRealName(),
                userJpaEntity.getNickname(),
                userJpaEntity.getPhoneNumber(),
                DateTimeFormatters.DOT_DATE.getFormatter().format(userJpaEntity.getCreatedTime()),
                userJpaEntity.getTermsOfUseAgreement(),
                userJpaEntity.getPersonalInformationAgreement(),
                userJpaEntity.getSnsAccountEntities().stream().map(this::mapToQueryResult).toList()
        );
    }

    private QueryUserInfoResult.SnsAccountInfo mapToQueryResult(SnsAccountJpaEntity snsAccountJpaEntity) {
        return new QueryUserInfoResult.SnsAccountInfo(
                snsAccountJpaEntity.getSnsDomain(),
                snsAccountJpaEntity.getEmail()
        );
    }

    public User mapToDomainEntity(UserJpaEntity userJpaEntity) {
        List<SnsAccount> snsAccounts = userJpaEntity.getSnsAccountEntities().stream().map(snsAccountMapper::mapToDomainEntity).toList();

        return User.load(
                userJpaEntity.getId(),
                userJpaEntity.getRealName(),
                userJpaEntity.getNickname(),
                userJpaEntity.getEmail(),
                userJpaEntity.getTermsOfUseAgreement(),
                userJpaEntity.getPersonalInformationAgreement(),
                userJpaEntity.getCreatedTime(),
                userJpaEntity.getUpdatedTime(),
                snsAccounts
        );
    }
}
