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
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class BookstoreApplication extends Application {
    Stage window;
    TableView<Customer> table;
    TableView<Books> table2;
    TextField nameInput, passInput, quantityInput,nInput, priceInput;
    Scene scene1,scene2, scene3, scene4,scene5,scene6, scene7;
    TableView<Books> tableCustom;
    TableView<Customer> UserTable;
    TextField nameInputCustom, priceInputCustom;
    // Requires : 
    // Modifies : 
    // Effects : Launches the program
  public static void main(String[] args) {
        //File data = new File("UserData.txt");
        //if(data.delete());
            Customer dummy = new Customer();
            Books test = new Books();
            dummy.resetList();
            test.resetTitles();
        launch(args);
    }
// Requires : All javafx procedures
// Modifies : All javafx procedures
// Effects :  Makes the GUI of the program
    @Override
    public void start(Stage primaryStage) throws Exception {
      
       
        window = primaryStage;
        window.setTitle("Bookstore Application");

        // ---------Owner scene1 Start---------------------------------------
        Label welcomeL = new Label("Welcome to the BookStore App");
        Label usernameL = new Label("Username :");
        Label passwordL = new Label("Password");   
        // creating a text field
        TextField usernameInp = new TextField();
        TextField passInp = new TextField();
        Button btn = new Button();
        btn.setText("Login");      
     
        btn.setOnAction(
               
              e->{
                   
                   // checks if the admin typed the proper credentials
                    if( isString(usernameInp,usernameInp.getText(),passInp, passInp.getText())==true){
                         window.setScene(scene2);
                         usernameInp.clear();
                         passInp.clear();
                    }
                    
                         else {
                        Customer dummy = new Customer();
                        Scanner sc = null;
                        try {
                            sc = new Scanner(dummy.getFile());
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(BookstoreApplication.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        String str;
                        int counter = 0;
                        while(sc.hasNextLine()) {
                            str = sc.nextLine();
                        
                            if(usernameInp.getText().equals(str.substring(0, str.indexOf("  "))) && passInp.getText().equals(str.substring(str.indexOf("  ") + 2))) {
                                
                            File data = new File("Userdata.txt");
        try {
            if (data.createNewFile());
        } catch (IOException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
        FileWriter fw;
        try {
            fw = new FileWriter(data,true);
             fw.write(str + "\n");
             fw.close();
        } 
        catch (IOException l) {
           System.out.println("An error");
           l.printStackTrace();
        }
                                
        
                                window.setScene(scene5);
                               usernameInp.clear();
                                 passInp.clear();
                            }
                            counter++;
                        }
                        sc.close();
                    }
              }
        );
       
         
        // This is inside the the window 
        // creating a grid pane and setpadding is setting the dgrid up in the scene
         GridPane grid =  new GridPane();
         grid.setPadding(new Insets(10,10,10,10));
         grid.setVgap(8);
         grid.setHgap(10);
         
         // THis is just to position everything inside the grid
         
          GridPane.setConstraints(welcomeL, 0, 0);
          GridPane.setConstraints(usernameL, 0, 1);
          GridPane.setConstraints(usernameInp, 1, 1);
          GridPane.setConstraints(passwordL, 0, 2);
          GridPane.setConstraints(passInp, 1, 2);
          GridPane.setConstraints(btn, 1, 3);
          
          grid.getChildren().addAll(welcomeL,usernameL,usernameInp,passwordL, passInp,btn);
        
        Scene scene1 = new Scene(grid, 400, 300);
        //-----------------Owner scene1End-----------------------------
         
         
         
            
        //Owner  scene2 Start----------------------------------------------------
         
         GridPane grid2 =  new GridPane();
          grid2.setPadding(new Insets(10,10,10,10));Button btn2 = new Button("    Books   ");
         Button btn3 = new Button("Customers");
         Button btn4 = new Button("    Logout  ");
        grid2.setAlignment(Pos. CENTER);
         grid2.setVgap(8);
         grid2.setHgap(10);
         
         GridPane.setConstraints(btn2, 0, 0);
         GridPane.setConstraints(btn3, 0, 1);
         GridPane.setConstraints(btn4, 0, 2);
        grid2.getChildren().addAll(btn2,btn3,btn4);
         
        // press logout, you go back to login screen
         btn4.setOnAction(e-> window.setScene(scene1));
         btn2.setOnAction(e-> window.setScene(scene3));
         btn3.setOnAction(e-> window.setScene(scene4));
         scene2 = new Scene(grid2, 500, 200);
        // Owner scene2 end-----------------------------------------------------
        
        //Name column
        TableColumn<Customer, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(300);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        //Price column
        TableColumn<Customer, String> passColumn = new TableColumn<>("Password");
        passColumn.setMinWidth(200);
        passColumn.setCellValueFactory(new PropertyValueFactory<>("pass"));

        
        //Name input
        nameInput = new TextField();
        nameInput.setPromptText("username");
        nameInput.setMinWidth(100);

        //Password input
        passInput = new TextField();
        passInput.setPromptText("Password");

       
        //Button
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> addButtonClicked());
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> deleteButtonClicked());
        Button Backbtn = new Button("Back");
        Backbtn.setOnAction(e-> {
            window.setScene(scene2);
        nameInput.clear();
       passInput.clear();
                });
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10,10,10,10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(nameInput, passInput, addButton, deleteButton,Backbtn);

        table = new TableView<>();
        table.setItems(getProduct());
        table.getColumns().addAll(nameColumn, passColumn);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(table, hBox);
        scene4 = new Scene(vBox);




        //Mc Ashleys Part
        //Button
        Button addButt = new Button("Add");
        addButt.setOnAction(e -> addButtClicked());
        Button deleteButt = new Button("Delete");
        deleteButt.setOnAction(e -> deleteButtClicked());
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            window.setScene(scene2); 
            nInput.clear();
            priceInput.clear();});
        
        //Name column
        TableColumn<Books, String> nameCol = new TableColumn<>("Name");
        nameCol.setMinWidth(300);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        //Price column
        TableColumn<Books, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setMinWidth(200);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        //Name input
        nInput = new TextField();
        nInput.setPromptText("Name");
        nInput.setMinWidth(100);

        //Price input
        priceInput = new TextField();
        priceInput.setPromptText("Price");


        HBox hhBox = new HBox();
        hhBox.setPadding(new Insets(10,10,10,10));
        hhBox.setSpacing(10);
        hhBox.getChildren().addAll(nInput, priceInput, addButt, deleteButt, backButton);

        table2 = new TableView<>();
        table2.setItems(getBooks());
        table2.getColumns().addAll(nameCol, priceColumn);
        
        //Layout 2
        VBox vvBox = new VBox();
        vvBox.getChildren().addAll(table2, hhBox);
        scene3 = new Scene(vvBox);
        
        
        //Customer Start Screen
        //Button
         
        Button Buy = new Button("Buy");
        Buy.setOnAction(e -> window.setScene(scene6));
        Button Redeem = new Button("Redeem Points & Buy");
        Redeem.setOnAction(e -> window.setScene(scene7));
        Button Logout = new Button("Logout");
        Logout.setOnAction(e->{
      File data = new File("Userdata.txt");
        try {
            if (data.createNewFile());
        } catch (IOException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
      PrintWriter writer = null;
        try {
            writer = new PrintWriter(data);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
        writer.print("");
        writer.close();
            window.setScene(scene1);
                });
        
        
        //Username Column
        TableColumn<Customer, String> usernameCol = new TableColumn<>("Username");
        usernameCol.setMinWidth(150);
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        TableColumn<Customer, String> pointsCol = new TableColumn<>("Points");
        pointsCol.setMinWidth(100);
        pointsCol.setCellValueFactory(new PropertyValueFactory<>("points"));
        
        TableColumn<Customer, String> statusCol = new TableColumn<>("Status");
        statusCol.setMinWidth(150);
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        
        
        
        TableColumn<Books, String> nameColumnCustom = new TableColumn<>("Name");
        nameColumnCustom.setMinWidth(200);
        nameColumnCustom.setCellValueFactory(new PropertyValueFactory<>("name"));

        //Price column
        TableColumn<Books, Double> priceColumnCustom = new TableColumn<>("Price");
        priceColumnCustom.setMinWidth(200);
        priceColumnCustom.setCellValueFactory(new PropertyValueFactory<>("price"));

        
        HBox hBoxCustom = new HBox();
        hBoxCustom.setPadding(new Insets(10,10,10,10));
        hBoxCustom.setSpacing(20);
        hBoxCustom.getChildren().addAll( Buy, Redeem, Logout);
        hBoxCustom.setAlignment(Pos. CENTER);
        
        
        HBox hboxD = new HBox();
         hboxD.setPadding(new Insets(10,10,10,10));
         int points = 0;
         String status;
         String name="default";
         try{
             File readPoints = new File(name+"Points.txt");
             Scanner scan = new Scanner(readPoints);
             points = points+scan.nextInt();
         }
             catch(IOException e){
             System.out.println("");
             }
        if(points<1000){
            status = "Silver";
        } 
        
        else{
            status = "Gold";
        }

        Label labelUserCred = new Label(" Welcome customer!");
        hboxD.getChildren().addAll(labelUserCred);
        /*table2 = new TableView<>();
        table2.setItems(getBooks());
        table2.getColumns().addAll(nameCol, priceColumn);
        
        
        VBox vvBox = new VBox();
        vvBox.getChildren().addAll(table2, hhBox);
        scene3 = new Scene(vvBox);
        */

        //UserTable.getColumns().add(usernameCol);
        //UserTable.getColumns().add(statusCol);
        //UserTable.getColumns().add(pointsCol);
        UserTable = new TableView<>();
        UserTable.setItems(getSpecificProduct()); /*has to be Specific*/
        UserTable.getColumns().addAll(usernameCol, statusCol, pointsCol); 
        UserTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        UserTable.setMaxHeight(50.0);
        

        
        tableCustom = new TableView<>();
        tableCustom.setItems(getBooks());
        tableCustom.getColumns().addAll(nameColumnCustom, priceColumnCustom);
        tableCustom.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        

        //Layout 2
        VBox vBoxCustom = new VBox();
        vBoxCustom.getChildren().addAll(hboxD, UserTable, tableCustom, hBoxCustom);
        scene5 = new Scene(vBoxCustom);

        
         // scene 6 stuffs
Label labelforTotalCost = new Label("Total cost:"+BuyClicked());
         Label labelforPoints = new Label("Points:  \n");
         Label labelforStatus = new Label("Status:  \n");

        TableColumn<Customer, String> costCol = new TableColumn<>("Total Cost");
        costCol.setMinWidth(150);
        costCol.setCellValueFactory(new PropertyValueFactory<>("cost"));
        
        TableColumn<Customer, String> newpointsCol = new TableColumn<>("Updated Points");
        newpointsCol.setMinWidth(100);
        newpointsCol.setCellValueFactory(new PropertyValueFactory<>("points"));
        
        TableColumn<Customer, String> newstatusCol = new TableColumn<>("Current Status");
        newstatusCol.setMinWidth(150);
        newstatusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        
        TableView CostTable = new TableView<>();
        CostTable.getColumns().addAll(costCol, newpointsCol, newstatusCol);
        CostTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        Button Logout2 = new Button("Logout");//new line
        Logout2.setOnAction(e->window.setScene(scene1)); //new line

        VBox vBoxfor6 = new VBox();
        vBoxfor6.setPadding(new Insets(10,10,10,10));
        vBoxfor6.setSpacing(30);
        vBoxfor6.getChildren().addAll(labelforTotalCost, CostTable, Logout2);//
        vBoxfor6.setAlignment(Pos. CENTER);//new line
        scene6 = new Scene(vBoxfor6,500,200);

        //scene 7 stuff
        Label RedeemTotalCost = new Label("Transaction Details\n");

        TableColumn<Customer, String> RcostCol = new TableColumn<>("Total Cost");
        RcostCol.setMinWidth(150);
        RcostCol.setCellValueFactory(new PropertyValueFactory<>("cost"));
        
        TableColumn<Customer, String> RnewpointsCol = new TableColumn<>("Updated Points");
        RnewpointsCol.setMinWidth(100);
        RnewpointsCol.setCellValueFactory(new PropertyValueFactory<>("points"));
        
        TableColumn<Customer, String> RnewstatusCol = new TableColumn<>("Current Status");
        RnewstatusCol.setMinWidth(150);
        RnewstatusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        
        TableView RedeemCostTable = new TableView<>();
        RedeemCostTable.getColumns().addAll(RcostCol, RnewpointsCol, RnewstatusCol);
        RedeemCostTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        Button Logout3 = new Button("Logout");//new line
        Logout3.setOnAction(e->window.setScene(scene1)); //new line

        VBox vBoxfor7 = new VBox();
        vBoxfor7.setPadding(new Insets(10,10,10,10));
        vBoxfor7.setSpacing(30);
        vBoxfor7.getChildren().addAll(RedeemTotalCost, RedeemCostTable, Logout3);//
        vBoxfor7.setAlignment(Pos. CENTER);//new line
        scene7 = new Scene(vBoxfor7,500,200);

        window.setScene(scene1);
        window.setTitle("Bookstore Application");
        window.show();
    }
    
    

    
    
    // JUST BACKEND STUUFS
    
    
       //Add button clicked
// Requires : User to be Owner/Admin
// Modifies : BooksList.txt
// Effects : Adds book(s) to the list of books 
    public void addButtClicked(){
        try {
            Books product = new Books();
            product.setPrice(Double.parseDouble(priceInput.getText()));
            product.setName(nInput.getText());
            table2.getItems().add(product);
            product.addTitles(nInput.getText() + "  " + priceInput.getText());
            nInput.clear();
            priceInput.clear();
        } catch (NumberFormatException ex) {
        }
    }

    //Delete button clicked
// Requires : User to be Owner/Admin
// Modifies : BooksList.txt
// Effects : Deletes book(s) from the list of books
    public void deleteButtClicked(){
        ObservableList<Books> productSelected, allBookss;
        allBookss = table2.getItems();
        productSelected = table2.getSelectionModel().getSelectedItems();
        if(!productSelected.isEmpty()) {
        int badLine = 0, counter = 0;
        String text = productSelected.get(0).getName() + "  " + productSelected.get(0).getPrice();
        System.out.println(text + "\n");
        Books test = new Books();
        for(int i = 0; i < test.getHeight(); i++) {
            if(text.equals(test.getTitles()[i])); {
                badLine = i - 1;
            }
        }
        System.out.println(badLine + "\n");
        test.removeItem(badLine);
        productSelected.forEach(allBookss::remove);
        File replicaFile = new File("Replica.txt");
        try {
            if (replicaFile.createNewFile());
        } catch (IOException ex) {
            Logger.getLogger(BookstoreApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scanner sc = null;
        try {
            sc = new Scanner(test.getShelf());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BookstoreApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scanner sc1 = null;
        try {
            sc1 = new Scanner(replicaFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BookstoreApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
        String str;
        while(sc.hasNextLine()) {
            str = sc.nextLine();
            if(counter != badLine) {
                FileWriter fw;
                try {
                    fw = new FileWriter(replicaFile,true);
                    fw.write(str + "\n");
                    fw.close();
                } catch (IOException e) {
                    System.out.println("An error");
                    e.printStackTrace();
                }
            }
            counter++;
        }
        test.curate();
        while(sc1.hasNextLine()) {
            str = sc1.nextLine();
            FileWriter fw;
            try {
                fw = new FileWriter(test.getShelf(),true);
                fw.write(str + '\n');
                fw.close();
            } catch (IOException e) {
                System.out.println("An error");
                e.printStackTrace();
            }
        }
        sc.close();
        sc1.close();
        if(replicaFile.delete());
        counter = 0;
    }
    }

    
    //Get all of the products
// Requires : 
// Modifies : 
// Effects : The books added by the Owner/Admin to be displayed to the customer
    public ObservableList<Books> getBooks(){
        ObservableList<Books> products = FXCollections.observableArrayList();
        /*products.add(new Books("Discrete Math", 300.00));
        products.add(new Books("Data Alg", 249.49));
        products.add(new Books("Electric Net", 199.00));
        products.add(new Books("Obj Oriented Prog", 219.99));
        products.add(new Books("Communications", 271.49)); */
        Scanner sc = null;
        try {
            Books test = new Books();
            sc = new Scanner(test.getShelf());
            } catch (FileNotFoundException ex) {
                Logger.getLogger(BookstoreApplication.class.getName()).log(Level.SEVERE, null, ex);
            }String str;
        while(sc.hasNextLine()) {
            str = sc.nextLine();
            products.add(new Books(str.substring(0, str.indexOf("  ")), Double.parseDouble(str.substring(str.indexOf("  ") + 2))));
            
        }
        sc.close();
        
        return products;
    }
    
 
    
    //Add button clicked
// Requires : User to be Customer
// Modifies : BooksList.txt
// Effects : Adds customer(s) product(s)
    public void addButtonClicked(){
        Customer product = new Customer();
        product.setName(nameInput.getText());
        product.setPass(passInput.getText());
        table.getItems().add(product);
        Customer dummy = new Customer();
        dummy.addList(nameInput.getText() + "  " + passInput.getText());
        nameInput.clear();
       passInput.clear();
    }

    //Delete button clicked
// Requires : User to be Customer
// Modifies : BooksList.txt
// Effects : Deletes customer(s) product(s)
    public void deleteButtonClicked(){
        ObservableList<Customer> productSelected, allProducts;
        allProducts = table.getItems();
        productSelected = table.getSelectionModel().getSelectedItems();
        if(!productSelected.isEmpty()) {
        int badLine = 0, counter = 0;
        String text = productSelected.get(0).getName() + "  " + productSelected.get(0).getPass();
        System.out.println(text + "\n");
        Customer dummy = new Customer();
        System.out.println(text + "hi\n");
        for(int i = 0; i < dummy.getTop(); i++) {
            if(text.equals(dummy.getList()[i])) {
                badLine = i;
            }
        }
        System.out.println(badLine + "\n");
        dummy.removeItem(badLine);
        productSelected.forEach(allProducts::remove);
        File replicaFile = new File("Replica.txt");
        try {
            if (replicaFile.createNewFile());
        } catch (IOException ex) {
            Logger.getLogger(BookstoreApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scanner sc = null;
        try {
        sc = new Scanner(dummy.getFile());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BookstoreApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scanner sc1 = null;
        try {
            sc1 = new Scanner(replicaFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BookstoreApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
        String str;
        while(sc.hasNextLine()) {
            str = sc.nextLine();
            if(counter != badLine) {
                FileWriter fw;
                try {
                    fw = new FileWriter(replicaFile,true);
                    fw.write(str + "\n");
                    fw.close();
                } catch (IOException e) {
                    System.out.println("An error");
                    e.printStackTrace();
                }
            }
            counter++;
        }
        dummy.clear();
        while(sc1.hasNextLine()) {
            str = sc1.nextLine();
            FileWriter fw;
            try {
                fw = new FileWriter(dummy.getFile(),true);
                fw.write(str + '\n');
                fw.close();
            } catch (IOException e) {
                System.out.println("An error");
                e.printStackTrace();
            }
        }
        sc.close();
        sc1.close();
        if(replicaFile.delete());
        counter = 0;
    }
    }

// Requires : Products > 0 
// Modifies :
// Effects : Gets all of the products 
    public ObservableList<Customer> getProduct(){
        ObservableList<Customer> products = FXCollections.observableArrayList();
        //products.add(new Books("Laptop", "Bro"));
        Scanner sc = null;
        try {
            Customer dummy = new Customer();
            sc = new Scanner(dummy.getFile());
            } catch (FileNotFoundException ex) {
                Logger.getLogger(BookstoreApplication.class.getName()).log(Level.SEVERE, null, ex);
            }String str;
        while(sc.hasNextLine()) {
            str = sc.nextLine();
            Customer dummy = new Customer();
            products.add(new Customer(str.substring(0, str.indexOf("  ")),str.substring(str.indexOf("  ") + 2)));
        }
        sc.close();
        return products;
    }
// Requires : Products > 0 
// Modifies :
// Effects : Gets specific product
    public ObservableList<Customer> getSpecificProduct(){
        ObservableList<Customer> products = FXCollections.observableArrayList();
        //products.add(new Books("Laptop", "Bro"));
        Scanner sc = null;
        File data = new File("Userdata.txt");
        try {
            if (data.createNewFile());
        } catch (IOException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }try {
            sc = new Scanner(data);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(BookstoreApplication.class.getName()).log(Level.SEVERE, null, ex);
            }
        String text = null;
        if(sc.hasNextLine()) {
        text = sc.nextLine();
        try {
            Customer dummy = new Customer();
            sc = new Scanner(dummy.getFile());
            } catch (FileNotFoundException ex) {
                Logger.getLogger(BookstoreApplication.class.getName()).log(Level.SEVERE, null, ex);
            }String str;
        while(sc.hasNextLine()) {
            str = sc.nextLine();
            if(text.substring(0, str.indexOf("  ")).equals(str.substring(0, str.indexOf("  "))) && text.substring(0, str.indexOf("  ")).equals(str.substring(str.indexOf("  ") + 2)))
            products.add(new Customer(str.substring(0, str.indexOf("  ")),str.substring(str.indexOf("  ") + 2)));
        }
}
        sc.close();
        return products;
    }
    /*public ObservableList<Customer> getUser(){
        ObservableList<Customer> user = FXCollections.observableArrayList();
        //products.add(new Books("Laptop", "Bro"));
        Scanner sc = null;
        try {
            Customer dummy = new Customer();
            sc = new Scanner(dummy.getFile());
            } catch (FileNotFoundException ex) {
                Logger.getLogger(BookstoreApplication.class.getName()).log(Level.SEVERE, null, ex);
            }String str;
        while(sc.hasNextLine()) {
            str = sc.nextLine();
            user.add(new Customer(str.substring(0, str.indexOf("  ")), str.substring(str.indexOf("  ") + 2)));
        }
        sc.close();
        return user;
    }*/
// Requires : Item to be selected and buy button to be cicked
// Modifies : 
// Effects : Buys the product that was selected before pressing buy
      public double BuyClicked(){
          double priceclicked =0;
        ObservableList<Books> productSelected, allBookss;
        allBookss = tableCustom.getItems();
        productSelected = tableCustom.getSelectionModel().getSelectedItems();
        for(Books bk : productSelected){
          priceclicked += bk.getPrice();
          botleft b = new botleft(getProduct().get(0).getName(), getProduct().get(0).getPoints(), bk.getPrice(), 0);

        }
        window.setScene(scene6);
        return priceclicked;
    }
// Requires : Redeem button to be clicked after purchase
// Modifies : Points
// Effects : Gives customer certain points based on the price of the book
    public double RedeemClicked(){
          double priceclicked =0;
        ObservableList<Books> productSelected, allBookss;
        allBookss = tableCustom.getItems();
        productSelected = tableCustom.getSelectionModel().getSelectedItems();
        for(Books bk : productSelected){
          priceclicked += bk.getPrice();
          botleft b = new botleft(getProduct().get(0).getName(), getProduct().get(0).getPoints(), bk.getPrice(), 1);

        }
        window.setScene(scene6);
        return priceclicked;
    }
    
// Requires : Text to be inputted in username and password text field
// Modifies :
// Effects : If username and password equal to admin, it returns true otherwise false
    private boolean isString(TextField usernameInp, String text, TextField passInp, String text1) {
       
        if(usernameInp.getText().equals("admin")&& passInp.getText().equals("admin")){
             System.out.println("THis is true");
           return true;
        }  
        else{
             System.out.println("THis is false");
            return false;
        }
        
    
    }


}
