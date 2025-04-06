package org.learning.microservices.delivery.core.application.use_cases.query.get.unfinished.orders;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.learning.microservices.delivery.core.domain.model.SharedKernel.Location;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class GetUnfinishedOrdersQueryModel {
    private final UUID id;
    private final Location location;

}
