package pkg484groupproj; 

import java.io.Serializable;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Employee implements Serializable{
    
    private int employeeID;
    private String firstName;
    private String lastName; 
    private String Email;
    private String phoneNumber;
    private String address;
    private double salary;
    private String jobTitle; 
    private double timeWorked;
    public static int nextID = 0;
    public static ObservableList obsEmp = FXCollections.observableArrayList();
    public static ArrayList<Employee> empAcc = new ArrayList<>();
    public static ArrayList<Employee> admins = new ArrayList<>();
    private String employeeType; 
    private String employeeCategory; 
    public String storeLoc;
    private String username;
    private String password;
    private ArrayList<Payroll> empPayroll;
    
    
    
    public Employee()
            {
                this.firstName = "NO NAME";
                this.lastName = "NO NAME"; 
                this.Email = "NO Email";
                this.phoneNumber = " ";
                this.address = "NO ADDRESS";
                this.salary = 0.0;                
                this.timeWorked = 0.0;
                this.employeeID = nextID++;
                this.jobTitle = " ";                
                this.storeLoc = " ";
                this.username = "NO USERNAME";
                this.password = "NO PASSWORD";
                
            }        
    //System Administrator Constructors for designated personel       
    public Employee(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
        this.jobTitle = "System Admin";
        this.employeeType = "Manager";
        this.username = firstName;
        this.password = lastName;
        if(admins.size() > 0)
        {
        int counter = 0;
        int loop = 0;
        while(loop < admins.size())
        {
            Employee temp = admins.get(loop);
            if(temp.firstName.equalsIgnoreCase(this.firstName))
            {
               counter++;
            }
            loop++;
        }
        if(counter > 0)
            {
                this.username = this.username + counter;  
            }            
        }
       admins.add(this);
        
    }
    
    public Employee(String firstName, String lastName, String Email, String phoneNumber, String address, double salary, String jobTitle,
            String employeeType, String employeeCategory, String storeLoc )  
    {
        this.firstName = firstName;
        this.lastName = lastName; 
        this.Email = Email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.salary = salary;
        //this.timeWorked = timeWorked;
        this.employeeID = nextID++;
        this.jobTitle = jobTitle; 
        this.employeeType = employeeType; //ceo, manager, or regular 
        this.employeeCategory = employeeCategory; //full time or part time 
        this.storeLoc = storeLoc;
        this.username = this.firstName;
        if(empAcc.size() > 0)
        {
            int counter = 0;
            int loop = 0;
            while(loop < empAcc.size())
            {
                Employee temp = empAcc.get(loop);
                if(temp.firstName.equalsIgnoreCase(this.firstName))
                {
                   counter++;
                }
                loop++;
            }
            if(counter > 0){
              this.username = this.username + counter;  
            }
            
        }
        this.password = this.lastName.toLowerCase();
        empAcc.add(this);
        obsEmp.add(this.employeeID + ": " + this.firstName + " " + this.lastName);
        
    }
    
    public Employee(String Email, String phoneNumber, String address, double salary, double timeWorked)
    {
        //this.employeeName = employeeName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.salary = salary;
        this.timeWorked = timeWorked;
    }
    
     public Employee(String firstName, String lastName, String Email, String phoneNumber, String address, double salary, String jobTitle)
    {
        this.firstName = firstName;
        this.lastName = lastName; 
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.salary = salary;  
        employeeID = nextID++;
        this.jobTitle = jobTitle; 
    }
    
    public int getEmployeeID()
    {
        return this.employeeID;
    }
    
    public void setFName (String firstName)
    {
        this.firstName = firstName;
    }
    
    public String getFirstName()
    {
        return this.firstName;
    }
    public void setLName (String lastName) {
        this.lastName = lastName; 
    }
    public String getLastName () {
        return this.lastName; 
    }
   
    public void setEmail(String Email)
    {
        this.Email = Email;
    }
         
    public String getEmail()
    {
        return this.Email;
    }
    
    public void setPhoneNumber (String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }
    
    public String getPhoneNumber()
    {
        return this.phoneNumber;
    }
    
        public void setAddress (String address)
    {
        this.address = address;
    }
    
    public String getAddress()
    {
        return this.address;
    }
    
        public void setSalary (double salary)
    {
        this.salary = salary;
    }
    
    public double getSalary()
    {
        return this.salary;
    }   
    
        public void setTimeWorked (double timeWorked)
    {
        this.timeWorked = timeWorked;
    }
    
    public double getTimeWorked()
    {
        return this.timeWorked;
    }
    public String getJobTitle() {
        return this.jobTitle; 
    }
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle; 
    }
    public String getStoreLoc(){
        return this.storeLoc;
    }
    public void setStore(String storeLoc){
        this.storeLoc = storeLoc;
    }
    public void setEmployeeType (String employeeType)
    {
        this.employeeType = employeeType;
    }
    
    public String getEmployeeType()
    {
        return this.employeeType;
    }
    
    public void setEmployeeCategory (String employeeCategory)
    {
        this.employeeCategory = employeeCategory;
    }
    
    public String getEmployeeCategory()
    {
        return this.employeeCategory;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }
    public String getUsername()
    {
        return this.username;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    public String getPassword()
    {
        return this.password;
    }
    public String toString()
    {
        String str = "";
        str += "ID: " + this.employeeID + " , Username: "+ this.username + " , First Name: " + this.firstName +  " , Last Name: " + this.lastName + ", Email: " + this.Email +
                ", Phone Number: " + this.phoneNumber + " , Address: " + this.address + " , Salary: " +
                this.salary + " , Time Worked: " + this.timeWorked;
        return str;
                
    }
    
}
