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
public class PartTimeEmployee extends Employee {
    
    private int partTimeID;
    public static int nextID = 0;

public PartTimeEmployee()
{
    super();
    this.partTimeID = nextID++;
}

public PartTimeEmployee (String employeeName, String email, int phoneNumber, String address, double salary, double workPay, double timeWorked)
{
        super(employeeName, email, phoneNumber, address, salary, workPay, timeWorked);
        this.partTimeID = nextID++;
        
}

public int getID()
{
    return this.partTimeID;
}

public String toString()
{
    String str = "";
    str += "ID: " + this.partTimeID + " , Name: " + this.getName() +  ", Email: " + super.getEmail() +
        ", Phone Number: " + super.getPhoneNumber() + " , Address: " + super.getAddress() + " , Salary: " +
        super.getSalary() + " , Work Pay: " + super.getPay() + " , Time Worked: " + super.getTimeWorked();
    return str;
    }

}
    
    
