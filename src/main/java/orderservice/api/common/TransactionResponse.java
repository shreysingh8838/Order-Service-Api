package orderservice.api.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import orderservice.api.entity.Order;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {
    private Order order;
    private int amount;
    private String transactionId;
}
