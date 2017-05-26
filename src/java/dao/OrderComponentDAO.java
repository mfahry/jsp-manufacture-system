/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import config.DB;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import model.OrderComponent;

/**
 *
 * @author ACER
 */
public class OrderComponentDAO extends DB{
    ResultSet rs;
    ComponentDAO cDAO;
    UserDAO uDAO;
    public OrderComponentDAO(){
        super("order_component");
        cDAO = new ComponentDAO();
        uDAO = new UserDAO();
    }
    
    public ArrayList<OrderComponent> selectAllOrderComponent() throws SQLException{
        ArrayList<OrderComponent> orderComponent = new ArrayList<OrderComponent>();
        rs = selectAll();
        while(rs.next()){
            OrderComponent o = new OrderComponent();
            o.setOrderComponentId(rs.getInt("ORDER_COMPONENT_ID"));
            o.setComponent(cDAO.selectById(rs.getInt("COMPONENT_ID")));
            o.setUser(uDAO.selectById(rs.getString("USERNAME")));
            o.setOrderDate(rs.getDate("ORDER_DATE"));
            o.setRequiredDate(rs.getDate("REQUIRED_DATE"));
            o.setScheduleBuyDate(rs.getDate("SCHEDULED_BUY_DATE"));
            o.setScheduleBuyUser(uDAO.selectById(rs.getString("SCHEDULED_BUY_USERNAME")));
            o.setStatus(rs.getInt("STATUS"));
            o.setQuantity(rs.getInt("QUANTITY"));
            o.setCost(rs.getDouble("COST"));
            orderComponent.add(o);
        }
        
        return orderComponent;
    }
    
    public ArrayList<OrderComponent> selectConditionOrderComponent(String[] columnNames, Object[] keyword) throws SQLException{
        ArrayList<OrderComponent> orderComponent = new ArrayList<OrderComponent>();
        rs = getData(columnNames, keyword);
        while(rs.next()){
            OrderComponent o = new OrderComponent();
            o.setOrderComponentId(rs.getInt("ORDER_COMPONENT_ID"));
            o.setComponent(cDAO.selectById(rs.getInt("COMPONENT_ID")));
            o.setUser(uDAO.selectById(rs.getString("USERNAME")));
            o.setOrderDate(rs.getDate("ORDER_DATE"));
            o.setRequiredDate(rs.getDate("REQUIRED_DATE"));
            o.setScheduleBuyDate(rs.getDate("SCHEDULED_BUY_DATE"));
            o.setScheduleBuyUser(uDAO.selectById(rs.getString("SCHEDULED_BUY_USERNAME")));
            o.setStatus(rs.getInt("STATUS"));
            o.setQuantity(rs.getInt("QUANTITY"));
            o.setCost(rs.getDouble("COST"));
            orderComponent.add(o);
        }
        
        return orderComponent;
    }
    
    public OrderComponent selectById(int keyword) throws SQLException{
        OrderComponent o = new OrderComponent();
        rs = getData("ORDER_COMPONENT_ID", keyword);
        if(rs.next()){
            o.setOrderComponentId(rs.getInt("ORDER_COMPONENT_ID"));
            o.setComponent(cDAO.selectById(rs.getInt("COMPONENT_ID")));
            o.setUser(uDAO.selectById(rs.getString("USERNAME")));
            o.setOrderDate(rs.getDate("ORDER_DATE"));
            o.setRequiredDate(rs.getDate("REQUIRED_DATE"));
            o.setScheduleBuyDate(rs.getDate("SCHEDULED_BUY_DATE"));
            o.setScheduleBuyUser(uDAO.selectById(rs.getString("SCHEDULED_BUY_USERNAME")));
            o.setStatus(rs.getInt("STATUS"));
            o.setQuantity(rs.getInt("QUANTITY"));
            o.setCost(rs.getDouble("COST"));
        }
        
        return o;
    }
    
    public int saveOrderComponent(OrderComponent o){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String[] coloumnNames = { "ORDER_COMPONENT_ID", 
            "COMPONENT_ID", 
            "USERNAME", 
            "ORDER_DATE", 
            "REQUIRED_DATE",
            "SCHEDULED_BUY_USERNAME",
            "STATUS", 
            "QUANTITY", 
            "COST"};
        Object[] newData = new Object[coloumnNames.length];
        
        //set orderComponent untuk insert
        newData[0] = o.getOrderComponentId();
        newData[1] = o.getComponent().getComponentId();
        newData[2] = o.getUser().getUsername();
        newData[3] = df.format(o.getOrderDate());
        newData[4] = df.format(o.getRequiredDate());
        newData[5] = o.getScheduleBuyUser().getUsername();
        newData[6] = o.getStatus();
        newData[7] = o.getQuantity();
        newData[8] = 0;
        
        return insert(coloumnNames, newData);
    }
    
    public int updateOrderComponent(OrderComponent o, int newComponentId, String newUsername, Date newOrderDate, Date newRequiredDate,  Date newSchedulerBuyDate, String newSchedulerBuyUsername, int newStatus, int newQuantity, double newCost){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String[] coloumnNames = { "COMPONENT_ID", "USERNAME", "ORDER_DATE", "REQUIRED_DATE", "SCHEDULED_BUY_DATE", "SCHEDULED_BUY_USERNAME", "STATUS", "QUANTITY", "COST"};
        Object[] newData = new Object[coloumnNames.length];
        String[] coloumnMatching = {"ORDER_COMPONENT_ID"}; 
        Object[] keywords = {o.getOrderComponentId()};
        
        //set orderComponent untuk update
        newData[0] = newComponentId;
        newData[1] = newUsername;
        newData[2] = df.format(newOrderDate);
        newData[3] = df.format(newRequiredDate);
        newData[4] = df.format(newSchedulerBuyDate);
        newData[5] = newSchedulerBuyUsername;
        newData[6] = newStatus;
        newData[7] = newQuantity;
        newData[8] = newCost;
        return update(coloumnNames, newData, coloumnMatching, keywords);
    }
    
    public int deleteOrderComponent(OrderComponent o){
        String[] coloumnNames = { "ORDER_COMPONENT_ID"};
        Object[] newData = {o.getOrderComponentId()};
        
        return delete(coloumnNames, newData);
    }
}
