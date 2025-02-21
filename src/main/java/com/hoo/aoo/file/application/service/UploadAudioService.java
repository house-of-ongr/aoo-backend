package com.hoo.aoo.file.application.service;

import com.hoo.aoo.common.domain.Authority;
import com.hoo.aoo.file.application.port.in.UploadFileResult;
import com.hoo.aoo.file.application.port.in.UploadPrivateAudioUseCase;
import com.hoo.aoo.file.domain.FileType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UploadAudioService implements UploadPrivateAudioUseCase {

    private final FileProperties fileProperties;
    private final UploadService uploadService;


    @Override
    @Transactional
    public UploadFileResult privateUpload(List<MultipartFile> audios, Long ownerId) {
        return uploadService.upload(audios, ownerId, new BasicFileIdCreateStrategy(fileProperties.getBaseDir(), Authority.ALL_PRIVATE_IMAGE_ACCESS, FileType.AUDIO));
    }

}
