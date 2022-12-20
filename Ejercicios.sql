
    -- Ejercicio 26
--1
SELECT * FROM peliculas WHERE (actor is null);
--1
SELECT * FROM peliculas WHERE (duracion is null);
UPDATE peliculas SET duracion = 0 WHERE (duracion is null);
SELECT * FROM peliculas WHERE (duracion = 0);
--1
SELECT * FROM peliculas WHERE (actor is null and duracion = 0);
DELETE FROM peliculas WHERE (actor is null and duracion = 0);

    -- Ejercicio 27
--1.1
SELECT * FROM visitas WHERE fechayhora between '2006-09-12' and '2006-10-11';
--1.2
SELECT * FROM visitas WHERE numero between 2 and 5;
--2.1
SELECT * FROM autos WHERE modelo between '1970' and '1990' ORDER BY modelo;
--2.2
SELECT * FROM autos WHERE precio between 50000 and 100000;

    --Ejercicio 28
--1
SELECT nombre, precio FROM medicamentos WHERE laboratorio IN ('Bayer', 'Bago');
--2
SELECT * FROM medicamentos WHERE cantidad BETWEEN 1 AND 5;
SELECT * FROM medicamentos WHERE cantidad IN (1, 2, 3, 4, 5);

    --Ejercicio 29
--1

--2





