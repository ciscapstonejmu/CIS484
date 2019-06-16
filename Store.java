package pkg484groupproj;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Store {
    
    
    private int storeID;
    private String storeName;
    private String storeAddress;
    private ArrayList<Expense> associatedExpenses = new ArrayList<>(); 
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
    
    public int getID()
    {
        return this.storeID;
    }
    
    public void setName (String storeName)
    {
        this.storeName = storeName;
    }
    
    public String getName()
    {
        return this.storeName;
    }
    
    public void setAddress (String storeAddress)
    {
        this.storeAddress = storeAddress;
    }
    
    public String getAddress()
    {
        return this.storeAddress;
    }
            
    public String toString()
    {
        String str = "";
        str += "ID: " + this.storeID + " , Name: " + this.storeName + " , Address: " + this.storeAddress;
        return str;
    }
           
    
}
