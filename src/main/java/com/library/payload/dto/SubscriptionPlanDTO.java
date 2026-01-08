package com.library.payload.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubscriptionPlanDTO {

    private Long id;

    @NotBlank(message = "Plan code is mandatory")
    private String planCode;

    @NotBlank(message = "Name is mandatory")
    private String name;

    private String description;

    @NotNull(message = "Duration in days is mandatory")
    @Positive(message = "Duration must be positive")
    private Integer durationDays;

    @NotNull(message = "Price is mandatory")
    @Positive(message = "Price must be positive")
    private Long price;

    private String currency;

    @NotNull(message = "Maximum books allowed is mandatory")
    @Positive(message = "Maximum books allowed must be positive")
    private Integer maxBooksAllowed;

    @NotNull(message = "Maximum days per book is mandatory")
    @Positive(message = "Maximum days per book must be positive")
    private Integer maxDaysPerBook;

    private Integer displayOrder;
    private Boolean isActive;
    private Boolean isFeatured;
    private String badgeText;
    private String adminNotes;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;

}
