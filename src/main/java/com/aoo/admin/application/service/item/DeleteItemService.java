package com.aoo.admin.application.service.item;

import com.aoo.admin.application.port.in.item.DeleteItemUseCase;
import com.aoo.admin.application.port.out.item.DeleteItemPort;
import com.aoo.admin.application.port.out.item.FindItemPort;
import com.aoo.admin.application.service.AdminErrorCode;
import com.aoo.admin.application.service.AdminException;
import com.aoo.admin.domain.item.Item;
import com.aoo.common.application.port.in.MessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteItemService implements DeleteItemUseCase {

    private final FindItemPort findItemPort;
    private final DeleteItemPort deleteItemPort;

    @Override
    public MessageDto deleteItem(Long id) {
        Item item = findItemPort.loadItem(id)
                .orElseThrow(() -> new AdminException(AdminErrorCode.ITEM_NOT_FOUND));

        if (item.hasSoundSource())
            throw new AdminException(AdminErrorCode.ITEM_HAS_SOUND_SOURCE);

        deleteItemPort.deleteItem(id);

        return new MessageDto(id + "번 아이템이 삭제되었습니다.");
    }
}
