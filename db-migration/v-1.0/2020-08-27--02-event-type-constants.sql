--liquibase formatted sql logicalFilePath:2020-08-27--02-event-type-constants

--changeset lepa-typeId:insert_bar
INSERT INTO event_type(event_type_id, event_type_name, description) values (1, 'BAR','Поход в бар');
--rollback delete from event_type where event_type_id=1

--changeset lepa-typeId:insert_team_play
INSERT INTO event_type(event_type_id, event_type_name, description) values (2, 'TEAM_PLAY','Командная работа');
--rollback  delete from event_type where event_type_id=2


--changeset lepa-typeId:insert_study
INSERT INTO event_type(event_type_id, event_type_name, description) values (3, 'STUDY','Обучение');
--rollback  delete from event_type where event_type_id=3


--changeset lepa-typeId:insert_board_game
INSERT INTO event_type(event_type_id, event_type_name, description) values (4, 'BOARD_GAME','Настольные игры');
--rollback delete from event_type where event_type_id=4


--changeset lepa-typeId:insert_quest
INSERT INTO event_type(event_type_id, event_type_name, description) values (5, 'QUEST','Совместный квест');
--rollback delete from event_type where event_type_id=5