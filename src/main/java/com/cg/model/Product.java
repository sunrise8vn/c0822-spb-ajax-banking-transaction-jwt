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
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(precision = 10, scale = 0, nullable = false)
    private BigDecimal price;

    private String description;

    @OneToOne
    @JoinColumn(name = "product_media_id", referencedColumnName = "id", nullable = false)
    private ProductMedia productMedia;
}
