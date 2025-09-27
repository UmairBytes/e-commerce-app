package com.umair.ecommerce.orderline;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository repository;
    private final OrderLineMapper mapper;
    public void saveOrderLine(OrderLineRequest request) {
        var order = mapper.toOrderLine(request);
    }
}
