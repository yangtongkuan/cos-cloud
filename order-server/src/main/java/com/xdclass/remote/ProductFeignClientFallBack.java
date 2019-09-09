package com.xdclass.remote;

import com.xdclass.controller.Product;
import com.xdclass.controller.R;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 *
 * @User: @Created by yangtk
 * @Date: @Date 2019/7/16 14:08
 * @Classname: ProductFeignClientFallBack
 * @To change this template use File | Settings | File Templates.
 */
@Component
public class ProductFeignClientFallBack implements ProductFeignClient {
    @Override
    public String findById(int id) {
        System.out.println("异常");
        return null;
    }

    @Override
    public R<Product> getById() {
        System.out.println("失败");
        return null;
    }

//    @Override
//    public Product getById() {
//        System.out.println("失败");
//        return null;
//    }
}
