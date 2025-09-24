package com.umair.ecommerce.orderline;


import com.umair.ecommerce.order.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity

public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    @ManyToOne
    @JoinColumn(name = "order id")
    private Order order;

    private Integer productId;
    private double quantity;
}
