package com.cognizant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cognizant.entity.Product;
import com.cognizant.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@Autowired
	private Validator validator;
	
	@RequestMapping(value="viewProducts.htm",method=RequestMethod.GET)
	public ModelAndView viewProducts(){
		List<Product> productList=productService.getAllProducts();
		ModelAndView mv=new ModelAndView();
		mv.addObject("productList",productList);
		mv.setViewName("viewproducts");
		return mv;
		
	}
	
	
	@RequestMapping(value="productForm.htm",method=RequestMethod.GET)
	public String loadProductForm(){
		return "productform";
	}
	@RequestMapping(value="addproduct.htm",method=RequestMethod.POST)
	public ModelAndView persistProduct(@ModelAttribute("product")Product product,Errors errors){
		
		ModelAndView mv=new ModelAndView();

		ValidationUtils.invokeValidator(validator, product, errors);
		if(errors.hasErrors()){
			mv.setViewName("productform");
		}else{
		boolean productPersist=productService.persistProduct(product);
		
		if(productPersist){
			mv.addObject("status","Product successfully registered");
		}else{
			mv.addObject("status","Product registration failed");
		}

		mv.setViewName("productform");
		}
		return mv;
	}
	
	
	@ModelAttribute("product")
	public Product createCommandObject(){
		Product product=new Product();
		product.setProductId(0);
		product.setProductName("Please type product name");
		product.setProductDescription("Please describe product");
		product.setProductPrice(0.0);
		return product;
	}
	@ModelAttribute("categoryList")
	public List<String> createProductCategory(){
		return productService.getProductCategoryNames();
	}

}
