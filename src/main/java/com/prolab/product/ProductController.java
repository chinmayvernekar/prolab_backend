package com.prolab.product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping("/create")
	public ResponseEntity<Product> createProduct(@RequestParam("productName") String productName,
			@RequestParam("productModel") String productModel, @RequestParam("productMake") String productMake,
			@RequestParam("qty") String qty, @RequestParam("unitPrice") String unitPrice,
			@RequestParam("offerPrice") String offerPrice, @RequestParam("supply_line_1") String supply_line_1,
			@RequestParam("supply_line_2") String supply_line_2, @RequestParam("supply_line_3") String supply_line_3,
			@RequestParam( value = "image_1" , required = false) MultipartFile image_1,
			@RequestParam( value = "image_2" , required = false) MultipartFile image_2,
			@RequestParam( value = "image_3" , required = false) MultipartFile image_3, 
			@RequestParam( value = "image_4" , required = false) MultipartFile image_4) {
		return productService.createProduct(productName, productModel, productMake, qty, unitPrice, offerPrice,
				supply_line_1, supply_line_2, supply_line_3, image_1, image_2, image_3, image_4);
	}
	
	@GetMapping(value = "/allProducts")
	public ResponseEntity<List<Product>> getAllProducts() {
		return productService.getAllProducts();
	}
	
	@GetMapping("/getProduct/{productId}")
	public ResponseEntity<Optional<Product>> getProductById(@PathVariable Integer productId) {
		return productService.getProductById(productId);
	}
	
	@GetMapping("/searchByProductname/{productName}")
	public ResponseEntity<List<Product>> getProductsByProductname(@PathVariable String productName) {
		return productService.getProductsByProductname(productName);
	}
}
