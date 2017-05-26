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
import model.User;

/**
 *
 * @author ACER
 */
public class UserDAO extends DB{
    ResultSet rs;
    
    public UserDAO(){
        super("user");
    }
    
    public ArrayList<User> selectAllUser() throws SQLException{
        ArrayList<User> user = new ArrayList<User>();
        rs = selectAll();
        while(rs.next()){
            User u = new User();
            u.setUsername(rs.getString("USERNAME"));
            u.setPassword(rs.getString("PASSWORD"));
            u.setEmployeeId(rs.getString("EMPLOYEE_ID"));
            u.setEmployeeName(rs.getString("EMPLOYEE_NAME"));
            u.setStatus(rs.getString("STATUS"));
            u.setUserGroup(rs.getString("USER_GROUP"));
            user.add(u);
        }
        
        return user;
    }
    
    public ArrayList<User> selectConditionUser(String[] columnNames, Object[] keyword) throws SQLException{
        ArrayList<User> user = new ArrayList<User>();
        rs = getData(columnNames, keyword);
        while(rs.next()){
            User u = new User();
            u.setUsername(rs.getString("USERNAME"));
            u.setPassword(rs.getString("PASSWORD"));
            u.setEmployeeId(rs.getString("EMPLOYEE_ID"));
            u.setEmployeeName(rs.getString("EMPLOYEE_NAME"));
            u.setStatus(rs.getString("STATUS"));
            u.setUserGroup(rs.getString("USER_GROUP"));
            user.add(u);
        }
        
        return user;
    }
    
    public User selectById(String keyword) throws SQLException{
        User u = new User();
        rs = getData("USERNAME", keyword);
        if(rs.next()){u.setUsername(rs.getString("USERNAME"));
            u.setPassword(rs.getString("PASSWORD"));
            u.setEmployeeId(rs.getString("EMPLOYEE_ID"));
            u.setEmployeeName(rs.getString("EMPLOYEE_NAME"));
            u.setStatus(rs.getString("STATUS"));
            u.setUserGroup(rs.getString("USER_GROUP"));
        }
        return u;
    }
    
    public int saveUser(User u){
        String[] coloumnNames = { "USERNAME", "PASSWORD", "EMPLOYEE_ID", "EMPLOYEE_NAME", "STATUS", "USER_GROUP"};
        Object[] newData = new Object[coloumnNames.length];
        
        //set component untuk insert
        newData[0] = u.getUsername();
        newData[1] = u.getPassword();
        newData[2] = u.getEmployeeId();
        newData[3] = u.getEmployeeName();
        newData[4] = u.getStatus();
        newData[5] = u.getUserGroup();
        
        return insert(coloumnNames, newData);
    }
    
    public int updateUser(User u, String newPassword, String newEmployeeId, String newEmployeeName, String newStatus, String newUserGrup){
        String[] coloumnNames = { "PASSWORD", "EMPLOYEE_ID", "EMPLOYEE_NAME", "STATUS", "USER_GROUP"};
        Object[] newData = new Object[coloumnNames.length];
        String[] coloumnMatching = {"USERNAME"}; 
        Object[] keywords = {u.getUsername()};
        
        //set component untuk update
        newData[0] = newPassword;
        newData[1] = newEmployeeId;
        newData[2] = newEmployeeName;
        newData[3] = newStatus;
        newData[4] = newUserGrup;
        
        return update(coloumnNames, newData, coloumnMatching, keywords);
    }
    
    public int deleteUser(User u){
        String[] coloumnNames = { "USERNAME"};
        Object[] newData = {u.getUsername()};
        
        return delete(coloumnNames, newData);
    }
}
