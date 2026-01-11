package com.library.mapper;

import com.library.modal.Subscription;
import com.library.payload.dto.SubscriptionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubsripMapper {

    SubscriptionDTO toDTO(Subscription subscription);

    Subscription toEntity(SubscriptionDTO subscriptionDTO);

    List<SubscriptionDTO> toDTOList(List<Subscription> subscriptionList);

    void updateEntityFromDTO(SubscriptionDTO subscriptionDTO, @MappingTarget Subscription subscription);

}
