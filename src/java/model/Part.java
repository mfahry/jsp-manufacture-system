/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Akip Maulana
 */
public class Part{

    private int partId;
    private String partName;
    private Product product;
    private int quantity;
    private String imgUrl;
    
    
    /**
     * @return the partId
     */
    public int getPartId() {
        return partId;
    }

    /**
     * @param partId the partId to set
     */
    public void setPartId(int partId) {
        this.partId = partId;
    }

    /**
     * @return the partName
     */
    public String getPartName() {
        return partName;
    }

    /**
     * @param partName the partName to set
     */
    public void setPartName(String partName) {
        this.partName = partName;
    }

    /**
     * @return the productId
     */
    public Product getProduct() {
        return product;
    }

    /**
     * @param productId the productId to set
     */
    public void setProduct(Product product) {
        this.product = product;
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
     * @return the imgUrl
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * @param imgUrl the imgUrl to set
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
    
}
