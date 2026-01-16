package com.library.payload.response;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StripePaymentResponse {

    private String clientSecret;
    private String paymentIntentId;
    private String checkOutUrl;
    private String status;
    private boolean success;
}
