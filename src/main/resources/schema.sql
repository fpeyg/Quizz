DROP TABLE IF EXISTS `resultat_user`;
DROP TABLE IF EXISTS `answer_user`;
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `choice`;
DROP TABLE IF EXISTS `question`;


CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `hashed_password` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `access_level` int(11) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*DROP TABLE IF EXISTS `resultat_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resultat_user` (
  `user_id` int(11) NOT NULL,
  `scrore` int(11) NOT NULL,
  `session_user` int(11) NOT NULL,
  FOREIGN KEY (user_id) REFERENCES user(user_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question` (
  `question_id` int(11) NOT NULL,
  `point` int(11) DEFAULT NULL,
  `wording` TEXT DEFAULT NULL,
   PRIMARY KEY (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


/*DROP TABLE IF EXISTS `choice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `choice` (
  `choice_id` int(11) NOT NULL,
  `question_id` int(11) NOT NULL,
  `scale` int(3) DEFAULT NULL,
  `anwser` TEXT DEFAULT NULL,
  `resultat` BOOLEAN,
  `clue` TEXT DEFAULT NULL,
   PRIMARY KEY (`choice_id`),
   FOREIGN KEY (question_id) REFERENCES question(question_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*DROP TABLE IF EXISTS `answer_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `answer_user` (
  `user_id` int(11) NOT NULL,
  `question_id` int(11) NOT NULL,
  `choice_id` int(11) NOT NULL,
  `answer_question` TEXT DEFAULT NULL,
  FOREIGN KEY (user_id) REFERENCES user(user_id),
  FOREIGN KEY (question_id) REFERENCES question(question_id),
  FOREIGN KEY (choice_id) REFERENCES choice(choice_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;