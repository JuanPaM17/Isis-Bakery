
/* DDL */
DROP TABLE tblventa;
DROP TABLE tbldetalle_pedido;
DROP TABLE tblpedido;
DROP TABLE tblusuario;
DROP TABLE tblproducto;
DROP TABLE tblestado_pedido;
DROP TABLE tbltipo_usuario;
DROP TABLE tblestado_usuario;

CREATE TABLE tblestado_pedido(
id_estado_pedido INTEGER NOT NULL AUTO_INCREMENT,
nombre_estado VARCHAR(20) NOT NULL, PRIMARY KEY(id_estado_pedido), UNIQUE(nombre_estado)
);
CREATE TABLE tbltipo_usuario(
id_tipo_usuario INTEGER NOT NULL AUTO_INCREMENT,
nombre_tipo VARCHAR(20) NOT NULL, PRIMARY KEY(id_tipo_usuario), UNIQUE(nombre_tipo)
);
CREATE TABLE tblestado_usuario(
id_estado_usuario INTEGER NOT NULL AUTO_INCREMENT,
nombre_estado VARCHAR(20) NOT NULL, PRIMARY KEY(id_estado_usuario), UNIQUE(nombre_estado)
);
CREATE TABLE tblusuario(
id_usuario INTEGER NOT NULL AUTO_INCREMENT,
cedula BIGINT NOT NULL,
nombre VARCHAR(20) NOT NULL,
apellido VARCHAR(20) NOT NULL,
fecha_nacimiento DATE NOT NULL,
celular BIGINT NOT NULL,
correo VARCHAR(50) NOT NULL,
direccion VARCHAR(50) NOT NULL,
color VARCHAR(20) NOT NULL,
contrasena VARCHAR(20) NOT NULL,
id_estado_usuario INTEGER NOT NULL,
id_tipo_usuario INTEGER NOT NULL, PRIMARY KEY(id_usuario), UNIQUE(cedula), UNIQUE(correo), FOREIGN KEY(id_estado_usuario) REFERENCES tblestado_usuario(id_estado_usuario), FOREIGN KEY(id_tipo_usuario) REFERENCES tbltipo_usuario(id_tipo_usuario)
);
CREATE INDEX indice_cedula_usuario ON tblusuario(nombre);
CREATE INDEX indice_correo_usuario ON tblusuario(correo);
CREATE INDEX indice_contrasena_usuario ON tblusuario(contrasena);
CREATE INDEX indice_estado_usuario ON tblusuario(id_estado_usuario);
CREATE INDEX indice_color_usuario ON tblusuario(color);
CREATE TABLE tblproducto(
id_producto INTEGER NOT NULL AUTO_INCREMENT,
codigo VARCHAR(20) NOT NULL,
nombre VARCHAR(50) NOT NULL,
descripcion VARCHAR(100) NOT NULL,
precio_venta FLOAT NOT NULL,
stock INT NOT NULL,
foto VARCHAR(100) NOT NULL, PRIMARY KEY(id_producto), UNIQUE(codigo)
);
CREATE INDEX indice_codigo_producto ON tblproducto(codigo);
CREATE INDEX indice_nombre_producto ON tblproducto(nombre);
CREATE TABLE tblpedido(
id_pedido INTEGER NOT NULL AUTO_INCREMENT,
id_cliente INTEGER NOT NULL,
total FLOAT NOT NULL,
fecha_recogida VARCHAR(20) NOT NULL,
hora_recogida VARCHAR(20) NOT NULL,
nombre_recoge VARCHAR(20) NOT NULL,
telefono_contacto BIGINT NOT NULL,
id_estado_pedido INTEGER NOT NULL, PRIMARY KEY(id_pedido), FOREIGN KEY(id_cliente) REFERENCES tblusuario(id_usuario), FOREIGN KEY(id_estado_pedido) REFERENCES tblestado_pedido(id_estado_pedido)
);
CREATE TABLE tbldetalle_pedido(
id_detalle_pedido INTEGER NOT NULL AUTO_INCREMENT,
id_pedido INTEGER NOT NULL,
id_producto INTEGER NOT NULL,
cantidad INTEGER NOT NULL,
subtotal FLOAT NOT NULL, PRIMARY KEY(id_detalle_pedido),
FOREIGN KEY(id_pedido) REFERENCES tblpedido(id_pedido), FOREIGN KEY(id_producto) REFERENCES tblproducto(id_producto)
);
CREATE INDEX indice_id_pedido_detallepedido ON tbldetalle_pedido(id_pedido);
CREATE INDEX indice_id_producto_detallepedido ON tbldetalle_pedido(id_producto);
CREATE TABLE tblventa(
id_venta INTEGER NOT NULL AUTO_INCREMENT,
id_pedido INTEGER NOT NULL,
id_vendedor INTEGER NOT NULL,
nit BIGINT NOT NULL,
fecha_generacion DATE NOT NULL, PRIMARY KEY(id_venta), FOREIGN KEY(id_pedido) REFERENCES tblpedido(id_pedido), FOREIGN KEY(id_vendedor) REFERENCES tblusuario(id_usuario), UNIQUE(nit)
);

