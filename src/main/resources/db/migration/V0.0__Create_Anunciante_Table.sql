CREATE TABLE IF NOT EXISTS `hikanban`.`anunciante` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`nome` VARCHAR(255) NOT NULL,
	`telefone`VARCHAR(255) NOT NULL,
	`email`VARCHAR(100) NOT NULL,
	UNIQUE KEY `email_UNIQUE` (`email`),
	PRIMARY KEY(`id`))
 ENGINE=InnoDB;