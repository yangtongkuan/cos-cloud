package com.xdclass.service;

import com.xdclass.domain.Product;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Map<Integer, Product> dataMap = new HashMap<>();

    static {
        Product p1 = new Product(1, "iphonex", 1111, 10);
        Product p2 = new Product(2, "冰箱", 2222, 10);
        Product p3 = new Product(3, "洗衣机", 3333, 10);
        Product p4 = new Product(4, "电话", 4444, 10);
        Product p5 = new Product(5, "汽车", 5555, 10);
        Product p6 = new Product(6, "椅子", 6666, 10);
        Product p7 = new Product(7, "java", 7777, 10);
        dataMap.put(p1.getId(), p1);
        dataMap.put(p2.getId(), p2);
        dataMap.put(p3.getId(), p3);
        dataMap.put(p4.getId(), p4);
        dataMap.put(p5.getId(), p5);
        dataMap.put(p6.getId(), p6);
        dataMap.put(p7.getId(), p7);

    }

    @Override
    public List<Product> listProduct() {
        Collection<Product> collection = dataMap.values();
        List<Product> list = new ArrayList<>(collection);
        return list;
    }

    @Override
    public Product findById(int id) {
        return dataMap.get(id);
    }
}
