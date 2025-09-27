package com.umair.ecommerce.kafka;

import com.umair.ecommerce.customer.CustomerResponse;
import com.umair.ecommerce.order.PaymentMethod;
import com.umair.ecommerce.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
