package com.xdclass.remote;

import com.xdclass.controller.Product;
import com.xdclass.controller.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * feign 自带的熔断降级
 */
//@FeignClient(name = "product-service", fallback = ProductFeignClientFallBack.class)
@FeignClient(name = "product-service")
public interface ProductFeignClient {

    @GetMapping("api/v1/product/find")
    String findById(@RequestParam("id") int id);
    @PostMapping("api/v1/product/get")
    R<Product> getById();
}
