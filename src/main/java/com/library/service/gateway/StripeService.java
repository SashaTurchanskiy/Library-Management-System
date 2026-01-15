package com.library.service.gateway;

import com.library.modal.Payment;
import com.library.modal.User;
import com.library.payload.response.StripePaymentResponse;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StripeService {

    @Value("{stripe.secret.key}")
    private String secretKey;

    @PostConstruct
    private void init(){
        Stripe.apiKey = secretKey;
    }

    public StripePaymentResponse createPaymentIntent(User user, Payment payment) throws StripeException {
        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount((long)(payment.getAmount() * 100))
                .setCurrency("usd")
                .setDescription(payment.getDescription())
                .setReceiptEmail(user.getEmail())
                .putMetadata("transactionId", payment.getTransactionId())
                .build();

        PaymentIntent intent = PaymentIntent.create(params);

        return StripePaymentResponse.builder()
                .clientSecret(intent.getClientSecret())
                .paymentIntentId(intent.getId())
                .status(intent.getStatus())
                .build();
    }
}
