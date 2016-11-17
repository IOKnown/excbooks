INSERT INTO users VALUES (DEFAULT ,'user', 'user', 'user', 'user@test.com', 300),
  (DEFAULT ,'petro', 'petro', 'petro', 'petro@test.com', 200),
  (DEFAULT ,'fedir', 'fedir', 'fedir', 'fedir@test.com', 4325);

INSERT INTO book VALUES (DEFAULT, 'Book of life', NULL, 20, 1),
  (DEFAULT, 'Bla-bla-bla', NULL, 24, 2);

INSERT INTO author VALUES (DEFAULT, 'ivan author'),
  (DEFAULT, 'petro author');

INSERT INTO author_book VALUES (1, 2),
  (2,1),
  (1,1);

INSERT INTO image VALUES (DEFAULT, 'https://s3.eu-central-1.amazonaws.com/ioknown/book/GbdCUTc7ksE.jpg',null,1),
  (DEFAULT, 'https://s3.eu-central-1.amazonaws.com/ioknown/book/Van.jpg',null,2);

ALTER TABLE users ADD  CONSTRAINT imgid_users_fk FOREIGN KEY (imgid) REFERENCES image;