create database medicare;
use medicare;

create table acceso (
	usuario varchar(50),
	contraseña varchar(50),
    perfil varchar(20)
);

insert into acceso
values ('jonathan','jonathan123','Administrador'),
('jair','jair123','Medico'),
('jorgue','jorgue123','Medico'),
('jadira','jadira123','Administrador');

select * from acceso;



create table medicos(
	cedula decimal(10,0) primary key,
    nombre varchar(100) not null,
    apellido varchar(100) not null,
    especialidad varchar(100) not null,
    telefono varchar(10) ,
    email varchar(100)
);

create table pacientes(
	n_historial_clinico  int auto_increment,
	cedula decimal(10,0) primary key,
    nombre varchar(100) not null,
    apellido varchar(100)not null,
    fecha_nacimiento date not null,
    sexo varchar(50) not null,
    telefono varchar(10),
    email varchar(100),
    unique(n_historial_clinico)
);

create table citas (
    id_cita int auto_increment primary key,
    cedula_paciente decimal(10,0),
    fecha_cita date,
    hora_cita time,
    especialidad varchar(100),
    nombre_medico varchar(100),
    motivo_cita text,
    estado_cita varchar(20) default 'Pendiente',
    foreign key (cedula_paciente) references pacientes(cedula)
);

create table consultas_medicas (
    id_historial int auto_increment primary key,
    cedula_paciente decimal(10,0),
    fecha_consulta date,
    motivo_consulta text,
    diagnostico text,
    tratamiento text,
    medicamentos text,
    tipo_examen varchar(100),
    resultados text,
    notas_medico text,
    foreign key (cedula_paciente) references pacientes(cedula)
);




select * from medicos;
select * from pacientes;

select * from citas;
select * from consultas_medicas;