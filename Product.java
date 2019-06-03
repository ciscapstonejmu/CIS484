package pkg484groupproj;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;


public class Product {
    private int productID;
    private String productName;
    private int quantity;
    private double price;
    private String location;
    private String foodDescription;
    private String category;
    private int aisle;
    private BufferedImage image;
    ArrayList<Double> availableDiscounts = new ArrayList<>();
    ArrayList<String> discountTitle = new ArrayList<>();
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
        
        if (category.equalsIgnoreCase("PRODUCE"))
        {
            this.aisle = 1;
        }
        
        if (category.equalsIgnoreCase("DAIRY"))
        {
            this.aisle = 2;
        }
        
        if (category.equalsIgnoreCase("CANDY"))
        {
            this.aisle = 3;
        }
        
        
        if (category.equalsIgnoreCase("CANNED FOODS"))
        {
            this.aisle = 4;
        }
        
        if (category.equalsIgnoreCase("PASTA"))
        {
            this.aisle = 5;
        }
        
        if (category.equalsIgnoreCase("SNACK FOODS"))
        {
            this.aisle = 6;
        }
        
        if (category.equalsIgnoreCase("BAKED GOODS"))
        {
            this.aisle = 7;
        }
        
        if (category.equalsIgnoreCase("FROZEN FOODS"))
        {
            this.aisle = 8;
        }
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
    
    public void setAisle(int aisle)
    {
        this.aisle = aisle;
    }
    
    public int getAisle()
    {
        return this.aisle;
    }
    
    public void setImage (BufferedImage image)
    {
        this.image = image;
    }
    
    public BufferedImage getImage()
    {
        return this.image;
    }
    
    public void addDiscount(String t, double d)
    {
        this.availableDiscounts.add(d);
        this.discountTitle.add(t);
    }
    
    public void applyDiscount(int index)
    {
        this.price = this.price - this.availableDiscounts.get(index);
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
