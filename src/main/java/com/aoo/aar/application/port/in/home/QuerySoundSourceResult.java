package com.aoo.aar.application.port.in.home;

public record QuerySoundSourceResult(
        String name,
        String description,
        String createdDate,
        String updatedDate,
        Long audioFileId
) {

}
