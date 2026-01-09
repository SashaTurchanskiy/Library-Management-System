package com.library.controller;

import com.library.payload.dto.SubscriptionPlanDTO;
import com.library.payload.response.ApiResponse;
import com.library.service.SubscriptionPlanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subscription-plans")
@RequiredArgsConstructor
public class SubscriptionPlanController {

    private final SubscriptionPlanService subscriptionPlanService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllSubscriptionPlans() {
        return ResponseEntity.ok(subscriptionPlanService.getAllSubscriptionPlans());
    }
    @PostMapping("/admin/create")
    public ResponseEntity<?> createSubscriptionPlan(@Valid @RequestBody SubscriptionPlanDTO planDTO) throws Exception {
        SubscriptionPlanDTO createdPlan = subscriptionPlanService.createSubscriptionPlan(planDTO);
        return ResponseEntity.ok(createdPlan);
    }
    @PutMapping("/admin/update/{planId}")
    public ResponseEntity<?> updateSubscriptionPlan(@PathVariable Long planId,
                                                    @Valid @RequestBody SubscriptionPlanDTO planDTO) throws Exception {
        SubscriptionPlanDTO updatedPlan = subscriptionPlanService.updateSubscriptionPlan(planId, planDTO);
        return ResponseEntity.ok(updatedPlan);
    }
    @DeleteMapping("/delete/{planId}")
    public ResponseEntity<ApiResponse> deleteSubscriptionPlan(@PathVariable Long planId) throws Exception {
        subscriptionPlanService.deleteSubscriptionPlan(planId);
        ApiResponse response = new ApiResponse("Subscription plan deleted successfully", true);
        return ResponseEntity.ok(response);
    }

}
