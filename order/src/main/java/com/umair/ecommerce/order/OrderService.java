package com.umair.ecommerce.order;

import com.umair.ecommerce.customer.CustomerClient;
import com.umair.ecommerce.exception.BusinessException;
import com.umair.ecommerce.kafka.OrderConfirmation;
import com.umair.ecommerce.kafka.OrderProducer;
import com.umair.ecommerce.orderline.OrderLineRequest;
import com.umair.ecommerce.orderline.OrderLineService;
import com.umair.ecommerce.product.ProductClient;
import com.umair.ecommerce.product.PurchaseRequest;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderMapper mapper;
    private final OrderRepository repository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;

    //    checking the customer->customer microservice
    public Integer createdOrder(@Valid OrderRequest request) {
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order:: No Customer Exist with the provided Id"));

//       purchase the product->product microservice
        var purchasedProducts = this.productClient.purchaseProducts(request.products());

//     persist order
        var order = this.repository.save(mapper.toOrder(request));

        //      persist order line
        for (PurchaseRequest purchaseRequest : request.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }
//      todo  start payment process

        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchasedProducts
                )
        );

//        send the order confirmation--> notification-ms(kafka)

        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return repository.findAll()
                .stream().map(mapper::fromOrder).collect(Collectors.toList());

    }

    public OrderResponse findById(Integer orderId) {
        return repository.findById(orderId)
                .map(mapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException(String.format("No order found with the provided ID: %d", orderId)));
    }
}
