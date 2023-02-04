package com.cg.repository;


import com.cg.model.Product;
import com.cg.model.dto.ProductResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT NEW com.cg.model.dto.ProductResponseDTO (" +
                "pm.product.id, " +
                "pm.product.title, " +
                "pm.product.price, " +
                "pm.product.description, " +
                "pm.fileFolder, " +
                "pm.fileName " +
            ") " +
            "FROM ProductMedia AS pm"
    )
    List<ProductResponseDTO> findAllProductResponseDTO();
}
