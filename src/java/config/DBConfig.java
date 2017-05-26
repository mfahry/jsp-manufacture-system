/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.sql.ResultSet;

/**
 *
 * @author Akip Maulana
 */
public interface DBConfig {
    public static final String SERVER_NAME = "localhost";
    public static final int SERVER_PORT = 3306;
    public static final String DB_NAME = "manufacture";
    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "Myfiorenta180892";
    
    /**
    *
    * @author Akip Maulana
    * to select All
    * @return ResultSet is contain data
    */
    public ResultSet selectAll();
    
    /**
    *
    * @author Akip Maulana
    * @param coloumnNames[] is list of field name in table
    * @param keywords[] is list of keywords will be matched with coloumnNames
    * @return ResultSet is contain data
    */
    public ResultSet getData(String[] coloumnNames, Object[] keywords);
    
    /**
    *
    * @author Akip Maulana
    * @param coloumnNames is specific field name in table
    * @param keywords will be matched with coloumnNames
    * @return ResultSet is contain data
    */
    public ResultSet getData(String coloumnNames, Object keywords);
    
    /**
    *
    * @author Akip Maulana
    * @param query is custom query for request data to Database
    * @return ResultSet is contain data
    */
    public ResultSet query(String query);
    
    /**
    *
    * @author Akip Maulana
    * @param query is custom query for request data to Database
    * @return 1 if success
    */
    public int runQuery(String query);
    
    /**
    *
    * @author Akip Maulana
    * @param coloumnNames is specific field be inserted
    * @param newData is new data
    * @return total inserted
    */
    public int insert(String[] coloumnNames, Object[] newData);
    
    /**
    *
    * @author Akip Maulana
    * @param coloumnMatching is matched by keywords
    * @param keywords is condition to update a few data
    * @return total deleted
    */
    public int delete(String[] coloumnMatching, Object[] keywords);
    
    /**
    *
    * @author Akip Maulana
    * @param coloumnNames is specific field name in table
    * @param newData is new data
    * @param coloumnMatching is matched by keywords
    * @param keywords is condition to update a few data
    * @return total updated
    */
    public int update(String[] coloumnNames, Object[] newData, String[] coloumnMatching, Object[] keywords);
    
}

