package com.app.services;

import com.app.entites.Cart;
import com.app.entites.PromoCode;
import com.app.exceptions.APIException;
import com.app.exceptions.ResourceNotFoundException;
import com.app.payloads.CartDTO;
import com.app.payloads.PromoCodeDTO;
import com.app.payloads.PromoCodeRequest;
import com.app.repositories.CartRepo;
import com.app.repositories.PromoCodeRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Transactional
@Service
public class PromoCodeServiceImpl implements  PromoCodeService{
    @Autowired
    private PromoCodeRepo codeRepo;

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CartDTO addCodeToUserCart(String email, long cartId, PromoCodeRequest promoCodeRequest) {
        Cart cart = cartRepo.findCartByEmailAndCartId(email, cartId);

        if (cart == null) {
            throw new ResourceNotFoundException("Cart", "cartId", cartId);
        }

        String promoCode = promoCodeRequest.getPromoCode();
        PromoCode code = codeRepo.findByPromoCode(promoCode);

        if (code == null) {
            throw new ResourceNotFoundException("Code", "promoCode", promoCode);
        }

        if (cart.getCode() != null && cart.getCode().getPromoId().equals(code.getPromoId())) {
            throw new APIException("Code is already applied to the cart");
        }

        cart.setCode(code);

        double amount = cart.getTotalPrice();
        double discount = (code.getDiscountPercentage() / 100) * amount;
        amount -= discount;
        cart.setTotalPrice(amount);

        cartRepo.save(cart);
        return modelMapper.map(cart, CartDTO.class);
    }

    @Override
    public PromoCodeDTO createCode(PromoCode code) {
        PromoCode savedCode = codeRepo.findByPromoCode(code.getPromoCode());

        if (savedCode != null) {
            throw new APIException("Code with the promocode '" + code.getPromoCode() + "' already exists !!!");
        }

        String promoCode = code.getPromoCode();

        if (promoCode == null || promoCode.isBlank()) {
            String generatedCode = generateRandomCode(10);
            code.setPromoCode(generatedCode);
        }

        savedCode = codeRepo.save(code);

        return modelMapper.map(savedCode, PromoCodeDTO.class);
    }

    @Override
    public PromoCodeDTO updateCode(PromoCode code, Long codeId) {
        PromoCode savedCode = codeRepo.findById(codeId)
                .orElseThrow(() -> new ResourceNotFoundException("Code", "codeId", codeId));



        savedCode.setPromoCode(code.getPromoCode());
        savedCode.setQuota(code.getQuota());
        savedCode.setDiscountPercentage(code.getDiscountPercentage());

        savedCode = codeRepo.save(savedCode);

        return modelMapper.map(savedCode, PromoCodeDTO.class);
    }

    @Override
    public String deleteCode(Long codeId) {
        PromoCode coupon = codeRepo.findById(codeId)
                .orElseThrow(() -> new ResourceNotFoundException("Code", "codeId", codeId));

        codeRepo.delete(coupon);

        return "Code with id " + codeId + " is deleted successfully";
    }

    private String generateRandomCode(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder promoCode = new StringBuilder();

        for (int i = 0; i < length; i++) {
            promoCode.append(characters.charAt(random.nextInt(characters.length())));
        }

        return promoCode.toString();
    }

}
