package com.xdclass.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {

    private int id;
    private String name; // 名字
    private int price;  // 价格 分为单位
    private int store; // 库存


}
