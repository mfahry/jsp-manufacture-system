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
import model.Part;
import model.Product;

/**
 *
 * @author ACER
 */
public class PartDAO extends DB{
    ResultSet rs;
    ProductDAO pDAO;
    
    public PartDAO() {
        super("part");
        pDAO = new ProductDAO();
    }
    
    public ArrayList<Part> selectAllPart() throws SQLException{
        ArrayList<Part> part = new ArrayList<Part>();
        rs = selectAll();
        while(rs.next()){
            Part p = new Part();
            p.setPartId(rs.getInt("PART_ID"));
            p.setPartName(rs.getString("PART_NAME"));
            p.setImgUrl(rs.getString("IMG_URL"));
            p.setProduct(pDAO.selectById(rs.getInt("PRODUCT_ID")));
            p.setQuantity(rs.getInt("QUANTITY"));
            part.add(p);
        }
        
        return part;
    }
    
    public ArrayList<Part> selectConditionPart(String[] columnNames, Object[] keyword) throws SQLException{
        ArrayList<Part> part = new ArrayList<Part>();
        rs = getData(columnNames, keyword);
        while(rs.next()){
            Part p = new Part();
            p.setPartId(rs.getInt("PART_ID"));
            p.setPartName(rs.getString("PART_NAME"));
            p.setImgUrl(rs.getString("IMG_URL"));
            p.setProduct(pDAO.selectById(rs.getInt("PRODUCT_ID")));
            p.setQuantity(rs.getInt("QUANTITY"));
            part.add(p);
        }
        
        return part;
    }
    
    public Part selectById(int keyword) throws SQLException{
        Part p = new Part();
        rs = getData("PART_ID", keyword);
        if(rs.next()){
            p.setPartId(rs.getInt("PART_ID"));
            p.setPartName(rs.getString("PART_NAME"));
            p.setImgUrl(rs.getString("IMG_URL"));
            p.setProduct(pDAO.selectById(rs.getInt("PRODUCT_ID")));
            p.setQuantity(rs.getInt("QUANTITY"));
        }
        
        return p;
    }
    
    public int savePart(Part p){
        String[] coloumnNames = { "PART_ID", "PART_NAME", "PRODUCT_ID", "QUANTITY", "IMG_URL"};
        Object[] newData = new Object[coloumnNames.length];
        
        //set part untuk insert
        newData[0] = p.getPartId();
        newData[1] = p.getPartName();
        newData[2] = p.getProduct().getProductId();
        newData[3] = p.getQuantity();
        newData[4] = p.getImgUrl();
        
        return insert(coloumnNames, newData);
    }
    
    public int updatePart(Part p, String newPartName, int newProductId, int newQuantity, String newImgUrl){
        String[] coloumnNames = { "PART_NAME", "PRODUCT_ID", "QUANTITY", "IMG_URL"};
        Object[] newData = new Object[coloumnNames.length];
        String[] coloumnMatching = {"PART_ID"}; 
        Object[] keywords = {p.getPartId()};
        
        //set part untuk update
        newData[0] = newPartName;
        newData[1] = newProductId;
        newData[2] = newQuantity;
        newData[3] = newImgUrl;
        
        return update(coloumnNames, newData, coloumnMatching, keywords);
    }
    
    public int deletePart(Part p){
        String[] coloumnNames = { "PART_ID"};
        Object[] newData = {p.getPartId()};
        
        return delete(coloumnNames, newData);
    }
    
}
