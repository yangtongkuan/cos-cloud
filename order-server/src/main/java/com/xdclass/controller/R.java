package com.xdclass.controller;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class R<T> implements Serializable {
    private T data;

    public R(T data) {
        this.data = data;
    }
}