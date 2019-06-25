package pkg484groupproj;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
    private String store;
    public ArrayList<String> datesPaid = new ArrayList<>();
    public ArrayList<Double> pmtsMade = new ArrayList<>();
    public static ObservableList obsExpense = FXCollections.observableArrayList();

    
    public static int nextID = 0;
    
    public Expense()
    {
        this.category = "NO CATEGORY";
        this.description = "NO DESCRIPTION";
        this.date = "NO DATE";
        this.status = "NO STATUS";
        this.amountPaid = 0.0;
        this.totalAmount = 0.0;
        this.remainingBalance = 0.0;
        this.store = "NO STORE";
    }
    
    
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
        this.store = this.payableStore.getStoreName() + ", " + this.payableStore.getStoreAddress();
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
        
        obsExpense.add(expenseID + " : " + this.date + " , " + this.description);
        
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
    
    public Store getPayableStore()
    {
        return this.payableStore;
    }
    public String getStore()
    {
        return this.store;
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
        this.remainingBalance = this.totalAmount - this.amountPaid;
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
