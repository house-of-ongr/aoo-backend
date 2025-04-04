package com.hoo.aoo.common.adapter.out.persistence.entity;

import com.hoo.aoo.admin.domain.universe.Universe;
import com.hoo.aoo.common.application.service.MockEntityFactoryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class UniverseJpaEntityTest {

    @Test
    @DisplayName("유니버스 도메인으로부터 엔티티 생성")
    void testCreateEntityByDomain() {
        // given
        Universe universe = MockEntityFactoryService.getUniverse();

        // when
        UniverseJpaEntity universeJpaEntity = UniverseJpaEntity.create(universe);

        // then
        assertThat(universeJpaEntity.getId()).isEqualTo(null);
        assertThat(universeJpaEntity.getTitle()).isEqualTo(universe.getBasicInfo().getTitle());
        assertThat(universeJpaEntity.getDescription()).isEqualTo(universe.getBasicInfo().getDescription());
        assertThat(universeJpaEntity.getPublicStatus()).isEqualTo(universe.getBasicInfo().getPublicStatus());
        assertThat(universeJpaEntity.getCategory()).isEqualTo(universe.getBasicInfo().getCategory());
        assertThat(universeJpaEntity.getViewCount()).isEqualTo(0L);
        assertThat(universeJpaEntity.getUniverseHashtags()).isEmpty();
        assertThat(universeJpaEntity.getUniverseLikes()).isEqualTo(List.of());
        assertThat(universeJpaEntity.getThumbnailFileId()).isEqualTo(universe.getThumbnailId());
        assertThat(universeJpaEntity.getThumbMusicFileId()).isEqualTo(universe.getThumbMusicId());
    }
}