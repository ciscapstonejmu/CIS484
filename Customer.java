package pkg484groupproj;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
 
public class Customer {
    
    private int customerID;
    private String customerName;
    private String email;
    private int phoneNumber;
    private String address;
    private String paymentMethod;
    public static int nextID = 0;
    ArrayList<Product> purchasedProducts = new ArrayList<>();
    public static ObservableList obsCust = FXCollections.observableArrayList(); 
    
    public Customer(String customerName, String email, int phoneNumber, String address, String paymentMethod)
    {
        this.customerName = customerName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.paymentMethod = paymentMethod;
    }
    
    public int getID()
    {
        return this.customerID;
    }
    
    public void setName(String customerName)
    {
        this.customerName = customerName;
    }
    
    public String getName()
    {
        return this.customerName;
    }
    
    public void setEmail(String emil)
    {
        this.email = email;
    }
    
    public String getEmail()
    {
        return this.email;
    }
    
    public void setPhoneNumber(int phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }
    
    public int getPhoneNumber()
    {
        return this.phoneNumber;
    }
    
    public void setAddress(String address)
    {
        this.address = address;
    }
    
    public String getAddress()
    {
        return this.address;
    }
    
    public void setPaymentMethod(String paymentMethod)
    {
        this.customerName = customerName;
    }
    
    public String getPaymentMethod()
    {
        return this.paymentMethod;
    }
    
    public void purchaseProduct(Product p)
    {
        this.purchasedProducts.add(p);
    }
    
    public String toString()
    {
        String str = "";
        str += "ID: " + this.customerID + " , Name: " + this.customerName + " , Email:" +
                this.email + " , Phone Number: " + this.phoneNumber + " , Address: " + this.address +
                " , Payment Method: " + this.paymentMethod;
        return str;
    }
    
    public String printProducts()
    {
        String str = "";
        for (int i = 0; i < this.purchasedProducts.size(); i++)
        {
            str += purchasedProducts.get(i).toString();
        }
        return str;
    }
    
}
