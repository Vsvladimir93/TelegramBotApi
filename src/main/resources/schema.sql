DROP TABLE IF EXISTS keywords;
DROP TABLE IF EXISTS subscribed_chats;
DROP TABLE IF EXISTS settings;

CREATE TABLE keywords (
	chat_id bigint NOT NULL,
	keyword varchar NOT NULL UNIQUE
);

CREATE TABLE settings (
	chat_id bigint PRIMARY KEY NOT NULL,
	cron_time varchar(255) NOT NULL,
	time_zone varchar(30) NOT NULL
);

CREATE TABLE subscribed_chats (
	chat_id bigint NOT NULL UNIQUE
);