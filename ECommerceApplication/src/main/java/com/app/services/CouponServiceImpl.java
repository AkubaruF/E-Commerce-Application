package com.app.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.app.entites.Cart;
import com.app.entites.Coupon;
import com.app.exceptions.APIException;
import com.app.exceptions.ResourceNotFoundException;
import com.app.payloads.CartDTO;
import com.app.payloads.CouponDTO;
import com.app.payloads.CouponResponse;
import com.app.repositories.CartRepo;
import com.app.repositories.CouponRepo;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class CouponServiceImpl implements CouponService {

  @Autowired
  private CouponRepo couponRepo;

  @Autowired
  private CartRepo cartRepo;

  @Autowired
  private ModelMapper modelMapper;

  @Override
  public CouponResponse getAllCoupon(Integer pageNumber, Integer pageSize,
      String sortBy, String sortOrder) {
    Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc")
        ? Sort.by(sortBy).ascending()
        : Sort.by(sortBy).descending();

    Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);

    Page<Coupon> pageCoupons = couponRepo.findAll(pageDetails);

    List<Coupon> coupons = pageCoupons.getContent();

    if (coupons.size() == 0) {
      throw new APIException("No coupon is created till now");
    }

    List<CouponDTO> couponDTOs = coupons.stream()
        .map(coupon -> modelMapper.map(coupon, CouponDTO.class))
        .collect(Collectors.toList());

    CouponResponse couponResponse = new CouponResponse();

    couponResponse.setContent(couponDTOs);
    couponResponse.setPageNumber(pageCoupons.getNumber());
    couponResponse.setPageSize(pageCoupons.getSize());
    couponResponse.setTotalElements(pageCoupons.getTotalElements());
    couponResponse.setTotalPages(pageCoupons.getTotalPages());
    couponResponse.setLastPage(pageCoupons.isLast());

    return couponResponse;
  }

  @Override
  public CartDTO addCouponToUserCart(String email, long cartId, Long couponId) {
    Cart cart = cartRepo.findCartByEmailAndCartId(email, cartId);

    if (cart == null) {
      throw new ResourceNotFoundException("Cart", "cartId", cartId);
    }

    Coupon coupon = couponRepo.findById(couponId)
        .orElseThrow(() -> new ResourceNotFoundException("Coupon", "couponId", couponId));

    if (cart.getCoupon() != null && cart.getCoupon().getCouponId().equals(couponId)) {
      throw new APIException("Coupon is already applied to the cart");
    }

    cart.setCoupon(coupon);
    // calculate the total amount after applying the coupon
    double totalAmount = cart.getTotalPrice();
    double discount = (coupon.getDiscountPercentage() / 100) * totalAmount;
    totalAmount -= discount;
    cart.setTotalPrice(totalAmount);

    cartRepo.save(cart);

    return modelMapper.map(cart, CartDTO.class);
  }

  @Override
  public CouponDTO createCoupon(Coupon coupon) {
    Coupon savedCoupon = couponRepo.findByTitle(coupon.getTitle());

    if (savedCoupon != null) {
      throw new APIException("Coupon with the title '" + coupon.getTitle() + "' already exists !!!");
    }

    savedCoupon = couponRepo.save(coupon);

    return modelMapper.map(savedCoupon, CouponDTO.class);
  }

  @Override
  public CouponDTO updateCoupon(Coupon coupon, Long couponId) {

    Coupon savedCoupon = couponRepo.findById(couponId)
        .orElseThrow(() -> new ResourceNotFoundException("Coupon", "couponId", couponId));

    savedCoupon.setTitle(coupon.getTitle());
    savedCoupon.setDescription(coupon.getDescription());
    savedCoupon.setDiscountPercentage(coupon.getDiscountPercentage());

    savedCoupon = couponRepo.save(savedCoupon);

    return modelMapper.map(savedCoupon, CouponDTO.class);
  }

  @Override
  public String deleteCoupon(Long couponId) {
    Coupon coupon = couponRepo.findById(couponId)
        .orElseThrow(() -> new ResourceNotFoundException("Coupon", "couponId", couponId));

    couponRepo.delete(coupon);

    return "Coupon with id " + couponId + " is deleted successfully";
  }
}
