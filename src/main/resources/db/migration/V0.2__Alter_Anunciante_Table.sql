ALTER TABLE `hikanban`.`anunciante` 
ADD COLUMN `senha` VARCHAR(255) NOT NULL AFTER `email`,
ADD COLUMN `user` VARCHAR(45) NULL AFTER `senha`;
