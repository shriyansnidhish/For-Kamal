package com.cognizant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cognizant.dao.ProductDAO;
import com.cognizant.entity.Product;
@Service
public class ProductServiceImpl implements ProductService{
	@Autowired@Qualifier("HibernateSessionProductDAOImpl")
	private ProductDAO productDAO;

	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return productDAO.getAllProducts();
	}

	@Override
	public List<String> getProductCategoryNames() {
		// TODO Auto-generated method stub
		return productDAO.getCategoriesNames();
	}

	@Override
	public boolean persistProduct(Product product) {
		// TODO Auto-generated method stub
		return productDAO.insertProduct(product);
	}

	@Override
	public int checkProduct(Product product) {
		// TODO Auto-generated method stub
		return productDAO.checkProduct(product);
	}

}
