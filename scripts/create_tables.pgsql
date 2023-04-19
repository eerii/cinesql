CREATE TABLE IF NOT EXISTS Cine(
    id serial PRIMARY KEY,
    nombre varchar(30) NOT NULL,
    ciudad varchar(30),
    direccion varchar(200),
    num_salas integer CHECK (num_salas >= 1)
);

CREATE TABLE IF NOT EXISTS Espectador(
    id serial PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS Socio(
    id serial PRIMARY KEY,
    dni char(9),
    nombre varchar(15) NOT NULL,
    apellido1 varchar(15) NOT NULL,
    apellido2 varchar(15),
    correo_electronico varchar(40) UNIQUE NOT NULL,
    telefono char(9),
    fecha_nacimiento date,
    clave varchar(50) NOT NULL,
    FOREIGN KEY (id) REFERENCES Espectador(id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Trabajador(
    id serial PRIMARY KEY,
    dni char(9) NOT NULL,
    nombre varchar(15) NOT NULL,
    apellido1 varchar(15) NOT NULL,
    apellido2 varchar(15),
    telefono char(9),
    correo_electronico varchar(40) UNIQUE NOT NULL CHECK (correo_electronico LIKE '%@cinetse.es'),
    experiencia bool,
    clave varchar(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS Dependiente(
    id serial PRIMARY KEY,
    idiomas integer CHECK (idiomas >= 0),
    FOREIGN KEY (id) REFERENCES Trabajador(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS Trabajar(
    id_trabajador serial,
    id_cine serial,
    fecha_inicio date NOT NULL,
    fecha_fin date,
    sueldo real NOT NULL CHECK (sueldo >= 0),
    puesto varchar(25),
    PRIMARY KEY (id_trabajador, id_cine, fecha_inicio),
    FOREIGN KEY (id_trabajador) REFERENCES Trabajador(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_cine) REFERENCES Cine(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS Sala(
    id_cine serial,
    num_sala integer,
    num_butacas integer CHECK (num_butacas >= 1),
    es_3d bool NOT NULL,
    PRIMARY KEY (id_cine, num_sala),
    FOREIGN KEY (id_cine) REFERENCES Cine(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS Pelicula(
    id serial PRIMARY KEY,
    titulo varchar(50) NOT NULL,
    director varchar(50),
    edad_recomendada integer NOT NULL CHECK (edad_recomendada >= 0),
    fecha_estreno date NOT NULL,
    genero varchar(15),
    duracion integer CHECK (duracion > 0),
    pais varchar(20)
);

CREATE TABLE IF NOT EXISTS Proyectar(
    id_pelicula serial,
    id_cine serial,
    num_sala integer,
    fecha date NOT NULL,
    hora time NOT NULL,
    PRIMARY KEY (id_pelicula, id_cine, num_sala, fecha, hora),
    FOREIGN KEY (id_cine, num_sala) REFERENCES Sala(id_cine, num_sala) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_pelicula) REFERENCES Pelicula(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS Producto(
    id serial PRIMARY KEY,
    precio real NOT NULL CHECK (precio >= 0)
);

CREATE TABLE IF NOT EXISTS LineaProducto(
    id serial,
    id_producto serial,
    cantidad integer NOT NULL CHECK (cantidad > 0),
    PRIMARY KEY (id, id_producto),
    FOREIGN KEY (id_producto) REFERENCES Producto(id) ON DELETE NO action ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS Comida(
    id serial PRIMARY KEY,
    tipo char(30) NOT NULL,
    tamanho char(10) NOT NULL,
    FOREIGN KEY (id) REFERENCES Producto(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS Entrada(
    id serial,
    id_cine serial,
    id_pelicula serial,
    fecha date NOT NULL,
    hora time NOT NULL,
    sala integer,
    num_asiento integer NOT NULL,
    PRIMARY KEY (id, fecha, hora, sala, id_cine, id_pelicula),
    FOREIGN KEY (id) REFERENCES Producto(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (fecha, hora, sala, id_cine, id_pelicula) REFERENCES Proyectar(fecha, hora, num_sala, id_cine, id_pelicula) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS Vender(
    id_trabajador serial,
    id_espectador serial,
    id_producto serial,
    id_linea serial,
    fecha date NOT NULL,
    coste real NOT NULL CHECK (coste >= 0),
    PRIMARY KEY (id_trabajador, id_espectador, id_producto, id_linea, fecha),
    FOREIGN KEY (id_trabajador) REFERENCES Dependiente(id) ON DELETE NO action ON UPDATE CASCADE,
    FOREIGN KEY (id_espectador) REFERENCES Espectador(id) ON DELETE NO action ON UPDATE CASCADE,
    FOREIGN KEY (id_linea, id_producto) REFERENCES LineaProducto(id, id_producto) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS Usuarios(
    nombre varchar(30) PRIMARY KEY,
    rol varchar(20)
);