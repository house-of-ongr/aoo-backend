package com.aoo.admin.application.port.out.house;

import com.aoo.admin.domain.house.House;
import com.aoo.admin.domain.house.room.Room;

import java.util.List;

public interface CreateHousePort {
    House createHouse(String title, String author, String description, Float width, Float height, Long basicImageId, Long borderImageId, List<Room> rooms);
}
