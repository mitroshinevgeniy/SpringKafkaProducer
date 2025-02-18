package ru.mitroshin.springKafkaProducer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import ru.mitroshin.springKafkaProducer.service.dto.ProductDto;
import ru.mitroshin.springKafkaProducer.service.event.ProductCreateEvent;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class ProductServiceImp implements ProductService{
    private final KafkaTemplate<String, ProductCreateEvent> kafkaTemplate;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public ProductServiceImp(KafkaTemplate<String, ProductCreateEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public String createProduct(ProductDto productDto) throws ExecutionException, InterruptedException {
        //TODO add to DB
        String productId = UUID.randomUUID().toString();

        ProductCreateEvent productCreateEvent = new ProductCreateEvent
                (productId, productDto.getTitle(),productDto.getPrice(), productDto.getCountProduct());

        sendProduct(productCreateEvent);

        return productId;
    }

    public void sendProduct(ProductCreateEvent productCreateEvent) throws ExecutionException, InterruptedException {

        SendResult<String, ProductCreateEvent> result = kafkaTemplate
                .send("product-topic", productCreateEvent.getId(), productCreateEvent)
                .get();

        LOGGER.info("topic {}", result.getRecordMetadata().topic());
        LOGGER.info("partition {}", result.getRecordMetadata().partition());
        LOGGER.info("timestamp {}", result.getRecordMetadata().timestamp());
        LOGGER.info("offset {}", result.getRecordMetadata().offset());
    }
}
