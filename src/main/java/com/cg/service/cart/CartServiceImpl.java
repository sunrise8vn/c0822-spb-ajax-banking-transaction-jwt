package com.cg.service.cart;


import com.cg.model.Cart;
import com.cg.model.CartDetail;
import com.cg.model.Product;
import com.cg.repository.CartDetailRepository;
import com.cg.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CartServiceImpl implements ICartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartDetailRepository cartDetailRepository;

    @Override
    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    @Override
    public Optional<Cart> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Cart> findByCreatedBy(String createdBy) {
        return cartRepository.findByCreatedBy(createdBy);
    }

    @Override
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public void addNewCart(Product product, Long quantity, String createdBy) {
        BigDecimal totalAmount = product.getPrice();
        Cart cart = new Cart();
        cart.setTotalAmount(totalAmount);
        cart.setCreatedBy(createdBy);
        cartRepository.save(cart);

        CartDetail cartDetail = new CartDetail();
        cartDetail.setCart(cart);
        cartDetail.setProduct(product);
        cartDetail.setProductTitle(product.getTitle());
        cartDetail.setProductPrice(product.getPrice());
        cartDetail.setProductQuantity(quantity);
        cartDetail.setProductAmount(product.getPrice());
        cartDetailRepository.save(cartDetail);
    }

    @Override
    public void addCartDetail(Cart cart, Product product, Long quantity) {
        CartDetail cartDetail = new CartDetail();
        cartDetail.setCart(cart);
        cartDetail.setProduct(product);
        cartDetail.setProductTitle(product.getTitle());
        cartDetail.setProductPrice(product.getPrice());
        cartDetail.setProductQuantity(quantity);
        cartDetail.setProductAmount(product.getPrice());
        cartDetailRepository.save(cartDetail);

        BigDecimal totalAmount = cartDetailRepository.getAllProductAmount(cart);
        cart.setTotalAmount(totalAmount);
        cartRepository.save(cart);
    }

    @Override
    public void updateCartDetail(CartDetail cartDetail, Long quantity) {
        Cart cart = cartDetail.getCart();

        BigDecimal productPrice = cartDetail.getProduct().getPrice();
        Long currentQuantity = cartDetail.getProductQuantity();
        long newQuantity = currentQuantity + quantity;

        BigDecimal productAmount = productPrice.multiply(BigDecimal.valueOf(newQuantity));
        cartDetail.setProductPrice(productPrice);
        cartDetail.setProductQuantity(newQuantity);
        cartDetail.setProductAmount(productAmount);
        cartDetailRepository.save(cartDetail);

        BigDecimal totalAmount = cartDetailRepository.getAllProductAmount(cart);
        cart.setTotalAmount(totalAmount);
        cartRepository.save(cart);

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void delete(Cart cart) {

    }

    @Override
    public void remove(Long id) {

    }
}
