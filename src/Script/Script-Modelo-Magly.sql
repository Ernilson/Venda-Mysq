-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema magaly
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema magaly
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `magaly` DEFAULT CHARACTER SET utf8 ;
USE `magaly` ;

-- -----------------------------------------------------
-- Table `magaly`.`acesso`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `magaly`.`acesso` (
  `id_Ac` INT(11) NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(60) NOT NULL,
  `senha` VARCHAR(60) NOT NULL,
  `perfil` VARCHAR(60) NULL DEFAULT NULL,
  PRIMARY KEY (`id_Ac`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `magaly`.`carrinho`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `magaly`.`carrinho` (
  `id_v` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NULL DEFAULT NULL,
  `descricao` VARCHAR(150) NULL DEFAULT NULL,
  `qtdp` VARCHAR(80) NULL DEFAULT NULL,
  `valor_item` VARCHAR(80) NULL DEFAULT NULL,
  `valor_sub_total` VARCHAR(80) NULL DEFAULT NULL,
  `valor_total` VARCHAR(80) NULL DEFAULT NULL,
  `forma_pg` VARCHAR(20) NULL DEFAULT NULL,
  `dataq` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_v`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `magaly`.`clientes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `magaly`.`clientes` (
  `id` INT(11) NOT NULL,
  `nome` VARCHAR(200) NOT NULL,
  `ender` VARCHAR(200) NOT NULL,
  `tel` VARCHAR(200) NOT NULL,
  `data_nasc` VARCHAR(200) NOT NULL,
  `estatus` VARCHAR(200) NOT NULL,
  `genero` VARCHAR(200) NOT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `magaly`.`venda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `magaly`.`venda` (
  `id_v` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NULL DEFAULT NULL,
  `descricao` VARCHAR(150) NULL DEFAULT NULL,
  `qtdp` VARCHAR(80) NULL DEFAULT NULL,
  `valor_item` VARCHAR(80) NULL DEFAULT NULL,
  `valor_sub_total` VARCHAR(80) NULL DEFAULT NULL,
  `valor_total` VARCHAR(80) NULL DEFAULT NULL,
  `forma_pg` VARCHAR(20) NULL DEFAULT NULL,
  `dataq` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_v`))
ENGINE = InnoDB
AUTO_INCREMENT = 14
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `magaly`.`produtos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `magaly`.`produtos` (
  `id_pro` INT(11) NOT NULL AUTO_INCREMENT,
  `descricao_p` VARCHAR(200) NOT NULL,
  `qtd` VARCHAR(20) NULL DEFAULT NULL,
  `preco` VARCHAR(20) NULL DEFAULT NULL,
  `dataq` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `id_v` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id_pro`),
  INDEX `fk_venda` (`id_v` ASC),
  CONSTRAINT `fk_venda`
    FOREIGN KEY (`id_v`)
    REFERENCES `magaly`.`venda` (`id_v`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
