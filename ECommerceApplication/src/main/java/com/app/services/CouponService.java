package com.app.services;

import com.app.entites.Coupon;
import com.app.payloads.CartDTO;
import com.app.payloads.CouponDTO;
import com.app.payloads.CouponResponse;

public interface CouponService {
    CouponResponse getAllCoupon(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    CartDTO addCouponToUserCart(String email, long cartId, Long couponId);

    CouponDTO createCoupon(Coupon coupon);

    CouponDTO updateCoupon(Coupon coupon, Long couponId);

    String deleteCoupon(Long couponId);
}
