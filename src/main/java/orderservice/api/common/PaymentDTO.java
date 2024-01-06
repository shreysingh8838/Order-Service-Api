package orderservice.api.common;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {
    private int paymentID;
    private String paymentStatus;
    private String transactionId;

    private int orderId;
    private double amount;
}