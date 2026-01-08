package com.library.service.impl;

import com.library.mapper.SubscriptionMapper;
import com.library.modal.SubscriptionPlan;
import com.library.modal.User;
import com.library.payload.dto.SubscriptionPlanDTO;
import com.library.repository.SubscriptionPlanRepository;
import com.library.service.SubscriptionPlanService;
import com.library.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionPlanServiceImpl implements SubscriptionPlanService {

    private final SubscriptionPlanRepository planRepository;
    @Qualifier("subscriptionMapperImpl")
    private final SubscriptionMapper planMapper;
    private final UserService userService;

    @Override
    public SubscriptionPlanDTO createSubscriptionPlan(SubscriptionPlanDTO planDto) throws Exception {
        if (planRepository.existsByPlanCode(planDto.getPlanCode())){
            throw new Exception("plan code already exists");
        }
        SubscriptionPlan plan = planMapper.toEntity(planDto);

        User currentUser = userService.getCurrentUser();
        plan.setCreatedBy(currentUser.getFullName());
        plan.setUpdatedBy(currentUser.getFullName());
        SubscriptionPlan savedPlan =  planRepository.save(plan);
        return planMapper.toDTO(savedPlan);
    }

    @Override
    public SubscriptionPlanDTO updateSubscriptionPlan(Long planId, SubscriptionPlanDTO planDto) throws Exception {
        SubscriptionPlan existingPlan = planRepository.findById(planId).orElseThrow(
                ()-> new Exception("Plan not found"));

        planMapper.updateEntityFromDTO(planDto, existingPlan);

        User currentUser = userService.getCurrentUser();
        existingPlan.setUpdatedBy(currentUser.getFullName());
        SubscriptionPlan updatedPlan = planRepository.save(existingPlan);

        return planMapper.toDTO(updatedPlan);
    }

    @Override
    public void deleteSubscriptionPlan(Long planId) throws Exception {
        SubscriptionPlan delete = planRepository.findById(planId).orElseThrow(
                ()-> new Exception("Plan not found"));
        planRepository.delete(delete);

    }

    @Override
    public List<SubscriptionPlanDTO> getAllSubscriptionPlans() {
        List<SubscriptionPlan> planList = planRepository.findAll();
        return planList.stream()
                .map(planMapper::toDTO).toList();
    }

    @Override
    public SubscriptionPlanDTO getSubscriptionPlanById(Long planId) throws Exception {
        SubscriptionPlan plan = planRepository.findById(planId).orElseThrow(
                ()-> new Exception("Plan not found"));
        return planMapper.toDTO(plan);
    }
}
