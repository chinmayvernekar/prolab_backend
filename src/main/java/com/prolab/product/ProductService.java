package com.prolab.product;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService {

	public ResponseEntity<Product> createProduct(String productName, String productModel, String productmake, String qty,
			String unitPrice, String offerPrice, String supply_line_1, String supply_line_2, String supply_line_3,
			MultipartFile image_1, MultipartFile image_2, MultipartFile image_3, MultipartFile image_4);

	public Product updateProduct(Product updateProduct);
	
	public ResponseEntity<List<Product>> getAllProducts();
	
	public ResponseEntity<Optional<Product>> getProductById(Integer productId);
	
	public ResponseEntity<List<Product>> getProductsByProductname(String productName);
}
