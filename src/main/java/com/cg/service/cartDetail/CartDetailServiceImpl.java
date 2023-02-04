package com.cg.service.cartDetail;

import com.cg.model.CartDetail;
import com.cg.model.Product;
import com.cg.repository.CartDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class CartDetailServiceImpl implements ICartDetailService {

    @Autowired
    private CartDetailRepository cartDetailRepository;

    @Override
    public List<CartDetail> findAll() {
        return null;
    }

    @Override
    public Optional<CartDetail> findById(Long id) {
        return cartDetailRepository.findById(id);
    }

    @Override
    public Optional<CartDetail> findByProduct(Product product) {
        return cartDetailRepository.findByProduct(product);
    }

    @Override
    public CartDetail save(CartDetail cartDetail) {
        return cartDetailRepository.save(cartDetail);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void delete(CartDetail cartDetail) {

    }

    @Override
    public void remove(Long id) {

    }
}
