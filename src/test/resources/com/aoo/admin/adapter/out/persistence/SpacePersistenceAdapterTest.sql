insert into UNIVERSE(UNIVERSE.ID, UNIVERSE.CREATED_TIME, UNIVERSE.UPDATED_TIME, UNIVERSE.CATEGORY, UNIVERSE.DESCRIPTION,
                     UNIVERSE.PUBLIC_STATUS, UNIVERSE.THUMB_MUSIC_FILE_ID, UNIVERSE.THUMBNAIL_FILE_ID,
                     universe.INNER_IMAGE_FILE_ID, UNIVERSE.TITLE, UNIVERSE.VIEW_COUNT)
values (1, '2025-06-09 10:30' - INTERVAL 9 HOUR, '2025-06-09 10:30' - INTERVAL 9 HOUR,
        'GOVERNMENT_AND_PUBLIC_INSTITUTION', '유니버스는 우주입니다.', 'PUBLIC', 1, 2, 3, '우주', 0);

insert into SPACE(SPACE.ID, SPACE.CREATED_TIME, SPACE.UPDATED_TIME, SPACE.DESCRIPTION, SPACE.INNER_IMAGE_FILE_ID,
                  SPACE.TITLE, SPACE.SX, SPACE.SY, SPACE.EX, SPACE.EY, SPACE.PARENT_SPACE_ID, SPACE.UNIVERSE_ID, HIDDEN)
values (1, '2025-06-09 10:30', '2025-06-09 10:30', '스페이스는 공간입니다.', 1, '공간', 0.3, 0.2, 0.2, 0.3, -1, 1, false);