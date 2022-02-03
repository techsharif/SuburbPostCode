CREATE TABLE suburb
(
    id                 BIGINT      NOT NULL AUTO_INCREMENT,
    suburb             VARCHAR(25) NOT NULL,
    postcode           BIGINT      NOT NULL,
    created_date       DATETIME    NOT NULL,
    last_modified_date DATETIME    NOT NULL,

    PRIMARY KEY (id),
    UNIQUE KEY (postcode)
)
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_bin;
