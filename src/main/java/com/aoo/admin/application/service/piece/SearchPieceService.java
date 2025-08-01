package com.aoo.admin.application.service.piece;

import com.aoo.admin.application.port.in.piece.SearchPieceCommand;
import com.aoo.admin.application.port.in.piece.SearchPieceResult;
import com.aoo.admin.application.port.in.piece.SearchPieceUseCase;
import com.aoo.admin.application.port.out.piece.FindPiecePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SearchPieceService implements SearchPieceUseCase {

    private final FindPiecePort findPiecePort;

    @Override
    public SearchPieceResult search(SearchPieceCommand command) {
        return findPiecePort.search(command);
    }
}
