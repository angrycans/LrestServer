/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.13.111
 Source Server Type    : MySQL
 Source Server Version : 50550
 Source Host           : 192.168.13.111
 Source Database       : testdb

 Target Server Type    : MySQL
 Target Server Version : 50550
 File Encoding         : utf-8

 Date: 08/16/2016 15:19:36 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `tcar_brands`
-- ----------------------------
DROP TABLE IF EXISTS `tcar_brands`;
CREATE TABLE `tcar_brands` (
  `id` int(11) NOT NULL,
  `name` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
--  Records of `tcar_brands`
-- ----------------------------
BEGIN;
INSERT INTO `tcar_brands` VALUES ('0', 'benz'), ('1', 'bmw');
COMMIT;

-- ----------------------------
--  Table structure for `tcar_models`
-- ----------------------------
DROP TABLE IF EXISTS `tcar_models`;
CREATE TABLE `tcar_models` (
  `id` int(11) NOT NULL,
  `name` varchar(64) DEFAULT NULL,
  `brand_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `tcar_models`
-- ----------------------------
BEGIN;
INSERT INTO `tcar_models` VALUES ('1', '宝马i3', '1'), ('2', '宝马x3', '1'), ('3', '宝马x5', '1'), ('4', '奔驰E级', '0'), ('5', '奔驰GLC', '0');
COMMIT;

-- ----------------------------
--  Table structure for `tcar_types`
-- ----------------------------
DROP TABLE IF EXISTS `tcar_types`;
CREATE TABLE `tcar_types` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `car_brand_id` int(11) DEFAULT NULL,
  `car_mode_id` int(11) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `tcar_types`
-- ----------------------------
BEGIN;
INSERT INTO `tcar_types` VALUES ('1', '1', '1', '2015款 时尚型', '416800'), ('2', '1', '1', '2014款 豪华型', '449800'), ('3', '1', '1', '2014款 增程型', '516800'), ('4', '1', '2', '2016款 sDrive20i', '421000'), ('5', '1', '2', '2014款 xDrive28i 领先型', '489200'), ('6', '1', '3', '2015款 xDrive28i', '758000'), ('7', '1', '3', '2014款 xDrive30d', '763200'), ('8', '0', '4', '2015款 改款 E 180 L', '398000'), ('9', '0', '4', '2015款 改款 E 200 L', '429000'), ('10', '0', '5', '2016款 GLC 200 4MATIC', '396000'), ('11', '0', '5', '2016款 GLC 260 4MATIC 动感型', '428000'), ('12', '0', '5', '2016款 GLC 300 4MATIC 动感型', '486000');
COMMIT;

-- ----------------------------
--  Table structure for `tusers`
-- ----------------------------
DROP TABLE IF EXISTS `tusers`;
CREATE TABLE `tusers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `passwd` varchar(64) DEFAULT NULL,
  `token` varchar(32) DEFAULT NULL,
  `create_at` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `tusers`
-- ----------------------------
BEGIN;
INSERT INTO `tusers` VALUES ('1', 'test', 'passwd', null, null);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
