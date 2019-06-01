package pkg484groupproj;

public class ProductOrder {
    
    private int productOrderID;
    private String orderDate;
    private int quantity;
    private double unitCost;
    private double subtotal;
    private String deliveryDate;
    public static int nextID;
    
    public ProductOrder (String orderDate, int quantity, double unitCost, double subtotal, String deliveryDate)
    {
        this.orderDate = orderDate;
        this.quantity = quantity;
        this.unitCost = unitCost;
        this.subtotal = subtotal;
        this.deliveryDate = deliveryDate;
        productOrderID = nextID++;
    }
    
    public int getID()
    {
        return this.productOrderID;
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
    
    public String toString()
    {
        String str = "";
        str += "ID: " + this.productOrderID + " , Order Date: " + this.orderDate + " , Quantity: " +
                this.quantity + "Unit Cost: " + this.unitCost + " , Subtotal: " + this.subtotal +
                " , Delivery Date: " + this.deliveryDate;
        return str;
    }
    
    
}
