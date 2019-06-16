
package pkg484groupproj;

import java.io.*;
import java.util.*;

//Being used to create primary data files upon start of system, can be modified as needed.
public class LoadData {
        static ArrayList<Employee> savedEmpList = new ArrayList<>();
        static  ArrayList<Object> loadData;
        static ArrayList<Product> savedProd = new ArrayList<>(); 
        static ArrayList<Expense> savedExp = new ArrayList<>();
        static ArrayList<Customer> savedCust = new ArrayList<>(); 
        static ArrayList<Store> savedStore = new ArrayList<>(); 
        static ArrayList<ProductOrder> savedPO = new ArrayList<>(); 
        static ArrayList<CustSale> savedSale = new ArrayList<>();
        static ArrayList<String> savedExpCat = new ArrayList<>();
        static ArrayList<String> savedExpStat = new ArrayList<>();
        static ArrayList<Supplier> savedSupp = new ArrayList<>(); 
        static ArrayList<Member> savedMem = new ArrayList();
        static ArrayList<String> savedCat = new ArrayList<>();
    
    public static void main(String[] args) {
        
        savedEmpList.add(new Employee("Shuvam", "Mishra"));
        savedEmpList.add(new Employee("Christian", "Yun"));
        savedEmpList.add(new Employee("Meghana", "Krishna"));
        savedEmpList.add(new Employee("Pierre", "Giaon"));
        savedEmpList.add(new Employee("Ethan", "Johnson"));
        savedEmpList.add(new Employee("TJ", "Guilfoil"));
        
        savedSupp.add(new Supplier("Food Supplier", "571-GODUKES", "food@supplier.com","800 S Main Street", "Fuck drinks", "571-GOHOKIES", "contact@supplier.com"));
        savedSupp.add(new Supplier("Beverage Supplier", "571-GODUKES", "beverage@supplier.com","800 S Main Street", "Fuck food", "571-GOHOKIES", "contact@supplier.com"));
        savedSupp.add(new Supplier("Snack Supplier", "571-GODUKES", "snack@supplier.com","800 S Main Street", "Fuck you", "571-GOHOKIES", "contact@supplier.com"));
        
        savedStore.add(new Store("Harrisonburg", "123 lane"));
        
        savedProd.add(new Product("Cheetos", 15.00,100,savedStore.get(0), "Cheesy Goodness", "SNACK FOODS", savedSupp.get(2)));
        savedProd.add(new Product("Steak", 50.00, 30, savedStore.get(0), "Makes you want Mooo-re", "FROZEN FOODS", savedSupp.get(0)));
        savedProd.add(new Product("MtnDew: GameFuel", 50.00, 30, savedStore.get(0), "Become a 12 year old dissing everyones moms", "BEVERAGES", savedSupp.get(1)));
//        for(Employee emp : savedEmpList)
//           {
//               System.out.println(emp.toString());
//           }
        try
        {
           ObjectOutputStream instanceObjectOutput = new ObjectOutputStream(new FileOutputStream("employees.dat"));
           instanceObjectOutput.writeObject(savedEmpList);
           instanceObjectOutput.close();
           
           for(Employee emp: savedEmpList)
           {
               System.out.println(emp);
           }
        }
        catch(Exception ex)
        {
         System.out.println(ex);
        }
        try
        {
           ObjectOutputStream instanceObjectOutput = new ObjectOutputStream(new FileOutputStream("suppliers.dat"));
           instanceObjectOutput.writeObject(savedSupp);
           instanceObjectOutput.close();
           
           for(Supplier sup: savedSupp)
           {
               System.out.println(sup);
           }
        }
        catch(Exception ex)
        {
         System.out.println(ex);
        }
        try
        {
           ObjectOutputStream instanceObjectOutput = new ObjectOutputStream(new FileOutputStream("products.dat"));
           instanceObjectOutput.writeObject(savedProd);
           instanceObjectOutput.close();
           
           for(Product prod: savedProd)
           {
               System.out.println(prod);
           }
        }
        catch(Exception ex)
        {
         System.out.println(ex);
        }
    }
    
}
