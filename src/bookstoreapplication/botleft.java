/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoreapplication;

/**
 *
 * @author Vishwa
 */
import java.io.*;
import java.util.*;
class botleft {
    private String name;
    private double balance;
    private int points;
    private int pointsUsed = 0;
    private double externSaveCost;
    private String status;
    // Requires : String str, int n, double d, int thing
// Modifies : balance, name, points
// Effects : constructor, sets balance, name, and points, and goes to buy or redeem and buy
    public botleft(String str, int n, double d, int thing) {
        balance = 100.0;
        name = str;
        points = n;
        if(thing == 0) buy(d);
        if(thing == 1) RedeemAndBuy(d);
    }

// Requires : Books > 0 
// Modifies : externsavecost
// Effects :If there is enough money to purchase a book, it is purchased with 
    //an applicable discount and the new balance shown afterwards, otherwise it displays a message

    private void buy(double outsideCost) {
        externSaveCost = outsideCost;
        if(getBalance() >= externSaveCost) {
            setBalance(getBalance() - externSaveCost);
            setPoints(getPoints() + (int)(externSaveCost)); 
        } else System.out.println("Too broke to buy this item.\n");
        viewCustomerCostScreen();
    }

// Requires : Books > 0 , outside cost
// Modifies : externsavecost, pointsused
// Effects : Gives applicable points after a purchase, which can be used for a 
    //discount as mentioned in the effects clause of method Buy

    private void RedeemAndBuy(double outsideCost) {
        externSaveCost = outsideCost;
        if((points-(points%100))/100 >= externSaveCost) {
            if(getBalance() >= externSaveCost%1) {
                setPoints(getPoints() - (int)(externSaveCost));
            } else System.out.println("Too broke to buy this item.\n");
        } else {
            pointsUsed = (int)(externSaveCost%1)*100;
            if(getBalance() >= (externSaveCost - pointsUsed/100)) {
                setBalance(getBalance() - externSaveCost + pointsUsed/100);
                setPoints(getPoints() - pointsUsed + (int)(externSaveCost));
            } else System.out.println("Too broke to buy this item.\n");
        }
        pointsUsed = 0;
        viewCustomerCostScreen();
    }
    private void viewupdatedPoints() {
        System.out.println("Points: " + getPoints() + "\n");
    }

// Requires : Points have to be of integer type
// Modifies : status
// Effects : Status gets provided a string depending on the points

    private void viewUpdatedStatus() {
        System.out.println("Status: ");
        
        if(points<1000){
            status = "Silver";
        } 
        
        else{
            status = "Gold";
        }
        System.out.println(status + "\n");   //ViewStatus method here
    }
    private void viewTotalCost() {
        System.out.println("Cost: " + externSaveCost + "\n");
    }
    private void viewCustomerCostScreen() {
        viewupdatedPoints();
        viewUpdatedStatus();
        viewTotalCost();
    }
    // Requires : 
// Modifies : 
// Effects : returns points
    private int getPoints() {
        return points;
    }
    // Requires : int i
// Modifies : points
// Effects : Changes the points to i
    private void setPoints(int i) {
        points = i;
    }
// Requires : 
// Modifies : 
// Effects : returns balance
    private double getBalance() {
        return balance;
}
// Requires : double d
// Modifies : balance
// Effects : Changes the calance to d
    private void setBalance(double d) {
        balance = d;
    }
    
     
}
