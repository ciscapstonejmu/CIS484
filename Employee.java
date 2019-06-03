package pkg484groupproj; 

public class Employee {
    
    private int employeeID;
    private String firstName;
    private String lastName; 
    private String email;
    private int phoneNumber;
    private String address;
    private double salary;
    private String jobTitle; 
    private double workPay;
    private double timeWorked;
    public static int nextID = 0;

    
    public Employee()
            {
                this.firstName = "NO NAME";
                this.lastName = "NO NAME"; 
                this.email = "NO EMAIL";
                this.phoneNumber = 0;
                this.address = "NO ADDRESS";
                this.salary = 0.0;
                this.workPay = 0.0;
                this.timeWorked = 0.0;
                this.employeeID = nextID++;
                this.jobTitle = " "; 
            }        
            
    
    
    public Employee(String firstName, String lastName, String email, int phoneNumber, String address, double salary, double workPay, double timeWorked, String jobTitle)
    {
        this.firstName = firstName;
        this.lastName = lastName; 
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.salary = salary;
        this.workPay = workPay;
        this.timeWorked = timeWorked;
        employeeID = nextID++;
        this.jobTitle = jobTitle; 
    }
    
    public Employee(String email, int phoneNumber, String address, double salary, double workPay, double timeWorked)
    {
        //this.employeeName = employeeName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.salary = salary;
        this.workPay = workPay;
        this.timeWorked = timeWorked;
    }
    
    
    public int getID()
    {
        return this.employeeID;
    }
    
    public void setFName (String firstName)
    {
        this.firstName = firstName;
    }
    
    public String getFName()
    {
        return this.firstName;
    }
    public void setLName (String lastName) {
        this.lastName = lastName; 
    }
    public String getLName () {
        return this.lastName; 
    }
   
    public void setEmail(String email)
    {
        this.email = email;
    }
         
    public String getEmail()
    {
        return this.email;
    }
    
    public void setPhoneNumber (int phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }
    
    public double getPhoneNumber()
    {
        return this.phoneNumber;
    }
    
        public void address (String address)
    {
        this.address = address;
    }
    
    public String getAddress()
    {
        return this.address;
    }
    
        public void setSalary (double Salary)
    {
        this.salary = salary;
    }
    
    public double getSalary()
    {
        return this.salary;
    }
    
        public void setPay (double workPay)
    {
        this.workPay = workPay;
    }
    
    public double getPay()
    {
        return this.workPay;
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
    public void setJobTitle() {
        this.jobTitle = jobTitle; 
    }
   
    public String toString()
    {
        String str = "";
        str += "ID: " + this.employeeID + " , First Name: " + this.firstName +  " , Last Name: " + this.lastName + ", Email: " + this.email +
                ", Phone Number: " + this.phoneNumber + " , Address: " + this.address + " , Salary: " +
                this.salary + " , Work Pay: " + this.workPay + " , Time Worked: " + this.timeWorked;
        return str;
                
    }
    
}
