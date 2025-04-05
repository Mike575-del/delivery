package org.learning.microservices.delivery.infrastructure.adapters.postgres.courier.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.learning.microservices.delivery.core.domain.model.SharedKernel.Location;
import org.learning.microservices.delivery.core.domain.model.courieraggregate.CourierStatus;
import org.learning.microservices.delivery.core.domain.model.courieraggregate.Transport;
import org.learning.microservices.delivery.infrastructure.adapters.postgres.converter.CourierStatusConverter;
import org.learning.microservices.delivery.infrastructure.adapters.postgres.converter.LocationConverter;
import org.learning.microservices.delivery.infrastructure.adapters.postgres.converter.TransportConverter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "couriers")
public class CourierEntity {

    @Id
    private UUID id;


    @Column(name = "name")
    private String name;

    @Column(name = "status")
    @Convert(converter = CourierStatusConverter.class)
    private CourierStatus status;

    @Column(name = "transport_name")
    @Convert(converter = TransportConverter.class)
    private Transport transport;

    @Column(name = "current_location")
    @Convert(converter = LocationConverter.class)
    private Location currentLocation;

}
