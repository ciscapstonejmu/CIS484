package pkg484groupproj;

public class Expense {
    private int expenseID;
    private String expenseType;
    private double amountPaid;
    public static int nextID = 0;
    
    public Expense(String expenseType, double amountPaid)
    {
        this.expenseType = expenseType;
        this.amountPaid = amountPaid;
        expenseID = nextID++;
    }
    
    public int getID()
    {
        return this.expenseID;
    }
    
    public void setType (String expenseType)
    {
        this.expenseType = expenseType;
    }
    
    public String getType()
    {
        return this.expenseType;
    }
    
    public void setAmountPaid(double amountPaid)
    {
        this.amountPaid = amountPaid;
    }
    
    public double getAmountPaid()
    {
        return this.amountPaid;
    }
    
    public String toString()
    {
        String str = "";
        str += "ID: " + this.expenseID + " , Amount Paid: " + this.amountPaid;
        return str;
    }
    
}
