package com.xdclass.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
public class ProductOrder implements Serializable {

    private int id;
    private String productName;
    private int price;
    private Date createTime;
    private int userId;
    private String username;
    private String tradeNo;
}
