package pkg484groupproj;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Expense {
    private int expenseID;
    private String category;
    private String description;
    private String date;
    private String status;
    private double amountPaid;
    public static ObservableList obsExpense = FXCollections.observableArrayList();
    public static int nextID = 0;
    
    public Expense(String category, String description, double amountPaid, String date, String status)
    {
        this.category = category;
        this.description = description;
        this.amountPaid = amountPaid;
        this.date = date;
        this.status = status;
        
        expenseID = nextID++;
        obsExpense.add(this.date + ": " + this.category);
        
    }
    
    public int getExpenseID()
    {
        return this.expenseID;
    }
    
    public void setCategory (String expenseType)
    {
        this.category = expenseType;
    }
    
    public String getCategory()
    {
        return this.category;
    }
    
    public void setDescription(String desc)
    {
        this.description = desc;
    }
    
    public String getDescription()
    {
        return this.description;
    }
    
    public void setAmountPaid(double amountPaid)
    {
        this.amountPaid = amountPaid;
    }
    
    public double getAmountPaid()
    {
        return this.amountPaid;
    }
    
    public void setDate(String d)
    {
        this.date = d;
    }
    
    public String getDate()
    {
        return this.date;
    }
    
    public void setStatus(String s)
    {
        this.status = s;
    }
    
    public String getStatus()
    {
        return this.status;
    }
    
    
    public String toString()
    {
        String str = "";
        str += "ID: " + this.expenseID + "Description: " + this.description + 
                " Category: " + this.category + " Amount Paid: " + this.amountPaid +
                " Date: " + this.date + " Status: " + this.status;
        return str;
    }
    
}
