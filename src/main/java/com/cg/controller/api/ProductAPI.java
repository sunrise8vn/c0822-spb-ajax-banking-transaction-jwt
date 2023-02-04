package com.cg.controller.api;


import com.cg.model.Product;
import com.cg.model.ProductMedia;
import com.cg.model.dto.ProductCreateDTO;
import com.cg.model.dto.ProductResponseDTO;
import com.cg.service.product.IProductService;
import com.cg.service.productMedia.IProductMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductAPI {

    @Autowired
    private IProductService productService;

    @Autowired
    private IProductMediaService productMediaService;

    @GetMapping
    public ResponseEntity<?> getAll() {

        List<ProductResponseDTO> productResponseDTOS = productService.findAllProductResponseDTO();

        return new ResponseEntity<>(productResponseDTOS, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<?> create(ProductCreateDTO productCreateDTO, MultipartFile file) {

        if (file == null) {
            Product product = productService.createWithOutMedia(productCreateDTO);

            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        else {
            Product product = productService.createWithMedia(productCreateDTO, file);

            ProductMedia productMedia = productMediaService.findByProduct(product);

            ProductResponseDTO productResponseDTO = product.toProductResponseDTO(productMedia);

            return new ResponseEntity<>(productResponseDTO, HttpStatus.CREATED);
        }
    }
}
