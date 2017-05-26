/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Akip Maulana
 */
public class DB implements DBConfig {

    protected Connection connection;
    protected Statement statement;
    protected boolean isConnected;

    protected String tableName;

    public DB(String tableName) {
        this.tableName = tableName;
        
        initialDB();
    }
    
    private void initialDB(){
        try{
            disconnect();
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.
                    getConnection("jdbc:mysql://"+SERVER_NAME+":"+SERVER_PORT+"/"+DB_NAME,
                            DB_USERNAME, DB_PASSWORD);
            statement = connection.createStatement();
            isConnected = true;
        } catch(ClassNotFoundException | SQLException e){
            isConnected = false;
            System.out.println("DisConnected");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ResultSet selectAll() {
        ResultSet rs = null;
        try {
            String s = "SELECT * FROM " + tableName;
            rs = statement.executeQuery(s);
        } catch (SQLException ex) {
        }
        return rs;
    }

    @Override
    public ResultSet getData(String[] coloumnNames, Object[] keyword) {
        ResultSet rs = null;
        try {
            String s = "SELECT * FROM " + tableName + " WHERE "
                    + getCondition(coloumnNames, keyword, "AND");
            rs = statement.executeQuery(s);
        } catch (SQLException ex) {
        }
        return rs;
    }

    @Override
    public ResultSet getData(String coloumnNames, Object keyword) {
        ResultSet rs = null;
        String condition = parsingToField(keyword);

        try {
            String s = "SELECT * FROM " + tableName + " WHERE " + coloumnNames + " = " + condition;
            rs = statement.executeQuery(s);
        } catch (SQLException ex) {
        }
        return rs;
    }

    @Override
    public ResultSet query(String query) {
        ResultSet rs = null;
        try {
            rs = statement.executeQuery(query);
        } catch (SQLException ex) {
        }
        return rs;
    }

    @Override
    public int runQuery(String query) {
        int result = 0;
        try {
            result = statement.executeUpdate(query);
            System.out.println(result + " Row ");
        } catch (SQLException ex) {
            System.out.println("error " + ex.getMessage());
        }
        return result;
    }

    @Override
    public int insert(String[] coloumnNames, Object[] newData) {
        int result = 0;

        String parameter = "";

        // Parameter Transform from coloumnNames
        for (int i = 0; i < coloumnNames.length; i++) {
            parameter += coloumnNames[i];
            if (i != coloumnNames.length - 1) {
                parameter += ",";
            }
        }

        String query = "INSERT INTO " + tableName + " (" + parameter + ") VALUES (";

        for (int i = 0; i < newData.length; i++) {
            query += parsingToField(newData[i]);
            if (i != coloumnNames.length - 1) {
                query += ",";
            }
        }

        query += ")";
        System.out.println(query);
        // result > 1 , there are inserted data
        result = runQuery(query);

        return result;
    }

    @Override
    public int delete(String[] coloumnMatching, Object[] keywords) {
        int result = 0;

        String query = "DELETE FROM " + tableName + " WHERE "
                + getCondition(coloumnMatching, keywords, "AND");

        // result > 1 , there are deleted data
        result = runQuery(query);

        return result;
    }

    @Override
    public int update(String[] coloumnNames, Object[] newData,
            String[] coloumnMatching, Object[] keywords) {
        int result = 0;

        String query = "UPDATE " + tableName + " SET " + getCondition(coloumnNames, newData, ",")
                + " WHERE " + getCondition(coloumnMatching, keywords, "AND");

        // result > 1 , there are updated data
        result = runQuery(query);

        return result;
    }

    protected void disconnect() {
        try {
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("Eroor " + e.getMessage());
        }
    }

    private String getCondition(String[] coloumnNames, Object[] keyword, String separator) {
        String s = "";
        for (int i = 0; i < coloumnNames.length; i++) {
            String condition = parsingToField(keyword[i]);
            s += coloumnNames[i] + " = " + condition;

            // Coloumn Terakhir tidak dikasih AND
            if (i != coloumnNames.length - 1) {
                s += " " + separator + " ";
            }
        }
        return s;
    }

    private String parsingToField(Object obj) {
        String result = "";
        if (obj instanceof Integer) {
            result = obj.toString();
        } else {
            result = "'" + obj.toString() + "'";
        }
        return result;
    }

}
