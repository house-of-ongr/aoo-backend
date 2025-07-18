package com.aoo.admin.application.service.home;

import com.aoo.admin.application.port.in.home.DeleteHomeUseCase;
import com.aoo.admin.application.port.out.home.DeleteHomePort;
import com.aoo.admin.application.port.out.item.DeleteItemPort;
import com.aoo.admin.application.port.out.item.FindItemPort;
import com.aoo.common.application.port.in.MessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteHomeService implements DeleteHomeUseCase {

    private final FindItemPort findItemPort;
    private final DeleteItemPort deleteItemPort;
    private final DeleteHomePort deleteHomePort;

    @Override
    @Transactional
    public MessageDto deleteHome(Long id) {

        findItemPort.loadAllItemsInHome(id).forEach(item ->
                deleteItemPort.deleteItem(item.getItemId().getId()));

        deleteHomePort.deleteHome(id);

        return new MessageDto(id + "번 홈이 삭제되었습니다.");
    }
}
