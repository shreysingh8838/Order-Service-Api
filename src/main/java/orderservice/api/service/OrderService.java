package orderservice.api.service;

import orderservice.api.common.Payment;
import orderservice.api.common.TransactionRequest;
import orderservice.api.common.TransactionResponse;
import orderservice.api.entity.Order;
import orderservice.api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;
    public TransactionResponse saveOrder(TransactionRequest request){
        String response = "";
        Order order = request.getOrder();
        Payment payment = request.getPayment();
        payment.setOrderId(order.getId());
        payment.setAmount(order.getPrice());

        // do a rest api call to payment and pass the orderid
        Payment paymentResponse =
                restTemplate.postForObject("http://PAYMENT-SERVICE/payment/doPayment", payment, Payment.class);
        response = paymentResponse.getPaymentStatus().equals("success") ? "Payment processing successful and order is placed" : "There is a failure in payment api, order not placed";

        if(paymentResponse.getPaymentStatus().equals("success")) {
            orderRepository.save(order);
        }
        return new TransactionResponse(order, paymentResponse.getAmount(), paymentResponse.getTransactionId(), response);
    }
}
