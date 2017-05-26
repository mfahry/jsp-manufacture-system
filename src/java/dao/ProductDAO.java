/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import config.DB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Product;

/**
 *
 * @author ACER
 */
public class ProductDAO extends DB{
    ResultSet rs;
    
    public ProductDAO(){
        super("product");
    }
    
    public ArrayList<Product> selectAllProduct() throws SQLException{
        ArrayList<Product> product = new ArrayList<Product>();
        rs = selectAll();
        while(rs.next()){
            Product p = new Product();
            p.setProductId(rs.getInt("PRODUCT_ID"));
            p.setProductName(rs.getString("PRODUCT_NAME"));
            p.setImgUrl(rs.getString("IMG_URL"));
            p.setQuantity(rs.getInt("QUANTITY"));
            product.add(p);
        }
        
        return product;
    }
    
    public ArrayList<Product> selectConditionProduct(String[] columnNames, Object[] keyword) throws SQLException{
        ArrayList<Product> product = new ArrayList<Product>();
        rs = getData(columnNames, keyword);
        while(rs.next()){
            Product p = new Product();
            p.setProductId(rs.getInt("PRODUCT_ID"));
            p.setProductName(rs.getString("PRODUCT_NAME"));
            p.setImgUrl(rs.getString("IMG_URL"));
            p.setQuantity(rs.getInt("QUANTITY"));
            product.add(p);
        }
        
        return product;
    }
    
    public Product selectById(int keyword) throws SQLException{
        Product p = new Product();
        rs = getData("PRODUCT_ID", keyword);
        if(rs.next()){
            p.setProductId(rs.getInt("PRODUCT_ID"));
            p.setProductName(rs.getString("PRODUCT_NAME"));
            p.setImgUrl(rs.getString("IMG_URL"));
            p.setQuantity(rs.getInt("QUANTITY"));
        }
        
        return p;
    }
    
    public int saveProduct(Product p){
        String[] coloumnNames = { "PRODUCT_ID", "PRODUCT_NAME", "QUANTITY", "IMG_URL"};
        Object[] newData = new Object[coloumnNames.length];
        
        //set component untuk insert
        newData[0] = p.getProductId();
        newData[1] = p.getProductName();
        newData[2] = p.getQuantity();
        newData[3] = p.getImgUrl();
        
        return insert(coloumnNames, newData);
    }
    
    public int updateProduct(Product p, String newProductName, int newPartId, int newQuantity, String newImgUrl){
        String[] coloumnNames = { "PRODUCT_NAME", "QUANTITY", "IMG_URL"};
        Object[] newData = new Object[coloumnNames.length];
        String[] coloumnMatching = {"PRODUCT_ID"}; 
        Object[] keywords = {p.getProductId()};
        
        //set component untuk update
        newData[0] = newProductName;
        newData[1] = newQuantity;
        newData[2] = newImgUrl;
        
        return update(coloumnNames, newData, coloumnMatching, keywords);
    }
    
    public int deleteProduct(Product p){
        String[] coloumnNames = { "PRODUCT_ID"};
        Object[] newData = {p.getProductId()};
        
        return delete(coloumnNames, newData);
    }
}
