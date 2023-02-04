package com.cg.service.cartDetail;

import com.cg.model.CartDetail;
import com.cg.model.Product;
import com.cg.service.IGeneralService;

import java.util.Optional;

public interface ICartDetailService extends IGeneralService<CartDetail> {

    Optional<CartDetail> findByProduct(Product product);
}
