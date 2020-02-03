# Quizz

/* MySQL 8 */
SET GLOBAL validate_password.length = 4;
SET GLOBAL validate_password.number_count = 0;
SET GLOBAL validate_password.policy = LOW;

CREATE DATABASE quizz;

CREATE USER 'grosminet'@'localhost' IDENTIFIED BY 'titi';
GRANT ALL PRIVILEGES ON quizz.* TO 'grosminet'@'localhost';
