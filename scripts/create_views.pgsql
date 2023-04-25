CREATE OR REPLACE VIEW SociosSinClave AS (
    SELECT
        s.id_espectador,
        s.dni,
        s.nombre,
        s.apellido1,
        s.apellido2,
        s.correo_electronico,
        s.telefono,
        s.fecha_nacimiento
    FROM
        socio s);

CREATE OR REPLACE VIEW TrabajadoresSinClave AS (
    SELECT
        t.id,
        t.dni,
        t.nombre,
        t.apellido1,
        t.apellido2,
        t.correo_electronico,
        t.telefono,
        t.experiencia
    FROM
        trabajador t);