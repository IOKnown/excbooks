DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE IF NOT EXISTS users
(
  uid       BIGSERIAL PRIMARY KEY,
  nfirst    VARCHAR(15) NOT NULL,
  nsecond   VARCHAR(15) NOT NULL,
  password  VARCHAR(60) NOT NULL,
  email     VARCHAR(15) NOT NULL UNIQUE,
  acbalance INT,
  imgid     BIGINT
);

DROP TABLE IF EXISTS book CASCADE;
CREATE TABLE IF NOT EXISTS book
(
  bid    BIGSERIAL PRIMARY KEY,
  bname  VARCHAR(45) NOT NULL,
  bdesc  TEXT,
  price  INT         NOT NULL,
  userid BIGINT      NOT NULL,
  CONSTRAINT user_fk
  FOREIGN KEY (userid)
  REFERENCES users
);

DROP TABLE IF EXISTS author CASCADE;
CREATE TABLE IF NOT EXISTS author
(
  aid   BIGSERIAL PRIMARY KEY,
  aname VARCHAR(60) NOT NULL
);

DROP TABLE IF EXISTS image CASCADE;
CREATE TABLE IF NOT EXISTS image
(
  imgid       BIGSERIAL PRIMARY KEY,
  imgurl      VARCHAR(90) UNIQUE NOT NULL,
  img_oldname VARCHAR(20),
  bookid      BIGINT,
  CONSTRAINT book_fk
  FOREIGN KEY (bookid)
  REFERENCES book
);

DROP TABLE IF EXISTS author_book CASCADE;
CREATE TABLE author_book
(
  bookid   BIGINT NOT NULL,
  authorid BIGINT NOT NULL,
  PRIMARY KEY (bookid, authorid),
  CONSTRAINT booking_fk
  FOREIGN KEY (bookid)
  REFERENCES book,
  CONSTRAINT author_fk
  FOREIGN KEY (authorid)
  REFERENCES author
);


