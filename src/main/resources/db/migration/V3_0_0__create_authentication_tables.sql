CREATE TABLE "user" (
  id           uuid PRIMARY KEY,
  first_name   VARCHAR(255),
  last_name    VARCHAR(255),
  email        VARCHAR(255),
  username     VARCHAR(255) NOT NULL,
  password     VARCHAR(255),
  date_created TIMESTAMP,
  date_updated TIMESTAMP
);

CREATE TABLE "role" (
  id           uuid PRIMARY KEY,
  name         VARCHAR(255),
  date_created TIMESTAMP,
  date_updated TIMESTAMP
);

CREATE TABLE "permission" (
  id           uuid PRIMARY KEY,
  name         VARCHAR(255) NOT NULL,
  date_created TIMESTAMP,
  date_updated TIMESTAMP
);

CREATE TABLE user_roles(
  id           uuid PRIMARY KEY,
  user_id      uuid NOT NULL,
  role_id      uuid NOT NULL,
  date_created TIMESTAMP,
  date_updated TIMESTAMP,
  CONSTRAINT fk_user_roles_user
  FOREIGN KEY (user_id)
  REFERENCES "user" (id),
  CONSTRAINT fk_user_roles_role
  FOREIGN KEY (role_id)
  REFERENCES "role" (id)
);

CREATE TABLE permission_roles (
  id           uuid PRIMARY KEY,
  permission_id uuid NOT NULL,
  role_id      uuid NOT NULL,
  date_created TIMESTAMP,
  date_updated TIMESTAMP,
  CONSTRAINT fk_roles_permissions
  FOREIGN KEY (permission_id)
  REFERENCES permission (id),
  CONSTRAINT fk_roles_permissions_role
  FOREIGN KEY (role_id)
  REFERENCES "role" (id)
);