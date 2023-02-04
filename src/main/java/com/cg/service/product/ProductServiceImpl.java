package com.cg.service.product;

import com.cg.exception.DataInputException;
import com.cg.model.Product;
import com.cg.model.ProductMedia;
import com.cg.model.dto.ProductCreateDTO;
import com.cg.model.dto.ProductResponseDTO;
import com.cg.repository.ProductMediaRepository;
import com.cg.repository.ProductRepository;
import com.cg.service.uploadMedia.UploadService;
import com.cg.utils.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
@Transactional
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMediaRepository productMediaRepository;

    @Autowired
    private UploadUtils uploadUtils;

    @Autowired
    private UploadService uploadService;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<ProductResponseDTO> findAllProductResponseDTO() {
        return productRepository.findAllProductResponseDTO();
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product createWithMedia(ProductCreateDTO productCreateDTO, MultipartFile file) {
        Product product = productCreateDTO.toProduct();
        productRepository.save(product);

        ProductMedia productMedia = new ProductMedia();
        productMedia.setProduct(product);
        productMedia.setFileType(file.getContentType());
        productMedia.setFileFolder(uploadUtils.IMAGE_UPLOAD_FOLDER);
        productMediaRepository.save(productMedia);

        uploadAndSaveProductImage(file, productMedia);

        return product;
    }

    private void uploadAndSaveProductImage(MultipartFile file, ProductMedia productMedia) {
        try {
            Map uploadResult = uploadService.uploadImage(file, uploadUtils.buildImageUploadParams(productMedia));
            String fileUrl = (String) uploadResult.get("secure_url");
            String fileFormat = (String) uploadResult.get("format");

            productMedia.setFileName(productMedia.getId() + "." + fileFormat);
            productMedia.setFileUrl(fileUrl);
            productMedia.setCloudId(productMedia.getFileFolder() + "/" + productMedia.getId());
            productMediaRepository.save(productMedia);

        } catch (IOException e) {
            e.printStackTrace();
            throw new DataInputException("Upload hình ảnh thất bại");
        }
    }

    @Override
    public Product createWithOutMedia(ProductCreateDTO productCreateDTO) {
        System.out.println("createWithOutMedia");
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void delete(Product product) {

    }

    @Override
    public void remove(Long id) {

    }
}
