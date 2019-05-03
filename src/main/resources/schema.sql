CREATE SCHEMA `openvantagetask` ;

CREATE TABLE `openvantagetask`.`tasks` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NULL,
  `category` VARCHAR(255) NULL,
  `description` VARCHAR(255) NULL,
  `duedate` DATETIME NULL,
  `status` VARCHAR(255) NULL,
  PRIMARY KEY (`id`));


