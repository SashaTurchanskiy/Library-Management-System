package com.library.service.impl;

import com.library.exception.SubscriptionException;
import com.library.mapper.SubscripMapper;
import com.library.mapper.SubscriptionMapper;
import com.library.modal.Subscription;
import com.library.modal.SubscriptionPlan;
import com.library.modal.User;
import com.library.payload.dto.SubscriptionDTO;
import com.library.repository.SubscriptionPlanRepository;
import com.library.repository.SubscriptionRepository;
import com.library.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subRepository;
    private final SubscripMapper subscripMapper;
    private final UserServiceImpl userService;
    private final SubscriptionPlanRepository subscriptionPlanRepository;

    @Override
    public SubscriptionDTO createSubscription(@RequestBody SubscriptionDTO subscriptionDTO) throws Exception {
        User user = userService.getCurrentUser();
        SubscriptionPlan plan = subscriptionPlanRepository.findById(subscriptionDTO.getPlanId())
                .orElseThrow(() -> new Exception("Subscription plan not found"));

        Subscription subscription = subscripMapper.toEntity(subscriptionDTO, plan, user);
        subscription.initializeFromPlan();
        subscription.setIsActive(false);
        subscription.setUser(user);
        subscription.setPlan(plan);
        Subscription savedSub = subRepository.save(subscription);

        //create Payment todo

        return subscripMapper.toDTO(savedSub);
    }

    @Override
    public SubscriptionDTO getUserActiveSubscription(Long userId) throws Exception {
        User user = userService.getCurrentUser();

        Subscription subscription = subRepository.findActiveSubscriptionByUserId(user.getId(), LocalDate.now())
                .orElseThrow(()-> new SubscriptionException("No active subscription found for user id: " + user.getId()));

        return subscripMapper.toDTO(subscription);
    }

    @Override
    public SubscriptionDTO cancelSubscription(Long subscriptionId, String reason) throws SubscriptionException {
        Subscription subscription = subRepository.findById(subscriptionId)
                .orElseThrow(() -> new SubscriptionException("Subscription not found with id: " + subscriptionId));

        if (!subscription.getIsActive()){
            throw new SubscriptionException("Subscription is already inactive with id: " + subscriptionId);
        }
        //mark subscription as inactive
        subscription.setIsActive(false);
        subscription.setCancelledAt(LocalDateTime.now());
        subscription.setCancelReason(reason != null ? reason : "Cancelled by user");

        Subscription savedSub = subRepository.save(subscription);
        return subscripMapper.toDTO(savedSub);
    }

    @Override
    public SubscriptionDTO activateSubscription(Long subscriptionId, Long paymentId) throws SubscriptionException {
        Subscription subscription = subRepository.findById(subscriptionId)
                .orElseThrow(() -> new SubscriptionException("Subscription not found with id: " + subscriptionId));

        //verify payment todo

        subscription.setIsActive(true);
        subscription.calculateEndDate();

        Subscription savedSub = subRepository.save(subscription);
        return subscripMapper.toDTO(savedSub);
    }

    @Override
    public List<SubscriptionDTO> getAllSubscriptions(Pageable pageable) {
        List<Subscription> subscriptions = subRepository.findAll();
        return subscripMapper.toDTOList(subscriptions);
    }

    @Override
    public void deactivateExpiredSubscriptions() {
        List<Subscription> expiredActiveSubscription = subRepository.findExpiredActiveSubscription(LocalDate.now());

        for (Subscription sub : expiredActiveSubscription) {
            sub.setIsActive(false);
            subRepository.save(sub);
        }
    }
}
