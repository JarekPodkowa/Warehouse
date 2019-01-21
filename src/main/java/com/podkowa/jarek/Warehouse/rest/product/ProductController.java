package com.podkowa.jarek.Warehouse.rest.product;

import com.podkowa.jarek.Warehouse.db.domain.Product;
import com.podkowa.jarek.Warehouse.db.domain.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    public void add(@RequestBody @Validated ProductDto productDto) {
        productService.add(productDto);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        productService.delete(id);
    }

    @GetMapping("/{id}")
    public Product get(@PathVariable int id) {
        return productService.getById(id);
    }
}
