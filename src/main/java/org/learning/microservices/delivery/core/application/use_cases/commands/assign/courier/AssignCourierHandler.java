package org.learning.microservices.delivery.core.application.use_cases.commands.assign.courier;

import lombok.AllArgsConstructor;
import org.learning.microservices.delivery.core.domain.model.courieraggregate.Courier;
import org.learning.microservices.delivery.core.domain.model.orderaggregate.Order;
import org.learning.microservices.delivery.core.domain.services.IDispatchService;
import org.learning.microservices.delivery.core.ports.ICourierRepository;
import org.learning.microservices.delivery.core.ports.IOrderRepository;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@AllArgsConstructor
public class AssignCourierHandler {
    private final ICourierRepository courierRepository;
    private final IOrderRepository orderRepository;
    private final IDispatchService dispatchService;
    private final TransactionTemplate transactionTemplate;

    public void handle(){
        Order order = orderRepository.getAnyCreatedOrder();
        Courier courier = dispatchService.dispatch(order, courierRepository.getAllFreeCouriers());
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                try{
                    order.assign(courier.getId());
                    orderRepository.updateOrder(order);
                    courier.setBusy();
                    courierRepository.updateCourier(courier);
                } catch (Exception e){
                    status.setRollbackOnly();
                }
            }
        });
    }
}
