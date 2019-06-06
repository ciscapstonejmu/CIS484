package pkg484groupproj;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;
import java.text.DecimalFormat;

public class Product {
    private int productID;
    private String productName;
    private int quantity;
    private double price;
    private Store store;
    private String location;
    private String foodDescription;
    private String category;
    private int aisle;
    private BufferedImage image;
    private int saleYear;
    private int saleMonth;
    private int saleDay;
    public static int nextID = 0;
    public static ObservableList obsProd = FXCollections.observableArrayList(); 
    
    //Fromatter for money figures
    DecimalFormat formatter = new DecimalFormat("#0.00");
    
    public Product(String productName, int quantity, double price, Store store, String foodDescription, String category, BufferedImage image)
    {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.location = store.getName();
        this.foodDescription = foodDescription;
        this.category = category;
        this.image = image;
        this.productID = nextID++;
        obsProd.add(this.productID + ": " + this.productName);
        
        if (category.equalsIgnoreCase("PRODUCE"))
        {
            this.aisle = 1;
        }
        
        if (category.equalsIgnoreCase("DAIRY"))
        {
            this.aisle = 2;
        }
        
        if (category.equalsIgnoreCase("BEVERAGES"))
        {
            this.aisle = 3;
        }
        
        if (category.equalsIgnoreCase("CANDY"))
        {
            this.aisle = 4;
        }
        
        
        if (category.equalsIgnoreCase("CANNED FOODS"))
        {
            this.aisle = 5;
        }
        
        if (category.equalsIgnoreCase("PASTA"))
        {
            this.aisle = 6;
        }
        
        if (category.equalsIgnoreCase("SNACK FOODS"))
        {
            this.aisle = 7;
        }
        
        if (category.equalsIgnoreCase("BAKED GOODS"))
        {
            this.aisle = 8;
        }
        
        if (category.equalsIgnoreCase("FROZEN FOODS"))
        {
            this.aisle = 9;
        }
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
    
    public void setLocation(String location)
    {
        this.location = location;
    }
    
    public String getLocation()
    {
        return this.location;
    }
    
    public void setFoodDescription(String foodDescription)
    {
        this.foodDescription = foodDescription;
    }
    
    public String getFoodDescription()
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
    
    public String toString()
    {
        String str = "";
        str += "ID: " + this.productID + " , Name: " + this.productName + " , Quantity" + 
                this.quantity + " , Price: " + this.price + " , Location: " + this.location +
                " , Description: " + this.foodDescription + " , Category: " + this.category;
        return str;
    }
    
    public String toStringSale()
    {
        String str = "";
        str += "ID: " + this.productID + " , Name: " + this.productName +  
                " , Price: " + formatter.format(this.price) + " , Description: " + this.foodDescription 
                + " , Category: " + this.category;
        return str;
    }
}
