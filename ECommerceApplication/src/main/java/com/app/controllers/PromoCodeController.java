package com.app.controllers;

import com.app.entites.PromoCode;
import com.app.payloads.*;
import com.app.services.CouponService;
import com.app.services.PromoCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "E-Commerce Application")
public class PromoCodeController {
    @Autowired
    private PromoCodeService codeService;

    @PostMapping("/public/users/{email}/carts/{cartId}/codes")
    public ResponseEntity<CartDTO> addCodeToUserCart(@RequestBody PromoCodeRequest promoCodeRequest, @PathVariable String email, @PathVariable long cartId) {
        CartDTO cart = codeService.addCodeToUserCart(email, cartId, promoCodeRequest);
        return new ResponseEntity<CartDTO>(cart, HttpStatus.FOUND);
    }

    @PostMapping("/admin/code")
    public ResponseEntity<PromoCodeDTO> createCode(@RequestBody PromoCode code) {
        PromoCodeDTO savedPromoCodeDTO = codeService.createCode(code);
        return new ResponseEntity<PromoCodeDTO>(savedPromoCodeDTO, HttpStatus.FOUND);
    }

    @PutMapping("/public/code/{codeId}")
    public ResponseEntity<PromoCodeDTO> updateCode(@RequestBody PromoCode code, @PathVariable Long codeId) {
        PromoCodeDTO savedPromoCodeDTO = codeService.updateCode(code, codeId);
        return new ResponseEntity<PromoCodeDTO>(savedPromoCodeDTO, HttpStatus.FOUND);
    }

    @DeleteMapping("/public/code/{codeId}")
    public ResponseEntity<String> deleteCode(@PathVariable Long codeId) {
        String status = codeService.deleteCode(codeId);
        return new ResponseEntity<String>(status, HttpStatus.FOUND);
    }

}
