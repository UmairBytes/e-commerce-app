package com.umair.ecommerce.customer;


import jakarta.validation.Valid;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Validated
public class Address {

    private String street;
    private String houseNumber;
    private String zipCode;
}
