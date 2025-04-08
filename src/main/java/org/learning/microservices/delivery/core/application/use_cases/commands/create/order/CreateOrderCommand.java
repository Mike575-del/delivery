package org.learning.microservices.delivery.core.application.use_cases.commands.create.order;

import lombok.AllArgsConstructor;
import lombok.Getter;


import java.util.UUID;

@Getter
@AllArgsConstructor
public class CreateOrderCommand {
    private final String street;
    private final UUID basketId;
}
