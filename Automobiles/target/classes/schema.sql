DROP TABLE IF EXISTS model_engine;
DROP TABLE IF EXISTS engine;
DROP TABLE IF EXISTS model;
DROP TABLE IF EXISTS type;
DROP TABLE IF EXISTS make;

CREATE TABLE make (
make_id int NOT NULL AUTO_INCREMENT,
make_name varchar (128) NOT NULL,
PRIMARY KEY (make_id)
);

CREATE TABLE type (
type_id int NOT NULL AUTO_INCREMENT,
type_name varchar (128) NOT NULL,
PRIMARY KEY (type_id)
);

CREATE TABLE model (
model_id int NOT NULL AUTO_INCREMENT,
make_id int NOT NULL,
type_id int NOT NULL,
model_name varchar (128) NOT NULL,
PRIMARY KEY (model_id),
FOREIGN KEY (make_id) REFERENCES make (make_id) ON DELETE CASCADE,
FOREIGN KEY (type_id) REFERENCES type (type_id) ON DELETE CASCADE
);

CREATE TABLE engine (
engine_id int NOT NULL AUTO_INCREMENT,
fuel_type varchar (60) NOT NULL,
size varchar (28) NOT null,
primary key (engine_id)
);

CREATE TABLE model_engine (
model_id int NOT NULL,
engine_id int NOT NULL,
FOREIGN KEY (model_id) REFERENCES model (model_id) ON DELETE CASCADE,
FOREIGN KEY (engine_id) REFERENCES engine (engine_id) ON DELETE CASCADE,
UNIQUE KEY (model_id, engine_id)
);