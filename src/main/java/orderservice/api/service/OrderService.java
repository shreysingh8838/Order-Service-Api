package orderservice.api.service;

import orderservice.api.common.PaymentDTO;
import orderservice.api.common.TransactionRequest;
import orderservice.api.entity.Order;
import orderservice.api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order saveOrder(TransactionRequest request){
        Order order = request.getOrder();
        PaymentDTO paymentDTO = request.getPaymentDTO();
        paymentDTO.setOrderId(order.getId());
        paymentDTO.setAmount(order.getPrice());

        // do a rest api call to payment and pass the orderid
        return orderRepository.save(order);
    }
}
