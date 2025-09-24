package com.umair.ecommerce.order;

import com.umair.ecommerce.customer.CustomerClient;
import com.umair.ecommerce.exception.BusinessException;
import com.umair.ecommerce.product.ProductClient;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerClient customerClient;
    private final ProductClient productClient;
//    checking the customer->customer microservice
    public Integer createdOrder(@Valid OrderRequest request) {
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order:: No Customer Exist with the provided Id"));

//       purchase the product->product microservice
        this.productClient.purchaseProducts(request.products());

//     persist order

//      persist order line

      return null;
    }
}
