package com.cg.service.product;

import com.cg.model.Product;
import com.cg.model.dto.ProductCreateDTO;
import com.cg.model.dto.ProductResponseDTO;
import com.cg.service.IGeneralService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IProductService extends IGeneralService<Product> {

    List<ProductResponseDTO> findAllProductResponseDTO();

    Product createWithMedia(ProductCreateDTO productCreateDTO, MultipartFile file);

    Product createWithOutMedia(ProductCreateDTO productCreateDTO);
}
