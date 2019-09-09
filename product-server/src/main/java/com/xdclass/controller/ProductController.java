package com.xdclass.controller;

import ch.qos.logback.core.util.TimeUtil;
import com.xdclass.domain.Product;
import com.xdclass.service.ProductService;
import com.xdclass.tools.R;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Value("${server.port}")
    private int port;

    @RequestMapping("list")
    public Object list() {
        return productService.listProduct();
    }


    @RequestMapping("get")
    public Object get() {
        Product product = new Product();
        product.setName("name");
        return new R<Product>(product);
    }

    @RequestMapping("list")
    public Object list1() {
        List<Product> list = new ArrayList<>();
        Product product = new Product();
        product.setName("name");
        list.add(product);
        Product product2 = new Product();
        product2.setName("name2");
        list.add(product);


        return new R<Product>(product);
    }


    @RequestMapping("find")
    public Object findById(@RequestParam("id") int id) {
        // 程序休10秒钟
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        Product service = productService.findById(id);
        Product product = new Product();
        BeanUtils.copyProperties(service, product);
        if (product != null) {
            product.setName(product.getName() + " data from " + port);
        }
        return product;
    }

//    @RequestMapping("get")
//    public Product getById() {
//         程序休10秒钟
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        Product service = productService.findById(1);
//        Product product = new Product();
//        BeanUtils.copyProperties(service, product);
//        if (product != null) {
//            product.setName(product.getName() + " data from " + port);
//        }
//        return product;
//    }

}
