package com.aoo.common.application.service;

import com.aoo.admin.application.port.in.piece.CreatePieceCommand;
import com.aoo.admin.application.port.in.sound.CreateSoundCommand;
import com.aoo.admin.application.port.in.space.CreateSpaceCommand;
import com.aoo.admin.application.port.in.universe.CreateUniverseCommand;
import com.aoo.admin.application.port.in.user.CreateDeletedUserPort;
import com.aoo.admin.application.port.out.home.CreateHomePort;
import com.aoo.admin.application.port.out.house.CreateHousePort;
import com.aoo.admin.application.port.out.house.CreateRoomPort;
import com.aoo.admin.application.port.out.item.CreateItemPort;
import com.aoo.admin.application.port.out.piece.CreatePiecePort;
import com.aoo.admin.application.port.out.snsaccount.CreateSnsAccountPort;
import com.aoo.admin.application.port.out.sound.CreateSoundPort;
import com.aoo.admin.application.port.out.soundsource.CreateSoundSourcePort;
import com.aoo.admin.application.port.out.space.CreateSpacePort;
import com.aoo.admin.application.port.out.universe.CreateUniversePort;
import com.aoo.admin.application.port.out.user.CreateUserPort;
import com.aoo.admin.domain.home.Home;
import com.aoo.admin.domain.house.House;
import com.aoo.admin.domain.house.room.Room;
import com.aoo.admin.domain.item.Item;
import com.aoo.admin.domain.item.Shape;
import com.aoo.admin.domain.item.soundsource.SoundSource;
import com.aoo.admin.domain.universe.Universe;
import com.aoo.admin.domain.universe.piece.sound.Sound;
import com.aoo.admin.domain.universe.space.Space;
import com.aoo.admin.domain.universe.piece.Piece;
import com.aoo.admin.domain.user.DeletedUser;
import com.aoo.admin.domain.user.User;
import com.aoo.admin.domain.user.snsaccount.SnsAccount;
import com.aoo.admin.domain.user.snsaccount.SnsDomain;
import com.aoo.common.application.port.out.IssueIdPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EntityFactoryService implements CreateUserPort, CreateSnsAccountPort, CreateHousePort, CreateRoomPort, CreateHomePort, CreateItemPort, CreateSoundSourcePort, CreateDeletedUserPort, CreateSpacePort, CreatePiecePort, CreateSoundPort {

    private final IssueIdPort issueIdPort;

    @Override
    public User createUser(SnsAccount snsAccount, Boolean termsOfUseAgreement, Boolean personalInformationAgreement) {
        Long newId = issueIdPort.issueUserId();
        return User.register(newId, termsOfUseAgreement, personalInformationAgreement, snsAccount);
    }

    @Override
    public SnsAccount createSnsAccount(SnsDomain snsDomain, String snsId, String realName, String nickname, String email) {
        Long newId = issueIdPort.issueSnsAccountId();
        return SnsAccount.create(newId, snsDomain, snsId, realName, nickname, email);
    }

    @Override
    public House createHouse(String title, String author, String description, Float width, Float height, Long basicImageId, Long borderImageId, List<Room> rooms) {
        Long newId = issueIdPort.issueHouseId();
        return House.create(newId, title, author, description, width, height, basicImageId, borderImageId, rooms);
    }

    @Override
    public Room createRoom(String name, Float x, Float y, Float z, Float width, Float height, Long imageFileId) {
        Long newId = issueIdPort.issueRoomId();
        return Room.create(newId, name, x, y, z, width, height, imageFileId);
    }

    @Override
    public Home createHome(House house, User user) {
        Long newId = issueIdPort.issueHomeId();
        return Home.create(newId, house, user);
    }

    @Override
    public Item createItem(Long homeId, Long roomId, Long userId, String name, Shape shape) {
        Long newId = issueIdPort.issueItemId();
        return Item.create(newId, homeId, roomId, userId, name, shape);
    }

    @Override
    public SoundSource createSoundSource(Long itemId, Long audioFileId, String name, String description, Boolean active) {
        Long newId = issueIdPort.issueSoundSourceId();
        return SoundSource.create(newId, itemId, audioFileId, name, description, active);
    }

    @Override
    public DeletedUser createDeletedUser(User user, Boolean termsOfDeletionAgreement, Boolean personalInformationDeletionAgreement) {
        Long newId = issueIdPort.issueDeletedUserId();
        return DeletedUser.create(newId, user, termsOfDeletionAgreement, personalInformationDeletionAgreement);
    }

    @Override
    public Space createSpace(CreateSpaceCommand command, Long innerImageFileId) {
        Long newId = issueIdPort.issueSpaceId();

        return Space.create(newId,
                innerImageFileId,
                command.universeId(),
                command.parentSpaceId(),
                command.title(),
                command.description(),
                command.startX(),
                command.startY(),
                command.endX(),
                command.endY(),
                command.hidden()
        );
    }

    @Override
    public Piece createPieceWithoutImageFile(CreatePieceCommand command) {
        Long newId = issueIdPort.issuePieceId();

        return Piece.create(newId,
                -1L,
                command.universeId(),
                command.parentSpaceId(),
                command.title(),
                command.description(),
                command.startX(),
                command.startY(),
                command.endX(),
                command.endY(),
                command.hidden()
        );
    }

    @Override
    public Sound createSound(Long audioId, CreateSoundCommand command) {
        Long newId = issueIdPort.issueSoundId();

        return Sound.create(newId, audioId, command.pieceId(), command.title(), command.description(), command.hidden());
    }
}
