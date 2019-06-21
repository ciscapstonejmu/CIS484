package pkg484groupproj;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Store implements Serializable{
    
    
    private int storeID;
    private String storeName;
    private String storeAddress;
    private double revenue;
    private double cost;
    public ArrayList<CustSale> associatedRevenue = new ArrayList<>();
    public ArrayList<Expense> associatedExpenses = new ArrayList<>(); 
    public static int nextID = 0;
    public static ObservableList obsStore = FXCollections.observableArrayList();
    
    
    
    public Store (String storeName, String storeAddress)
    {
        this.storeName = storeName;
        this.storeAddress = storeAddress;
        this.storeID = nextID++; 
        //obsStore.add(this.storeID + ": " + storeName);
        obsStore.add(this.storeName);
    }
    
    public int getStoreID()
    {
        return this.storeID;
    }
    
    public void setName (String storeName)
    {
        this.storeName = storeName;
    }
    
    public String getStoreName()
    {
        return this.storeName;
    }
    
    public void setAddress (String storeAddress)
    {
        this.storeAddress = storeAddress;
    }
    
    public String getStoreAddress()
    {
        return this.storeAddress;
    }
    
    public void addExpense(Expense exp)
    {
        this.associatedExpenses.add(exp);
    }
    
        public void removeExpense (Expense exp)
    {
        this.associatedExpenses.remove(exp);
    }
    
    public int getExpensesLength()
    {
        return this.associatedExpenses.size();
    }
    public void addRevenue(CustSale sale){
        this.associatedRevenue.add(sale);
    }
    public void removeExpense(CustSale sale){
        this.associatedRevenue.remove(sale);
    }
    public String DisplayExpenses()
    {
        String str = "";
        str += this.storeName + "\n";
        for (int i = 0; i < this.associatedExpenses.size(); i++)
        {
            str += this.associatedExpenses.get(i).toString();
        }
        return str;
    }
    public double annualRevenue(String year)
    {
        double tR = 0.0;
        for(int i = 0; i<this.associatedRevenue.size(); i++){
            if(this.associatedRevenue.get(i).getSaleDate().substring(6).equalsIgnoreCase(year)){
                tR += this.associatedRevenue.get(i).getSubtotal();}
        }
        return tR;
    }
    public double annualExpense(String year)
    {
        double tR = 0.0;
        for(int i = 0; i<this.associatedExpenses.size(); i++){
            for(int k = 0; k<associatedExpenses.get(i).datesPaid.size(); k++)
            {
               if(this.associatedExpenses.get(i).datesPaid.get(k).substring(6).equalsIgnoreCase(year)){
                tR += this.associatedExpenses.get(i).pmtsMade.get(k);} 
            }
            
        }
        return tR;
    }
    public double calculateMonthProfit(String month)
    {
    double profit = 0;
    String DateRn = LocalDate.now().format(DateTimeFormatter.ofPattern("MM-dd-yyyy")).substring(0, 2);
    this.cost = 0.0;
    this.revenue = 0.0;
    for(int i = 0; i<this.associatedExpenses.size(); i++){
        for(int k = 0; k<this.associatedExpenses.get(i).datesPaid.size(); k++)
        {
           if(this.associatedExpenses.get(i).datesPaid.get(k).substring(0, 2).equalsIgnoreCase(DateRn))
           {
            this.cost += this.associatedExpenses.get(i).pmtsMade.get(k);
           } 
        }
        
    }
    for(int i = 0; i<this.associatedRevenue.size(); i++)
    {    
     if(this.associatedRevenue.get(i).getSaleDate().substring(0, 2).equalsIgnoreCase(DateRn))
        {
          this.revenue += this.associatedRevenue.get(i).getSubtotal();
        }        
    }
    profit = revenue - cost;
    return profit; 
    }
    public double calculateYearProfit(String year)
    {
    double profit = 0.0;
    String DateRn = LocalDate.now().format(DateTimeFormatter.ofPattern("MM-dd-yyyy")).substring(6);
    this.cost = 0.0;
    this.revenue = 0.0;
    for(int i = 0; i<this.associatedExpenses.size(); i++){
        for(int k = 0; k<this.associatedExpenses.get(i).datesPaid.size(); k++)
        {
           if(this.associatedExpenses.get(i).datesPaid.get(k).substring(6).equalsIgnoreCase(DateRn))
           {
            this.cost += this.associatedExpenses.get(i).pmtsMade.get(k);
           } 
        }
        
    }
    for(int i = 0; i<this.associatedRevenue.size(); i++)
    {    
     if(this.associatedRevenue.get(i).getSaleDate().substring(6).equalsIgnoreCase(DateRn))
        {
          this.revenue += this.associatedRevenue.get(i).getSubtotal();
        }        
    }
    profit = revenue - cost;
    return profit; 
    }
            
    public String toString()
    {
        String str = "";
        str += "ID: " + this.storeID + " , Name: " + this.storeName + " , Address: " + this.storeAddress;
        return str;
    }
           
    
}
