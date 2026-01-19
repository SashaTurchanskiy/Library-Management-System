package com.library.payload.dto;

import com.library.domain.BookLoadStatus;
import com.library.domain.BookLoadType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookLoanDTO {

    private Long id;
    private Long userId;
    private String userName;
    private String userEmail;
    private Long bookId;
    private String bookTitle;
    private String bookIsbn;
    private String bookAuthor;
    private String bookCoverImage;
    private BookLoadType type;
    private BookLoadStatus status;
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private Long remainingDate;
    private LocalDate returnDate;
    private Integer renewalCount;
    private Integer maxRenewals;
    private BigDecimal fineAmount;
    private Boolean finePaid;
    private String notes;
    private Boolean isOverdue;
    private Integer overdueDays;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
