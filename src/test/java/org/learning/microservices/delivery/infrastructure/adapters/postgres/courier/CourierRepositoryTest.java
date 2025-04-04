package org.learning.microservices.delivery.infrastructure.adapters.postgres.courier;

import org.learning.microservices.delivery.infrastructure.adapters.postgres.PostgresConfig;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {
        PostgresConfig.class
})
class CourierRepositoryTest {

}