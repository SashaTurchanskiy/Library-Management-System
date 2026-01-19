package com.library.mapper;

import com.library.modal.Payment;
import com.library.payload.dto.PaymentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "subscriptionId", target = "subscription.id")
    Payment toEntity(PaymentDTO paymentDTO);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "subscription.id", target = "subscriptionId")
    @Mapping(source = "user.fullName", target = "userName")
    @Mapping(source = "user.email", target = "userEmail")
    PaymentDTO toDTO(Payment payment);

}
