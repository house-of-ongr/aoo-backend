package com.aoo.admin.domain.home;

import com.aoo.admin.domain.exception.BadHomeNameFormatException;
import com.aoo.admin.domain.house.House;
import com.aoo.admin.domain.user.User;
import lombok.Getter;

@Getter
public class HomeDetail {
    private String name;

    public HomeDetail(String name) {
        this.name = name;
    }

    public HomeDetail(House house, User user) {
        this.name = user.getUserInfo().getNickname() + "의 " + house.getHouseDetail().getTitle();
    }

    public void updateName(String homeName) {
        if (homeName == null || homeName.isBlank()) throw new BadHomeNameFormatException(homeName);
        this.name = homeName;
    }
}
