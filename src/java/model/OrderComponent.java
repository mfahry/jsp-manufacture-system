/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author Akip Maulana
 */
public class OrderComponent{
    private int orderComponentId;
    private Component component;
    private User user;
    private Date orderDate;
    private Date requiredDate;
    private Date scheduleBuyDate;
    private User scheduleBuyUser;
    private int status;
    private int quantity;
    private double cost;
   
    /**
     * @return the componentId
     */
    public Component getComponent() {
        return component;
    }

    /**
     * @param componentId the componentId to set
     */
    public void setComponent(Component component) {
        this.component = component;
    }

    /**
     * @return the username
     */
    public User getUser() {
        return user;
    }

    /**
     * @param username the username to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the orderDate
     */
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     * @param orderDate the orderDate to set
     */
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * @return the requiredDate
     */
    public Date getRequiredDate() {
        return requiredDate;
    }

    /**
     * @param requiredDate the requiredDate to set
     */
    public void setRequiredDate(Date requiredDate) {
        this.requiredDate = requiredDate;
    }

    /**
     * @return the scheduleBuyDate
     */
    public Date getScheduleBuyDate() {
        return scheduleBuyDate;
    }

    /**
     * @param scheduleBuyDate the scheduleBuyDate to set
     */
    public void setScheduleBuyDate(Date scheduleBuyDate) {
        this.scheduleBuyDate = scheduleBuyDate;
    }

    /**
     * @return the orderComponentId
     */
    public int getOrderComponentId() {
        return orderComponentId;
    }

    /**
     * @param orderComponentId the orderComponentId to set
     */
    public void setOrderComponentId(int orderComponentId) {
        this.orderComponentId = orderComponentId;
    }

    /**
     * @return the scheduleBuyUsername
     */
    public User getScheduleBuyUser() {
        return scheduleBuyUser;
    }

    /**
     * @param scheduleBuyUsername the scheduleBuyUsername to set
     */
    public void setScheduleBuyUser(User scheduleBuyUser) {
        this.scheduleBuyUser = scheduleBuyUser;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the cost
     */
    public double getCost() {
        return cost;
    }

    /**
     * @param cost the cost to set
     */
    public void setCost(double cost) {
        this.cost = cost;
    }
    
}
