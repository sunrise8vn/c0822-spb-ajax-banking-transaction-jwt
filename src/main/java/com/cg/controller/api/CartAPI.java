package com.cg.controller.api;


import com.cg.exception.DataInputException;
import com.cg.model.Cart;
import com.cg.model.CartDetail;
import com.cg.model.Product;
import com.cg.model.dto.CartRequestDTO;
import com.cg.service.cart.ICartService;
import com.cg.service.cartDetail.ICartDetailService;
import com.cg.service.product.IProductService;
import com.cg.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/carts")
public class CartAPI {

    @Autowired
    private IProductService productService;

    @Autowired
    private ICartService cartService;

    @Autowired
    private ICartDetailService cartDetailService;

    @Autowired
    private AppUtils appUtils;

    @PostMapping("/add-to-cart")
    public ResponseEntity<?> addCart(@RequestBody CartRequestDTO cartRequestDTO) {

        String username = appUtils.getUserName();

        Long productId = cartRequestDTO.getProductId();

        Optional<Product> productOptional = productService.findById(productId);

        if (!productOptional.isPresent()) {
            throw new DataInputException("Sản phẩm không hợp lệ");
        }

        Product product = productOptional.get();
        Long quantity = cartRequestDTO.getQuantity();

        Optional<Cart> cartOptional = cartService.findByCreatedBy(username);

        if (!cartOptional.isPresent()) {
            cartService.addNewCart(product, quantity, username);
        }
        else {
            Optional<CartDetail> cartDetailOptional = cartDetailService.findByProduct(product);

            if (!cartDetailOptional.isPresent()) {
                cartService.addCartDetail(cartOptional.get(), product, quantity);
            }
            else {
                cartService.updateCartDetail(cartDetailOptional.get(), quantity);
            }
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
