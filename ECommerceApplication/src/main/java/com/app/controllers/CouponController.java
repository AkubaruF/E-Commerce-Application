package com.app.controllers;

import com.app.config.AppConstants;
import com.app.entites.Category;
import com.app.entites.Coupon;
import com.app.payloads.*;
import com.app.services.CouponService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "E-Commerce Application")
public class CouponController {
    @Autowired
    private CouponService couponService;

    @GetMapping("/public/coupon")
    public ResponseEntity<CouponResponse> getAllCoupon(
            @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstants.SORT_CATEGORIES_BY, required = false) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder){

        CouponResponse couponResponse = couponService.getAllCoupon(pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<CouponResponse>(couponResponse, HttpStatus.FOUND);
    }

    @PostMapping("/public/users/{email}/orders/{orderId}/coupon/{couponId}")
    public ResponseEntity<CouponDTO> addCouponToUserCart(@PathVariable String email, @PathVariable Long orderId, @PathVariable Long couponId){
        CouponDTO coupon = couponService.addCouponToUserCart(email, orderId, couponId);
        return new ResponseEntity<CouponDTO>(coupon, HttpStatus.FOUND);
    }

    @PostMapping("/public/coupon")
    public ResponseEntity<CouponDTO> createCoupon(@Valid @RequestBody Coupon coupon){
        CouponDTO savedCouponDTO = couponService.createCoupon(coupon);
        return new ResponseEntity<CouponDTO>(savedCouponDTO, HttpStatus.FOUND);
    }

    @PutMapping("/public/coupon/{couponId}")
    public ResponseEntity<CouponDTO> updateCoupon(@Valid @RequestBody Coupon coupon, @PathVariable Long couponId){
        CouponDTO savedCouponDTO = couponService.updateCoupon(coupon, couponId);
        return new ResponseEntity<CouponDTO>(savedCouponDTO, HttpStatus.FOUND);
    }

    @DeleteMapping("/public/coupon/{couponId}")
    public ResponseEntity<String> deleteCoupon(@PathVariable Long couponId){
        String status = couponService.deleteCoupon(couponId);
        return new ResponseEntity<String>(status, HttpStatus.FOUND);
    }

}


