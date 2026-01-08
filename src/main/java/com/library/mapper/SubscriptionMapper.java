package com.library.mapper;

import com.library.modal.SubscriptionPlan;
import com.library.payload.dto.SubscriptionPlanDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {

    SubscriptionPlanDTO toDTO(SubscriptionPlan subscriptionPlan);

    //@Mapping(target = "currency", ignore = true)
    SubscriptionPlan toEntity(SubscriptionPlanDTO subscriptionPlanDTO);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDTO(SubscriptionPlanDTO planDTO, @MappingTarget SubscriptionPlan subscriptionPlan);

}
