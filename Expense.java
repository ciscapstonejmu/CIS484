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
    private double totalAmount;
    private double remainingBalance;
    private Store payableStore;
    public static ObservableList obsExpense = FXCollections.observableArrayList();

    
    public static int nextID = 0;
    
        public Expense(String category, String description, double amountPaid, double totalAmount, String date)
    {
        this.category = category;
        this.description = description;
        this.amountPaid = amountPaid;
        this.totalAmount = totalAmount;
        this.date = date;
        this.status = status;
        
        expenseID = nextID++;
        obsExpense.add(this.date + ": " + this.category);
        
       
    }
    
    public Expense(String category, String description, double amountPaid, double amount, String date, Store store)
    {
        this.category = category;
        this.description = description;
        this.amountPaid = amountPaid;
        this.totalAmount = amount;
        this.date = date;
        this.status = status;
        this.remainingBalance = totalAmount - amountPaid;
        this.payableStore = store;
        //this.payableStore = payableStore;
       
        
        this.expenseID = nextID++;
        
        if (amountPaid < amount)
        {
            this.status = "Ongoing";
 
        }
        if (amountPaid >= amount)
        {
            this.status = "Paid";
        }
        
        Expense.obsExpense.add(expenseID + " : " + this.date + " , " + this.description);
        
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
    
    public void setTotalAmount(double amount)
    {
        this.totalAmount = amount;
    }
    
    public double getTotalAmount()
    {
        return this.totalAmount;
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
    
    public void setStore(Store s)
    {
        this.payableStore = s;
    }
    
    public Store getStore()
    {
        return this.payableStore;
    }
    
    public void setRemainingBalance(double rb)
    {
        this.remainingBalance = rb;
    }
    
    public double getRemainingBalance()
    {
        return this.remainingBalance;
    }
    
    public void payExpense(double payment)
    {
        this.amountPaid += payment;
        this.remainingBalance = totalAmount - amountPaid;
    }
    
    
    
    
    public String toString()
    {
        String str = "";
        str += "ID: " + this.expenseID + "Description: " + this.description + 
                " Category: " + this.category + " Amount Paid: " + this.amountPaid + "Total Amount: " 
                + this.totalAmount + " Date: " + this.date + " Status: " + this.status;
        return str;
    }
    
}
