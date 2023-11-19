CREATE TABLE tb_person (
    id tinyint NOT NULL AUTO_INCREMENT ,
    register varchar(150) NOT NULL,
    age int NOT NULL,
    weight decimal(10,2) NOT NULL,
    w_rank varchar(11) NOT NULL,
    image varchar (255) NOT NULL,
    PRIMARY KEY (id)
);