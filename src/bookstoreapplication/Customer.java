/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoreapplication;


import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Customer {
    private String name;
    private String pass;
    static private String[] list = new String[100];
    static private int top = 0;
    private final File file;
    private int points;
    private String status;
    static ArrayList<Customer> Clients = new ArrayList<>();
    public File getFile() {
        return file;
    }
    
    public Customer getClient(int i) {
        return Clients.get(i);
    }

// Requires : 
// Modifies : list
// Effects : Resets the list using the information within file

    public void resetList() {
        Scanner s = null;
        String str;
                        try {
                            s = new Scanner(file);
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(BookstoreApplication.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        while(s.hasNextLine()) {
                            str = s.nextLine();
                            addList(str);
                        }
    }

    // creating a dummy default constructor
// Requires : File CustomerList.txt
// Modifies :
// Effects : constructor, makes sure file's file exists
    public Customer (){
        file = new File("CustomerList.txt");
        try {
            if (file.createNewFile());
        } catch (IOException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
        checkStatus();
    }

// Requires : File CustomerList.txt
// Modifies :
// Effects : constructor, makes sure file's file exists

    public Customer (String username, String password){
        this.name = username;
        this.pass = password;
        file = new File("CustomerList.txt");
        try {
            if (file.createNewFile());
        } catch (IOException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
        checkStatus();
    }
    // Requires : 
// Modifies : 
// Effects : returns top
    public int getTop() {
        return top;
    }
    // Requires : 
// Modifies : 
// Effects : returns list
    public String[] getList() {
        return list;
    }

// Requires : Stirng str
// Modifies : Clients, list
// Effects : Adds new customer to clients and new  customer string to list

    public void addList(String str) {
        list[top] = str;
        Clients.add(new Customer(str.substring(0, str.indexOf("  ")), str.substring(str.indexOf("  ") + 2)));
        top++;
    }
// Requires : File file
// Modifies : File file
// Effects : Adds msg in file

    public void write(String msg){

        FileWriter fw;
        try {
            fw = new FileWriter(file,true);
             fw.write(msg + "\n");
             fw.close();
        } 
        catch (IOException e) {
           System.out.println("An error");
           e.printStackTrace();
        }


    }
    // Requires : String username
// Modifies : name
// Effects : Changes the name to username
    
     public void setName(String username) {
        this.name = username;

    }

    // Requires : String pass
// Modifies : name
// Effects : Changes the pass to password
    public void setPass(String password) {
        this.pass = password;
        write(name+"  "+password);
    }
// Requires : 
// Modifies : 
// Effects : returns name

    public String getName() {
        return name;
    }
    
// Requires : 
// Modifies : 
// Effects : returns pass
    public String getPass() {
        return pass;
    }

// Requires : File file
// Modifies : File file
// Effects : Clears file

    public void clear() {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
        writer.print("");
        writer.close();
    }

// Requires : 1 or more Items
// Modifies : list, top
// Effects : Removes certain item depending on User

    void removeItem(int badLine) {
        for(int i = badLine; i < top; i++) {
            list[i] = list[i + 1];
        }
        System.out.println(list[badLine] + "\n");
        Clients.remove(badLine);
        list[top - 1] = null;
        top--;
    }
    // Requires : 
// Modifies : 
// Effects : returns points
    public int getPoints() {
        return points;
    }
    // Requires : 
// Modifies : 
// Effects : returns status
    public String getStatus() {
        return status;
    }

// Requires : Points have to be of integer type
// Modifies :
// Effects : Status gets provided a string depending on the points

    public void checkStatus() {
        if(points<1000){
            status = "Silver";
        } 
        
        else{
            status = "Gold";
        }
    }

    public void setPoints(int num) {
        points = num;
        checkStatus();
    }
    
    
    


}

