package pkg484groupproj;


public class Employee {
    
    private int employeeID;
    private String employeeName;
    private String email;
    private int phoneNumber;
    private String address;
    private double salary;
    private double workPay;
    private double timeWorked;
    public static int nextID = 0;
    
    public Employee()
            {
                this.employeeName = "NO NAME";
                this.email = "NO EMAIL";
                this.phoneNumber = 0;
                this.address = "NO ADDRESS";
                this.salary = 0.0;
                this.workPay = 0.0;
                this.timeWorked = 0.0;
                this.employeeID = nextID++;
            }        
            
    
    
    public Employee(String employeeName, String email, int phoneNumber, String address, double salary, double workPay, double timeWorked)
    {
        this.employeeName = employeeName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.salary = salary;
        this.workPay = workPay;
        this.timeWorked = timeWorked;
        employeeID = nextID++;
    }
    
    public Employee(String email, int phoneNumber, String address, double salary, double workPay, double timeWorked)
    {
        this.employeeName = employeeName;
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
    
    public void setName (String employeeName)
    {
        this.employeeName = employeeName;
    }
    
    public String getName()
    {
        return this.employeeName;
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
    
    public String toString()
    {
        String str = "";
        str += "ID: " + this.employeeID + " , Name: " + this.employeeName +  ", Email: " + this.email +
                ", Phone Number: " + this.phoneNumber + " , Address: " + this.address + " , Salary: " +
                this.salary + " , Work Pay: " + this.workPay + " , Time Worked: " + this.timeWorked;
        return str;
                
    }
    
}
