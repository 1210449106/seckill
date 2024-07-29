/*
 * 王睿阳 拥有本软件版权 2023 并保留所有权利。
 * Copyright 2023, WRY Information&Science Technology Co.,Ltd,
 * All rights reserved.
 */
package xyz.wrywebsite.service;

import org.springframework.stereotype.Service;

/**
 * @author wry
 * @version 1.0
 * @classname PayMessageService
 * @description
 * @since 1.0
 */
public interface PayMessageService {
    void sendPayDelayMessage(Integer orderId);
}
