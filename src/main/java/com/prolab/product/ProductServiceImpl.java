package com.prolab.product;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class ProductServiceImpl implements ProductService {

	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	ProductRepo productRepo;

	@Override
	public ResponseEntity<Product> createProduct(String productName, String productModel, String productmake, String qty,
			String unitPrice, String offerPrice, String supply_line_1, String supply_line_2, String supply_line_3,
			MultipartFile image_1, MultipartFile image_2, MultipartFile image_3, MultipartFile image_4) {
		// TODO Auto-generated method stub
		Product product = new Product();
		try {
			logger.info("Create Product Start ...................................................");
			product.setProductName(productName);
			product.setProductModel(productModel);
			product.setProductMake(productmake);
			product.setQty(qty);
			product.setUnitPrice(unitPrice);
			product.setOfferPrice(offerPrice);
			product.setSupply_line_1(supply_line_1);
			product.setSupply_line_2(supply_line_2);
			product.setSupply_line_3(supply_line_3);
			if (!image_1.isEmpty()) {
//				product.setImage_1_name(image_1.getOriginalFilename());
//				product.setImage_1(image_1.getBytes());
				product.setImage_1_path(saveFileToExternalLocationOnServer(image_1));
			}
			if (!image_2.isEmpty()) {
//				product.setImage_2_name(image_2.getOriginalFilename());
//				product.setImage_2(image_2.getBytes());
				product.setImage_2_path(saveFileToExternalLocationOnServer(image_2));
			}
			if (!image_3.isEmpty()) {
//				product.setImage_3_name(image_3.getOriginalFilename());
//				product.setImage_3(image_3.getBytes());
				product.setImage_3_path(saveFileToExternalLocationOnServer(image_3));
			}
			if (!image_4.isEmpty()) {
//				product.setImage_4_name(image_4.getOriginalFilename());
//				product.setImage_4(image_4.getBytes());
				product.setImage_4_path(saveFileToExternalLocationOnServer(image_4));
			}
			logger.info("Create Product End .....................................................");
			productRepo.save(product);
		} catch (Exception e) {
			// TODO: handle exception
			ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(product);
	}

	@Override
	public Product updateProduct(Product updateProduct) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<List<Product>> getAllProducts() {
		// TODO Auto-generated method stub
		List<Product> products = null;
		try {
			products = productRepo.findAll();
			
		} catch (Exception e) {
			// TODO: handle exception
			ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
		}
		return ResponseEntity.status(HttpStatus.OK).body(products);
	}

	public static String saveFileToExternalLocationOnServer(MultipartFile file) throws IOException {
		byte[] fileBytes = file.getBytes();
		Path currentDirectory = Paths.get("").toAbsolutePath();
		// Go back to the parent directory
        Path parentDirectory = currentDirectory.getParent();
		String imageFolderPath = parentDirectory + File.separator + "image";
        File imageFolder = new File(imageFolderPath);
        if (!imageFolder.exists()) {
            imageFolder.mkdirs();
        }
        // Create a new File object
        File imageFile = new File(imageFolder,file.getOriginalFilename());
        // Write the byte array to the File object
        try (FileOutputStream fos = new FileOutputStream(imageFile)) {
            fos.write(fileBytes);
        }
        return imageFile.getAbsolutePath();
	}


	@Override
	public ResponseEntity<Optional<Product>> getProductById(Integer productId) {
		// TODO Auto-generated method stub
		Optional<Product> product = productRepo.findById(productId);
		return ResponseEntity.status(HttpStatus.OK).body(product);
	}


	@Override
	public ResponseEntity<List<Product>> getProductsByProductname(String productName) {
		// TODO Auto-generated method stub
		List<Product> productsName = new ArrayList<>();
		
		if(productName.length() >= 3) {
			for (Product fetchProductName : productRepo.findAll()) {
				if(fetchProductName.getProductName().startsWith(productName)) {
					productsName.add(fetchProductName);
				}
			}
		}
		return ResponseEntity.status(HttpStatus.OK).body(productsName);
	}

}
