package com.xdclass;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class ProductTo  implements Serializable {
    private String name;
    private int totalCount;
}
