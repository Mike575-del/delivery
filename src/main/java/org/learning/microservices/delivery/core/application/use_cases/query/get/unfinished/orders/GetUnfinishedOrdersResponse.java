package org.learning.microservices.delivery.core.application.use_cases.query.get.unfinished.orders;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetUnfinishedOrdersResponse {
    private final List<GetUnfinishedOrdersQueryModel> orders;
}
