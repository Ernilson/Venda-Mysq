
create database Magaly
default character set utf8
default collate utf8_general_ci;

use Magaly;

select * from clientes;

CREATE TABLE `clientes` (
  `id` int(11) NOT NULL,
  `nome` varchar(200) NOT NULL,
  `ender` varchar(200) NOT NULL,
  `tel` varchar(200) NOT NULL,
  `data_nasc` varchar(200) NOT NULL,
  `estatus` varchar(200) NOT NULL,
  `genero` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET= utf8;

ALTER TABLE `cadastro` ADD `column` VARCHAR(100) NOT NULL AFTER `id_venda`; -- comando para altera uma coluna

create table acesso(
id_Ac int auto_increment,
login varchar(60) not null,
senha varchar(60) not null,
perfil varchar(60) null,
id_cad int,
constraint pk_acesso primary key (id_Ac),
constraint fk_cadastro2 foreign key (id_cad)
references cadastro (id_cad)
)default charset = utf8;

select * from acesso;

create table venda(
id_v serial,
nome varchar(100)not null,
descricao varchar(150) not null,
qtdp varchar(80),
valor_item varchar(80),
valor_sub_total varchar(80),
valor_total varchar(80), -- Valor Total
forma_pg varchar(20),
dataq timestamp default current_timestamp,
    primary key (id_v)
);

create table carrinho(
id_c serial,
nome varchar(100)not null,
descricao varchar(150) not null,
qtdp varchar(80),
valor_item varchar(80),
valor_sub_total varchar(80),
valor_total varchar(80), -- Valor Total
forma_pg varchar(20),
dataq timestamp default current_timestamp,
    primary key (id_c)
);

create table teste(
id_t serial,
    forma_pg varchar(80),
    venda int,
    carrinho int,
    primary key (id_t),
    foreign key(venda) references venda(id_v),
    foreign key(carrinho) references carrinho(id_c));



create table produtos(
id_pro int auto_increment,
descricao_p varchar(200) not null,
qtd varchar(20),
preco varchar(20),  -- por unidade (valor item)
dataq timestamp default current_timestamp,
id_v int,
constraint pk_produtos primary key (id_pro),
constraint fk_venda foreign key (id_v)
references venda (id_v)
)default charset = utf8;

use magaly;

insert into produtos(descricao, qtd, preco) values ('cachorro quente','1', '6.00');

UPDATE `acesso` SET `login` = 'Francisca', `senha` = 'adriano', perfil='Usuario' WHERE `acesso`.`id_Ac` = 2;
drop table acesso;
drop table produtos;
insert into acesso(login, senha, perfil) values ('Francisca','adriano','Usuario');
insert into acesso(login, senha, perfil) values ('Josias','64673820134','Pastor Josias');
insert into acesso(login, senha, perfil) values ('admin','musica','Adminstrador');

ALTER TABLE `venda` ADD `descricao` VARCHAR(100) NOT NULL AFTER `id_venda`; -- comando para altera uma coluna

select Cad.nome,ender,estatus,
Fam.id_fam,profissao,conge,filhos -- Tabela Cadastro com Tabela Familia;
from familia as Fam
inner join cadastro as Cad
on (Cad.id_cad = Fam.id_cad);
-------------------------------------------------------------------------------------------------------
select Cad.nome,estatus,
Vend.id_v, descricao, valor -- Tabela Cadastro com Tabela Venda;
from venda as Vend
inner join cadastro as Cad
on (Cad.id_cad = Vend.id_cad);
--------------------------------------------------------------------------------------------------------
select Vend.descricao,valor,nome, Prod.descricao,qtd,preco from venda as Vend inner join produtos as Prod on (Prod.id_pro = Vend.id_v);
select Vend.nome,valor, Prod.descricao_p,qtd,preco from venda as Vend inner join produtos as Prod on (Prod.id_pro = Vend.id_v);
select Vend.nome,qtd, valor, Prod.descricao_p,preco from venda as Vend inner join produtos as Prod on (Prod.id_pro = Vend.id_v);



select Vend.descricao,valor,nome, Prod.descricao,qtd,preco from venda as Vend inner join produtos as Prod on (Prod.id_pro = Vend.id_v) where descricao like "%"g%"";