/* DML */
INSERT INTO tblestado_pedido (nombre_estado) VALUES ('espera');
INSERT INTO tblestado_pedido (nombre_estado) VALUES ('aceptado');
INSERT INTO tblestado_pedido (nombre_estado) VALUES ('rechazado');
INSERT INTO tblestado_pedido (nombre_estado) VALUES ('venta');
INSERT INTO tbltipo_usuario (nombre_tipo) VALUES ('administrador');
INSERT INTO tbltipo_usuario (nombre_tipo) VALUES ('cliente');
INSERT INTO tbltipo_usuario (nombre_tipo) VALUES ('vendedor');
INSERT INTO tblestado_usuario (nombre_estado) VALUES ('activo');
INSERT INTO tblestado_usuario (nombre_estado) VALUES ('desactivo');
INSERT INTO tblusuario (cedula,nombre,apellido,fecha_nacimiento,celular,correo,direccion,color,contrasena,id_estado_usuario,id_tipo_usuario) VALUES (1234567890,'admin','admin','2004/04/17',1234567890,'admin@hotmail.com','belen','azul','admin',1,1);
INSERT INTO tblusuario (cedula,nombre,apellido,fecha_nacimiento,celular,correo,direccion,color,contrasena,id_estado_usuario,id_tipo_usuario) VALUES (1234567894, "Santiago", "Torres Aguirre", "2004-04-17",3006465445, "santi@hotmail.es","Medellin","azul","santi",1,3);
INSERT INTO tblusuario (cedula,nombre,apellido,fecha_nacimiento,celular,correo,direccion,color,contrasena,id_estado_usuario,id_tipo_usuario) VALUES (1039700598, "Juan Pablo", "Madrigal Castañeda", "2004-04-17",3213901548, "juanmarcas@hotmail.com","Medellin","verde","juan",1,3);
INSERT INTO tblusuario (cedula,nombre,apellido,fecha_nacimiento,celular,correo,direccion,color,contrasena,id_estado_usuario,id_tipo_usuario) VALUES (1234567891, "Jhoa", "Alzate Aguirre", "2023-02-20",1234567890, "jhoa@udea.com","Cr 77B Apt 775","azul","1234",1,2);
INSERT INTO tblusuario (cedula,nombre,apellido,fecha_nacimiento,celular,correo,direccion,color,contrasena,id_estado_usuario,id_tipo_usuario) VALUES (3987654321, "Laura", "Alzate Aguirre", "1999-03-08",3027738932, "laualzateaquirre@gmail.com","Calle 40 76J","azul","1234",1,2);
INSERT INTO tblproducto(codigo,nombre,descripcion,precio_venta,stock,foto) VALUES ("P01","Buñuelos", "Rellenos de queso", 3999, 80, "https://i.postimg.cc/jdD1RfBL/Bu-uelos.png");
INSERT INTO tblproducto(codigo,nombre,descripcion,precio_venta,stock,foto) VALUES ("P02","Croissant", "Panes hojaldrados franceses", 3500, 35, "https://i.postimg.cc/RhDbcCJS/Croissant.png");
INSERT INTO tblproducto(codigo,nombre,descripcion,precio_venta,stock,foto) VALUES ("P03","Galleta de mora", "Mora artesanal", 700, 40, "https://i.postimg.cc/gJ472HY6/Galletas-de-Mora.png");
INSERT INTO tblproducto(codigo,nombre,descripcion,precio_venta,stock,foto) VALUES ("P04","Pan Tradicional", "Maiz Campesino", 3000, 646, "https://i.postimg.cc/wv9PzGdp/Pan-Tradicional.png");
INSERT INTO tblproducto(codigo,nombre,descripcion,precio_venta,stock,foto) VALUES ("P05","Pastel de pollo", "Verduras y pollo sazonado", 2500, 49, "https://i.postimg.cc/FzRBdkRC/Pastel-de-Pollo.png");
INSERT INTO tblproducto(codigo,nombre,descripcion,precio_venta,stock,foto) VALUES ("P06","Torta", "Torta Sabor a chocolate", 3999, 40, "https://i.postimg.cc/pVG7NCGw/Torta.png");
INSERT INTO tblproducto(codigo,nombre,descripcion,precio_venta,stock,foto) VALUES ("P07","Pandebono", "Pan de queso hecho con almidón de yuca y queso", 3500, 20, "https://i.postimg.cc/sDr86hHy/pandebono.jpg");
INSERT INTO tblproducto(codigo,nombre,descripcion,precio_venta,stock,foto) VALUES ("P08","Almojábana", "Similar al pandebono pero hecho con maíz", 2500, 30, "https://i.postimg.cc/9MPtgJfj/almojabana.jpg");
INSERT INTO tblproducto(codigo,nombre,descripcion,precio_venta,stock,foto) VALUES ("P09","Empanadas", "Pasteles rellenos de carne, pollo o papa", 4000, 120, "https://i.postimg.cc/FsV62HwS/empanadas.jpg");
INSERT INTO tblproducto(codigo,nombre,descripcion,precio_venta,stock,foto) VALUES ("P10","Roscas", "Panes dulces en forma de anillo, con azúcar y canela", 5000, 40, "https://i.postimg.cc/Y9zk2GRw/bizcocho.jpg");
INSERT INTO tblproducto(codigo,nombre,descripcion,precio_venta,stock,foto) VALUES ("P11","Pan francés", "Pan blanco clásico", 2000, 100, "https://i.postimg.cc/6QQ53s1n/Pan-Frances.jpg");
INSERT INTO tblproducto(codigo,nombre,descripcion,precio_venta,stock,foto) VALUES ("P12","Cafe 100% Colombiano", "Disfruta de una taza de cafe", 1200, 40, "https://i.postimg.cc/JhPJVnbC/cafe.png");
INSERT INTO tblproducto(codigo,nombre,descripcion,precio_venta,stock,foto) VALUES ("P13","Galletas de avena", "Galletas de avena y miel", 2000, 15, "https://i.postimg.cc/jqNm4XXZ/galletas-avena.jpg");
INSERT INTO tblproducto(codigo,nombre,descripcion,precio_venta,stock,foto) VALUES ("P14","Galletas de chocolat", "Galletas de chocolate", 2000, 10, "https://i.postimg.cc/CMb3CgHR/galletas-chocolate.jpg");
INSERT INTO tblproducto(codigo,nombre,descripcion,precio_venta,stock,foto) VALUES ("P15","Bizcochos", "Torta dulce tradicional de limon", 3500, 20, "https://i.postimg.cc/4d3Nj3fN/roscas.jpg");
INSERT INTO tblproducto(codigo,nombre,descripcion,precio_venta,stock,foto) VALUES ("P16","Mantecadas", "Magdalenas con sabor a mantequilla", 2000, 50, "https://i.postimg.cc/fRK5jYKP/mantecadas.png");
INSERT INTO tblproducto(codigo,nombre,descripcion,precio_venta,stock,foto) VALUES ("P17","Rollos de canela", "Rollos de canela dulces y pegajosos", 3500, 20, "https://i.postimg.cc/Y25Xpr0d/Rollos-de-canela.jpg");
INSERT INTO tblproducto(codigo,nombre,descripcion,precio_venta,stock,foto) VALUES ("P18","Donas", "Donas con glaseado", 2000, 40, "https://i.postimg.cc/vZcD3sLd/donas.jpg");
INSERT INTO tblproducto(codigo,nombre,descripcion,precio_venta,stock,foto) VALUES ("P19","Hojaldres", "Panes de hojaldre crujientes, dulces o salados", 2000, 40, "https://i.postimg.cc/jqwg0wxW/Hojaldres.jpg");
INSERT INTO tblproducto(codigo,nombre,descripcion,precio_venta,stock,foto) VALUES ("P20","Torta negra", " torta densa y rica en frutas confitadas y nueces", 10000, 10, "https://i.postimg.cc/GpmKM9gm/torta-negra.jpg");

