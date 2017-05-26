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
import model.Component;

/**
 *
 * @author ACER
 */
public class ComponentDAO extends DB{
    ResultSet rs;
    PartDAO pDAO;
    
    public ComponentDAO(){
        super("component");
        pDAO = new PartDAO();
    }
    
    public ArrayList<Component> selectAllComponent() throws SQLException{
        ArrayList<Component> component = new ArrayList<Component>();
        rs = selectAll();
        while(rs.next()){
            Component c = new Component();
            c.setComponentId(rs.getInt("COMPONENT_ID"));
            c.setComponentName(rs.getString("COMPONENT_NAME"));
            c.setImgUrl(rs.getString("IMG_URL"));
            c.setPart(pDAO.selectById(rs.getInt("PART_ID")));
            c.setQuantity(rs.getInt("QUANTITY"));
            component.add(c);
        }
        
        return component;
    }
    
    public ArrayList<Component> selectConditionComponent(String[] columnNames, Object[] keyword) throws SQLException{
        ArrayList<Component> component = new ArrayList<Component>();
        rs = getData(columnNames, keyword);
        while(rs.next()){
            Component c = new Component();
            c.setComponentId(rs.getInt("COMPONENT_ID"));
            c.setComponentName(rs.getString("COMPONENT_NAME"));
            c.setImgUrl(rs.getString("IMG_URL"));
            c.setPart(pDAO.selectById(rs.getInt("PART_ID")));
            c.setQuantity(rs.getInt("QUANTITY"));
            component.add(c);
        }
        
        return component;
    }
    
    public Component selectById(int keyword) throws SQLException{
        Component c = new Component();
        rs = getData("COMPONENT_ID", keyword);
        if(rs.next()){
            c.setComponentId(rs.getInt("COMPONENT_ID"));
            c.setComponentName(rs.getString("COMPONENT_NAME"));
            c.setImgUrl(rs.getString("IMG_URL"));
            c.setPart(pDAO.selectById(rs.getInt("PART_ID")));
            c.setQuantity(rs.getInt("QUANTITY"));
        }
        
        return c;
    }
    
    public int saveComponent(Component c){
        String[] coloumnNames = { "COMPONENT_ID", "COMPONENT_NAME", "PART_ID", "QUANTITY", "IMG_URL"};
        Object[] newData = new Object[coloumnNames.length];
        
        //set component untuk insert
        newData[0] = c.getComponentId();
        newData[1] = c.getComponentName();
        newData[2] = c.getPart().getPartId();
        newData[3] = c.getQuantity();
        newData[4] = c.getImgUrl();
        
        return insert(coloumnNames, newData);
    }
    
    public int updateComponent(Component c, String newComponentName, int newPartId, int newQuantity, String newImgUrl){
        String[] coloumnNames = { "COMPONENT_NAME", "PART_ID", "QUANTITY", "IMG_URL"};
        Object[] newData = new Object[coloumnNames.length];
        String[] coloumnMatching = {"COMPONENT_ID"}; 
        Object[] keywords = {c.getComponentId()};
        
        //set component untuk update
        newData[0] = newComponentName;
        newData[1] = newPartId;
        newData[2] = newQuantity;
        newData[3] = newImgUrl;
        
        return update(coloumnNames, newData, coloumnMatching, keywords);
    }
    
    public int deleteComponent(Component c){
        String[] coloumnNames = { "COMPONENT_ID"};
        Object[] newData = {c.getComponentId()};
        
        return delete(coloumnNames, newData);
    }
}
