package com.cg.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cart_detail")
public class CartDetail extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_title", nullable = false)
    private String productTitle;

    @Column(name = "product_price", precision = 10, scale = 0, nullable = false)
    private BigDecimal productPrice;

    @Column(name = "product_quantity", nullable = false)
    private Long productQuantity;

    @Column(name = "product_amount", precision = 10, scale = 0, nullable = false)
    private BigDecimal productAmount;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "id", nullable = false)
    private Cart cart;
}
