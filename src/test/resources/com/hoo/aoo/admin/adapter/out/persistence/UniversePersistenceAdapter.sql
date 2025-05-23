insert into UNIVERSE(UNIVERSE.ID, UNIVERSE.CREATED_TIME, UNIVERSE.UPDATED_TIME, UNIVERSE.CATEGORY, UNIVERSE.DESCRIPTION, UNIVERSE.PUBLIC_STATUS, UNIVERSE.THUMB_MUSIC_FILE_ID, UNIVERSE.THUMBNAIL_FILE_ID, UNIVERSE.TITLE, UNIVERSE.VIEW_COUNT) values
       (1, CURRENT_TIMESTAMP - INTERVAL 9 HOUR, CURRENT_TIMESTAMP - INTERVAL 9 HOUR, 'GOVERNMENT_AND_PUBLIC_INSTITUTION', '공공기관 관련 유니버스입니다.', 'PUBLIC', '1', '1', '정책 유니버스', 1),
       (2, CURRENT_TIMESTAMP - INTERVAL 9 HOUR, CURRENT_TIMESTAMP - INTERVAL 9 HOUR, 'GOVERNMENT_AND_PUBLIC_INSTITUTION', '비공개 공공기관 유니버스입니다.', 'PRIVATE', '2', '2', '비공개 정책', 2),
       (3, CURRENT_TIMESTAMP - INTERVAL 9 HOUR, CURRENT_TIMESTAMP - INTERVAL 9 HOUR, 'HEALTH_INSTITUTION', '건강 기관과 관련된 콘텐츠입니다.', 'PUBLIC', '3', '3', '건강 유니버스', 3),
       (4, CURRENT_TIMESTAMP - INTERVAL 9 HOUR, CURRENT_TIMESTAMP - INTERVAL 9 HOUR, 'HEALTH_INSTITUTION', '비공개 건강 관련 정보입니다.', 'PRIVATE', '4', '4', '비공개 건강 정보', 5),
       (5, CURRENT_TIMESTAMP - INTERVAL 9 HOUR, CURRENT_TIMESTAMP - INTERVAL 9 HOUR, 'LIFE', '생활과 일상에 대한 이야기입니다.', 'PUBLIC', '5', '5', '라이프스타일 유니버스', 4),
       (6, CURRENT_TIMESTAMP - INTERVAL 9 HOUR, CURRENT_TIMESTAMP - INTERVAL 9 HOUR, 'LIFE', '비공개 일상 콘텐츠입니다.', 'PRIVATE', '6', '6', '비공개 라이프', 7),
       (7, CURRENT_TIMESTAMP - INTERVAL 9 HOUR, CURRENT_TIMESTAMP - INTERVAL 9 HOUR, 'FASHION_AND_BEAUTY', '패션과 뷰티 관련 유니버스입니다.', 'PUBLIC', '7', '7', '패션 유니버스', 10),
       (8, CURRENT_TIMESTAMP - INTERVAL 9 HOUR, CURRENT_TIMESTAMP - INTERVAL 9 HOUR, 'FASHION_AND_BEAUTY', '비공개 패션 콘텐츠입니다.', 'PRIVATE', '8', '8', '비공개 뷰티', 7),
       (9, CURRENT_TIMESTAMP - INTERVAL 9 HOUR, CURRENT_TIMESTAMP - INTERVAL 9 HOUR, 'HEALTH_INSTITUTION', '웰빙과 건강을 위한 콘텐츠입니다.', 'PUBLIC', '9', '9', '웰니스 유니버스', 4),
       (10,CURRENT_TIMESTAMP - INTERVAL 9 HOUR, CURRENT_TIMESTAMP - INTERVAL 9 HOUR, 'LIFE', '삶의 소소한 이야기를 담았습니다.', 'PUBLIC', '10', '10', '소소한 유니버스', 6),
       (11,CURRENT_TIMESTAMP - INTERVAL 9 HOUR, CURRENT_TIMESTAMP - INTERVAL 9 HOUR, 'FASHION_AND_BEAUTY', '럭셔리와 아름다움의 세계입니다.', 'PUBLIC', '11', '11', '럭셔리 유니버스', 1),
       (12,CURRENT_TIMESTAMP - INTERVAL 9 HOUR, CURRENT_TIMESTAMP - INTERVAL 9 HOUR, 'GOVERNMENT_AND_PUBLIC_INSTITUTION', '유니버스는 우주입니다.', 'PUBLIC', '12', '12', '우주', 0);


insert into HASHTAG(ID, TAG, CREATED_TIME, UPDATED_TIME) values
    ('1', 'exist', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('2', '우주', current_timestamp, current_timestamp),
    ('3', '행성', current_timestamp, current_timestamp),
    ('4', '지구', current_timestamp, current_timestamp),
    ('5', '별', current_timestamp, current_timestamp);

insert into UNIVERSE_HASHTAG(ID, HASHTAG_ID, UNIVERSE_ID) values
    ('1', '2', '12'),
    ('2', '3', '12'),
    ('3', '4', '12'),
    ('4', '5', '12');

insert into AAR_USER(ID, REAL_NAME, PHONE_NUMBER, NICKNAME, TERMS_OF_USE_AGREEMENT, PERSONAL_INFORMATION_AGREEMENT) values
    (10, '남상엽', 'NOT_SET', 'leaf', false, false),
    (11, '남상엽', 'NOT_SET', 'leaf', false, false),
    (12, '남상엽', 'NOT_SET', 'leaf', false, false),
    (13, '남상엽', 'NOT_SET', 'leaf', false, false);

insert into UNIVERSE_LIKE(UNIVERSE_LIKE.ID, UNIVERSE_LIKE.CREATED_TIME, UNIVERSE_LIKE.UPDATED_TIME, UNIVERSE_LIKE.UNIVERSE_ID, UNIVERSE_LIKE.USER_ID) values
    (1, current_timestamp, current_timestamp, 12, 10),
    (2, current_timestamp, current_timestamp, 12, 11),
    (3, current_timestamp, current_timestamp, 12, 12),
    (4, current_timestamp, current_timestamp, 12, 13);
