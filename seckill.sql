/*
 Navicat Premium Data Transfer

 Source Server         : 阿里云
 Source Server Type    : MySQL
 Source Server Version : 80037
 Source Host           : 8.130.54.36:3306
 Source Schema         : seckill

 Target Server Type    : MySQL
 Target Server Version : 80037
 File Encoding         : 65001

 Date: 31/07/2024 23:02:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_goods
-- ----------------------------
DROP TABLE IF EXISTS `t_goods`;
CREATE TABLE `t_goods`  (
  `goods_id` int NOT NULL AUTO_INCREMENT COMMENT '商品id，自增主键',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品标题',
  `introduce` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品详情',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品价格',
  `count` int NULL DEFAULT NULL COMMENT '库存数量',
  `random_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '随机码',
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片地址',
  `start_time` datetime NULL DEFAULT NULL COMMENT '秒杀开始时间',
  `end_time` datetime NULL DEFAULT NULL COMMENT '秒杀结束时间',
  PRIMARY KEY (`goods_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_goods
-- ----------------------------
INSERT INTO `t_goods` VALUES (1, 'iPhone X', '苹果全面屏手机 深空灰色 移动联通电信 256GB', 8388.00, 1000, '49020387-0a86-4f3f-9e13-463688ad4ef1', 'http://localhost:8080/image/01.jpg', '2022-09-01 09:30:01', '2022-09-30 23:59:59');
INSERT INTO `t_goods` VALUES (2, '小米6', '全网通 4GB+64GB 亮黑色 移动联通电信4G手机 双卡双待', 2099.00, 1000, '0c7dab04-0743-4170-b2cf-144e8653b58e', 'http://localhost:8080/image/02.jpg', '2024-06-01 09:30:01', '2024-06-06 23:59:59');
INSERT INTO `t_goods` VALUES (3, 'OPPO R11s', '双卡双待全面屏拍照手机 香槟色 全网通(4G RAM+64G ROM)官方标配', 2999.00, 0, '8fcdccf4-1cf9-480b-b0aa-ce53fb4e206b', 'http://localhost:8080/image/03.jpg', '2023-12-10 09:30:01', '2024-06-30 23:59:59');
INSERT INTO `t_goods` VALUES (4, '华为 nova2S', '全面屏四摄 6GB +64GB 曜石黑 移动联通电信4G手机 双卡双待', 3099.00, 1000, '3908fbbc-c15f-4d4e-ad68-b8e9e3643342', 'http://localhost:8080/image/04.jpg', '2022-09-01 09:30:01', '2022-11-30 23:59:59');
INSERT INTO `t_goods` VALUES (5, 'vivo X20Plus', '全面屏双摄拍照手机 4GB+64GB 玫瑰金 玫瑰金 4GB+ 64GB', 2099.00, 1000, 'ed859065-7b0b-4a85-8827-36c588c1f899', 'http://localhost:8080/image/05.jpg', '2022-09-01 09:30:01', '2022-09-30 23:59:59');
INSERT INTO `t_goods` VALUES (6, '华硕（ASUS）', 'F556UQ独显GT940游戏本15.6英寸顽石战斗版手提商务笔记本电脑', 5099.00, 1000, 'd5dfb679-f433-4ae1-9b2e-e2332304660d', 'http://localhost:8080/image/06.jpg', '2024-05-10 09:30:01', '2024-05-30 23:59:59');
INSERT INTO `t_goods` VALUES (7, 'ThinkPad S1', '翻转轻薄碳纤维手写本（i5-8250U 8G 256GSSD 背光键盘 FHD）', 9499.00, 1000, 'ab17ba3d-01ca-4114-9c64-836a34014ef0', 'http://localhost:8080/image/07.jpg', '2022-12-01 16:30:01', '2022-12-08 23:59:59');

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`  (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `goods_id` int NULL DEFAULT NULL,
  `user_id` int NULL DEFAULT NULL,
  `goods_price` decimal(10, 2) NULL DEFAULT NULL,
  `goods_count` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `total_price` decimal(10, 2) NULL DEFAULT NULL,
  `status` int NULL DEFAULT NULL,
  `creat_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_order
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
