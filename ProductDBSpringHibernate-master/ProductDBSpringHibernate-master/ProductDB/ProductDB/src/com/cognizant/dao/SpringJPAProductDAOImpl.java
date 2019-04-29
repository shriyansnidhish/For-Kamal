package com.cognizant.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.entity.Product;

@Repository("SpringJPAProductDAOImpl")
public class SpringJPAProductDAOImpl implements ProductDAO {

	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		Query query=entityManager.createNamedQuery("findAllProducts");
		List<Product> productList=query.getResultList();
		return productList;
		
	}

	@Override
	public List<String> getCategoriesNames() {
		// TODO Auto-generated method stub
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		Query query=entityManager.createNativeQuery("select category_name from product_categories");
		List<String> categoryList=query.getResultList();
		return categoryList;
	}

	@Override
	public boolean insertProduct(Product product) {
		// TODO Auto-generated method stub
		boolean productPersisted=false;
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction=entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(product);
		entityTransaction.commit();
		productPersisted=true;
		return productPersisted;
	}

	@Override
	public int checkProduct(Product product) {
		// TODO Auto-generated method stub
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		int productExists=0;
		Query query=entityManager.createNamedQuery("checkProduct");
		query.setParameter(1,product.getProductId());
		query.setParameter(2, product.getProductCategory());
		List<Product> productList=query.getResultList();
		for(Product productDB:productList){
			if(productDB.getProductId()==product.getProductId()){
				productExists= 1;
			}else if(productDB.getProductCategory().equals(product.getProductCategory())){
				productExists= 2;
			}else{
				productExists= 0;
			}
		}
	   return productExists;
	}

}
