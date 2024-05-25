/*
 Navicat Premium Data Transfer

 Source Server         : liunx-syl
 Source Server Type    : MySQL
 Source Server Version : 80037
 Source Host           : 59.110.139.253:3306
 Source Schema         : doordash

 Target Server Type    : MySQL
 Target Server Version : 80037
 File Encoding         : 65001

 Date: 25/05/2024 23:18:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address_book
-- ----------------------------
DROP TABLE IF EXISTS `address_book`;
CREATE TABLE `address_book`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `consignee` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '收货人',
  `sex` varchar(2) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '性别',
  `phone` varchar(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '手机号',
  `province_code` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '省级区划编号',
  `province_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '省级名称',
  `city_code` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '市级区划编号',
  `city_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '市级名称',
  `district_code` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '区级区划编号',
  `district_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '区级名称',
  `detail` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '详细地址',
  `label` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标签',
  `is_default` tinyint(1) NOT NULL DEFAULT 0 COMMENT '默认 0 否 1是',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = '地址簿' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of address_book
-- ----------------------------
INSERT INTO `address_book` VALUES (1, 1, '桂定宝', '0', '13458466578', '51', '四川省', '5101', '成都市', '510116', '双流区', '西南民族大学28-2-6-3', '3', 1);

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` int NULL DEFAULT NULL COMMENT '类型   1 菜品分类 2 套餐分类',
  `name` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '分类名称',
  `sort` int NOT NULL DEFAULT 0 COMMENT '顺序',
  `status` int NULL DEFAULT NULL COMMENT '分类状态 0:禁用，1:启用',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `create_user` bigint NULL DEFAULT NULL COMMENT '创建人',
  `update_user` bigint NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_category_name`(`name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = '菜品及套餐分类' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (11, 1, '酒水饮料', 10, 1, '2022-06-09 22:09:18', '2022-06-09 22:09:18', 1, 1);
INSERT INTO `category` VALUES (12, 1, '传统主食', 9, 1, '2022-06-09 22:09:32', '2022-06-09 22:18:53', 1, 1);
INSERT INTO `category` VALUES (13, 2, '人气套餐', 3, 1, '2022-06-09 22:11:38', '2024-05-09 22:34:25', 1, 1);
INSERT INTO `category` VALUES (15, 2, '商务套餐', 4, 1, '2022-06-09 22:14:10', '2024-05-09 22:35:12', 1, 1);
INSERT INTO `category` VALUES (16, 1, '蜀味烤鱼', 12, 1, '2022-06-09 22:15:37', '2024-05-09 22:35:17', 1, 1);
INSERT INTO `category` VALUES (17, 1, '蜀味牛蛙', 13, 1, '2022-06-09 22:16:14', '2024-05-09 22:52:57', 1, 1);
INSERT INTO `category` VALUES (18, 1, '特色蒸菜', 15, 1, '2022-06-09 22:17:42', '2024-05-16 15:06:34', 1, 1);
INSERT INTO `category` VALUES (19, 1, '新鲜时蔬', 7, 1, '2022-06-09 22:18:12', '2022-06-09 22:18:28', 1, 1);
INSERT INTO `category` VALUES (20, 1, '水煮鱼', 8, 1, '2022-06-09 22:22:29', '2022-06-09 22:23:45', 1, 1);
INSERT INTO `category` VALUES (21, 1, '汤类', 11, 1, '2022-06-10 10:51:47', '2022-06-10 10:51:47', 1, 1);
INSERT INTO `category` VALUES (23, 2, '情侣套餐', 2, 1, '2024-05-09 21:35:33', '2024-05-09 22:44:21', 1, 1);
INSERT INTO `category` VALUES (24, 2, '家庭套餐', 1, 1, '2024-05-09 21:35:56', '2024-05-09 22:56:43', 1, 1);
INSERT INTO `category` VALUES (26, 2, '单人套餐', 5, 1, '2024-05-09 22:52:43', '2024-05-09 22:52:48', 1, 1);
INSERT INTO `category` VALUES (27, 1, '特殊小吃', 14, 1, '2024-05-09 22:57:03', '2024-05-17 13:32:01', 1, 1);
INSERT INTO `category` VALUES (29, 2, '双人套餐', 6, 1, '2024-05-16 15:06:16', '2024-05-16 15:06:50', 1, 1);

-- ----------------------------
-- Table structure for dish
-- ----------------------------
DROP TABLE IF EXISTS `dish`;
CREATE TABLE `dish`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '菜品名称',
  `category_id` bigint NOT NULL COMMENT '菜品分类id',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '菜品价格',
  `image` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '图片',
  `description` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '描述信息',
  `status` int NULL DEFAULT 1 COMMENT '0 停售 1 起售',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `create_user` bigint NULL DEFAULT NULL COMMENT '创建人',
  `update_user` bigint NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_dish_name`(`name` ASC) USING BTREE,
  INDEX `category_id`(`category_id` ASC) USING BTREE,
  CONSTRAINT `dish_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 81 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = '菜品' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of dish
-- ----------------------------
INSERT INTO `dish` VALUES (46, '王老吉', 11, 6.00, 'https://doordash.oss-cn-beijing.aliyuncs.com/9f363f6d-d0d4-4fa0-b28e-e60354c3d4c2.png', '', 1, '2022-06-09 22:40:47', '2024-05-17 13:22:46', 1, 1);
INSERT INTO `dish` VALUES (47, '北冰洋', 11, 4.00, 'https://doordash.oss-cn-beijing.aliyuncs.com/6c0a4f42-694d-4998-9191-14c57ba91510.png', '还是小时候的味道', 1, '2022-06-10 09:18:49', '2024-05-17 13:22:39', 1, 1);
INSERT INTO `dish` VALUES (48, '雪花啤酒', 11, 4.00, 'https://doordash.oss-cn-beijing.aliyuncs.com/2e37d5e7-3287-4d36-8a7e-109ba1f3cafe.png', '', 1, '2022-06-10 09:22:54', '2024-05-17 13:22:31', 1, 1);
INSERT INTO `dish` VALUES (49, '米饭', 12, 2.00, 'https://doordash.oss-cn-beijing.aliyuncs.com/8888294a-a520-4c98-bc58-867b9e9fafa4.png', '精选五常大米', 1, '2022-06-10 09:30:17', '2024-05-17 13:22:22', 1, 1);
INSERT INTO `dish` VALUES (50, '馒头', 12, 1.00, 'https://doordash.oss-cn-beijing.aliyuncs.com/5fb07262-df26-43f7-8826-ae668fe3e176.png', '优质面粉', 1, '2022-06-10 09:34:28', '2024-05-17 13:22:15', 1, 1);
INSERT INTO `dish` VALUES (51, '老坛酸菜鱼', 20, 56.00, 'https://doordash.oss-cn-beijing.aliyuncs.com/65b68be7-7cd2-44d4-9db2-7181bbc81f6b.png', '原料：汤，草鱼，酸菜', 1, '2022-06-10 09:40:51', '2024-05-17 13:22:07', 1, 1);
INSERT INTO `dish` VALUES (52, '经典酸菜鮰鱼', 20, 66.00, 'https://doordash.oss-cn-beijing.aliyuncs.com/2133c9c4-25cd-4c1d-92b4-d992af421adf.png', '原料：酸菜，江团，鮰鱼', 1, '2022-06-10 09:46:02', '2024-05-17 13:21:50', 1, 1);
INSERT INTO `dish` VALUES (53, '蜀味水煮草鱼', 20, 38.00, 'https://doordash.oss-cn-beijing.aliyuncs.com/9b0b414d-5e61-4a16-8872-903577862282.png', '原料：草鱼，汤', 1, '2022-06-10 09:48:37', '2024-05-17 13:21:39', 1, 1);
INSERT INTO `dish` VALUES (54, '清炒小油菜', 19, 18.00, 'https://doordash.oss-cn-beijing.aliyuncs.com/de4b501d-0463-46b1-b22f-0aeff1e1d4f0.png', '原料：小油菜', 1, '2022-06-10 09:51:46', '2024-05-17 13:21:15', 1, 1);
INSERT INTO `dish` VALUES (55, '蒜蓉娃娃菜', 19, 18.00, 'https://doordash.oss-cn-beijing.aliyuncs.com/c568f962-203c-4eda-ba04-85953c6d8d63.png', '原料：蒜，娃娃菜', 1, '2022-06-10 09:53:37', '2024-05-17 13:21:00', 1, 1);
INSERT INTO `dish` VALUES (56, '清炒西兰花', 19, 18.00, 'https://doordash.oss-cn-beijing.aliyuncs.com/bd61069f-bf4a-44a8-8b74-07a34d34c2dc.png', '原料：西兰花', 1, '2022-06-10 09:55:44', '2024-05-17 13:20:49', 1, 1);
INSERT INTO `dish` VALUES (57, '炝炒圆白菜', 19, 18.00, 'https://doordash.oss-cn-beijing.aliyuncs.com/39dfd64f-e772-446d-aa74-78a9ab29c6cf.png', '原料：圆白菜', 1, '2022-06-10 09:58:35', '2024-05-17 13:20:38', 1, 1);
INSERT INTO `dish` VALUES (58, '清蒸鲈鱼', 18, 98.00, 'https://doordash.oss-cn-beijing.aliyuncs.com/6cd841e0-bcde-400f-801e-e66593db90f9.png', '原料：鲈鱼', 1, '2022-06-10 10:12:28', '2024-05-17 13:20:22', 1, 1);
INSERT INTO `dish` VALUES (59, '东坡肘子', 18, 138.00, 'https://doordash.oss-cn-beijing.aliyuncs.com/eda74642-4135-4862-aa11-5db8201ec87d.png', '原料：猪肘棒', 1, '2022-06-10 10:24:03', '2024-05-17 13:19:53', 1, 1);
INSERT INTO `dish` VALUES (60, '梅菜扣肉', 18, 58.00, 'https://doordash.oss-cn-beijing.aliyuncs.com/997e6c26-48b7-48e6-90d3-7615c709ed2e.png', '原料：猪肉，梅菜', 1, '2022-06-10 10:26:03', '2024-05-17 13:19:43', 1, 1);
INSERT INTO `dish` VALUES (61, '剁椒鱼头', 18, 66.00, 'https://doordash.oss-cn-beijing.aliyuncs.com/b8864260-8c7c-4339-96f9-fd7df87889c8.png', '原料：鲢鱼，剁椒', 1, '2022-06-10 10:28:54', '2024-05-17 13:19:33', 1, 1);
INSERT INTO `dish` VALUES (62, '金汤酸菜牛蛙', 17, 88.00, 'https://doordash.oss-cn-beijing.aliyuncs.com/329d5738-9a61-4d5c-a743-8456d7571421.png', '原料：鲜活牛蛙，酸菜', 1, '2022-06-10 10:33:05', '2024-05-17 13:19:22', 1, 1);
INSERT INTO `dish` VALUES (63, '香锅牛蛙', 17, 88.00, 'https://doordash.oss-cn-beijing.aliyuncs.com/41b81a61-0a51-4b9d-8c47-d1821956c7d0.png', '配料：鲜活牛蛙，莲藕，青笋', 1, '2022-06-10 10:35:40', '2024-05-17 13:19:05', 1, 1);
INSERT INTO `dish` VALUES (64, '馋嘴牛蛙', 17, 88.00, 'https://doordash.oss-cn-beijing.aliyuncs.com/3d027e29-5ea5-45fa-8f53-ea9e7db0a29c.png', '配料：鲜活牛蛙，丝瓜，黄豆芽', 1, '2022-06-10 10:37:52', '2024-05-17 13:18:55', 1, 1);
INSERT INTO `dish` VALUES (65, '草鱼2斤', 16, 68.00, 'https://doordash.oss-cn-beijing.aliyuncs.com/6528032e-1034-4087-b316-91048c757cfe.png', '原料：草鱼，黄豆芽，莲藕', 1, '2022-06-10 10:41:08', '2024-05-17 13:18:43', 1, 1);
INSERT INTO `dish` VALUES (66, '江团鱼2斤', 16, 119.00, 'https://doordash.oss-cn-beijing.aliyuncs.com/7179971a-dbfe-40a3-86d9-14bc2387e3e6.png', '配料：江团鱼，黄豆芽，莲藕', 1, '2022-06-10 10:42:42', '2024-05-17 13:18:16', 1, 1);
INSERT INTO `dish` VALUES (67, '鮰鱼2斤', 16, 72.00, 'https://doordash.oss-cn-beijing.aliyuncs.com/498307a0-1afb-4c3b-93dc-dddd39d14d13.png', '原料：鮰鱼，黄豆芽，莲藕', 1, '2022-06-10 10:43:56', '2024-05-17 13:18:09', 1, 1);
INSERT INTO `dish` VALUES (68, '鸡蛋汤', 21, 10.00, 'https://doordash.oss-cn-beijing.aliyuncs.com/2133207c-23a9-4c2a-ac43-914cde890ed6.png', '配料：鸡蛋，紫菜', 1, '2022-06-10 10:54:25', '2024-05-23 22:38:34', 1, 1);
INSERT INTO `dish` VALUES (80, '平菇豆腐汤', 21, 9.00, 'https://doordash.oss-cn-beijing.aliyuncs.com/6c0f3bbb-68ce-4b5d-a0c9-b8952805bcdf.png', '好喝呢', 1, '2024-05-17 13:17:31', '2024-05-23 22:32:59', 1, 1);

-- ----------------------------
-- Table structure for dish_flavor
-- ----------------------------
DROP TABLE IF EXISTS `dish_flavor`;
CREATE TABLE `dish_flavor`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dish_id` bigint NULL DEFAULT NULL COMMENT '菜品',
  `name` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '口味名称',
  `value` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '口味数据list',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `dish_id`(`dish_id` ASC) USING BTREE,
  CONSTRAINT `dish_flavor_ibfk_1` FOREIGN KEY (`dish_id`) REFERENCES `dish` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 119 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = '菜品口味关系表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of dish_flavor
-- ----------------------------
INSERT INTO `dish_flavor` VALUES (40, 10, '甜味', '[\"无糖\",\"少糖\",\"半糖\",\"多糖\",\"全糖\"]');
INSERT INTO `dish_flavor` VALUES (41, 7, '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]');
INSERT INTO `dish_flavor` VALUES (42, 7, '温度', '[\"热饮\",\"常温\",\"去冰\",\"少冰\",\"多冰\"]');
INSERT INTO `dish_flavor` VALUES (45, 6, '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]');
INSERT INTO `dish_flavor` VALUES (46, 6, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]');
INSERT INTO `dish_flavor` VALUES (47, 5, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]');
INSERT INTO `dish_flavor` VALUES (48, 5, '甜味', '[\"无糖\",\"少糖\",\"半糖\",\"多糖\",\"全糖\"]');
INSERT INTO `dish_flavor` VALUES (49, 2, '甜味', '[\"无糖\",\"少糖\",\"半糖\",\"多糖\",\"全糖\"]');
INSERT INTO `dish_flavor` VALUES (50, 4, '甜味', '[\"无糖\",\"少糖\",\"半糖\",\"多糖\",\"全糖\"]');
INSERT INTO `dish_flavor` VALUES (51, 3, '甜味', '[\"无糖\",\"少糖\",\"半糖\",\"多糖\",\"全糖\"]');
INSERT INTO `dish_flavor` VALUES (52, 3, '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]');
INSERT INTO `dish_flavor` VALUES (105, 68, '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\"]');
INSERT INTO `dish_flavor` VALUES (106, 67, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]');
INSERT INTO `dish_flavor` VALUES (107, 66, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]');
INSERT INTO `dish_flavor` VALUES (108, 65, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]');
INSERT INTO `dish_flavor` VALUES (109, 60, '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]');
INSERT INTO `dish_flavor` VALUES (110, 57, '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]');
INSERT INTO `dish_flavor` VALUES (111, 56, '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]');
INSERT INTO `dish_flavor` VALUES (112, 54, '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\"]');
INSERT INTO `dish_flavor` VALUES (113, 53, '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]');
INSERT INTO `dish_flavor` VALUES (114, 53, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]');
INSERT INTO `dish_flavor` VALUES (115, 52, '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]');
INSERT INTO `dish_flavor` VALUES (116, 52, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]');
INSERT INTO `dish_flavor` VALUES (117, 51, '忌口', '[\"不要葱\",\"不要蒜\",\"不要香菜\",\"不要辣\"]');
INSERT INTO `dish_flavor` VALUES (118, 51, '辣度', '[\"不辣\",\"微辣\",\"中辣\",\"重辣\"]');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '姓名',
  `username` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '密码',
  `phone` varchar(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '手机号',
  `sex` varchar(2) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '性别',
  `id_number` varchar(18) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '身份证号',
  `status` int NOT NULL DEFAULT 1 COMMENT '状态 0:禁用，1:启用',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `create_user` bigint NULL DEFAULT NULL COMMENT '创建人',
  `update_user` bigint NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_username`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = '员工信息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (1, '管理员', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '13812312312', '1', '110101199001010047', 1, '2022-02-15 15:51:20', '2022-02-17 09:16:20', 10, 1);
INSERT INTO `employee` VALUES (2, 'zhangsan', '12323', 'e10adc3949ba59abbe56e057f20f883e', '12344545', '1', '54452454', 0, '2024-03-14 23:08:34', '2024-03-14 23:08:34', 10, 10);
INSERT INTO `employee` VALUES (3, '陈冠圭', '202131101014', 'e10adc3949ba59abbe56e057f20f883e', '15915921891', '1', '255895622311013589', 0, '2024-03-18 16:01:21', '2024-05-16 14:41:39', 1, 1);
INSERT INTO `employee` VALUES (4, '宋敖楠', '202031801092', 'e10adc3949ba59abbe56e057f20f883e', '15928673133', '0', '526965844955623256', 0, '2024-03-18 22:56:57', '2024-05-18 14:31:28', 1, 1);
INSERT INTO `employee` VALUES (5, '桂定宝', '202131101055', 'e10adc3949ba59abbe56e057f20f883e', '13458466578', '1', '522128211022563245', 1, '2024-03-29 14:34:29', '2024-03-29 14:34:29', 1, 1);
INSERT INTO `employee` VALUES (6, '图拉吉·如则', '202131101015', 'e10adc3949ba59abbe56e057f20f883e', '15925652365', '1', '511781200522043556', 1, '2024-05-09 21:08:47', '2024-05-09 21:08:47', 1, 1);
INSERT INTO `employee` VALUES (11, '张三', '202131101025', 'e10adc3949ba59abbe56e057f20f883e', '13458699589', '1', '122596566233126555', 1, '2024-05-16 15:03:47', '2024-05-16 15:05:19', 1, 1);

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '名字',
  `image` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '图片',
  `order_id` bigint NOT NULL COMMENT '订单id',
  `dish_id` bigint NULL DEFAULT NULL COMMENT '菜品id',
  `setmeal_id` bigint NULL DEFAULT NULL COMMENT '套餐id',
  `dish_flavor` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '口味',
  `number` int NOT NULL DEFAULT 1 COMMENT '数量',
  `amount` decimal(10, 2) NOT NULL COMMENT '金额',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = '订单明细表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of order_detail
-- ----------------------------
INSERT INTO `order_detail` VALUES (1, '经典酸菜鮰鱼', 'https://doordash.oss-cn-beijing.aliyuncs.com/2133c9c4-25cd-4c1d-92b4-d992af421adf.png', 4, 52, NULL, '不辣,不要香菜', 1, 66.00);
INSERT INTO `order_detail` VALUES (2, '东坡肘子', 'https://doordash.oss-cn-beijing.aliyuncs.com/eda74642-4135-4862-aa11-5db8201ec87d.png', 5, 59, NULL, NULL, 1, 138.00);
INSERT INTO `order_detail` VALUES (3, '香锅牛蛙', 'https://doordash.oss-cn-beijing.aliyuncs.com/41b81a61-0a51-4b9d-8c47-d1821956c7d0.png', 5, 63, NULL, NULL, 1, 88.00);
INSERT INTO `order_detail` VALUES (4, '鮰鱼2斤', 'https://doordash.oss-cn-beijing.aliyuncs.com/498307a0-1afb-4c3b-93dc-dddd39d14d13.png', 5, 67, NULL, '不辣', 1, 72.00);
INSERT INTO `order_detail` VALUES (5, '雪花啤酒', 'https://doordash.oss-cn-beijing.aliyuncs.com/2e37d5e7-3287-4d36-8a7e-109ba1f3cafe.png', 5, 48, NULL, NULL, 1, 4.00);
INSERT INTO `order_detail` VALUES (6, '东坡肘子', 'https://doordash.oss-cn-beijing.aliyuncs.com/eda74642-4135-4862-aa11-5db8201ec87d.png', 6, 59, NULL, NULL, 1, 138.00);
INSERT INTO `order_detail` VALUES (7, '香锅牛蛙', 'https://doordash.oss-cn-beijing.aliyuncs.com/41b81a61-0a51-4b9d-8c47-d1821956c7d0.png', 6, 63, NULL, NULL, 1, 88.00);
INSERT INTO `order_detail` VALUES (8, '鮰鱼2斤', 'https://doordash.oss-cn-beijing.aliyuncs.com/498307a0-1afb-4c3b-93dc-dddd39d14d13.png', 6, 67, NULL, '不辣', 1, 72.00);
INSERT INTO `order_detail` VALUES (9, '雪花啤酒', 'https://doordash.oss-cn-beijing.aliyuncs.com/2e37d5e7-3287-4d36-8a7e-109ba1f3cafe.png', 6, 48, NULL, NULL, 1, 4.00);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `number` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '订单号',
  `status` int NOT NULL DEFAULT 1 COMMENT '订单状态 1待付款 2待接单 3已接单 4派送中 5已完成 6已取消 7退款',
  `user_id` bigint NOT NULL COMMENT '下单用户',
  `address_book_id` bigint NOT NULL COMMENT '地址id',
  `order_time` datetime NOT NULL COMMENT '下单时间',
  `checkout_time` datetime NULL DEFAULT NULL COMMENT '结账时间',
  `pay_method` int NOT NULL DEFAULT 1 COMMENT '支付方式 1微信,2支付宝',
  `pay_status` tinyint NOT NULL DEFAULT 0 COMMENT '支付状态 0未支付 1已支付 2退款',
  `amount` decimal(10, 2) NOT NULL COMMENT '实收金额',
  `remark` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '备注',
  `phone` varchar(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '手机号',
  `address` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '地址',
  `user_name` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '用户名称',
  `consignee` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '收货人',
  `cancel_reason` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '订单取消原因',
  `rejection_reason` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '订单拒绝原因',
  `cancel_time` datetime NULL DEFAULT NULL COMMENT '订单取消时间',
  `estimated_delivery_time` datetime NULL DEFAULT NULL COMMENT '预计送达时间',
  `delivery_status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '配送状态  1立即送出  0选择具体时间',
  `delivery_time` datetime NULL DEFAULT NULL COMMENT '送达时间',
  `pack_amount` int NULL DEFAULT NULL COMMENT '打包费',
  `tableware_number` int NULL DEFAULT NULL COMMENT '餐具数量',
  `tableware_status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '餐具数量状态  1按餐量提供  0选择具体数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = '订单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (4, '1716489213850', 1, 1, 1, '2024-05-24 02:33:34', NULL, 1, 0, 73.00, '', '13458466578', '西南民族大学28-2-6-3', NULL, '桂定宝', NULL, NULL, NULL, '2024-05-24 03:33:00', 0, NULL, 1, 0, 0);
INSERT INTO `orders` VALUES (5, '1716558847465', 1, 1, 1, '2024-05-24 21:54:07', NULL, 1, 0, 312.00, '', '13458466578', '西南民族大学28-2-6-3', NULL, '桂定宝', NULL, NULL, NULL, '2024-05-24 22:54:00', 0, NULL, 4, 0, 0);
INSERT INTO `orders` VALUES (6, '1716558928394', 1, 1, 1, '2024-05-24 21:55:28', NULL, 1, 0, 312.00, '两份餐具', '13458466578', '西南民族大学28-2-6-3', NULL, '桂定宝', NULL, NULL, NULL, '2024-05-24 22:55:00', 0, NULL, 4, 2, 0);

-- ----------------------------
-- Table structure for setmeal
-- ----------------------------
DROP TABLE IF EXISTS `setmeal`;
CREATE TABLE `setmeal`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `category_id` bigint NOT NULL COMMENT '菜品分类id',
  `name` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL COMMENT '套餐名称',
  `price` decimal(10, 2) NOT NULL COMMENT '套餐价格',
  `status` int NULL DEFAULT 1 COMMENT '售卖状态 0:停售 1:起售',
  `description` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '描述信息',
  `image` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '图片',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `create_user` bigint NULL DEFAULT NULL COMMENT '创建人',
  `update_user` bigint NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_setmeal_name`(`name` ASC) USING BTREE,
  INDEX `category_id`(`category_id` ASC) USING BTREE,
  CONSTRAINT `setmeal_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = '套餐' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of setmeal
-- ----------------------------
INSERT INTO `setmeal` VALUES (32, 23, '情人节套餐', 150.00, 1, '情人节专属套餐', 'https://doordash.oss-cn-beijing.aliyuncs.com/16pic_6039395_s.jpg', '2024-05-18 16:01:25', '2024-05-18 16:01:25', 1, 1);
INSERT INTO `setmeal` VALUES (33, 24, '一家三口', 199.00, 1, '一家三口', 'https://doordash.oss-cn-beijing.aliyuncs.com/4824721d-f388-4544-a580-a63ed6c2ef8e.jpeg', '2024-05-20 14:48:47', '2024-05-20 14:48:47', NULL, NULL);
INSERT INTO `setmeal` VALUES (34, 29, '父子套餐', 152.00, 1, '温馨的父子套餐', 'https://doordash.oss-cn-beijing.aliyuncs.com/5b761469-e43f-4a67-a796-345cc5ffb113.jpg', '2024-05-24 01:06:10', '2024-05-24 01:06:10', NULL, NULL);
INSERT INTO `setmeal` VALUES (35, 24, '团员套餐', 312.00, 1, '阖家团圆', 'https://doordash.oss-cn-beijing.aliyuncs.com/706a0384-6b44-4300-9ed3-ff1e00aea11b.jpg', '2024-05-24 01:10:12', '2024-05-24 01:10:12', NULL, NULL);

-- ----------------------------
-- Table structure for setmeal_dish
-- ----------------------------
DROP TABLE IF EXISTS `setmeal_dish`;
CREATE TABLE `setmeal_dish`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `setmeal_id` bigint NULL DEFAULT NULL COMMENT '套餐id',
  `dish_id` bigint NULL DEFAULT NULL COMMENT '菜品id',
  `name` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '菜品名称 （冗余字段）',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '菜品单价（冗余字段）',
  `copies` int NULL DEFAULT NULL COMMENT '菜品份数',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `dish_id`(`dish_id` ASC) USING BTREE,
  INDEX `setmeal_id`(`setmeal_id` ASC) USING BTREE,
  CONSTRAINT `setmeal_dish_ibfk_1` FOREIGN KEY (`dish_id`) REFERENCES `dish` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `setmeal_dish_ibfk_2` FOREIGN KEY (`setmeal_id`) REFERENCES `setmeal` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 75 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = '套餐菜品关系' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of setmeal_dish
-- ----------------------------
INSERT INTO `setmeal_dish` VALUES (10, 32, 65, '草鱼2斤', 68.00, 1);
INSERT INTO `setmeal_dish` VALUES (11, 32, 63, '香锅牛蛙', 88.00, 1);
INSERT INTO `setmeal_dish` VALUES (12, 32, 80, '平菇豆腐汤', 8.00, 1);
INSERT INTO `setmeal_dish` VALUES (13, 32, 47, '北冰洋', 4.00, 1);
INSERT INTO `setmeal_dish` VALUES (14, 32, 46, '王老吉', 6.00, 1);
INSERT INTO `setmeal_dish` VALUES (15, 32, 49, '米饭', 2.00, 1);
INSERT INTO `setmeal_dish` VALUES (16, 32, 57, '炝炒圆白菜', 18.00, 1);
INSERT INTO `setmeal_dish` VALUES (41, 33, 64, '馋嘴牛蛙', 88.00, 1);
INSERT INTO `setmeal_dish` VALUES (42, 33, 65, '草鱼2斤', 68.00, 1);
INSERT INTO `setmeal_dish` VALUES (43, 33, 68, '鸡蛋汤', 10.00, 1);
INSERT INTO `setmeal_dish` VALUES (44, 33, 49, '米饭', 2.00, 3);
INSERT INTO `setmeal_dish` VALUES (45, 33, 53, '蜀味水煮草鱼', 38.00, 1);
INSERT INTO `setmeal_dish` VALUES (46, 33, 54, '清炒小油菜', 18.00, 1);
INSERT INTO `setmeal_dish` VALUES (61, 35, 59, '东坡肘子', 138.00, 1);
INSERT INTO `setmeal_dish` VALUES (62, 35, 62, '金汤酸菜牛蛙', 88.00, 1);
INSERT INTO `setmeal_dish` VALUES (63, 35, 66, '江团鱼2斤', 119.00, 1);
INSERT INTO `setmeal_dish` VALUES (64, 35, 80, '平菇豆腐汤', 9.00, 1);
INSERT INTO `setmeal_dish` VALUES (65, 35, 48, '雪花啤酒', 4.00, 1);
INSERT INTO `setmeal_dish` VALUES (66, 35, 46, '王老吉', 6.00, 2);
INSERT INTO `setmeal_dish` VALUES (67, 35, 47, '北冰洋', 4.00, 2);
INSERT INTO `setmeal_dish` VALUES (68, 35, 52, '经典酸菜鮰鱼', 66.00, 1);
INSERT INTO `setmeal_dish` VALUES (69, 35, 55, '蒜蓉娃娃菜', 18.00, 1);
INSERT INTO `setmeal_dish` VALUES (70, 35, 49, '米饭', 2.00, 5);
INSERT INTO `setmeal_dish` VALUES (71, 34, 64, '馋嘴牛蛙', 88.00, 1);
INSERT INTO `setmeal_dish` VALUES (72, 34, 59, '东坡肘子', 138.00, 1);
INSERT INTO `setmeal_dish` VALUES (73, 34, 68, '鸡蛋汤', 10.00, 1);
INSERT INTO `setmeal_dish` VALUES (74, 34, 49, '米饭', 2.00, 2);

-- ----------------------------
-- Table structure for shopping_cart
-- ----------------------------
DROP TABLE IF EXISTS `shopping_cart`;
CREATE TABLE `shopping_cart`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '商品名称',
  `image` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '图片',
  `user_id` bigint NOT NULL COMMENT '主键',
  `dish_id` bigint NULL DEFAULT NULL COMMENT '菜品id',
  `setmeal_id` bigint NULL DEFAULT NULL COMMENT '套餐id',
  `dish_flavor` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '口味',
  `number` int NOT NULL DEFAULT 1 COMMENT '数量',
  `amount` decimal(10, 2) NOT NULL COMMENT '金额',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = '购物车' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of shopping_cart
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `openid` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '微信用户唯一标识',
  `name` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '姓名',
  `phone` varchar(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '手机号',
  `sex` varchar(2) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '性别',
  `id_number` varchar(18) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '身份证号',
  `avatar` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL COMMENT '头像',
  `create_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin COMMENT = '用户信息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'o_d935Yypvz_T1laHwx0lMcGyCac', NULL, NULL, NULL, NULL, NULL, '2024-05-23 20:45:22');

SET FOREIGN_KEY_CHECKS = 1;
