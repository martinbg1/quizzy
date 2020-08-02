CREATE TABLE users (
    userID INTEGER PRIMARY KEY AUTOINCREMENT,
    name   TEXT    NOT NULL,
    score  INTEGER
);

CREATE TABLE questions (
    questionID INTEGER PRIMARY KEY AUTOINCREMENT,
    prompt     TEXT    NOT NULL,
    answer     CHAR    NOT NULL
);

INSERT INTO users VALUES('Hva er det høyeste fjellet i verden?\na)Himalaya\nb)Galdhøpiggen\nc)Mount Everest','c');
INSERT INTO users VALUES('Hva er hovedstaden i USA?\na)Washington, DC\nb)New York\nc)Los Angeles','a');
