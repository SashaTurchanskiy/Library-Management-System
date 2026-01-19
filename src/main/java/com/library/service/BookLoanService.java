package com.library.service;

import com.library.domain.BookLoanStatus;
import com.library.payload.dto.BookLoanDTO;
import com.library.payload.request.BookLoanSearchRequest;
import com.library.payload.request.CheckinRequest;
import com.library.payload.request.CheckoutRequest;
import com.library.payload.request.RenewalRequest;
import com.library.payload.response.PageResponse;

public interface BookLoanService {

    BookLoanDTO checkoutBook(CheckoutRequest checkoutRequest);

    BookLoanDTO checkoutBookForUser(Long userId, CheckoutRequest checkoutRequest);

    BookLoanDTO checkInBook(CheckinRequest checkinRequest);

    BookLoanDTO renewCheckout(RenewalRequest renewalRequest);

    PageResponse<BookLoanDTO> getMyBookLoans(BookLoanStatus status, int page, int size);

    PageResponse<BookLoanDTO> getBookLoans(BookLoanSearchRequest request);

    int updateOverdueBookLoan();
}
