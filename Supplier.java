package pkg484groupproj;

public class Supplier {
    
    private int supplierID;
    private String supplierName;
    private String supplierAddress;
    private String supplierCountry;
    private String salesContactName;
    private int salesContactPhoneNumber;
    private String salesContactEmail;
    private String salesContactAddress;
    public static int nextID;
    
    public Supplier (String supplierName, String address, String country, String salesContactName, int salesContactPhoneNumber, String salesContactEmail, String salesContactAddress)
    {
        this.supplierName = supplierName;
        this.supplierAddress = supplierAddress;
        this.supplierCountry = supplierCountry;
        this.salesContactName = salesContactName;
        this.salesContactPhoneNumber = salesContactPhoneNumber;
        this.salesContactEmail = salesContactEmail;
        this.salesContactAddress = salesContactAddress;
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
    
    public void setContactPhone(int salesContactPhoneNumber)
    {
        this.salesContactPhoneNumber = salesContactPhoneNumber;
    }
    
    public int getContactPhone()
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
    
    public String toString()
    {
        String str = "";
        str += "Supplier ID: " + this.supplierID + " , Supplier Name: " + this.supplierName +
                " , Supplier Address: " + this.supplierAddress + " , Supplier Country: " +
                this.supplierCountry + " , Sales Contact Name: " + this.salesContactName +
                " , Sales Contact Phone Number: " + this.salesContactPhoneNumber + 
                " , Sales Contact Email: " + this.salesContactEmail + " , Sales Contact Address: " +
                this.salesContactAddress;
        return str;
    }
    
    
    
}
