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
public class CostProduction{
    private Product product;
    private String productionYear;
    private int amount;
    private int usedAmount;

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
     * @return the productionYear
     */
    public String getProductionYear() {
        return productionYear;
    }

    /**
     * @param productionYear the productionYear to set
     */
    public void setProductionYear(String productionYear) {
        this.productionYear = productionYear;
    }

    /**
     * @return the amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * @return the used_amount
     */
    public int getUsedAmount() {
        return usedAmount;
    }

    /**
     * @param used_amount the used_amount to set
     */
    public void setUsedAmount(int usedAmount) {
        this.usedAmount = usedAmount;
    }
    
    
}
