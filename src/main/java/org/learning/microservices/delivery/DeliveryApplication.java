package org.learning.microservices.delivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class DeliveryApplication {
    public static void main(String[] args) {

        SpringApplication.run(DeliveryApplication.class, args);

        System.out.println("Hello world!");
    }
}