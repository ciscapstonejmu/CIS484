package pkg484groupproj;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Product implements Serializable{
    private int productID;
    private String productName;
    private int quantity;
    private double price;
    private Store store;
    private String location;
    private String foodDescription;
    private String category;
    private int aisle;
    public boolean hasImage = false;
    private int saleYear;
    private String supplier;
    private Supplier supp; 
    private int saleMonth;
    private int saleDay;
    public static int nextID = 0;
    private double cost;
    public static ObservableList obsProd = FXCollections.observableArrayList(); 
    private File img;
    //Fromatter for money figures
    DecimalFormat formatter = new DecimalFormat("#0.00");
    
    public Product()
    {
        this.productName = "NO NAME";
        this.quantity = 0;
        this.price = 0.0;
        this.cost = 0.0;
        this.foodDescription = "NO DESCRIPTION";
        this.category = "NO CATEGORY";
        
    }

     public Product(String productName, int quantity, double price, double cost, Store store, String foodDescription, String category, Supplier supp, File img)
    {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.location = store.getStoreName();
        this.foodDescription = foodDescription;
        this.category = category;
        this.supp = supp; 
        this.supplier = supp.getName();
        this.img = img;
        this.hasImage = true;
        this.productID = nextID++;
        this.store = store; 
        this.cost = cost; 
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
    public Product(String productName, double price, double cost, int quantity, Store store, String foodDescription, String category, Supplier supp)
    {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.location = store.getStoreName();
        this.foodDescription = foodDescription;
        this.category = category;
        this.supp = supp; 
        this.supplier = supp.getName();
        this.img = null;
        this.productID = nextID++;
        this.store = store; 
        this.cost = cost; 
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
     public void setProductID(int i ) {
        this.productID = i; 
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
    public void addQuantity(int quantity)
    {
        this.quantity += quantity;
    }
    public void sellQuantity(int quantity)
    {
        this.quantity -= quantity;
    }
    public void setPrice(double price)
    {
        this.price = price;
    }
    
    public double getPrice()
    {
        return this.price;
    }
    public double getCost() {
        return this.cost; 
    }
    public void setCost(double cost) {
        this.cost = cost; 
    }
    public void setLocation(String location)
    {
        this.location = location;
    }
    
    public String getLocation()
    {
        return this.location;
    }
    
    public void setSupplier(String supplier)
    {
        this.supplier = supplier;
    }
    
    public String getSupplier()
    {
        return this.supplier;
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
    
    public void setImage (File file) 
    {
        this.img = file;
    }
    
    public BufferedImage getImage() throws IOException 
    {
        return ImageIO.read(img);
    }
    public void setStore(Store store) {
        this.store = store; 
    }
    public Store getStore() {
        return this.store; 
    }
    
    public void setSupp(Supplier supp) {
        this.supp = supp;
    }
    public Supplier getSupp(){
        return this.supp;
    }
    public void setFile(File file){
        this.img = file;
        this.hasImage = true;
    }
    public File getFile(){
        return this.img;
    }
    public void updateSupp(Supplier supp) {
        this.supp = supp; 
        this.supplier = supp.getName(); 
    }
    public void updateCmboBox(Product prod, int spot){
        
        obsProd.remove(spot);
        obsProd.add(spot, prod.productID + "" + prod.productName);
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
        str += "ID: " + this.productID + " " + this.productName +  
                " , Price: " + formatter.format(this.price);
        return str;
    }
   
}
