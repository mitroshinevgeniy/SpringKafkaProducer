package ru.mitroshin.springKafkaProducer.service.event;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateEvent {
    private String id;
    private String title;
    private BigDecimal price;
    private long countProduct;
}
