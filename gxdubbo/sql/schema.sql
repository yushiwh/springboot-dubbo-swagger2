CREATE TABLE `user` (
	`id` INT(10) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(50) NULL DEFAULT NULL,
	`age` INT(11) NULL DEFAULT NULL,
	`sex` INT(10) NULL DEFAULT NULL,
	`address` VARCHAR(100) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
)
COMMENT='测试用户表'
COLLATE='utf8mb4_general_ci'
ENGINE=InnoDB
ROW_FORMAT=DEFAULT
AUTO_INCREMENT=1