package com.hoo.aoo.admin.application.port.in.universe;

import com.hoo.aoo.admin.application.service.AdminErrorCode;
import com.hoo.aoo.admin.application.service.AdminException;
import com.hoo.aoo.admin.domain.universe.Category;
import com.hoo.aoo.admin.domain.universe.PublicStatus;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record UpdateUniverseCommand(
        Long targetId,
        String title,
        String description,
        Category category,
        PublicStatus publicStatus,
        List<String> tags,
        MultipartFile thumbnail,
        MultipartFile thumbMusic
) {

    public UpdateUniverseCommand {
        if (targetId == null || targetId <= 0) throw new AdminException(AdminErrorCode.ILLEGAL_ARGUMENT_EXCEPTION);
        if (title != null && (title.isBlank() || title.length() > 100))
            throw new AdminException(AdminErrorCode.ILLEGAL_ARGUMENT_EXCEPTION);
        if (description != null && description.length() > 5000)
            throw new AdminException(AdminErrorCode.ILLEGAL_ARGUMENT_EXCEPTION);
        if ((tags != null && !tags.isEmpty()) && (tags.size() > 10 || String.join("", tags).length() > 500))
            throw new AdminException(AdminErrorCode.ILLEGAL_ARGUMENT_EXCEPTION);
    }

    public static UpdateUniverseCommand from(UpdateUniverseCommand baseCommand, MultipartFile thumbnail, MultipartFile thumbMusic) {
        if (thumbnail != null && thumbnail.getSize() > 2 * 1024 * 1024)
            throw new AdminException(AdminErrorCode.ILLEGAL_UNIVERSE_CREATE_FILE);
        if (thumbMusic != null && thumbMusic.getSize() > 2 * 1024 * 1024)
            throw new AdminException(AdminErrorCode.ILLEGAL_UNIVERSE_CREATE_FILE);

        return new UpdateUniverseCommand(
                baseCommand.targetId(),
                baseCommand.title(),
                baseCommand.description(),
                baseCommand.category(),
                baseCommand.publicStatus(),
                baseCommand.tags(),
                thumbnail,
                thumbMusic
        );
    }
}
