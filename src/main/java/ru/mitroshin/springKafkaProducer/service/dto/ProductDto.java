package ru.mitroshin.springKafkaProducer.service.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private String title;
    private BigDecimal price;
    private long countProduct;
}
