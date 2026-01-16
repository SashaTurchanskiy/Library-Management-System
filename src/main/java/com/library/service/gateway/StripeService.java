package com.library.service.gateway;

import com.library.modal.Payment;
import com.library.modal.User;
import com.library.payload.response.StripePaymentResponse;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class StripeService {

    @Value("${stripe.secret.key}")
    private String secretKey;

    @PostConstruct
    private void init() {
        Stripe.apiKey = secretKey;
    }

    public StripePaymentResponse createCheckoutSession(User user, Payment payment, String successUrl, String cancelUrl) throws StripeException, StripeException {
        SessionCreateParams params = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl(successUrl)
                .setCancelUrl(cancelUrl)
                .addLineItem(
                        SessionCreateParams.LineItem.builder()
                                .setQuantity(1L)
                                .setPriceData(
                                        SessionCreateParams.LineItem.PriceData.builder()
                                                .setCurrency("usd")
                                                .setUnitAmount(payment.getAmount()) // у центах
                                                .setProductData(
                                                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                .setName(payment.getDescription())
                                                                .build()
                                                )
                                                .build()
                                )
                                .build()
                )
                .build();

        Session session = Session.create(params);

        return StripePaymentResponse.builder()
                .paymentIntentId(session.getPaymentIntent())
                .status(session.getPaymentStatus())
                .checkOutUrl(session.getUrl()) // ✅ ось справжня URL для користувача
                .build();
    }
    public PaymentIntent retrievePaymentIntent(String paymentIntentId) throws StripeException {
        return PaymentIntent.retrieve(paymentIntentId);
    }
}
