--liquibase formatted sql logicalFilePath:2020-09-09--03-event

--changeset lepa-typeId:column subway
ALTER TABLE events ADD COLUMN subway text
--rollback ALTER TABLE events DROP COLUMN subway