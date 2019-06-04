package pkg484groupproj;

import java.awt.image.BufferedImage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

    public class Product {
    private int productID;
    private String productName;
    private int quantity;
    private double price;
    private String location;
    private String prodDescription;
    private String category;
    private BufferedImage image;
    public static int nextID = 0;
    public static ObservableList obsProd = FXCollections.observableArrayList();
    public String supplier; 
    
    
    public Product(String productName, int quantity, double price, String location, String prodDescription, String category, BufferedImage image)
    {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.location = location;
        this.prodDescription = prodDescription;
        this.category = category;
        this.image = image;
        
        
    }
    public Product(String productName, int quantity, double price, String location, String prodDescription, String category, String supplier)
    {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.location = location;
        this.prodDescription = prodDescription;
        this.category = category;
        this.image = image;
        this.supplier = supplier; 
        this.productID = nextID++;
        obsProd.add(this.productID + ": " + this.productName);
    }
    public int getProductID()
    {
        return this.productID;
    }
    
    public void setProductName(String productName)
    {
        this.productName = productName;
    }
    
    public String getProductName()
    {
        return this.productName;
    }
    
    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
    
    public int getQuantity()
    {
        return this.quantity;
    }
    
    public void setPrice(double price)
    {
        this.price = price;
    }
    
    public double getPrice()
    {
        return this.price;
    }
    
    public void setLocation(String productName)
    {
        this.productName = productName;
    }
    
    public String getLocation()
    {
        return this.location;
    }
    
    public void setFoodDescription(String prodDescription)
    {
        this.prodDescription = prodDescription;
    }
    
    public String getFoodDescription()
    {
        return this.prodDescription;
    }
    
    public void setCategory(String category)
    {
        this.category = category;
    }
    
    public String getCategory()
    {
        return this.category;
    }
    
    public void setImage (BufferedImage image)
    {
        this.image = image;
    }
    
    public BufferedImage getImage()
    {
        return this.image;
    }
    
    public String toString()
    {
        String str = "";
        str += "ID: " + this.productID + " , Name: " + this.productName + " , Quantity" + 
                this.quantity + " , Price: " + this.price + " , Location: " + this.location +
                " , Description: " + this.prodDescription + " , Category: " + this.category;
        return str;
    }
}

