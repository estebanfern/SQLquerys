CREATE TABLE "usuarios" (
  "id" SERIAL PRIMARY KEY,
  "email" text UNIQUE NOT NULL,
  "celular" int NOT NULL,
  "primerNombre" text NOT NULL,
  "segundoNombre" text,
  "primerApellido" text NOT NULL,
  "segundoApellido" text NOT NULL
);

CREATE TABLE "cursos" (
  "idCurso" SERIAL PRIMARY KEY,
  "fechaInicio" date NOT NULL,
  "fechaFin" date NOT NULL,
  "costo" int NOT NULL,
  "turno" int NOT NULL
);

CREATE TABLE "profesor" (
  "idProfe" SERIAL PRIMARY KEY,
  "nombre" text NOT NULL,
  "apellido" text NOT NULL
);

CREATE TABLE "inscripciones" (
  "idInscripcion" SERIAL PRIMARY KEY,
  "idUser" int NOT NULL,
  "idCurso" int NOT NULL,
  "fechaInscripcion" date NOT NULL,
  "pagado" boolean NOT NULL
);

CREATE TABLE "malla" (
  "idMalla" SERIAL PRIMARY KEY,
  "idCurso" int NOT NULL,
  "idMateria" int NOT NULL
);

CREATE TABLE "interesados" (
  "idInteresado" SERIAL PRIMARY KEY,
  "celular" int NOT NULL,
  "email" text NOT NULL
);

CREATE TABLE "intereses" (
  "idInteres" SERIAL PRIMARY KEY,
  "idCurso" int NOT NULL,
  "idInteresado" int NOT NULL
);

CREATE TABLE "materias" (
  "idMateria" SERIAL PRIMARY KEY,
  "idProfe" int NOT NULL,
  "seccion" varchar[2] NOT NULL
);

ALTER TABLE "inscripciones" ADD FOREIGN KEY ("idUser") REFERENCES "usuarios" ("id");

ALTER TABLE "inscripciones" ADD FOREIGN KEY ("idCurso") REFERENCES "cursos" ("idCurso");

ALTER TABLE "malla" ADD FOREIGN KEY ("idCurso") REFERENCES "cursos" ("idCurso");

ALTER TABLE "materias" ADD FOREIGN KEY ("idProfe") REFERENCES "profesor" ("idProfe");

ALTER TABLE "malla" ADD FOREIGN KEY ("idMateria") REFERENCES "materias" ("idMateria");

ALTER TABLE "intereses" ADD FOREIGN KEY ("idInteresado") REFERENCES "interesados" ("idInteresado");

ALTER TABLE "intereses" ADD FOREIGN KEY ("idCurso") REFERENCES "cursos" ("idCurso");
