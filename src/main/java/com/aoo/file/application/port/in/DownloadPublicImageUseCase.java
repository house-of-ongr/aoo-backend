package com.aoo.file.application.port.in;

public interface DownloadPublicImageUseCase {
    DownloadFileResult publicDownload(Long fileId, boolean attachment);
}
