package com.umair.ecommerce.customer;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class CustomerService {

    private final  CustomerRepository  customerRepo;

    public String createCustomer(@Valid CustomerRequest request) {
    }
}
