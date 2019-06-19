package pkg484groupproj;
//Authors: Meghana Krishna, TJ Guilfoil, Shuvam Mishra, Ethan Johnson, Christian Yun, Pierre Giaon
//CIS 484 DR. EZELL
//MIDPOINT SUBMISSION
//Description: This system is a grocery store POS, employee, and inventory management system for the employees to use 
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProductOrder {
    
    private int productOrderID;
    private String orderDate;
    private int quantity;
    private double unitCost;
    private double unitPrice;
    private String PODesc;
    private double subtotal;
    private String deliveryDate;
    public static int nextID;
    private String prodName; 
    private String supplier; 
    private String store; 
    private Store storeLoc; 
    private Supplier associatedSupplier;
    private String category; 
    
    public static ObservableList obsProdOrder = FXCollections.observableArrayList(); 
    
    public ProductOrder (String orderDate, int quantity, double unitCost, double subtotal, String deliveryDate)
    {
        this.orderDate = orderDate;
        this.quantity = quantity;
        this.unitCost = unitCost;
        this.subtotal = subtotal;
        this.deliveryDate = deliveryDate;
        productOrderID = nextID++;
    }
    public ProductOrder (int quantity, String prodName, double unitCost, double subtotal, String deliveryDate, String orderDate,
            String supplier, String store, String category)
    {
        this.orderDate = orderDate;
        this.quantity = quantity;
        this.unitCost = unitCost;
        this.subtotal = subtotal;
        this.deliveryDate = deliveryDate;
        productOrderID = nextID++;
        this.prodName = prodName;
        this.supplier = supplier; 
        this.store = store; 
        this.category = category; 
        this.orderDate = orderDate; 
    }
    public ProductOrder (int quantity, String prodName, double unitCost, double unitPrice, double subtotal, String deliveryDate, String orderDate,
            Supplier supp, Store storeLoc, String category, String PODesc)
    {
        this.orderDate = orderDate;
        this.quantity = quantity;
        this.unitCost = unitCost;
        this.unitPrice = unitPrice; 
        this.subtotal = subtotal;
        this.deliveryDate = deliveryDate;
        productOrderID = nextID++;
        this.prodName = prodName;
        this.supplier = supplier; 
        this.storeLoc = storeLoc; 
        this.store = storeLoc.getStoreName(); 
        this.associatedSupplier = supp; 
        this.supplier = supp.getName(); 
        this.category = category; 
        this.orderDate = orderDate; 
        this.PODesc = PODesc; 
    }
    
    public int getID()
    {
        return this.productOrderID;
    }
    public void setID() {
        this.productOrderID = productOrderID; 
    }
    public String getProdOName() {
        return this.prodName; 
    }
    public void setProdOName(String prodName) {
        this.prodName = prodName; 
    }
    public void setDate(String orderDate)
    {
        this.orderDate = orderDate;
    }
    
    public String getDate()
    {
        return this.orderDate;
    }
    
    
    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
    
    public int getQuantity()
    {
        return this.quantity;
    }
    
    public void setUnitCost(double unitCost)
    {
        this.unitCost = unitCost;
    }
    
    public double getUnitCost()
    {
        return this.unitCost;
    }
    
    public void setSubtotal(double subtotal)
    {
        this.subtotal = subtotal;
    }
    
    public double getSubtotal()
    {
        return this.subtotal;
    }
    
    public void setDeliveryDate(String deliveryDate)
    {
        this.deliveryDate = deliveryDate;
    }
    
    public String getDeliveryDate()
    {
        return this.deliveryDate;
    }
    public void setCategory(String category) {
        this.category = category; 
    }
    public String getCategory() {
        return this.category; 
    }
    public void setStore(String store) {
        this.store = store; 
    }
    public String getStore() {
        return this.store; 
    }
    public void setSupplier(String supplier) {
        this.supplier = supplier; 
    }
    public String getSupplier() {
        return this.supplier; 
    }
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate; 
    }
   
    public  void setStoreLoc(Store storeLoc) {
        this.storeLoc = storeLoc;
    }
    
    public Store getStoreLoc() {
        return this.storeLoc;
    }
    
    public void setAssociatedSupplier (Supplier supp)
    {
        this.associatedSupplier = supp;
    }
    
    public Supplier getAssociatedSupplier()
    {
        return this.associatedSupplier;
    }
    public String getPODesc(){
       return this.PODesc; 
    }
    public String toString()
    {
        String str = "";
        str += "ID: " + this.productOrderID + " , Order Date: " + this.orderDate + " , Quantity: " +
                this.quantity + " , Unit Cost: $" + this.unitCost + " , Subtotal: $" + this.subtotal + " , Delivery Date: " + this.deliveryDate + 
                ", Category: " + this.category + " , Supplier: " + this.supplier
                + " , Store: " + this.store; 
        //str += "ID: " + this.productOrderID + " , Order Date: " + this.orderDate + " , Quantity: " + this.quantity; 
        //str+= 
        return str;
    }
    
    
}
