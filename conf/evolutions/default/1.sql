# Items SCHEMA
# --- !Ups
create table items (
  id INT not null auto_increment,
  title VARCHAR(255),
  description VARCHAR(255),
  status BIT
);

#
INSERT INTO items(title, description, status) VALUES ('title', 'description', 0)

# --- !Downs

DROP TABLE items;