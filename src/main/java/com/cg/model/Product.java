package com.cg.model;

import com.cg.model.dto.ProductResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
@Accessors(chain = true)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(precision = 10, scale = 0, nullable = false)
    private BigDecimal price;

    private String description;

    public ProductResponseDTO toProductResponseDTO(ProductMedia productMedia) {
        return new ProductResponseDTO()
                .setId(id)
                .setTitle(title)
                .setPrice(price)
                .setDescription(description)
                .setFolderName(productMedia.getFileFolder())
                .setFileName(productMedia.getFileName())
                ;
    }

}
