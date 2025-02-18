package ru.mitroshin.springKafkaProducer.service;

import ru.mitroshin.springKafkaProducer.service.dto.ProductDto;

import java.util.concurrent.ExecutionException;

public interface ProductService {
    String createProduct(ProductDto productDto) throws ExecutionException, InterruptedException;
}
