package pkg484groupproj;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Supplier {
    
    private int supplierID;
    private String supplierName;
    private String supplierAddress;
    private String supplierCountry;
    private String salesContactName;
    private String salesContactPhoneNumber;
    private String salesContactEmail;
    private String salesContactAddress;
    public static int nextID;
    private String supplierPhone; 
    private String supplierEmail; 
    public static ObservableList obsSupp = FXCollections.observableArrayList(); 
 
     public Supplier (String supplierName, String supplierPhone, String supplierEmail, 
             String supplierAddress, String salesContactName, String salesContactPhoneNumber, String salesContactEmail)
    {
        this.supplierName = supplierName;
        this.supplierPhone = supplierPhone; 
        this.supplierAddress = supplierAddress;
        this.supplierEmail = supplierEmail; 
        this.salesContactName = salesContactName;
        this.salesContactPhoneNumber = salesContactPhoneNumber;
        this.salesContactEmail = salesContactEmail;
       
        supplierID = nextID++;
    }
    public int getID()
    {
        return this.supplierID;
    }
    public void setName(String supplierName)
    {
        this.supplierName = supplierName;
    }
    
    public String getName()
    {
        return this.supplierName;
    }
    
    public void setSupplierAddress(String supplierAddress)
    {
        this.supplierAddress = supplierAddress;
    }
    
    public String getSupplierAddress()
    {
        return this.supplierAddress;
    }
    
     public void setCountry(String supplierCountry)
    {
        this.supplierCountry = supplierCountry;
    }
    
    public String getCountry()
    {
        return this.supplierCountry;
    }
    
    public void setContactName(String salesContactName)
    {
        this.salesContactName = salesContactName;
    }
    
    public String getContactName()
    {
        return this.salesContactName;
    }
    
    public void setContactPhone(String salesContactPhoneNumber)
    {
        this.salesContactPhoneNumber = salesContactPhoneNumber;
    }
    
    public String getContactPhone()
    {
        return this.salesContactPhoneNumber;
    }
    
    public void setContactEmail(String salesContactEmail)
    {
        this.salesContactEmail = salesContactEmail;
    }
    
    public String getContactEmail()
    {
        return this.salesContactEmail;
    }
    
    public void setContactAddress(String salesContactAddress)
    {
        this.salesContactAddress = salesContactAddress;
    }
    
    public String getContactAddress()
    {
        return this.salesContactAddress;
    }
    public String getSupplierPhone() {
        return this.supplierPhone; 
    }
    public void setSupplierPhone(String supplierPhone) {
        this.supplierPhone = supplierPhone; 
    }
    public void setSupplierEmail(String supplier) {
        this.supplierEmail = supplierEmail; 
    }
    public String getSupplierEmail() {
        return this.supplierEmail; 
    }
    public String toString()
    {
        String str = "";
        str += "Supplier ID: " + this.supplierID + " , Supplier Name: " + this.supplierName + " , Suppler Phone Number: " +
                this.supplierPhone + " , Supplier Email: " + this.supplierEmail + 
                " , Supplier Address: " + this.supplierAddress + " , Sales Contact Name: " + this.salesContactName +
                " , Sales Contact Phone Number: " + this.salesContactPhoneNumber + 
                " , Sales Contact Email: " + this.salesContactEmail;
        return str;
    }
    
    
    
}
