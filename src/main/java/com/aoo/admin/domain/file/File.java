package com.aoo.admin.domain.file;

import lombok.Getter;

@Getter
public class File {

    private final FileId fileId;
    private final FileType fileType;

    public File(FileId fileId, FileType fileType) {
        this.fileId = fileId;
        this.fileType = fileType;
    }
}
