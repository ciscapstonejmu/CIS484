
package pkg484groupproj;

import java.io.*;
import java.util.*;

//Being used to create primary data files upon start of system, can be modified as needed.
public class LoadData {
        static public ArrayList<Employee> savedEmpList = new ArrayList<>();
        static public ArrayList<Employee> loadEmp;
    
    public static void main(String[] args) {
        
        savedEmpList.add(new Employee("Shuvam", "Mishra"));
        savedEmpList.add(new Employee("Christian", "Yun"));
        savedEmpList.add(new Employee("Meghana", "Krishna"));
        savedEmpList.add(new Employee("Pierre", "Giaon"));
        savedEmpList.add(new Employee("Ethan", "Johnson"));
        savedEmpList.add(new Employee("TJ", "Guilfoil"));
//        for(Employee emp : savedEmpList)
//           {
//               System.out.println(emp.toString());
//           }
        try
        {
           ObjectOutputStream instanceObjectOutput = new ObjectOutputStream(new FileOutputStream("employees.dat"));
           instanceObjectOutput.writeObject(savedEmpList);
           instanceObjectOutput.close();
           
           ObjectInputStream instanceObjectInput = new ObjectInputStream(new FileInputStream("employees.dat"));
           
           loadEmp = (ArrayList<Employee>)(instanceObjectInput.readObject());
           for(Employee emp : loadEmp)
           {
              
                   System.out.println(emp.toString());
               
           }
           
        }
        catch(Exception ex)
        {
            
        }
    }
    
}
