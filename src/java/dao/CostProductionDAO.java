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
import model.CostProduction;

/**
 *
 * @author ACER
 */
public class CostProductionDAO extends DB{
    private ResultSet rs;
    private ProductDAO pDAO;
    public CostProductionDAO(){
        super("cost_production");
        pDAO = new ProductDAO();
    }
    
    public ArrayList<CostProduction> selectAllCostProduction() throws SQLException{
        ArrayList<CostProduction> costProduction = new ArrayList<CostProduction>();
        rs = selectAll();
        while(rs.next()){
            CostProduction c = new CostProduction();
            c.setProduct(pDAO.selectById(rs.getInt("PRODUCT_ID")));
            c.setProductionYear(rs.getString("PRODUCTION_YEAR"));
            c.setAmount(rs.getInt("AMOUNT"));
            c.setUsedAmount(rs.getInt("USED_AMOUNT"));
            
            costProduction.add(c);
        }
        
        return costProduction;
    }
    
    public ArrayList<CostProduction> selectConditionCostProduction(String[] columnNames, Object[] keyword) throws SQLException{
        ArrayList<CostProduction> costProduction = new ArrayList<CostProduction>();
        rs = getData(columnNames, keyword);
        while(rs.next()){
            CostProduction c = new CostProduction();
            c.setProduct(pDAO.selectById(rs.getInt("PRODUCT_ID")));
            c.setProductionYear(rs.getString("PRODUCTION_YEAR"));
            c.setAmount(rs.getInt("AMOUNT"));
            c.setUsedAmount(rs.getInt("USED_AMOUNT"));
            
            costProduction.add(c);
        }
        
        return costProduction;
    }
    
    public CostProduction selectById(String[] columnNames, Object[] keyword) throws SQLException{
        CostProduction c = new CostProduction();
        rs = getData(columnNames, keyword);
        if(rs.next()){  
            c.setProduct(pDAO.selectById(rs.getInt("PRODUCT_ID")));
            c.setProductionYear(rs.getString("PRODUCTION_YEAR"));
            c.setAmount(rs.getInt("AMOUNT"));
            c.setUsedAmount(rs.getInt("USED_AMOUNT"));
        }
        return c;
    }
    
    
    public int saveCostProduction(CostProduction c){
        String[] coloumnNames = { "PRODUCT_ID", "PRODUCTION_YEAR", "AMOUNT", "USED_AMOUNT"};
        Object[] newData = new Object[coloumnNames.length];
        
        //set component untuk insert
        newData[0] = c.getProduct().getProductId();
        newData[1] = c.getProductionYear();
        newData[2] = c.getAmount();
        newData[3] = c.getUsedAmount();
        
        return insert(coloumnNames, newData);
    }
    
    public int updateCostProduction(CostProduction c, int newAmount, int newUsedAmount){
        String[] coloumnNames = { "AMOUNT", "USED_AMOUNT"};
        Object[] newData = new Object[coloumnNames.length];
        String[] coloumnMatching = {"PRODUCT_ID", "PRODUCTION_YEAR"}; 
        Object[] keywords = {c.getProduct().getProductId(), c.getProductionYear()};
        
        //set component untuk update
        newData[0] = newAmount;
        newData[1] = newUsedAmount;
        
        return update(coloumnNames, newData, coloumnMatching, keywords);
    }
    
    public int deleteCostProduction(CostProduction c){
        String[] coloumnNames = { "PRODUCT_ID", "PRODUCTION_YEAR"};
        Object[] newData = {c.getProduct().getProductId(), c.getProductionYear()};
        
        return delete(coloumnNames, newData);
    }
    
    public String getUsedAmountTotal() throws SQLException{
        String query = "SELECT SUM(USED_AMOUNT) AS USED_AMOUNT, "
                + "SUM(AMOUNT) AS AMOUNT FROM "+tableName;
        rs = query(query);
        String result = "0";
        
        if(rs.next()){  
            result = rs.getInt("USED_AMOUNT") + "";
        }
        
        return result;
    }
    
    public ArrayList<CostProduction> getGrafikCostProduction() throws SQLException{
        String query = "SELECT PRODUCTION_YEAR, SUM( USED_AMOUNT ) AS USED_AMOUNT, "
                        + "SUM( AMOUNT ) AS AMOUNT\n" +
                        "FROM `cost_production`\n" +
                        "GROUP BY PRODUCTION_YEAR";
        
        ArrayList<CostProduction> costProd = new ArrayList<CostProduction>();
        rs = query(query);
        while(rs.next()){
            CostProduction c = new CostProduction();
            c.setProduct(null);
            c.setProductionYear(rs.getString("PRODUCTION_YEAR"));
            c.setAmount(rs.getInt("AMOUNT"));
            c.setUsedAmount(rs.getInt("USED_AMOUNT"));
            
            costProd.add(c);
        }
        return costProd;
    }
    
    public ProductDAO getPDao(){
        return pDAO;
    }
}
