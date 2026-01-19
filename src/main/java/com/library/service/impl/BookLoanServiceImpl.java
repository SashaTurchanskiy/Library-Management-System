package com.library.service.impl;

import com.library.domain.BookLoanStatus;
import com.library.payload.dto.BookLoanDTO;
import com.library.payload.request.BookLoanSearchRequest;
import com.library.payload.request.CheckinRequest;
import com.library.payload.request.CheckoutRequest;
import com.library.payload.request.RenewalRequest;
import com.library.payload.response.PageResponse;
import com.library.repository.BookLoanRepository;
import com.library.service.BookLoanService;
import com.library.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookLoanServiceImpl implements BookLoanService {

    private final BookLoanRepository bookLoanRepository;
    private final UserService userService;

    @Override
    public BookLoanDTO checkoutBook(CheckoutRequest checkoutRequest) {
        return null;
    }

    @Override
    public BookLoanDTO checkoutBookForUser(Long userId, CheckoutRequest checkoutRequest) {
        return null;
    }

    @Override
    public BookLoanDTO checkInBook(CheckinRequest checkinRequest) {
        return null;
    }

    @Override
    public BookLoanDTO renewCheckout(RenewalRequest renewalRequest) {
        return null;
    }

    @Override
    public PageResponse<BookLoanDTO> getMyBookLoans(BookLoanStatus status, int page, int size) {
        return null;
    }

    @Override
    public PageResponse<BookLoanDTO> getBookLoans(BookLoanSearchRequest request) {
        return null;
    }

    @Override
    public int updateOverdueBookLoan() {
        return 0;
    }
}
