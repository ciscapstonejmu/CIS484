package Group;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class GUI extends Application 
{
    // Sign In Menu
    Label lblThrifty = new Label("Thrifty");
    Label lblStore = new Label("$tore");
    Label lblSignIn = new Label("Sign In");
    Label lblUserName = new Label("Username");
    Label lblPassword = new Label("Password");
    Button btnSignIn = new Button("Sign In");
    Button btnForgot = new Button("Forgot Password");
    TextField txtUser = new TextField();
    PasswordField txtPass = new PasswordField(); 
    //TextField txtPass = new TextField();
    
    //Inventory Management
    Label lblInvMan = new Label("Inventory Management");
    Button btnAddProd = new Button("Add Product");
    Button btnRemoveProd = new Button("Remove Product");
    
    //Add a Product
    Label lblAddProd = new Label("Add a Product");
    Label lblProdName = new Label("Product Name");
    TextField txtProdName = new TextField();
    Label lblProdDesc = new Label("Description");
    TextField txtProdDesc = new TextField();
    Label lblUnitPrice = new Label("Unit Price");
    TextField txtUnitPrice = new TextField();
    Label lblVendor = new Label("Vendor");
    TextField txtVendor = new TextField();
    Label lblCategory = new Label("Category");
    ObservableList<String> category = FXCollections.observableArrayList("Meats/Dairy",
            "Produce", "Canned Goods", "Dry Stock", "Medicines/Self Care", "Other");
    ComboBox cmboCategory = new ComboBox(category);
    Label lblQuantity = new Label("Quantity");
    TextField txtProdQuantity = new TextField();
    Label lblImage = new Label("Image");
    Button addProd = new Button("Add Product");
    
    // Employee Management
    Label lblEmpMan = new Label("Employee Management");
    Label lblEditEmp = new Label("Edit Employee");
    // create combobox based off of employees
    Button btnEditEmp = new Button("Edit Employee");
    Button btnAddEmp = new Button("Add Employee");
    
    // Add an Employee
    Label lblAddEmp = new Label("Add an Employee");
    Label lblFName = new Label("First Name");
    TextField txtFName = new TextField();
    Label lblLName = new Label("Last Name");
    TextField txtLName = new TextField();
    // DOB and Gender not included
    Label lblEmail = new Label("Email");
    TextField txtEmail = new TextField();
    Label lblPhone = new Label("Phone#");
    TextField txtPhone = new TextField();
    Label lblAddress = new Label("Address");
    TextField txtAddress = new TextField();
    Label lblJobTitle = new Label("Job Title");
    TextField txtJobTitle = new TextField();
    Label lblSalary = new Label("Salary");
    TextField txtSalary = new TextField();
    Button addEmp = new Button("Add Employee");
    
    // Expense Management
    Label lblExpMan = new Label("Expense Management");
    Button btnAddExp = new Button("Add Expense");
    Button btnPayExp = new Button("Pay Expense");
    
    // Add Expense
    Label lblAddExp = new Label("Add an Expense");
    Label lblExpDesc = new Label("Description");
    TextField txtExpDesc = new TextField();
    Label lblDate = new Label("Date");
    DatePicker date = new DatePicker();
    ObservableList<String> expCategory = FXCollections.observableArrayList("Store Mortgage",
            "Utilities", "Lawn Care", "System Maintenance", "Payroll", "Inventory Purchases");
    ComboBox cmboExpCategory = new ComboBox(expCategory);
    Label lblStatus = new Label("Status");
    ObservableList<String> status = FXCollections.observableArrayList("Paid", "Ongoing");
    ComboBox cmboStatus = new ComboBox(status);
    Label lblAmount = new Label("Amount");
    TextField txtAmount = new TextField();
    Button addExp = new Button("Add Expense");
    
    //POS Report
    Label lblPOS = new Label("Point of Sales Report");
    Label lblViewType = new Label("View Type");
    ObservableList<String> type = FXCollections.observableArrayList("Week - daily",
            "Month - weekly", "Year - monthly");
    ComboBox cmboType = new ComboBox(type);
    Label lblLocation = new Label("Location");
    // make combobox for locations (dont know what they are
    Button btnPOSDisplay = new Button("Display");
    TabPane tbPOS = new TabPane();
    Tab tabPOSText = new Tab("Text");
    Tab tabPOSChart = new Tab("Chart");
    GridPane formPane = new GridPane();
    //create text area
    
    // Employee Report
    Label lblEmpRep = new Label("Employee Report");
    // combobox for location
    Button btnEmpDisplay = new Button("Display");
    // create a textarea
    
    // Monthly Expense Report
    Label lblMonthRep = new Label("Monthly Expense Report");
    // combobox for location
    // something to see past months
    Button btnMonthDisplay = new Button("Display");
    
    // Profit v. Expense Report
    Label lblPvE = new Label("Profit v. Expense Report");
    Label lblYear = new Label("Year");
    //combobox of year
    //combobox of location
    Button btnPvEDisplay = new Button("Display");
    TabPane tbPvE = new TabPane();
    Tab tabPText = new Tab("Text");
    Tab tabPPie = new Tab("Pie Graph");
    Tab tabPBar = new Tab("Bar Graph");
    Tab tabPLine = new Tab("Line Graph");
    
    // Menu Bar for Admin
    MenuBar menuAdmin = new MenuBar();
    Menu menuHome = new Menu("Main Menu");
    Menu menuManage = new Menu("Management");
    Menu menuPayroll = new Menu("Payroll");
    Menu menuReports = new Menu("Reports");
    ObservableList<String> loginType = FXCollections.observableArrayList("Manager", "Associate");
    ComboBox cmboLoginType = new ComboBox(loginType);
    
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    
    @Override
    public void start(Stage primaryStage) 
    {
        GridPane primaryPane = new GridPane();
        primaryPane.setAlignment(Pos.CENTER);
        
        primaryPane.setHgap(10);
        primaryPane.setVgap(10);
        
        
        
        primaryPane.add(lblThrifty, 0, 0);
        primaryPane.add(lblStore, 0, 1);
        primaryPane.add(lblSignIn, 0, 3);
        primaryPane.add(lblUserName, 0, 4);
        primaryPane.add(txtUser, 0, 5);
        primaryPane.add(lblPassword, 0, 6);
        primaryPane.add(txtPass, 0, 7);
        primaryPane.add(cmboLoginType, 0, 8);
        primaryPane.add(btnSignIn, 0, 9);
        primaryPane.add(btnForgot, 0, 10);
        
        lblThrifty.setStyle("-fx-font: bold 20pt \"Arial\";");
        lblStore.setStyle("-fx-font: bold 20pt \"Arial\";");
        lblSignIn.setStyle("-fx-font: bold 14pt \"Arial\";");
        
        lblInvMan.setStyle("-fx-font: bold 20pt \"Arial\";");
        lblEmpMan.setStyle("-fx-font: bold 20pt \"Arial\";");
        lblExpMan.setStyle("-fx-font: bold 20pt \"Arial\";");
        lblPOS.setStyle("-fx-font: bold 20pt \"Arial\";");
        lblEmpRep.setStyle("-fx-font: bold 20pt \"Arial\";");
        lblMonthRep.setStyle("-fx-font: bold 20pt \"Arial\";");
        lblPvE.setStyle("-fx-font: bold 20pt \"Arial\";");
        lblAddProd.setStyle("-fx-font: bold 14pt \"Arial\";");
        lblAddEmp.setStyle("-fx-font: bold 14pt \"Arial\";");
        lblAddExp.setStyle("-fx-font: bold 14pt \"Arial\";");
        GridPane secondPane = new GridPane();
        secondPane.setAlignment(Pos.TOP_CENTER);
        secondPane.setVgap(10);
        secondPane.setHgap(10);
            
        Label lblMain = new Label("Main Menu");
        Label lblSelect = new Label("Select an option:");
        Scene secondScene = new Scene(secondPane, 800, 500);
        Stage secondStage = new Stage();
        secondStage.setScene(secondScene);
        
        GridPane thirdPane = new GridPane();
        thirdPane.setAlignment(Pos.TOP_CENTER);
        thirdPane.setVgap(10);
        thirdPane.setHgap(10);


        Scene thirdScene = new Scene(thirdPane, 400, 400);
        Stage thirdStage = new Stage();
        thirdStage.setScene(thirdScene);
        
        menuAdmin.getMenus().addAll(menuHome, menuManage, menuPayroll, menuReports);
        menuHome.getItems().add(new MenuItem("Main Menu"));
        menuManage.getItems().add(new MenuItem("Employee Management"));
        menuManage.getItems().add(new MenuItem("Inventory Management"));
        menuManage.getItems().add(new MenuItem("Expense Management"));
        menuPayroll.getItems().add(new MenuItem("Payroll"));
        menuReports.getItems().add(new MenuItem("POS Report"));
        menuReports.getItems().add(new MenuItem("Employee Report"));
        menuReports.getItems().add(new MenuItem("Monthly Expenense Report"));
        menuReports.getItems().add(new MenuItem("Profit v. Expense Report"));
        menuAdmin.setMinWidth(secondScene.getWidth());
        
        Scene primaryScene = new Scene(primaryPane, 500, 500);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("Main Form");
        primaryStage.show();
        
        
        btnSignIn.setOnAction(e -> {
            // code to validate log in, has to have two handlers for admin and worker
            // error handling
            
            //creating the second main menu gui
            secondStage.setTitle("Main Menu");
            secondStage.show();
            primaryStage.close();
            
            
            
            secondPane.add(menuAdmin, 0, 0, 4, 1);
            secondPane.add(lblThrifty, 3, 2);
            secondPane.add(lblStore, 3, 3);
            secondPane.add(lblMain, 3, 4);
            secondPane.add(lblSelect, 3, 5);
            
            
            
        });
        
        menuAdmin.getMenus().get(0).getItems().get(0).setOnAction(e -> {
            // Main Menu
            secondPane.getChildren().clear();
            formPane.getChildren().clear();
            secondPane.setAlignment(Pos.TOP_CENTER);
            secondStage.setTitle("Main Menu");
            secondPane.add(menuAdmin, 0, 0, 4, 1);
            
            
            secondPane.add(lblThrifty, 3, 2);
            secondPane.add(lblStore, 3, 3);
            secondPane.add(lblMain, 3, 4);
            secondPane.add(lblSelect, 3, 5);
            
        });
        
        menuAdmin.getMenus().get(1).getItems().get(1).setOnAction(e -> {
            // Inventory Management
            secondPane.getChildren().clear();
            formPane.getChildren().clear();
            secondPane.setAlignment(Pos.TOP_CENTER);
            secondStage.setTitle("Inventory Management");
            secondPane.add(menuAdmin, 0, 0, 4, 1);
            
            secondPane.add(lblInvMan, 3, 2);
            secondPane.add(btnAddProd, 3, 3);
            secondPane.add(btnRemoveProd, 3, 4);
            
        });
        
        btnAddProd.setOnAction(e -> {
            // Add a Product
            thirdPane.getChildren().clear();
            thirdStage.setTitle("Add a Product");
            thirdStage.show();
            thirdPane.setAlignment(Pos.TOP_CENTER);
            
            thirdPane.add(lblAddProd, 0, 0);
            thirdPane.add(lblProdName, 0, 1);
            thirdPane.add(txtProdName, 0, 2);
            thirdPane.add(lblProdDesc, 0, 3);
            thirdPane.add(txtProdDesc, 0, 4);
            thirdPane.add(lblCategory, 1, 3);
            thirdPane.add(cmboCategory, 1, 4);
            thirdPane.add(lblUnitPrice, 0, 5);
            thirdPane.add(txtUnitPrice, 0, 6);
            thirdPane.add(lblQuantity, 1, 5);
            thirdPane.add(txtProdQuantity, 1, 6);
            thirdPane.add(lblVendor, 0, 7);
            thirdPane.add(txtVendor, 0, 8);
            thirdPane.add(lblImage, 0, 9);
            
            thirdPane.add(addProd, 0, 10);
        });
        
        addProd.setOnAction(e -> {
            //product added clicked
            alert.setTitle("Success!");
            alert.setHeaderText("The product has been added!");
            alert.showAndWait();
            //insert code to add product to object
            
            thirdStage.close();
        });
        
        menuAdmin.getMenus().get(1).getItems().get(0).setOnAction(e -> {
            // Employee Management
            secondPane.getChildren().clear();
            formPane.getChildren().clear();
            secondPane.setAlignment(Pos.TOP_CENTER);
            secondStage.setTitle("Employee Management");
            secondPane.add(menuAdmin, 0, 0, 4, 1);
            
            secondPane.add(lblEmpMan, 3, 1);
            secondPane.add(lblEditEmp, 3, 3);
            //add combobox
            secondPane.add(btnEditEmp, 3, 5);
            secondPane.add(btnAddEmp, 3, 7);
        });
        
        btnAddEmp.setOnAction(e -> {
            thirdPane.getChildren().clear();
            thirdStage.setTitle("Add an Employee");
            thirdStage.show();
            thirdPane.setAlignment(Pos.TOP_CENTER);
            
            thirdPane.add(lblAddEmp, 0, 0);
            thirdPane.add(lblFName, 0, 1);
            thirdPane.add(txtFName, 0, 2);
            thirdPane.add(lblLName, 1, 1);
            thirdPane.add(txtLName, 1, 2);
            thirdPane.add(lblEmail, 0, 3);
            thirdPane.add(txtEmail, 0, 4);
            thirdPane.add(lblPhone, 1, 3);
            thirdPane.add(txtPhone, 1, 4);
            thirdPane.add(lblAddress, 0, 5);
            thirdPane.add(txtAddress, 0, 6);
            thirdPane.add(lblJobTitle, 1, 5);
            thirdPane.add(txtJobTitle, 1, 6);
            thirdPane.add(lblSalary, 0, 7);
            thirdPane.add(txtSalary, 0, 8);
            thirdPane.add(addEmp, 0, 9);
            
        });
        
        addEmp.setOnAction(e -> {
            //emplouee added clicked
            alert.setTitle("Success!");
            alert.setHeaderText("The employee has been added!");
            alert.showAndWait();
            //insert code to add employee to object
            
            thirdStage.close();
        });
        
        menuAdmin.getMenus().get(1).getItems().get(2).setOnAction(e -> {
           // Expense Management
           secondPane.getChildren().clear();
           formPane.getChildren().clear();
           secondPane.setAlignment(Pos.TOP_CENTER);
           secondStage.setTitle("Expense Management");
           secondPane.add(menuAdmin, 0, 0, 4, 1);
           
           secondPane.add(lblExpMan, 3, 1);
           secondPane.add(btnAddExp, 3, 2);
           secondPane.add(btnPayExp, 3, 3);
           
        });
        
        btnAddExp.setOnAction(e -> {
            // Adding an Expense
            thirdPane.getChildren().clear();
            thirdStage.setTitle("Add an Expense");
            thirdStage.show();
            thirdPane.setAlignment(Pos.TOP_CENTER);
            
            thirdPane.add(lblAddExp, 0, 0);
            thirdPane.add(lblExpDesc, 0, 1);
            thirdPane.add(txtExpDesc, 0, 2);
            thirdPane.add(lblDate, 0, 3);
            thirdPane.add(date, 0, 4);
            thirdPane.add(lblCategory, 1, 1);
            thirdPane.add(cmboExpCategory, 1, 2);
            thirdPane.add(lblStatus, 1, 3);
            thirdPane.add(cmboStatus, 1, 4);
            thirdPane.add(lblAmount, 0, 5);
            thirdPane.add(txtAmount, 0, 6);
            thirdPane.add(addExp, 0, 7);
        });
        
        addExp.setOnAction(e -> {
            //emplouee added clicked
            alert.setTitle("Success!");
            alert.setHeaderText("The expense has been added!");
            alert.showAndWait();
            //insert code to add employee to object
            
            thirdStage.close();
        });
        
        menuAdmin.getMenus().get(3).getItems().get(0).setOnAction(e -> {
            // Point of Sales Report
            secondPane.getChildren().clear();
            formPane.getChildren().clear();
            secondPane.setAlignment(Pos.TOP_CENTER);
            formPane.setAlignment(Pos.TOP_LEFT);
            formPane.setVgap(10);
            secondStage.setTitle("Point of Sales Report");
            secondPane.add(menuAdmin, 0, 0, 4, 1);
            secondPane.add(tbPOS, 0, 1, 2, 1);
            tbPOS.getTabs().add(tabPOSText);
            tbPOS.getTabs().add(tabPOSChart);
            tbPOS.setMinWidth(secondScene.getWidth());
            //tbPOS.setMinHeight(secondScene.getHeight());
            
            formPane.add(lblPOS, 3, 2);
            formPane.add(lblViewType, 3, 4);
            formPane.add(cmboType, 3, 5);
            formPane.add(lblLocation, 3, 6);
            // cmbobox for location
            formPane.add(btnPOSDisplay, 3, 8);
            
            //create the table
            
            tabPOSText.setContent(formPane);
        });
        
        menuAdmin.getMenus().get(3).getItems().get(1).setOnAction(e -> {
            // Employee Report
            secondPane.getChildren().clear();
            formPane.getChildren().clear();
            
            secondPane.setAlignment(Pos.TOP_CENTER);
            
            secondStage.setTitle("Employee Report");
            secondPane.add(menuAdmin, 0, 0, 4, 1);
            
            secondPane.add(lblEmpRep, 3, 3);
            secondPane.add(lblLocation, 3, 4);
            // combobox
            secondPane.add(btnEmpDisplay, 3, 5);
            
        });
        
        menuAdmin.getMenus().get(3).getItems().get(2).setOnAction(e -> {
           // Monthly Expense Report
           secondPane.getChildren().clear();
           formPane.getChildren().clear();
           secondPane.setAlignment(Pos.TOP_CENTER);
           secondStage.setTitle("Monthly Expense Report");
           secondPane.add(menuAdmin, 0, 0, 4, 1);
           
           secondPane.add(lblMonthRep, 3, 3);
           secondPane.add(lblLocation, 3, 4);
           // combobox
           secondPane.add(btnMonthDisplay, 3, 6);
           
        });
        
        menuAdmin.getMenus().get(3).getItems().get(3).setOnAction(e -> {
           // Profit v. Expense Report
           secondPane.getChildren().clear();
           formPane.getChildren().clear();
           secondPane.setAlignment(Pos.TOP_CENTER);
           formPane.setAlignment(Pos.TOP_LEFT);
           formPane.setVgap(10);
           secondStage.setTitle("Profit v. Expense Report");
           secondPane.add(menuAdmin, 0, 0, 4, 1);
           secondPane.add(tbPvE, 0, 1, 4, 1);
           tbPvE.getTabs().add(tabPText);
           tbPvE.getTabs().add(tabPPie);
           tbPvE.getTabs().add(tabPBar);
           tbPvE.getTabs().add(tabPLine);
           tbPvE.setMinWidth(secondScene.getWidth());
           
           formPane.add(lblPvE, 0, 0);
           formPane.add(lblYear, 0, 1);
           //combobox
           formPane.add(lblLocation, 0, 3);
           //combobox
           formPane.add(btnPvEDisplay, 0, 5);
           
           tabPText.setContent(formPane);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
