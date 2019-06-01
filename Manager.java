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
public class Manager extends Employee {
    
    private int managerID;
    public static int nextID = 0;

public Manager()
{
    super();
    this.managerID = nextID++;
}

public Manager (String employeeName, String email, int phoneNumber, String address, double salary, double workPay, double timeWorked)
{
        super(employeeName, email, phoneNumber, address, salary, workPay, timeWorked);
        this.managerID = nextID++;
        
}

public int getID()
{
    return this.managerID;
}

public String toString()
{
    String str = "";
    str += "ID: " + this.managerID + " , Name: " + this.getName() +  ", Email: " + super.getEmail() +
        ", Phone Number: " + super.getPhoneNumber() + " , Address: " + super.getAddress() + " , Salary: " +
        super.getSalary() + " , Work Pay: " + super.getPay() + " , Time Worked: " + super.getTimeWorked();
    return str;
    }
}
    

