package ru.mitroshin.springKafkaProducer.controller;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {
    public Date timestamp;
    public String message;
}
