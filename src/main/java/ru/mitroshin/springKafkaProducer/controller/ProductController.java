package ru.mitroshin.springKafkaProducer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mitroshin.springKafkaProducer.service.ProductService;
import ru.mitroshin.springKafkaProducer.service.dto.ProductDto;

import java.util.Date;

@RestController
@RequestMapping("/product")
public class ProductController {
    ProductService productService;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> addProduct(@RequestBody ProductDto productDto){
        if (productDto == null) {
            return ResponseEntity.badRequest().body("Product cannot be empty");
        }

        String productId = null;

        try {
            productId = productService.createProduct(productDto);
            LOGGER.info("productId {}", productId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(new Date(), e.getMessage()));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("Hello, id: " + productId);

    }
}
