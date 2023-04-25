--Creación de roles de usuario
CREATE ROLE Administrador;

CREATE ROLE Dependiente;

CREATE ROLE Cliente;

--Establecimiento de permisos sobre la base de cada rol
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO Administrador;

GRANT SELECT ON TrabajadoresSinClave, Dependiente, Vender, Espectador, SociosSinClave, LineaProducto, Producto, Comida, Entrada, Proyectar, Pelicula, Sala, Usuarios TO Dependiente;

GRANT INSERT, UPDATE, DELETE ON Vender, LineaProducto, Producto, Comida, Entrada TO Dependiente;

GRANT SELECT ON Comida, Entrada, Producto, LineaProducto, Proyectar, Pelicula, Sala, Cine, Usuarios TO Cliente;

GRANT EXECUTE ON FUNCTION registrar_socio(text, text, text, text, text, text, text, text), guardar_compra(integer, integer, text, float8) TO Cliente;