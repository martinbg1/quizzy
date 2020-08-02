--CREATE TABLE users (
--    userID INTEGER PRIMARY KEY AUTOINCREMENT,
--    name   TEXT    NOT NULL,
--    score  INTEGER
--);
--
--CREATE TABLE questions (
--    questionID INTEGER PRIMARY KEY AUTOINCREMENT,
--    prompt     TEXT    NOT NULL,
--    answer     CHAR    NOT NULL
--);

INSERT INTO questions VALUES(NULL, 'Hva er det høyeste fjellet i verden?' || char(10) || 'a) Himalaya' || char(10) ||'b) Galdhøpiggen' || char(10) ||'c) Mount Everest','c', 'geography');
INSERT INTO questions VALUES(NULL, 'Hva er hovedstaden i USA?' || char(10) || 'a) Washington, DC' || char(10) || 'b) New York' || char(10) || 'c) Los Angeles','a', 'geography');
INSERT INTO questions VALUES(NULL, 'Hva het første mannen som gikk på månen?' || char(10) || 'a) Edwin Aldrin' || char(10) || 'b) Neil Armstrong' || char(10) || 'c) Chris Hadfield','b', 'history');
INSERT INTO questions VALUES(NULL, 'Når okkuperte tyske styrker Norge under 2. verdenskrig?' || char(10) || 'a) 9. april 1939' || char(10) || 'b) 8. mai 1940' || char(10) || 'c) 9. april 1940','c', 'history');

