DROP TABLE IF EXISTS TEST;

CREATE TABLE IF NOT EXISTS TEST (
    Names VARCHAR(50) PRIMARY KEY,
    Email VARCHAR(50),
    Department VARCHAR(40)
);

CREATE TABLE IF NOT EXISTS ANOTHER(
    Names VARCHAR(50) PRIMARY KEY,
    Email VARCHAR(50),
    Department VARCHAR(40)
);