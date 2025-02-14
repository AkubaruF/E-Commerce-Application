package com.app.services;

import com.app.entites.Coupon;
import com.app.payloads.CouponDTO;
import com.app.payloads.CouponResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class CouponServiceImpl implements CouponService {
    @Override
    public CouponResponse getAllCoupon(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        //referensinya mungkin dari getCategories di CategoryService
        return null;
    }

    @Override
    public CouponDTO addCouponToUserCart(String email, Long orderId, Long couponId) {
        //referensinya mungkin dari addProduct di ProductService;
        return null;
    }

    @Override
    public CouponDTO createCoupon(Coupon coupon) {
        //referensinya mungkin dari createCategories di CategoryService
        return null;
    }

    @Override
    public CouponDTO updateCoupon(Coupon coupon, Long couponId) {
        //referensinya mungkin dari updateCategories di CategoryService
        return null;
    }

    @Override
    public String deleteCoupon(Long couponId) {
        //referensinya mungkin dari deleteCategories di CategoryService
        return "";
    }
}
