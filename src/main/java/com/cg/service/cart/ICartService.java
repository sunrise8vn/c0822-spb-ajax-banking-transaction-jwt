package com.cg.service.cart;

import com.cg.model.Cart;
import com.cg.model.CartDetail;
import com.cg.model.Product;
import com.cg.service.IGeneralService;

import java.util.Optional;

public interface ICartService extends IGeneralService<Cart> {

    Optional<Cart> findByCreatedBy(String createdBy);

    void addNewCart(Product product, Long quantity, String createdBy);

    void addCartDetail(Cart cart, Product product, Long quantity);

    void updateCartDetail(CartDetail cartDetail, Long quantity);
}
