package com.xdclass.service.impl;

import com.xdclass.controller.Product;
import com.xdclass.controller.R;
import com.xdclass.domain.ProductOrder;
import com.xdclass.remote.ProductFeignClient;
import com.xdclass.service.ProductOrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class ProductOrderServiceImpl implements ProductOrderService {

    //    @Autowired
//    private RestTemplate restTemplate;
    @Autowired
    private ProductFeignClient productFeignClient;

    @Override
    public ProductOrder save(int userId, int productId) {
//        Object object = restTemplate.getForObject("http://product-service/api/v1/product/find?id=" + productId, Object.class);
//        System.out.println(object);
        String s = productFeignClient.findById(productId);
        System.out.println(s);
        ProductOrder productOrder = new ProductOrder();
        productOrder.setCreateTime(new Date());
        productOrder.setUserId(userId);
        productOrder.setTradeNo(UUID.randomUUID().toString());
        ProductOrder result = new ProductOrder();
        // 复制对象
        BeanUtils.copyProperties(productOrder, result);
        return result;
    }

    @Override
    public ProductOrder get() {
        R<Product> r = productFeignClient.getById();
        System.out.println(r.toString());
        return new ProductOrder().setUserId(r.getData().getId());
    }
}
