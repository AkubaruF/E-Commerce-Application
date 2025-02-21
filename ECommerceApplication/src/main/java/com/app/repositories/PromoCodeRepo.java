package com.app.repositories;

import com.app.entites.PromoCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromoCodeRepo extends JpaRepository<PromoCode, Long> {
    PromoCode findByPromoCode(String promoCode);
}
