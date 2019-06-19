package pkg484groupproj;

import java.io.Serializable;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Store{
    
    
    private int storeID;
    private String storeName;
    private String storeAddress;
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
    
    public String DisplayExpenses()
    {
        String str = "";
        str += this.storeName + "\n";
        for (int i = 0; i < this.associatedExpenses.size(); i++)
        {
            str += associatedExpenses.get(i).toString();
        }
        return str;
    }
            
    public String toString()
    {
        String str = "";
        str += "ID: " + this.storeID + " , Name: " + this.storeName + " , Address: " + this.storeAddress;
        return str;
    }
           
    
}
