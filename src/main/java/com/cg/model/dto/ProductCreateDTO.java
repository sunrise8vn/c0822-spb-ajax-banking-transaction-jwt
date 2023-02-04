package com.cg.model.dto;

import com.cg.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductCreateDTO {

    private Long id;
    private String title;
    private BigDecimal price;
    private String description;


    public Product toProduct() {
        return new Product()
                .setId(id)
                .setTitle(title)
                .setPrice(price)
                .setDescription(description)
                ;
    }

    @Override
    public String toString() {
        return "ProductCreateDTO{" +
                "title='" + title + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
