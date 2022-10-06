/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jd522_sa;

import java.sql.*;
import javax.swing.JOptionPane;
import java.util.Date;

/**
 *
 * @author Devon
 */
public class Account {
    private int ID = 0;
    private double Balance = 0.0;  
    private double inBalance = 0.0;
    private String User = "";
    private Date dateCreated;

    public Account() {
        dateCreated = new Date();
    }

    public Account(int ID, double Balance, double inBalance, String User ) {
        this();
        this.ID = ID;
        this.User = User;
        this.Balance = Balance;
        this.inBalance = inBalance;        
    }

    public int getId() {
        return this.ID;
    }
    
    public String getUser() {
        return this.User;
    }

    public double getBalance() {
        return this.Balance;
    }

    public double getinBalance() {
        return this.inBalance;
    }
    
    public String getDateCreated() {
        return this.dateCreated.toString();
    }

    public void setId(int ID) {
        this.ID = ID;
    }
    
    public void setUser(String User) 
    {
        this.User = User;
    }

    public void setBalance(double Balance) 
    {
        this.Balance = Balance;
    }
    
    public void setinBalance(double inBalance) 
    {
        this.inBalance = inBalance;
    }

    public void withdraw(double amount)
    {
        try{
        if(amount < Balance)
        {
            this.Balance = Balance - amount; //Withdraw Calculation
            
            //Establish Database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ctuatm" , "root" , "2009@Vianca");
            
            //Create Statement and execute
            Statement state = conn.createStatement();
            state.execute("Update atm set CURRENTBALANCE = '" + getBalance() + "' where ID = " + getId() + "");
            JOptionPane.showMessageDialog(null, "Withdaw Succesfull");
            
            //Close Connection
            state.close();
            conn.close();
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Withdrawal amount is greater than current balance");
        }
        }
        catch (Exception e) 
        {
           JOptionPane.showMessageDialog(null,e);
        }
    }

    public void deposit(double amount) {
        try{
        if(amount < 10000)
        {
            this.Balance = Balance + amount; //Withdraw Calculation
            
            //Establish Database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ctuatm" , "root" , "2009@Vianca");
            
            //Create Statement and execute
            Statement state = conn.createStatement();
            state.execute("Update atm set CURRENTBALANCE = '" + Balance + "' where ID = " + getId() + "");
            JOptionPane.showMessageDialog(null, "Deposit Succesfull");
            
            //Close Connection
            state.close();
            conn.close();
        }
        else
        {
            JOptionPane.showMessageDialog(null, "No amount greater than 10.000 will be deposited. Please contact the bank");
        }
        }
        catch (Exception e) 
        {
           JOptionPane.showMessageDialog(null,e);
        }
    }
}
//

