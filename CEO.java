/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg484groupproj;

/**
 *
 * @author owner
 */
public class CEO extends Employee {
    
    
    private int CEOID;
    public static int nextID = 0;
    
    public CEO() 
    {
        super();
        this.CEOID = nextID++;
        
    }
    
    public CEO(String employeeName, String email, int phoneNumber, String address, double salary, double workPay, double timeWorked)
    {
        super(employeeName, email, phoneNumber, address, salary, workPay, timeWorked);
        this.CEOID = nextID++;
    }
    
    public int getID()
    {
        return this.CEOID;
    }
    
    public String toString()
    {
        String str = "";
        str += "ID: " + this.CEOID + " , Name: " + this.getName() +  ", Email: " + super.getEmail() +
                ", Phone Number: " + super.getPhoneNumber() + " , Address: " + super.getAddress() + " , Salary: " +
                super.getSalary() + " , Work Pay: " + super.getPay() + " , Time Worked: " + super.getTimeWorked();
        return str;
    }
            
}
