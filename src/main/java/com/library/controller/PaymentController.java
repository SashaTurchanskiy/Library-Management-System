package com.library.controller;

import com.library.payload.dto.PaymentDTO;
import com.library.payload.request.PaymentInitiateRequest;
import com.library.payload.request.PaymentVerifyRequest;
import com.library.payload.response.PaymentInitiateResponse;
import com.library.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    // 1️⃣ Ініціація платежу
    @PostMapping("/initiate")
    public ResponseEntity<PaymentInitiateResponse> initiatePayment(
            @Valid @RequestBody PaymentInitiateRequest request) throws Exception {
        PaymentInitiateResponse response = paymentService.initiatePayment(request);
        return ResponseEntity.ok(response);
    }

    // 2️⃣ Верифікація платежу
    @PostMapping("/verify")
    public ResponseEntity<PaymentDTO> verifyPayment(
            @Valid @RequestBody PaymentVerifyRequest request) throws Exception {
        PaymentDTO response = paymentService.verifyPayment(request);
        return ResponseEntity.ok(response);
    }

    // 3️⃣ Отримати всі платежі з пагінацією
    @GetMapping
    public ResponseEntity<Page<PaymentDTO>> getAllPayments(Pageable pageable) {
        Page<PaymentDTO> payments = paymentService.getAllPayments(pageable);
        return ResponseEntity.ok(payments);
    }
}

