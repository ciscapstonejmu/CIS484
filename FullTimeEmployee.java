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
public class FullTimeEmployee extends Employee {
    
    private int fullTimeID;
    public static int nextID = 0;

public FullTimeEmployee()
{
    super();
    this.fullTimeID = nextID++;
}

public FullTimeEmployee (String employeeName, String email, int phoneNumber, String address, double salary, double workPay, double timeWorked)
{
        super(employeeName, email, phoneNumber, address, salary, workPay, timeWorked);
        this.fullTimeID = nextID++;
        
}

public int getID()
{
    return this.fullTimeID;
}

public String toString()
 {
       String str = "";
       str += "ID: " + this.fullTimeID + " , Name: " + this.getName() +  ", Email: " + super.getEmail() +
               ", Phone Number: " + super.getPhoneNumber() + " , Address: " + super.getAddress() + " , Salary: " +
               super.getSalary() + " , Work Pay: " + super.getPay() + " , Time Worked: " + super.getTimeWorked();
        return str;
    }

}
    

