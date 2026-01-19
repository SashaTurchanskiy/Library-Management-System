package com.library.payload.request;

import com.library.domain.BookLoadStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckinRequest {

    @NotNull(message = "Book loan ID is mandatory")
    private Long bookLoadId;

    private BookLoadStatus condition = BookLoadStatus.RETURNED;

    private String notes;
}
