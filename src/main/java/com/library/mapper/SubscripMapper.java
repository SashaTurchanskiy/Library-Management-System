package com.library.mapper;

import com.library.modal.Subscription;
import com.library.modal.SubscriptionPlan;
import com.library.modal.User;
import com.library.payload.dto.SubscriptionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubscripMapper {

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "userName", source = "user.fullName")
    @Mapping(target = "userEmail", source = "user.email")
    @Mapping(target = "planId", source = "plan.id")
    @Mapping(target = "planName", source = "plan.name")
    @Mapping(target = "planCode", source = "plan.planCode")
    SubscriptionDTO toDTO(Subscription subscription);

    Subscription toEntity(SubscriptionDTO subscriptionDTO);

    List<SubscriptionDTO> toDTOList(List<Subscription> subscriptionList);

    void updateEntityFromDTO(SubscriptionDTO subscriptionDTO, @MappingTarget Subscription subscription);

    // ✅ Додатковий метод для мапінгу з трьома параметрами
     default Subscription toEntity(SubscriptionDTO dto, SubscriptionPlan plan, User user) {
        if (dto == null) {
            return null;
        }

        Subscription subscription = toEntity(dto); // базовий мапінг DTO → Subscription
        subscription.setUser(user);
        subscription.setPlan(plan);
        return subscription;
    }
}

