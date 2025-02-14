package com.app.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.payloads.CategoryResponse;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "E-Commerce Application")
public class CouponController {
    @GetMapping("/public/coupon")
    public ResponseEntity<String> getAllCoupon(){
        // referensi nya dari CategoryController getAllCategories();
        // String nya ganti Response Entity(termasuk return type) misal: CouponDTO, CouponResponse
        return new ResponseEntity<String>("SUCCESS", HttpStatus.FOUND);
    }

    @PostMapping("/public/users/{email}/orders/{orderId}/coupon/{couponId}")
    public ResponseEntity<String> addCouponToUserCart(){
        //referensinya dari OrderController orderProducts();
        // String nya ganti Response Entity(termasuk return type) misal: CouponDTO, CouponResponse
        return new ResponseEntity<String>("SUCCESS", HttpStatus.FOUND);
    }

    @PostMapping("/public/coupon")
    public ResponseEntity<String> createCoupon(){
        // referensi dari CategoryController createCategory();
        // String nya ganti Response Entity(termasuk return type) misal: CouponDTO, CouponResponse
        return new ResponseEntity<String>("SUCCESS", HttpStatus.FOUND);
    }

    @PutMapping("/public/coupon/{couponId}")
    public ResponseEntity<String> updateCoupon(){
        // referensi dari CategoryController updateCategory()
        // String nya ganti Response Entity(termasuk return type) misal: CouponDTO, CouponResponse
        return new ResponseEntity<String>("SUCCESS", HttpStatus.FOUND);
    }

    @DeleteMapping("/public/coupon/{couponId}")
    public ResponseEntity<String> deleteCoupon(){
        // referensi dari CategoryController deleteCategory()
        // String nya ganti Response Entity(termasuk return type) misal: CouponDTO, CouponResponse
        return new ResponseEntity<String>("SUCCESS", HttpStatus.FOUND);
    }


}


