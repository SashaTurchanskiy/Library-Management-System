package com.library.service.impl;

import com.library.domain.PaymentStatus;
import com.library.modal.Payment;
import com.library.modal.Subscription;
import com.library.modal.User;
import com.library.payload.dto.PaymentDTO;
import com.library.payload.request.PaymentInitiateRequest;
import com.library.payload.request.PaymentVerifyRequest;
import com.library.payload.response.PaymentInitiateResponse;
import com.library.payload.response.StripePaymentResponse;
import com.library.repository.PaymentRepository;
import com.library.repository.SubscriptionRepository;
import com.library.repository.UserRepository;
import com.library.service.PaymentService;
import com.library.service.gateway.StripeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final StripeService stripeService;

    @Override
    public PaymentInitiateResponse initiatePayment(PaymentInitiateRequest request) throws Exception {

        User user = userRepository.findById(request.getUserId()).get();

        Payment payment = new Payment();
        payment.setUser(user);
        payment.setPaymentType(request.getPaymentType());
        payment.setGateway(request.getGateway());
        payment.setAmount(request.getAmount());

        payment.setDescription(request.getDescription());
        payment.setStatus(PaymentStatus.PENDING);
        payment.setTransactionId("TXN_" + UUID.randomUUID());
        payment.setInitiatedAt(LocalDateTime.now());

        if (request.getSubscriptionId() != null){
            Subscription sub = subscriptionRepository
                    .findById(request.getSubscriptionId())
                    .orElseThrow(()-> new Exception("Subscription not found"));
            payment.setSubscription(sub);
        }
        payment = paymentRepository.save(payment);

        StripePaymentResponse stripeResponse = stripeService.createPaymentIntent(user, payment);

        PaymentInitiateResponse response = new PaymentInitiateResponse();
        response.setPaymentId(payment.getId());
        response.setGateway(payment.getGateway());
        response.setTransactionId(payment.getTransactionId());
        response.setAmount(payment.getAmount());
        response.setDescription(payment.getDescription());
        response.setCheckOutUrl(stripeResponse.getClientSecret());
        response.setMessage("Stripe payment initiated");
        response.setSuccess(true);

        return response;
    }

    @Override
    public PaymentDTO verifyPayment(PaymentVerifyRequest request) {
        return null;
    }

    @Override
    public Page<PaymentDTO> getAllPayments(Pageable pageable) {
        return null;
    }
}
