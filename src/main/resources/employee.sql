

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('1', 'meimei1', '1234567891');
INSERT INTO `employee` VALUES ('2', 'meimei2', '1234567892');
INSERT INTO `employee` VALUES ('3', 'meimei3', '1234567893');
INSERT INTO `employee` VALUES ('4', 'meimei4', '1234567894');
INSERT INTO `employee` VALUES ('5', 'meimei5', '1234567895');
