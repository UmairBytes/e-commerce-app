package com.umair.ecommerce.order;

import com.umair.ecommerce.product.PurchaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        Integer id,
        String reference,

        @Positive(message = "Order amount should be Positive")
        BigDecimal amount,

        @NotNull(message = "Payment method should be preceised")
        PaymentMethod paymentMethod,
        @NotNull(message = "Customer should be present")
        @NotBlank(message = "Customer should be present")
        @NotEmpty(message = "Customer should be present")
        String customerId,

        @NotEmpty(message = "You should purchase atleast one product")
        List<PurchaseRequest> products
) {
}
