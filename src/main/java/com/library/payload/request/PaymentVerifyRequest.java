package com.library.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentVerifyRequest {

    private Long paymentId;
    private String transactionId;
    private String paymentIntentId;
    private String gateway;



}
