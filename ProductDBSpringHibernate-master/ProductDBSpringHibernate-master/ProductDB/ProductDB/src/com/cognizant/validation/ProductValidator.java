package com.cognizant.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.cognizant.entity.Product;
import com.cognizant.service.ProductService;

@Component
public class ProductValidator implements Validator {
	
	@Autowired
	private ProductService productService;

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return arg0.equals(Product.class);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		// TODO Auto-generated method stub
		Product product=(Product)arg0;
		int productExists=productService.checkProduct(product);
		if(productExists==1){
			arg1.rejectValue("productId", "com.cognizant.entity.Product.productId.duplicate");
		}else if(productExists==2){
			arg1.rejectValue("productCategory", "com.cognizant.entity.Product.productCategory.duplicate");

		}else if(productExists==3){
			arg1.rejectValue("productId", "com.cognizant.entity.Product.productId.duplicate");
			arg1.rejectValue("productCategory", "com.cognizant.entity.Product.productCategory.duplicate");


		}
		
	}
	
	
	

}
