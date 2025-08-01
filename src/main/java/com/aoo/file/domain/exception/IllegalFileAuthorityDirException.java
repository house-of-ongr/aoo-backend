package com.aoo.file.domain.exception;

public class IllegalFileAuthorityDirException extends RuntimeException {
    public IllegalFileAuthorityDirException(String authorityDir) {
        super("Illegal File Authority dir : " + authorityDir);
    }
}
