SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `marsdb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `marsdb` ;

-- -----------------------------------------------------
-- Table `marsdb`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `marsdb`.`User` ;

CREATE  TABLE IF NOT EXISTS `marsdb`.`User` (
  `user_id` INT NOT NULL AUTO_INCREMENT ,
  `email` VARCHAR(100) NOT NULL ,
  `password` VARCHAR(100) NOT NULL ,
  `nickname` VARCHAR(45) NOT NULL ,
  `gender` TINYINT(1) NOT NULL ,
  `image` VARCHAR(100) NULL ,
  `priority` INT NULL DEFAULT 0 ,
  `time` TIMESTAMP NULL DEFAULT now() ,
  `followerCount` INT NOT NULL DEFAULT 0 ,
  `followingCount` INT NOT NULL DEFAULT 0 ,
  `postCount` INT NOT NULL DEFAULT 0 ,
  `confirm` TINYINT(1) NOT NULL DEFAULT false ,
  PRIMARY KEY (`user_id`) ,
  UNIQUE INDEX `id_UNIQUE` (`user_id` ASC) ,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) ,
  UNIQUE INDEX `nickname_UNIQUE` (`nickname` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `marsdb`.`Message`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `marsdb`.`Message` ;

CREATE  TABLE IF NOT EXISTS `marsdb`.`Message` (
  `message_id` INT NOT NULL AUTO_INCREMENT ,
  `user_id` INT NULL ,
  `text` VARCHAR(300) NOT NULL ,
  `image` VARCHAR(100) NULL ,
  `time` TIMESTAMP NULL DEFAULT NOW() ,
  `position` VARCHAR(100) NULL ,
  `source_id` INT NOT NULL DEFAULT -1 ,
  `commentCount` INT NULL DEFAULT 0 ,
  `forwardCount` INT NULL DEFAULT 0 ,
  PRIMARY KEY (`message_id`) ,
  UNIQUE INDEX `id_UNIQUE` (`message_id` ASC) ,
  INDEX `m_userid` (`user_id` ASC) ,
  CONSTRAINT `m_userid`
    FOREIGN KEY (`user_id` )
    REFERENCES `marsdb`.`User` (`user_id` )
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `marsdb`.`Comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `marsdb`.`Comment` ;

CREATE  TABLE IF NOT EXISTS `marsdb`.`Comment` (
  `comment_id` INT NOT NULL AUTO_INCREMENT ,
  `message_id` INT NULL ,
  `user_id` INT NULL ,
  `text` VARCHAR(300) NOT NULL ,
  `time` TIMESTAMP NOT NULL DEFAULT NOW() ,
  PRIMARY KEY (`comment_id`) ,
  UNIQUE INDEX `id_UNIQUE` (`comment_id` ASC) ,
  INDEX `c_userid` (`user_id` ASC) ,
  INDEX `c_messageid` (`message_id` ASC) ,
  CONSTRAINT `c_userid`
    FOREIGN KEY (`user_id` )
    REFERENCES `marsdb`.`User` (`user_id` )
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `c_messageid`
    FOREIGN KEY (`message_id` )
    REFERENCES `marsdb`.`Message` (`message_id` )
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `marsdb`.`Follow`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `marsdb`.`Follow` ;

CREATE  TABLE IF NOT EXISTS `marsdb`.`Follow` (
  `follow_id` INT NOT NULL AUTO_INCREMENT ,
  `following_id` INT NULL ,
  `followed_id` INT NULL ,
  PRIMARY KEY (`follow_id`) ,
  UNIQUE INDEX `id_UNIQUE` (`follow_id` ASC) ,
  INDEX `f_followingid` (`following_id` ASC) ,
  INDEX `f_followedid` (`followed_id` ASC) ,
  CONSTRAINT `f_followingid`
    FOREIGN KEY (`following_id` )
    REFERENCES `marsdb`.`User` (`user_id` )
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `f_followedid`
    FOREIGN KEY (`followed_id` )
    REFERENCES `marsdb`.`User` (`user_id` )
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `marsdb`.`Favorite`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `marsdb`.`Favorite` ;

CREATE  TABLE IF NOT EXISTS `marsdb`.`Favorite` (
  `favorite_id` INT NOT NULL AUTO_INCREMENT ,
  `user_id` INT NULL ,
  `message_id` INT NULL ,
  `time` TIMESTAMP NOT NULL DEFAULT NOW() ,
  PRIMARY KEY (`favorite_id`) ,
  UNIQUE INDEX `favorite_id_UNIQUE` (`favorite_id` ASC) ,
  INDEX `f_user_id` (`user_id` ASC) ,
  INDEX `f_message_id` (`message_id` ASC) ,
  CONSTRAINT `f_user_id`
    FOREIGN KEY (`user_id` )
    REFERENCES `marsdb`.`User` (`user_id` )
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `f_message_id`
    FOREIGN KEY (`message_id` )
    REFERENCES `marsdb`.`Message` (`message_id` )
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `marsdb`.`Topic`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `marsdb`.`Topic` ;

CREATE  TABLE IF NOT EXISTS `marsdb`.`Topic` (
  `topic_id` INT NOT NULL AUTO_INCREMENT ,
  `title` VARCHAR(100) NOT NULL ,
  `content` VARCHAR(1000) NULL ,
  `count` INT NOT NULL DEFAULT 1 ,
  PRIMARY KEY (`topic_id`) ,
  UNIQUE INDEX `title_UNIQUE` (`title` ASC) ,
  UNIQUE INDEX `topic_id_UNIQUE` (`topic_id` ASC) )
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
