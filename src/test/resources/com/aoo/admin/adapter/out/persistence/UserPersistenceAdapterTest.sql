insert into AAR_USER(ID, REAL_NAME, PHONE_NUMBER, NICKNAME, email, TERMS_OF_USE_AGREEMENT,
                     PERSONAL_INFORMATION_AGREEMENT, CREATED_TIME)
values (10, '남상엽', '01012345678', 'lea', 'test@example.com', false, false, '2025-06-03 02:32:01'),
       (11, '남상', '01012343158', 'up', 'up@example.com', false, false, '2025-06-03 02:32:00'),
       (12, '남엽', '01012345678', 'leaf2', 'leaf@example.com', false, false, '2025-06-03 02:32:00'),
       (13, '엽상', '01012345678', 'leaf3', 'leaff@example.com', false, false, '2025-06-03 02:32:00'),
       (14, '상엽', '01031585678', 'upleaf', 'upleaf@example.com', false, false, '2025-06-03 02:32:00'),
       (15, '남상엽돌', '01012345678', 'southupleafstone', 'south@example.com', false, false, '2025-06-03 02:32:00'),
       (16, '남상돌', '01012315867', 'upstone', 'stoneup@example.com', false, false, '2025-06-03 02:32:00'),
       (17, '남엽돌', '01012345678', 'leafstone', 'lstone@example.com', false, false, '2025-06-03 02:31:59');


insert into SNS_ACCOUNT(ID, REAL_NAME, NICKNAME, EMAIL, SNS_ID, SNS_DOMAIN, USER_ID)
values (1, '남상엽', 'leaf', 'test@example.com', 'SNS_ID', 'KAKAO', 10);

insert into HOUSE(ID, TITLE, AUTHOR, DESCRIPTION, BASIC_IMAGE_FILE_ID, BORDER_IMAGE_FILE_ID, WIDTH, HEIGHT,
                  CREATED_TIME, UPDATED_TIME)
values (20, 'cozy house', 'leaf', 'my cozy house', 1, 2, 5000, 5000, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

insert into ROOM(ID, NAME, X, Y, Z, WIDTH, HEIGHT, IMAGE_FILE_ID, HOUSE_ID)
values (1, '거실', 0, 0, 0, 5000, 1000, 5, 20),
       (2, '주방', 0, 1000, 0, 5000, 1000, 6, 20);

insert into HOME(ID, USER_ID, HOUSE_ID, NAME, CREATED_TIME, UPDATED_TIME)
values (1, 10, 20, 'leaf의 cozy house', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (2, 10, 20, 'leaf의 simple house', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


insert into ITEM_SHAPE(ID, X, Y, DTYPE)
values (1, 100, 100, 'RECTANGLE'),
       (2, 200, 200, 'CIRCLE'),
       (3, 500, 500, 'ELLIPSE');

insert into ITEM_SHAPE_RECTANGLE(ID, WIDTH, HEIGHT, ROTATION)
values (1, 10, 10, 5);

insert into ITEM_SHAPE_CIRCLE(ID, RADIUS)
values (2, 10.5);

insert into ITEM_SHAPE_ELLIPSE(ID, RADIUSX, RADIUSY, ROTATION)
values (3, 15, 15, 90);

insert into ITEM(ID, NAME, HOME_ID, ROOM_ID, ITEM_SHAPE_ID)
values (1, '설이', 1, 1, 1),
       (2, '강아지', 1, 1, 2),
       (3, '화분', 1, 1, 3);

insert into SOUND_SOURCE(ID, NAME, DESCRIPTION, AUDIO_FILE_ID, IS_ACTIVE, CREATED_TIME, UPDATED_TIME, ITEM_ID)
values (1, '골골송', '2025년 골골송 V1', 1, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
       (2, '멍멍송', '2025년 멍멍송 V1', 2, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),
       (3, '골골송 V2', '2025년 골골송 V2', 3, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);

insert into BUSINESS_USER(ID, EMAIL, PASSWORD, NICKNAME, STATUS, TERMS_OF_USE_AGREEMENT, PERSONAL_INFORMATION_AGREEMENT, CREATED_TIME, UPDATED_TIME)
values (1, 'test@example.com', '{bcrypt}$2a$10$E4pJtJxQtjHHH11j8/tNTOJljXDFJc3NWGlbCt00SZQB8Nn7DiKF.', 'temp_user123',
        'WAITING', true, true, '2025-06-27 20:15', '2025-06-27 20:15');