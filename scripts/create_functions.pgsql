CREATE OR REPLACE FUNCTION registrar_socio(nombre text, apellido1 text, apellido2 text, dni text, correo text, telefono text, fecha_nac text, clave text)
    RETURNS void
    SECURITY INVOKER
    AS $$
DECLARE
    id integer;
BEGIN
    SELECT
        nextval(pg_get_serial_sequence('espectador', 'id')) INTO id;
    EXECUTE format('INSERT INTO Usuarios VALUES(%L,%L)', correo, 'Cliente');
    EXECUTE format('INSERT INTO Espectador VALUES(%L)', id);
    EXECUTE format('INSERT INTO Socio VALUES(%L,%L,%L,%L,%L,%L,%L,%L,%L)', id, dni, nombre, apellido1, apellido2, correo, telefono, fecha_nac, clave);
    EXECUTE format('CREATE USER %I IN ROLE Cliente PASSWORD %L', correo, clave);
END;
$$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION guardar_compra(id_linea integer, id_producto integer, correo text, coste float8)
    RETURNS void
    SECURITY INVOKER
    AS $$
DECLARE
    id_usuario integer;
BEGIN
    --Se obtiene el id del usuario
    SELECT
        id_espectador INTO id_usuario
    FROM
        usuario
    WHERE
        correo_electronico = correo;
    --Se inserta en la tabla LineaProducto
    EXECUTE format('insert into LineaProducto values(%L,%L,1)', id_linea, id_producto);
    --Se inserta en la tabla Vender
    EXECUTE format('insert into Vender values(1,%L,%L,%L,%L,)', id_usuario, id_producto, id_linea, CURRENT_DATE, coste);
END;
$$
LANGUAGE plpgsql;

