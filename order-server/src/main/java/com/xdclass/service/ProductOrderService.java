package com.xdclass.service;

import com.xdclass.domain.ProductOrder;

public interface ProductOrderService {
    ProductOrder save(int userId, int productId);
    ProductOrder get();
}
