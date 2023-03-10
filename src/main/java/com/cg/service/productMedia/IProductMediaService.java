package com.cg.service.productMedia;

import com.cg.model.Product;
import com.cg.model.ProductMedia;
import com.cg.service.IGeneralService;

public interface IProductMediaService extends IGeneralService<ProductMedia> {

    ProductMedia findByProduct(Product product);
}
