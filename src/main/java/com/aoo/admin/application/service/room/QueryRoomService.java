package com.aoo.admin.application.service.room;

import com.aoo.admin.application.port.in.room.QueryRoomInfoUseCase;
import com.aoo.admin.application.port.in.room.QueryRoomItemsResult;
import com.aoo.admin.application.port.in.room.QueryRoomItemsUseCase;
import com.aoo.admin.application.port.in.room.QueryRoomResult;
import com.aoo.admin.application.port.out.item.FindItemPort;
import com.aoo.admin.application.port.out.room.FindRoomPort;
import com.aoo.admin.application.service.AdminErrorCode;
import com.aoo.admin.application.service.AdminException;
import com.aoo.admin.domain.house.room.Room;
import com.aoo.admin.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QueryRoomService implements QueryRoomInfoUseCase, QueryRoomItemsUseCase {

    private final FindRoomPort findRoomPort;
    private final FindItemPort findItemPort;

    @Override
    @Transactional(readOnly = true)
    public QueryRoomResult queryRoom(Long id) {

        Room room = findRoomPort.load(id)
                .orElseThrow(() -> new AdminException(AdminErrorCode.ROOM_NOT_FOUND));

        return QueryRoomResult.of(room);
    }

    @Override
    public QueryRoomItemsResult queryRoomItems(Long homeId, Long roomId) {

        Room room = findRoomPort.load(roomId)
                .orElseThrow(() -> new AdminException(AdminErrorCode.ROOM_NOT_FOUND));

        List<Item> items = findItemPort.loadAllItemsInHomeAndRoom(homeId, roomId);

        return QueryRoomItemsResult.of(room, items);
    }
}
