package org.learning.microservices.delivery.infrastructure.adapters.postgres.courier;

import org.learning.microservices.delivery.core.domain.model.courieraggregate.Courier;
import org.learning.microservices.delivery.core.domain.model.courieraggregate.CourierStatus;
import org.learning.microservices.delivery.core.ports.ICourierRepository;
import org.learning.microservices.delivery.infrastructure.adapters.postgres.courier.entity.CourierEntity;
import org.learning.microservices.delivery.infrastructure.adapters.postgres.courier.entity.CourierMapper;
import org.learning.microservices.delivery.infrastructure.adapters.postgres.courier.repository.CourierJpaRepository;

import java.util.List;
import java.util.UUID;


public class CourierRepository implements ICourierRepository {

    private final CourierJpaRepository courierJpaRepository;
    private final CourierMapper mapper;

    public CourierRepository(CourierJpaRepository courierJpaRepository, CourierMapper mapper){
        this.courierJpaRepository = courierJpaRepository;
        this.mapper = mapper;
    }

    @Override
    public void addCourier(Courier courier) {
        CourierEntity entity = mapper.toEntity(courier);
        courierJpaRepository.save(entity);
    }

    @Override
    public void updateCourier(Courier courier) {

        courierJpaRepository.deleteById(courier.getId());
        addCourier(courier);
    }

    @Override
    public Courier getCourierById(UUID id) {
        CourierEntity entity = courierJpaRepository.findById(id).orElseThrow();

        return mapper.toModel(entity);
    }

    @Override
    public List<Courier> getAllFreeCouriers() {

        return courierJpaRepository.findAll().stream()
                .map(mapper::toModel)
                .filter(courier -> courier.getStatus().equals(CourierStatus.Free))
                .toList();
    }

    @Override
    public List<Courier> getAllBusyCouriers(){
        return courierJpaRepository.findAll().stream()
                .map(mapper::toModel)
                .filter(courier -> courier.getStatus().equals(CourierStatus.Busy))
                .toList();
    }
}
