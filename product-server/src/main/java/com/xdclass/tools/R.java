package com.xdclass.tools;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/8/8 11:34
 * @Classname: R
 * @To change this template use File | Settings | File Templates.
 */
@Data
@Accessors(chain = true)
public class R<T> implements Serializable {
    private T data;

    public R(T data) {
        this.data = data;
    }
}
