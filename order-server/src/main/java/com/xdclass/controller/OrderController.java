package com.xdclass.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.xdclass.domain.ProductOrder;
import com.xdclass.service.ProductOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/order")
@RestController
public class OrderController {

    @Autowired
    private ProductOrderService productOrderService;

    @RequestMapping("save")
    @HystrixCommand(fallbackMethod = "saveFallback")
    public Object save(@RequestParam("userId") int userId, @RequestParam("productId") int productId) {
        ProductOrder save = productOrderService.save(userId, productId);
        return save;
    }

    @RequestMapping("get")
    public Object get() {
        ProductOrder save = productOrderService.get();
        return save;
    }
    public Object saveFallback( int userId,  int productId) {
        return "123";
    }
}
