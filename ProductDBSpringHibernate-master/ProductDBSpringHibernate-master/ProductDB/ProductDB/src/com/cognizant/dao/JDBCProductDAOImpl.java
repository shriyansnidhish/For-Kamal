package com.cognizant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.entity.Product;
import com.cognizant.helper.ConnectionManager;
import com.cognizant.helper.ProductQuery;
@Repository("JDBCProductDAOImpl")
public class JDBCProductDAOImpl implements ProductDAO{
	@Autowired
	private ConnectionManager manager;
	@Autowired
	private ProductQuery productQuery;

	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		Connection connection=manager.openConnection();
		List<Product> productList=new ArrayList<Product>();
		try {
			Statement statement=connection.createStatement();
			ResultSet resultSet=statement.executeQuery(productQuery.getSelectAllProductsQuery());
			while(resultSet.next()){
				Product product=new Product();
				product.setProductId(resultSet.getInt("product_id"));
				product.setProductName(resultSet.getString("product_name"));
				product.setProductCategory(resultSet.getString("product_category"));
				product.setProductDescription(resultSet.getString("product_description"));
				product.setProductPrice(resultSet.getDouble("product_price"));
				product.setBrand(resultSet.getString("brand"));
				
				productList.add(product);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		manager.closeConnection();
		return productList;
	}

	@Override
	public List<String> getCategoriesNames() {
		// TODO Auto-generated method stub
		Connection connection=manager.openConnection();
		List<String> categoryList=new ArrayList<String>();
		try {
			Statement statement=connection.createStatement();
			ResultSet resultSet=statement.executeQuery(productQuery.getSelectProductCategoryName());
			while(resultSet.next()){
				categoryList.add(resultSet.getString("category_name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		manager.closeConnection();
		return categoryList;
	}

	@Override
	public boolean insertProduct(Product product) {
		// TODO Auto-generated method stub
		Connection connection=manager.openConnection();
		boolean productInserted=false;
		try {
			PreparedStatement statement=
					connection.prepareStatement(productQuery.getInsertProduct());
			statement.setInt(1,product.getProductId());
			statement.setString(2,product.getProductName());
			statement.setString(3,product.getProductCategory());
			statement.setString(4, product.getProductDescription());
			statement.setDouble(5,product.getProductPrice());
			statement.setString(6, product.getBrand());
			int rows=statement.executeUpdate();
			if(rows>0)
				productInserted=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		manager.closeConnection();
		return productInserted;
	}

	@Override
	public int checkProduct(Product product) {
		// TODO Auto-generated method stub
		Connection connection=manager.openConnection();
		int productExists=0;
		Product productDB=new Product();

		try {
			PreparedStatement statement=
					connection.prepareStatement(productQuery.getCheckProduct());
			statement.setInt(1,product.getProductId());
			statement.setString(2, product.getProductCategory());
			ResultSet resultSet=statement.executeQuery();
			while(resultSet.next()){
				productDB.setProductId(resultSet.getInt("product_id"));
				productDB.setProductCategory(resultSet.getString("product_category"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(productDB.getProductId()==product.getProductId()){
			productExists= 1;
		}else if(productDB.getProductCategory().equals(product.getProductCategory())){
			productExists= 2;
		}else if(productDB.getProductId()==product.getProductId() && productDB.getProductCategory().equals(product.getProductCategory())){
			productExists= 3;
		}
		manager.closeConnection();
		return productExists;
	}

}
