package com.aoo.admin.domain.home;

import com.aoo.admin.domain.exception.BadHomeNameFormatException;
import com.aoo.admin.domain.house.House;
import com.aoo.admin.domain.user.User;
import com.aoo.common.application.service.MockEntityFactoryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class HomeDetailTest {

    @Test
    @DisplayName("홈 이름 생성 테스트")
    void testCreateHomeName() throws Exception {
        // given
        House house = MockEntityFactoryService.getHouse();
        User user = MockEntityFactoryService.getUser();

        // when
        HomeDetail detail = new HomeDetail(house, user);

        // then
        assertThat(detail.getName()).isEqualTo(user.getUserInfo().getNickname() + "의 " + house.getHouseDetail().getTitle());
    }

    @Test
    @DisplayName("홈 이름 수정 테스트")
    void testUpdateHomeName() {
        // given
        String nullName = null;
        String emptyName = "";
        String blankName = " ";
        String newName = "new name";
        HomeDetail detail = new HomeDetail("original name");

        // when
        assertThatThrownBy(() -> detail.updateName(nullName)).isInstanceOf(BadHomeNameFormatException.class);
        assertThatThrownBy(() -> detail.updateName(emptyName)).isInstanceOf(BadHomeNameFormatException.class);
        assertThatThrownBy(() -> detail.updateName(blankName)).isInstanceOf(BadHomeNameFormatException.class);
        detail.updateName(newName);

        // then
        assertThat(detail.getName()).isEqualTo(newName);
    }
}