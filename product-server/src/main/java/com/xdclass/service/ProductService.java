package com.xdclass.service;

import com.xdclass.domain.Product;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {

    List<Product> listProduct();

    Product findById(int id);

}
