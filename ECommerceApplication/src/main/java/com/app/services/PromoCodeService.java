package com.app.services;

import com.app.payloads.CartDTO;
import com.app.payloads.PromoCodeDTO;
import com.app.entites.PromoCode;
import com.app.payloads.PromoCodeRequest;


public interface PromoCodeService {
    CartDTO addCodeToUserCart(String email, long cartId, PromoCodeRequest promoCodeRequest);
    PromoCodeDTO createCode(PromoCode code);
    PromoCodeDTO updateCode(PromoCode code, Long codeId);
    String deleteCode(Long codeId);
}

