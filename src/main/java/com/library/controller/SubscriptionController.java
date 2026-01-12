package com.library.controller;

import com.library.exception.SubscriptionException;
import com.library.payload.dto.SubscriptionDTO;
import com.library.payload.response.ApiResponse;
import com.library.service.SubscriptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping("/subscribe")
    public ResponseEntity<?> subscribe(@Valid @RequestBody SubscriptionDTO subscriptionDTO) throws Exception {
        SubscriptionDTO dto = subscriptionService.createSubscription(subscriptionDTO);
        return ResponseEntity.ok(dto);
    }
    @GetMapping("/user/active")
    public ResponseEntity<?> getUserActiveSubscription(@RequestParam (required = false) Long userId) throws Exception {

        SubscriptionDTO dto = subscriptionService.getUserActiveSubscription(userId);
        return ResponseEntity.ok(dto);
    }
    @GetMapping("/admin/all")
    public ResponseEntity<?> getAllSubscriptions() {
        int page = 0;
        int size = 10;
        Pageable pageable = PageRequest.of(page, size);
        List<SubscriptionDTO> subscriptionDTOS = subscriptionService.getAllSubscriptions(pageable);
        return ResponseEntity.ok(subscriptionDTOS);
    }
    @GetMapping("/admin/deactivate-expired")
    public ResponseEntity<?> deactivateExpiredSubscription(){
        int page = 0;
        int size = 10;
        Pageable pageable = PageRequest.of(page, size);
        subscriptionService.deactivateExpiredSubscriptions();
        ApiResponse response = new ApiResponse("Expired subscriptions deactivated successfully", true);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/cancel/{subscriptionId}")
    public ResponseEntity<?> cancelSubscription(@PathVariable Long subscriptionId,
                                                @RequestParam(required = false) String reason) throws SubscriptionException {

        SubscriptionDTO dto = subscriptionService.cancelSubscription(subscriptionId, reason);
        return ResponseEntity.ok(dto);
    }
    @PostMapping("/activate")
    public ResponseEntity<?> activateSubscription(@RequestParam Long subscriptionId,
                                                  @RequestParam Long paymentId) throws SubscriptionException {

        SubscriptionDTO dto = subscriptionService.activateSubscription(subscriptionId, paymentId);
        return ResponseEntity.ok(dto);
    }

}
