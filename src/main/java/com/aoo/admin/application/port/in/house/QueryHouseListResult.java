package com.aoo.admin.application.port.in.house;

import com.aoo.common.adapter.in.web.DateTimeFormatters;
import com.aoo.common.adapter.out.persistence.entity.HouseJpaEntity;
import com.aoo.common.application.port.in.Pagination;

import java.util.List;

public record QueryHouseListResult(
        List<HouseInfo> houses,
        Pagination pagination
) {
    public record HouseInfo(
            Long id,
            String title,
            String author,
            String description,
            String createdDate,
            String updatedDate,
            Long imageId
    ) {

        public static HouseInfo of(HouseJpaEntity houseJpaEntity) {
            return new HouseInfo(
                    houseJpaEntity.getId(),
                    houseJpaEntity.getTitle(),
                    houseJpaEntity.getAuthor(),
                    houseJpaEntity.getDescription().length() > 100 ? houseJpaEntity.getDescription().substring(0, 100) + "..." : houseJpaEntity.getDescription(),
                    DateTimeFormatters.ENGLISH_DATE.getFormatter().format(houseJpaEntity.getCreatedTime()),
                    DateTimeFormatters.ENGLISH_DATE.getFormatter().format(houseJpaEntity.getUpdatedTime()),
                    houseJpaEntity.getBasicImageFileId());
        }
    }
}
