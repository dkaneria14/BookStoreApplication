/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoreapplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Books {
    
    private String name;
    private double price;
    static Books cart;
    private final File shelf;
    static private String[] titles = new String[100];
    static private int height = 0;
   // Requires : 
// Modifies : 
// Effects : returns shelf
    public File getShelf() {
        return shelf;
    }
    // Requires : 
// Modifies : 
// Effects : returns height
    public int getHeight() {
        return height;
    }
    // Requires : 
// Modifies : 
// Effects : returns titles
    public String[] getTitles() {
        return titles;
    }
    // Requires : String str
// Modifies : titles
// Effects : changes the highest index string of titles to str
    public void addTitles(String str) {
        titles[height] = str;
        height++;
    }

// Requires : File shelf 
// Modifies : 
// Effects : Takes lines found within file shelf and adds to titles

    public void resetTitles() {
        Scanner s = null;
        String str;
                        try {
                            s = new Scanner(shelf);
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(BookstoreApplication.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        while(s.hasNextLine()) {
                            str = s.nextLine();
                            addTitles(str);
                        }
    }

// Requires : File BookList.txt
// Modifies : name, price
// Effects : constructor, Displays Books within BookList.txt

   public Books(){
        this.name = "";
        this.price = 0;
            shelf = new File("BookList.txt");
        try {
            if (shelf.createNewFile());
        } catch (IOException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

// Requires : File BookList.txt
// Modifies : name, price
// Effects : constructor, Displays Books within BookList.txt

    public Books(String name, double price){
        this.name = name;
        this.price = price;
            shelf = new File("BookList.txt");
        try {
            if (shelf.createNewFile());
        } catch (IOException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

// Requires : File Shelf 
// Modifies : File shelf
// Effects : Adds msg in shelf 

public void draw(String msg){

        FileWriter fw;
        try {
             fw = new FileWriter(shelf,true);
             fw.write(msg + "\n");
             fw.close();
        } 
        catch (IOException e) {
           System.out.println("An error");
           e.printStackTrace();
        }


    }
// Requires : 
// Modifies : 
// Effects : returns name
    public String getName() {
        return name;
    }
// Requires : String username
// Modifies : name
// Effects : Changes the name to username
    public void setName(String username) {
        this.name = username;
         draw(username+"  "+price);

    }
// Requires : 
// Modifies : 
// Effects : returns price
    public double getPrice() {
        return price;
    }
// Requires : double p
// Modifies : price
// Effects : Changes the price to p
    public void setPrice(double p) {
        this.price = p;
    }

// Requires : At least 1 book present within the list
// Modifies : titles, height
// Effects : Removes certain Book Item from list of Books depending on User

    void removeItem(int badLine) {
        for(int i = badLine; i < height; i++) {
            titles[i] = titles[i + 1];
        }
        System.out.println(titles[badLine] + "\n");
        titles[height - 1] = null;
        height--;
    }
    
// Requires : File shelf
// Modifies : File shelf
// Effects : Clears the file

    public void curate() {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(shelf);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
        writer.print("");
        writer.close();
    }
}