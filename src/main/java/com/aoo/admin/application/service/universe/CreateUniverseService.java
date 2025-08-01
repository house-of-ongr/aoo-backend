package com.aoo.admin.application.service.universe;

import com.aoo.admin.application.port.in.universe.CreateUniverseCommand;
import com.aoo.admin.application.port.in.universe.CreateUniverseResult;
import com.aoo.admin.application.port.in.universe.CreateUniverseUseCase;
import com.aoo.admin.application.port.out.category.FindCategoryPort;
import com.aoo.admin.application.port.out.universe.CreateUniversePort;
import com.aoo.admin.application.port.out.universe.SaveUniversePort;
import com.aoo.admin.application.port.out.user.FindUserPort;
import com.aoo.admin.application.service.AdminErrorCode;
import com.aoo.admin.application.service.AdminException;
import com.aoo.admin.domain.universe.Universe;
import com.aoo.admin.domain.universe.UniverseCategory;
import com.aoo.admin.domain.user.User;
import com.aoo.file.application.port.in.UploadFileResult;
import com.aoo.file.application.port.in.UploadPublicAudioUseCase;
import com.aoo.file.application.port.in.UploadPublicImageUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.aoo.common.adapter.out.persistence.entity.QUniverseJpaEntity.universeJpaEntity;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateUniverseService implements CreateUniverseUseCase {

    private final FindUserPort findUserPort;
    private final FindCategoryPort findCategoryPort;
    private final UploadPublicImageUseCase uploadPublicImageUseCase;
    private final UploadPublicAudioUseCase uploadPublicAudioUseCase;
    private final SaveUniversePort saveUniversePort;

    @Override
    public CreateUniverseResult create(CreateUniverseCommand command) {
        User author = findUserPort.loadUser(command.authorId()).orElseThrow(() -> new AdminException(AdminErrorCode.USER_NOT_FOUND));

        UploadFileResult.FileInfo thumbMusic = uploadPublicAudioUseCase.publicUpload(command.fileMap().get("thumbMusic"));
        UploadFileResult.FileInfo thumbnail = uploadPublicImageUseCase.publicUpload(command.fileMap().get("thumbnail"));
        UploadFileResult.FileInfo innerImage = uploadPublicImageUseCase.publicUpload(command.fileMap().get("innerImage"));

        UniverseCategory universeCategory = findCategoryPort.findUniverseCategory(command.categoryId());

        Universe universe = Universe.create(
                thumbMusic.id(),
                thumbnail.id(),
                innerImage.id(),
                command.title(),
                command.description(),
                universeCategory,
                command.publicStatus(),
                command.hashtags(),
                author
        );

        Universe savedUniverse = saveUniversePort.save(universe);

        return new CreateUniverseResult(
                String.format("[#%d]번 유니버스가 생성되었습니다.", savedUniverse.getId()),
                savedUniverse.getId(),
                savedUniverse.getFileInfo().getThumbMusicId(),
                savedUniverse.getFileInfo().getThumbnailId(),
                savedUniverse.getFileInfo().getImageId(),
                savedUniverse.getAuthorInfo().getId(),
                savedUniverse.getDateInfo().getCreatedTime().toEpochSecond(),
                savedUniverse.getCategory().getId(),
                savedUniverse.getBasicInfo().getTitle(),
                savedUniverse.getBasicInfo().getDescription(),
                savedUniverse.getAuthorInfo().getNickname(),
                savedUniverse.getBasicInfo().getPublicStatus().name(),
                savedUniverse.getSocialInfo().getHashtags()
        );
    }

}
