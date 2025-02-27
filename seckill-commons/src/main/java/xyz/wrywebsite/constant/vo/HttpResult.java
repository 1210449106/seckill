/*
 * 王睿阳 拥有本软件版权 2023 并保留所有权利。
 * Copyright 2023, WRY Information&Science Technology Co.,Ltd,
 * All rights reserved.
 */
package xyz.wrywebsite.constant.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wry
 * @version 1.0
 * @classname HttpResult
 * @description
 * @since 1.0
 */
@Data
public class HttpResult {
    private Integer code;
    private String message;
    private Object data;

    public HttpResult(ResponseEnum responseEnum, Object data) {
        this.code = responseEnum.getCode();
        this.message = responseEnum.getMessage();
        this.data = data;
    }
}
