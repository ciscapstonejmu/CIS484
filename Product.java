package pkg484groupproj;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Product {
    private int productID;
    private String productName;
    private int quantity;
    private double price;
    private String location;
    private String foodDescription;
    private String category;
    private BufferedImage image;
    public static int nextID = 0;
    
    
    public Product(String productName, int quantity, double price, String location, String foodDescription, String category, BufferedImage image)
    {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.location = location;
        this.foodDescription = foodDescription;
        this.category = category;
        this.image = image;
    }
    
    public int getID()
    {
        return this.productID;
    }
    
    public void setName(String productName)
    {
        this.productName = productName;
    }
    
    public String getName()
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
    
    public void setDescription(String foodDescription)
    {
        this.foodDescription = foodDescription;
    }
    
    public String getDescription()
    {
        return this.foodDescription;
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
                " , Description: " + this.foodDescription + " , Category: " + this.category;
        return str;
    }
}
