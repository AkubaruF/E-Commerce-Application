package com.app.services;

import com.app.entites.Brand;
import com.app.entites.Category;
import com.app.payloads.BrandDTO;
import com.app.payloads.BrandResponse;
import com.app.payloads.CategoryDTO;
import com.app.payloads.CategoryResponse;

public interface BrandService {

	BrandDTO createBrand(Brand brand);

	BrandResponse getBrand(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

	BrandDTO updateBrand(Brand brand, Long brandId);

	String deleteBrand(Long brandId);
}
