package com.wakarkhan.deliverydropnavigation.Models;

/**
 * Created by HP on 27-10-2016.
 */
public class Orders {
    private String productName;
    private String websiteName;
    private String productImageUrl;
    private String status;
    public String getProductName(){
        return productName;
    }
    public String getStatus(){
        return status;
    }
    public String getWebsiteName(){
        return websiteName;
    }
    public String getProductImageUrl(){
        return productImageUrl;
    }
    public void setProductName(String productName){
        this.productName=productName;
    }
    public void setWebsiteName(String websiteName){
        this.websiteName=websiteName;
    }
    public void setProductImageUrl(String productImageUrl){
        this.productImageUrl=productImageUrl;
    }
    public void setStatus(String status){
        this.status=status;
    }
}
