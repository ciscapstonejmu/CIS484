package pkg484groupproj;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javafx.application.Application;
import javafx.collections.*;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import static javafx.application.Application.launch;
import java.util.ArrayList;
import static pkg484groupproj.GUI.expList;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import static pkg484groupproj.GUI.obsExpense;

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
    Label storeLoc = new Label("Store Location"); 
    TextField txtStoreLoc = new TextField();
    boolean isAuthorized = false;
    boolean found = false;
    //TextField txtPass = new TextField();
    
    //Array Lists
    static ArrayList<Employee> savedEmp;
    static ArrayList<Employee> empList = new ArrayList<>();
    static ArrayList<Product> prodList = new ArrayList<>(); 
    static ArrayList<Expense> expList = new ArrayList<>();
    static ArrayList<Customer> custList = new ArrayList<>(); 
    static ArrayList<Store> storeList = new ArrayList<>(); 
    static ArrayList<ProductOrder> prodOrderList = new ArrayList<>(); 
    static ArrayList<CustSale> saleList = new ArrayList<>();
    static ArrayList<String> expCategoryList = new ArrayList<>();
    static ArrayList<String> expStatusList = new ArrayList<>();
    static ArrayList<Supplier> suppList = new ArrayList<>(); 
    static ArrayList<Member> memList = new ArrayList();
    
    //Observabble Lists/Combo Boxes
    ObservableList obsEmp = FXCollections.observableArrayList();
    ComboBox cmboEmp = new ComboBox(Employee.obsEmp); 
    ObservableList obsProd = FXCollections.observableArrayList();
    ComboBox cmboProd = new ComboBox(Product.obsProd); 
    ObservableList<Customer> obsCust = FXCollections.observableArrayList();
    ComboBox cmboCust = new ComboBox(obsCust); 
    static ObservableList obsStore = FXCollections.observableArrayList();
    static ComboBox cmboStore = new ComboBox(Store.obsStore);
    ObservableList<WritableImage> imageList = FXCollections.observableArrayList();
    static ObservableList obsProdOrder = FXCollections.observableArrayList();
    static ComboBox cmboProdOrder = new ComboBox(ProductOrder.obsProdOrder); 
    //ObservableList<Store> obsStore = FXCollections.observableArrayList();
    //ComboBox cmboStore = new ComboBox(obsStore);
    public static ObservableList<CustSale> obsSale = FXCollections.observableArrayList();
    public static ComboBox cmboSale = new ComboBox(obsSale);
    public static ObservableList<Supplier> obsSupp = FXCollections.observableArrayList(); 
    public static ComboBox cmboSupp = new ComboBox(Supplier.obsSupp); 
    public static ObservableList<Member> obsMem = FXCollections.observableArrayList();
    public static ComboBox<Member> cmboMem = new ComboBox(Member.obsMem);
//    ObservableList 
    static ObservableList<Expense> obsExpense = FXCollections.observableArrayList();
    ComboBox cmboExpense = new ComboBox(obsExpense);
    
    int expVar = 0;
    int unpaidExpVar = 0;
    String payExpVar = "";
    
    // Main Menu Buttons
    Button btnRingSale = new Button("Ring Sale");
    
    //Tables
    TableView<Employee> empTable = new TableView<>();
    ObservableList<Employee> empData = FXCollections.observableArrayList(empList);
    TableView<Product> prodTable = new TableView<>();
    ObservableList<Product> prodData = FXCollections.observableArrayList(prodList);
    TableView<Expense> expTable = new TableView<>();
    ObservableList<Expense> expData = FXCollections.observableArrayList(expList);
    
    
    
    //Create Supplier Form 
    Label lblSuppName = new Label("Supplier Name: "); 
    TextField txtSuppName = new TextField(); 
    Label lblSuppEmail = new Label("Supplier Email: "); 
    TextField txtSuppEmail = new TextField(); 
    Label lblSuppAdd = new Label("Supplier Address: "); 
    TextField txtSuppAdd = new TextField(); 
    Label lblSuppContactName = new Label("Supplier Contact Name: "); 
    TextField txtSuppContactName = new TextField(); 
    Label lblSuppContactPhone = new Label("Supplier Contact Phone Number: "); 
    TextField txtSuppContactPhone = new TextField(); 
    Label lblSuppPhone = new Label("Supplier Phone Number: "); 
    TextField txtSuppPhone = new TextField(); 
    Label lblAddSupp = new Label("Create a New Supplier");
    Button btnNewSupp = new Button("Create New Supplier"); 
    Label lblSuppContactEmail = new Label("Suppler Contact Email Address: "); 
    TextField txtSuppContactEmail = new TextField(); 
    
    //Inventory Management
    Label lblInvMan = new Label("Management");
    Label lblInv = new Label("Inventory");
    Button btnAddProd = new Button("Add Product");
    Button btnRemoveProd = new Button("Remove Product");
    Button btnHandleImage = new Button("View/Edit Product Image");
    
    
    

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
    ObservableList<String> obsCategory = FXCollections.observableArrayList();
    ComboBox cmboCategory = new ComboBox(obsCategory);
    Label lblQuantity = new Label("Quantity");
    TextField txtProdQuantity = new TextField();
    Label lblImage = new Label("Image");
    Button btnImage = new Button("Add Image");
    ImageView myImageView = new ImageView();
    ImageView imageViewTwo = new ImageView();
    BufferedImage tempImage = null;
    Button addProd = new Button("Add Product");
    TextField txtSupplier = new TextField(); 
    Label lblSupplier = new Label("Supplier"); 
    Label lblProdStore = new Label("Store"); 
    Button btnCalcProdSubTotal = new Button("Calculate Subtotal");
    
    Button createSupp = new Button("Create a Supplier"); 

    Label lblOrderDate = new Label("Order Date"); 
    DatePicker orderDate = new DatePicker(); 
    Button btnAddOrder = new Button("Create Product Order"); 
    Button btnViewOrder = new Button("View Product Orders");
    Button btnCompleteOrder = new Button("Complete Product Order"); 
    TextArea prodOrderOutput = new TextArea(); 
    Button btnDisplayPO = new Button("Display Product Order Information"); 
    
    
    int imageChosen;
    
    //Remove a Product
    Label lblRemoveProd = new Label("Remove a Product");
    Label lblSelectProd = new Label("Select a Product");
    Button btnChangeImage = new Button("Change Image");
    Button btnViewImage = new Button("View Image");
    // select a product
    Label lblRQuantity = new Label("Select quantity to remove");
    TextField txtRQuantity = new TextField();
    Button removeProd = new Button("Remove Product");
    
    // Employee Management
    Label lblEmpMan = new Label("Management");
    Label lblEmp = new Label("Employee");
    Label lblEditEmp = new Label("Edit Employee");
    // create combobox based off of employees
    Button btnEditEmp = new Button("Edit Employee");
    Button EditEmp = new Button("Edit Employee");
    Button btnAddEmp = new Button("Add Employee");
    
    // Add an Employee
    Label lblAddEmp = new Label("Add an Employee");
    Label lblFName = new Label("First Name");
    TextField txtFName = new TextField();
    Label lblLName = new Label("Last Name");
    TextField txtLName = new TextField();
    Employee eC = null;    
    Expense exC = null;
    Label lblEmail = new Label("Email");
    TextField txtEmail = new TextField();
    Label lblPhone = new Label("Phone#");
    TextField txtPhone = new TextField();
    Label lblAddress = new Label("Address");
    TextField txtAddress = new TextField();
    static ObservableList<String> job = FXCollections.observableArrayList("Cashier", "Bagger", "Cleaner", "Shelfer", "Organizer","Customer Service", "General Manager", "Area Manager");
    static ComboBox cmboJob = new ComboBox(job);
    Label lblJobTitle = new Label("Job Title");
    Label lblSalary = new Label("Salary");
    TextField txtSalary = new TextField();
    Button addEmp = new Button("Add Employee");
    Label empType = new Label("Employee Type "); 
    ObservableList<String> eType = FXCollections.observableArrayList("Full Time", "Part Time");
    ComboBox cmboEType = new ComboBox(eType);
    TextField txtEmpType = new TextField(); 
    Label empCat = new Label("Employee Category"); 
    ObservableList<String> empCategory = FXCollections.observableArrayList("Manager", "Associate");
    ComboBox cmboEmpCat = new ComboBox(empCategory);
    TextField txtEmpCat = new TextField();
    Button editEmp = new Button("Save Edits");
    
    // Expense Management
    Label lblExpMan = new Label("Management");
    Label lblExp = new Label("Expense");
    Button btnAddExp = new Button("Add Expense");
    Button btnPayExp = new Button("Pay Expense");
    Button btnEditExp = new Button("Edit Expense");
    
    
    
    // Add Expense
    Label lblAddExp = new Label("Add an Expense");
    Label lblExpDesc = new Label("Description");
    TextField txtExpDesc = new TextField();
    Label lblDate = new Label("Date");
    DatePicker expDate = new DatePicker();
    ObservableList<String> expCategory = FXCollections.observableArrayList("Store Mortgage",
            "Utilities", "Lawn Care", "System Maintenance", "Payroll", "Inventory Purchases");
    ComboBox cmboExpCategory = new ComboBox(expCategory);
    Label lblStatus = new Label("Status");
    ObservableList<String> obsStatus = FXCollections.observableArrayList("Paid", "Ongoing");
    ComboBox cmboStatus = new ComboBox(obsStatus);
    Label lblAmount = new Label("Amount");
    TextField txtAmount = new TextField();
    Label lblTtlAmount = new Label("Total Amount");
    TextField txtTtlAmount = new TextField();
    Button addExp = new Button("Add Expense");
    TextField txtUnitCost = new TextField(); 
    Label lblUnitCost = new Label("Unit Cost"); 
    TextField txtSubTotal = new TextField(); 
    Label lblSubTotal = new Label("Subtotal");
    TextField txtDelDate = new TextField(); 
    Label lblDelDate = new Label("Delivery Date"); 
    DatePicker calendar = new DatePicker();
    Label lblExpStore = new Label("Please select an associated store:");
    
    Label lblChooseExp = new Label("Choose an expense to edit:");
    Button editExp = new Button("Edit Expense");
    static ObservableList<Expense> obsUnpaidExp = FXCollections.observableArrayList();
    ComboBox cmboUnpaidExp = new ComboBox(obsUnpaidExp);
    
    Label lblPayExpenseHeader = new Label("Pay Expense");
    Label lblExpCurrentAmount = new Label("Current Balance for this Expense:");
    TextField txtExpCurrentAmount = new TextField();
    Label lblChoosePayment = new Label("Select an Amount to Pay");
    TextField txtChoosePayment = new TextField();
    Label lblDisplayExpAmount = new Label("New Expense Balance");
    TextField txtDisplayExpAmount = new TextField();
    
    //Label lblChooseExp = new Label("Choose and expense to edit:");
    
    //Button editExp = new Button("Edit Expense");
    /*
    //POS - Ring Sale/Generate Receipt
    Label lblSaleStore = new Label("Store:");
    Label saleCust = new Label("Customer:");
    Label lblClubMem = new Label("Thrifty Club Member?");
    Label lblSaleProd = new Label("Product:");
    Label lblSaleProdQuan = new Label("Quantity:");
    
    TextField txtSaleQuantity = new TextField();
    
    
    Button btnAddToSale = new Button("Add to Sale ->");
    Button btnCompleteSale = new Button("Complete Sale ->");
    Button btnReceipt = new Button("Generate Receipt ->");
    
    TextArea txtSaleOutput = new TextArea();
    
    ObservableList<String> isMemList = FXCollections.observableArrayList(
            "Yes",
            "No"
            );
    final ComboBox cmboIsMem = new ComboBox(isMemList);
    //ComboBox cmboOrderCust = new ComboBox(Customer.obsCustomer);
    //ComboBox cmboOrderStore = new ComboBox(Store.obsStore);
    //ComboBox cmboOrderProd = new ComboBox(Product.obsProduct);
    */    

    // Payroll
    Label lblPayroll = new Label("Payroll");
    // managers view
    Label lblPEEmp = new Label("Edit Employee");
    Button btnPEEmp = new Button("Edit Employee");
    // Both
    Button btnEnterTime = new Button("Enter Time");
    TextArea txtPayroll = new TextArea();
    
    
    //POS Report
    Label lblPOS = new Label("Point of Sales");
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
    TextArea txtPOS = new TextArea();
    //create text area
    
    // Employee Report
    Label lblEmpRep = new Label("Employee Report");
    Label lblEmpLocation = new Label("Location");
    // combobox for location
    Button btnEmpDisplay = new Button("Display");
    TextArea txtEmp = new TextArea();
    //Button btnAddOrder = new Button("Create Product Order"); 
    //Button btnViewOrder = new Button("View Product Orders");
    //Button btnCompleteOrder = new Button("Complete Product Order"); 
    
    // Monthly Expense Report
    Label lblMonthRep = new Label("Monthly Expense");
    Label lblReport = new Label("Report");
    Label lblMLocation = new Label("Location");
    TextArea txtMonth = new TextArea();
    // combobox for location
    // something to see past months
    Button btnMonthDisplay = new Button("Display");
    
    // Profit v. Expense Report
    Label lblPvE = new Label("Profit v. Expense");
    Label lblYear = new Label("Year");
    ObservableList<String> year = FXCollections.observableArrayList("2015", "2016", "2017", "2018", "2019");
    ComboBox cmboYear = new ComboBox(year);
    Label lblPLocation = new Label("Location");
    //combobox of location
    Button btnPvEDisplay = new Button("Display");
    TabPane tbPvE = new TabPane();
    Tab tabPText = new Tab("Text");
    Tab tabPPie = new Tab("Pie Graph");
    Tab tabPBar = new Tab("Bar Graph");
    Tab tabPLine = new Tab("Line Graph");
    GridPane textPane = new GridPane();
    TextArea txtPvE = new TextArea();
    
    // Menu Bar for Admin
    MenuBar menuAdmin = new MenuBar();
    Menu menuHome = new Menu("Main Menu");
    Menu menuManage = new Menu("Management");
    Menu menuPayroll = new Menu("Payroll");
    Menu menuReports = new Menu("Reports");
    Menu menuSystem = new Menu("System");
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
        
            Store store1 = new Store("Harrisonburg", "123 lane");
            Store store2 = new Store ("New Market", "456 lane");
            Store store3 = new Store ("Staunton", "789 lane");           
            Store store4 = new Store ("Waynesboro", "456 way");
            Store store5 = new Store ("Charlottesville", "123 way");
            obsStore.add(store1);
            obsStore.add(store2);
            obsStore.add(store3);
            obsStore.add(store4);
            obsStore.add(store5);
            storeList.addAll(obsStore);
            
            obsCategory.add("PRODUCE");
            obsCategory.add("DAIRY");
            obsCategory.add("BEVERAGES");
            obsCategory.add("CANDY");
            obsCategory.add("CANNED FOODS");
            obsCategory.add("PASTA");
            obsCategory.add("SNACK FOODS");
            obsCategory.add("BAKED GOODS");
            obsCategory.add("FROZEN FOODS");
            obsCategory.add("OTHER");
        
        primaryPane.add(lblThrifty, 0, 0);
        primaryPane.add(lblStore, 0, 1);
        primaryPane.add(lblSignIn, 0, 3);
        primaryPane.add(lblUserName, 0, 4);
        primaryPane.add(txtUser, 0, 5);
        primaryPane.add(lblPassword, 0, 6);
        primaryPane.add(txtPass, 0, 7);
        //primaryPane.add(cmboLoginType, 0, 8);
        primaryPane.add(btnSignIn, 0, 9);
        //primaryPane.add(btnForgot, 0, 10);
        
        GridPane sixthPane = new GridPane(); 
        sixthPane.setAlignment(Pos.TOP_CENTER); 
        sixthPane.setVgap(10);
        sixthPane.setHgap(10); 

        Scene sixthScene = new Scene(sixthPane, 500, 500); 
        Stage sixthStage = new Stage(); 
        sixthStage.setScene(sixthScene);
        
        GridPane seventhPane = new GridPane();
       seventhPane.setAlignment(Pos.TOP_CENTER);
       seventhPane.setVgap(10);
       seventhPane.setHgap(10);

       Scene seventhScene = new Scene(seventhPane, 500, 500);
       Stage seventhStage = new Stage();
       seventhStage.setScene(seventhScene);


       GridPane eighthPane = new GridPane();
       eighthPane.setAlignment(Pos.TOP_CENTER);
       eighthPane.setVgap(10);
       eighthPane.setHgap(10);

       Scene eighthScene = new Scene(eighthPane, 500, 500);
       Stage eighthStage = new Stage();
       eighthStage.setScene(eighthScene);
        
        lblThrifty.setStyle("-fx-font: bold 36pt \"Comic Sans ms\"; -fx-text-fill: red;");
        lblStore.setStyle("-fx-font: bold 36pt \"Comic Sans ms\"; -fx-text-fill: blue;");
        lblSignIn.setStyle("-fx-font: bold 16pt \"Arial\";");
        
        
        
        lblInvMan.setStyle("-fx-font: bold 20pt \"Arial\";");
        lblInv.setStyle("-fx-font: bold 20pt \"Arial\";");
        lblEmpMan.setStyle("-fx-font: bold 20pt \"Arial\";");
        lblEmp.setStyle("-fx-font: bold 20pt \"Arial\";");

        lblExpMan.setStyle("-fx-font: bold 20pt \"Arial\";");
        lblExp.setStyle("-fx-font: bold 20pt \"Arial\";");
        lblPOS.setStyle("-fx-font: bold 20pt \"Arial\";");
        lblReport.setStyle("-fx-font: bold 20pt \"Arial\";");
        lblPayroll.setStyle("-fx-font: bold 20pt \"Arial\";");
        lblEmpRep.setStyle("-fx-font: bold 20pt \"Arial\";");
        lblMonthRep.setStyle("-fx-font: bold 20pt \"Arial\";");
        lblPvE.setStyle("-fx-font: bold 20pt \"Arial\";");
        lblAddProd.setStyle("-fx-font: bold 14pt \"Arial\";");
        lblAddEmp.setStyle("-fx-font: bold 14pt \"Arial\";");
        lblAddExp.setStyle("-fx-font: bold 14pt \"Arial\";");
        lblRemoveProd.setStyle("-fx-font: bold 14pt \"Arial\";");
        lblAddSupp.setStyle("-fx-font: bold 14pt \"Arial\";"); 
        
        
        GridPane secondPane = new GridPane();
        
        secondPane.setVgap(10);
        secondPane.setHgap(15);
            
        Label lblMain = new Label("Main Menu");
        lblMain.setStyle("-fx-font: bold 16pt \"Arial\";");
        Label lblSelect = new Label("Select an option from the Menu Bar above.");
        lblSelect.setStyle("-fx-font: 12pt \"Arial\";");
//        Scene secondScene = new Scene(secondPane, 900, 500);
       
        //Stage secondStage = new Stage();
        //secondStage.setScene(secondScene);
        
        
        GridPane thirdPane = new GridPane();
        thirdPane.setAlignment(Pos.TOP_CENTER);
        thirdPane.setVgap(10);
        thirdPane.setHgap(10);


        Scene thirdScene = new Scene(thirdPane, 500, 500);
        Stage thirdStage = new Stage();
        thirdStage.setScene(thirdScene);
        
        GridPane fifthPane = new GridPane(); 
        fifthPane.setAlignment(Pos.TOP_CENTER); 
        fifthPane.setVgap(10); 
        fifthPane.setHgap(10); 
        
        Scene fifthScene = new Scene(fifthPane, 500, 500); 
        Stage fifthStage = new Stage(); 
        fifthStage.setScene(fifthScene); 
        
        menuAdmin.getMenus().addAll(menuHome, menuManage, menuPayroll, menuReports, menuSystem);
        menuHome.getItems().add(new MenuItem("Main Menu"));
        menuManage.getItems().add(new MenuItem("Employee Management"));
        menuManage.getItems().add(new MenuItem("Inventory Management"));
        menuManage.getItems().add(new MenuItem("Expense Management"));
        menuPayroll.getItems().add(new MenuItem("Payroll"));
        menuReports.getItems().add(new MenuItem("POS Report"));
        menuReports.getItems().add(new MenuItem("Employee Report"));
        menuReports.getItems().add(new MenuItem("Monthly Expenense Report"));
        menuReports.getItems().add(new MenuItem("Profit v. Expense Report"));
        menuSystem.getItems().add(new MenuItem("Sign Out"));
        
        menuAdmin.getMenus().get(4).getItems().get(0).setStyle("-fx-background-color: red; -fx-text-fill: black;");
        
        GridPane fourthPane = new GridPane();
        fourthPane.setAlignment(Pos.TOP_CENTER);
        fourthPane.setVgap(10);
        fourthPane.setHgap(10);
        
        txtPOS.setEditable(false);
        txtMonth.setEditable(false);
        txtEmp.setEditable(false);
        txtPvE.setEditable(false);
        
        Scene fourthScene = new Scene(fourthPane, 500, 500);
        Stage fourthStage = new Stage();
        fourthStage.setScene(fourthScene);
        
        Label lblSelectProd = new Label("Select a Product:");
        Label lblSelectImage = new Label("Select an Image: ");
        
        
        //Employee Table
        //TableColumn tbEmpID = new TableColumn("ID");
        TableColumn tbEmpFName = new TableColumn("First Name");
        TableColumn tbEmpLName = new TableColumn("Last Name");
        TableColumn tbEmpEmail = new TableColumn("Email");
        TableColumn tbEmpPhone = new TableColumn("Phone");
        TableColumn tbEmpAddress = new TableColumn("Address");
        TableColumn tbEmpSalary = new TableColumn("Salary");
        TableColumn tbEmpJTitle = new TableColumn("Job Title");
        TableColumn tbEmpType = new TableColumn("Type");
        TableColumn tbEmpCat = new TableColumn("Category");
        TableColumn tbEmpLoc = new TableColumn("Location");
        
        //ValueFactory for Employee Table
        //tbEmpID.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("employeeID"));
        tbEmpFName.setCellValueFactory(new PropertyValueFactory<Employee, String>("firstName"));
        tbEmpLName.setCellValueFactory(new PropertyValueFactory<Employee, String>("lastName"));
        tbEmpEmail.setCellValueFactory(new PropertyValueFactory<Employee, String>("email"));
        tbEmpPhone.setCellValueFactory(new PropertyValueFactory<Employee, String>("phoneNumber"));
        tbEmpAddress.setCellValueFactory(new PropertyValueFactory<Employee, String>("address"));
        tbEmpSalary.setCellValueFactory(new PropertyValueFactory<Employee, Double>("salary"));
        tbEmpJTitle.setCellValueFactory(new PropertyValueFactory<Employee, String>("jobTitle"));
        tbEmpType.setCellValueFactory(new PropertyValueFactory<Employee, String>("employeeType"));
        tbEmpCat.setCellValueFactory(new PropertyValueFactory<Employee, String>("employeeCategory"));
        tbEmpLoc.setCellValueFactory(new PropertyValueFactory<Employee, String>("storeLoc"));
        
        
        // Product Table
        //TableColumn tbProdID = new TableColumn("ID");
        TableColumn tbProdName = new TableColumn("Name");
        TableColumn tbProdQuantity = new TableColumn("Quantity");
        TableColumn tbProdPrice = new TableColumn("Price");
        TableColumn tbProdDesc = new TableColumn("Description");
        TableColumn tbProdCategory = new TableColumn("Category");
        TableColumn tbProdLocation = new TableColumn("Location");
        TableColumn tbProdSupplier = new TableColumn("Supplier");
        TableColumn tbProdImage = new TableColumn("Image");
        
        Label Thrifty = new Label("Thrifty");
        Label Store = new Label("$tore");
        Thrifty.setStyle("-fx-font: bold 36pt \"Comic Sans ms\"; -fx-text-fill: red;");
        Store.setStyle("-fx-font: bold 36pt \"Comic Sans ms\"; -fx-text-fill: blue;");
        
        //ValueFactory for Product Table
        //tbProdID.setCellValueFactory(new PropertyValueFactory<Product, Integer>("productID"));
        tbProdName.setCellValueFactory(new PropertyValueFactory<Product, String>("productName"));
        tbProdQuantity.setCellValueFactory(new PropertyValueFactory<Product, Integer>("quantity"));
        tbProdPrice.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
        tbProdDesc.setCellValueFactory(new PropertyValueFactory<Product, String>("foodDescription"));
        tbProdCategory.setCellValueFactory(new PropertyValueFactory<Product, String>("category"));
        tbProdLocation.setCellValueFactory(new PropertyValueFactory<Product, String>("location"));
         tbProdSupplier.setCellValueFactory(new PropertyValueFactory<Product, String>("Supplier"));
        tbProdImage.setCellValueFactory(new PropertyValueFactory<Product, BufferedImage>("image"));
        
        // Expense Table
        TableColumn tbExpID = new TableColumn("ID");
        TableColumn tbExpCategory = new TableColumn("Category");
        TableColumn tbExpDesc = new TableColumn("Description");
        TableColumn tbExpAmount = new TableColumn("Amount Paid");
        TableColumn tbExpTtlAmount = new TableColumn("Total Amount");
        TableColumn tbExpDate = new TableColumn("Date Paid");
        TableColumn tbExpStatus = new TableColumn("Status");

        //ValueFactory for Expense Table
        tbExpID.setCellValueFactory(new PropertyValueFactory<Expense, Integer>("expenseID"));
        tbExpCategory.setCellValueFactory(new PropertyValueFactory<Expense, String>("category"));
        tbExpDesc.setCellValueFactory(new PropertyValueFactory<Expense, String>("description"));
        tbExpAmount.setCellValueFactory(new PropertyValueFactory<Expense, Double>("amountPaid"));
        tbExpTtlAmount.setCellValueFactory(new PropertyValueFactory<Expense, Double>("totalAmount"));
        tbExpDate.setCellValueFactory(new PropertyValueFactory<Expense, String>("date"));
        tbExpStatus.setCellValueFactory(new PropertyValueFactory<Expense, String>("status"));
        
        
        
        Scene primaryScene = new Scene(primaryPane, 500, 500);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("Main Form");
        primaryStage.show();
        
        BorderPane border = new BorderPane();
        border.setTop(menuAdmin);
        
        secondPane.setAlignment(Pos.TOP_CENTER);
        
        
            secondPane.add(Thrifty, 3, 2);
            secondPane.add(Store, 3, 3);
            secondPane.add(lblMain, 3, 4);
            secondPane.add(lblSelect, 3, 5);
            secondPane.add(btnRingSale, 3, 6);
        border.setCenter(secondPane);
        Scene secondScene = new Scene(border, 1050, 500);
        Stage secondStage = new Stage();
        secondStage.setScene(secondScene);
        menuAdmin.setMinWidth(secondScene.getWidth());
        
        try
        {
          ObjectInputStream instanceObjectInput = new ObjectInputStream(new FileInputStream("employees.dat"));

           savedEmp = (ArrayList<Employee>)(instanceObjectInput.readObject());
           for(Employee emp : savedEmp){
               System.out.println(emp.toString());
           }
           instanceObjectInput.close();
        }
        catch(Exception ex)
        {

        }
        
        
        btnSignIn.setOnAction(e -> {
            // code to validate log in, has to have two handlers for admin and worker
            // error handling

   //RUN LoadData.java BEFORE TRYING TO LOG IN.
           for(int i = 0; i< savedEmp.size();i++)
           {
               Employee temp = savedEmp.get(i);
               if(temp.getUsername().equalsIgnoreCase(txtUser.getText()) && temp.getPassword().equals(txtPass.getText()))
               {
                   found = true;
                   if(temp.getEmployeeType().equalsIgnoreCase("Manager"))
                   {
                       isAuthorized = true;
                   }
               }
           }

             if(!found) { // added the contents of the previously existing else statement here, outside the while
                alert.setTitle("Incorrect Login");
                alert.setHeaderText("Invalid Username or Password");
                alert.showAndWait();
                 
             }
            
            else
            {
             
            secondPane.setAlignment(Pos.TOP_CENTER);
            secondStage.setTitle("Main Menu");
            secondStage.show();
            primaryStage.close();
            
            
           
            
            //Set Ring Sale Button Formatting
//            btnRingSale.setPrefSize(120, 150);
//            btnRingSale.setFont(Font.font("Times New Roman", 20));
//            
             
            if(isAuthorized == false)
            {
            menuAdmin.getMenus().get(1).getItems().get(0).setDisable(true); //Employee management
            menuAdmin.getMenus().get(1).getItems().get(2).setDisable(true); //Expense Management
            }
            }
        });
                
        menuAdmin.getMenus().get(0).getItems().get(0).setOnAction(e -> {
            // Main Menu
            secondPane.getChildren().clear();
            formPane.getChildren().clear();
            border.getChildren().clear();
            
            secondPane.setAlignment(Pos.TOP_CENTER);
            secondStage.setTitle("Main Menu");
            //secondPane.add(menuAdmin, 0, 0, 4, 1);
            
            secondPane.add(lblThrifty, 3, 2);
            secondPane.add(lblStore, 3, 3);
            secondPane.add(lblMain, 3, 4);
            secondPane.add(lblSelect, 3, 5);
            secondPane.add(btnRingSale, 3, 6);

            border.setTop(menuAdmin);
            border.setCenter(secondPane);
            
        });
        
            prodTable.getColumns().addAll(tbProdName, tbProdQuantity, tbProdPrice, tbProdDesc, tbProdCategory, tbProdLocation, tbProdSupplier, tbProdImage);        
            menuAdmin.getMenus().get(1).getItems().get(1).setOnAction(e -> {
            // Inventory Management
            
            secondPane.getChildren().clear();
            formPane.getChildren().clear();
            border.getChildren().clear();
            secondPane.setAlignment(Pos.TOP_CENTER);
            secondStage.setTitle("Inventory Management");
            
            btnAddProd.setMaxWidth(Double.MAX_VALUE);
            btnRemoveProd.setMaxWidth(Double.MAX_VALUE);
            btnHandleImage.setMaxWidth(Double.MAX_VALUE);
            createSupp.setMaxWidth(Double.MAX_VALUE);
            btnViewOrder.setMaxWidth(Double.MAX_VALUE);
            btnAddOrder.setMaxWidth(Double.MAX_VALUE);
            VBox vbButtons = new VBox();
            vbButtons.setSpacing(10);
            vbButtons.setPadding(new Insets(10, 20, 10 , 20));
            
            vbButtons.getChildren().addAll(lblInv, lblInvMan, btnAddProd, btnRemoveProd, btnHandleImage, createSupp, btnAddOrder, btnViewOrder);
            //secondPane.add(vbButtons, 3, 3);
            prodTable.setItems(prodData);
            //secondPane.add(prodTable, 5, 3);
            border.setTop(menuAdmin);
            border.setLeft(vbButtons);
            border.setCenter(prodTable);
            prodTable.setMinWidth(secondScene.getWidth());
//            secondPane.setMaxWidth(secondScene.getWidth());
            
        });
        btnAddOrder.setOnAction(e -> {
            fifthPane.getChildren().clear();
            fifthStage.setTitle("Create a Product Order");
            fifthStage.show();
            fifthPane.setAlignment(Pos.TOP_CENTER);
            
            
            fifthPane.add(lblProdName, 0, 0);
            fifthPane.add(txtProdName, 0, 1);
            fifthPane.add(lblUnitCost, 1,4); 
            fifthPane.add(txtUnitCost, 1, 5); 
            fifthPane.add(lblCategory, 0, 2);
            fifthPane.add(cmboCategory, 0, 3);
            fifthPane.add(lblSubTotal, 1, 7); 
            fifthPane.add(btnCalcProdSubTotal, 0, 8);
            fifthPane.add(txtSubTotal, 1, 8); 
            fifthPane.add(lblOrderDate, 0, 6); 
            fifthPane.add(orderDate, 0, 7); 
            //need to add order date into the pane and use the constructor with it 
            
     
            fifthPane.add(lblDelDate, 0, 4); 
          
            fifthPane.add(calendar, 0, 5);
            fifthPane.add(lblQuantity, 1, 0);
            fifthPane.add(txtProdQuantity, 1, 1);
//            thirdPane.add(lblVendor, 0, 7);
//            thirdPane.add(txtVendor, 0, 8);
            fifthPane.add(lblSupplier, 1, 2); 
            fifthPane.add(cmboSupp, 1, 3);
            //thirdPane.add(lblImage, 0, 9);
            //thirdPane.add(btnImage, 0, 10);
            fifthPane.add(lblProdStore, 2,2);
            fifthPane.add(cmboStore, 2, 3); //when picking which store the product is for, should be selected from already existing stores created 
            
            
            fifthPane.add(btnCompleteOrder, 0, 9);
        }); 
        
        createSupp.setOnAction(e -> {
            eighthPane.getChildren().clear();
            eighthStage.setTitle("Create a Supplier");
            eighthStage.show();
            eighthPane.setAlignment(Pos.TOP_CENTER);       
            eighthPane.add(lblAddSupp, 0, 0); 
            eighthPane.add(lblSuppName, 0,1); 
            eighthPane.add(txtSuppName, 0, 2); 
            eighthPane.add(lblSuppPhone, 1,1); 
            eighthPane.add(txtSuppPhone, 1,2); 
            eighthPane.add(lblSuppEmail, 0, 3);
            eighthPane.add(txtSuppEmail, 0, 4);
            eighthPane.add(lblSuppAdd, 0, 5);
            eighthPane.add(txtSuppAdd, 0, 6);
            eighthPane.add(lblSuppContactName, 1, 3);
            eighthPane.add(txtSuppContactName, 1, 4);
            eighthPane.add(lblSuppContactPhone, 1, 5);
            eighthPane.add(txtSuppContactPhone, 1, 6);
            eighthPane.add(lblSuppContactEmail, 1, 7);
            eighthPane.add(txtSuppContactEmail, 1, 8); 

            eighthPane.add(btnNewSupp, 0, 8); 
        });
        
        btnNewSupp.setOnAction(e -> {
            suppList.add(new Supplier(txtSuppName.getText(), 
                    txtSuppPhone.getText(), txtSuppEmail.getText(), txtSuppAdd.getText(), txtSuppContactName.getText(), 
                    txtSuppContactPhone.getText(), txtSuppContactEmail.getText())); 
            Supplier.obsSupp.add(txtSuppName.getText());
            alert.setTitle("Success!");
            alert.setHeaderText("The new supplier has been created!");
            alert.showAndWait();
            //insert code to add product to object

            eighthStage.close();  

            for (int i = 0; i <suppList.size(); i ++) {
                System.out.println(suppList.get(i)); 
            }
        }); 
        
        btnCalcProdSubTotal.setOnAction(e -> {
            double quantity = Integer.parseInt(txtProdQuantity.getText());
            double cost = Double.parseDouble(txtUnitCost.getText()); 
            double subTotal = quantity * cost; 
            String subTotal2 = ""; 
            subTotal2 = Double.toString(subTotal); 
            txtSubTotal.setText(subTotal2); 
            
        }); 
        
        btnCompleteOrder.setOnAction(e -> {

             String date = calendar.getValue().format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
            String orderDate2 = orderDate.getValue().format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
            prodOrderList.add(new ProductOrder(Integer.parseInt(txtProdQuantity.getText()), txtProdName.getText(), Double.parseDouble(txtUnitCost.getText()),
                    Double.parseDouble(txtSubTotal.getText()), date, orderDate2, (String)cmboSupp.getValue(), (String)cmboStore.getValue(), (String)cmboCategory.getValue())); 
            ProductOrder.obsProdOrder.add(txtProdName.getText());

            alert.setTitle("Success!");
            alert.setHeaderText("The product order has been created!");
            alert.showAndWait();
            //insert code to add product to object

             fifthStage.close();  
            //for (int i = 0; i < prodOrderList.size(); i++) {
                //System.out.println(prodOrderList.get(i)); 
            //}
        }); 
        
        btnViewOrder.setOnAction(e -> {
            fifthPane.getChildren().clear();
            fifthStage.setTitle("View Product Orders");
            fifthStage.show();
            fifthPane.setAlignment(Pos.TOP_CENTER);


             fifthPane.add(cmboProdOrder, 0, 0); 
            fifthPane.add(btnDisplayPO, 0, 1); 
            fifthPane.add(prodOrderOutput, 1, 2, 12, 15);  
        }); 
        
        
        btnDisplayPO.setOnAction(e -> {
            ProductOrder tempProdOrder = prodOrderList.get(cmboProdOrder.getSelectionModel().getSelectedIndex());
            //String prodOrderSelect2 = Integer.toString(prodOrderSelect); 
            prodOrderOutput.setText(tempProdOrder.toString());   
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
//            thirdPane.add(lblVendor, 0, 7);
//            thirdPane.add(txtVendor, 0, 8);
            thirdPane.add(lblSupplier, 0, 7); 
            thirdPane.add(txtSupplier, 0, 8); 
            thirdPane.add(lblImage, 0, 9);
            thirdPane.add(btnImage, 0, 10);
            thirdPane.add(lblProdStore, 1,7 );
            thirdPane.add(cmboStore, 1, 8 ); //when picking which store the product is for, should be selected from already existing stores created 
            
            
            thirdPane.add(addProd, 0, 16);
           
        });
        
        addProd.setOnAction(e -> {
            //product added clicked
            int storeLocation = cmboStore.getSelectionModel().getSelectedIndex(); 
            String storeLocation2 = Integer.toString(storeLocation); 
            int productCategory = cmboCategory.getSelectionModel().getSelectedIndex();  
            
            prodList.add(new Product(txtProdName.getText(), 
                    Integer.valueOf(txtProdQuantity.getText()),
                    Double.valueOf(txtUnitPrice.getText()), 
                     storeList.get(cmboStore.getSelectionModel().getSelectedIndex()), txtProdDesc.getText(), 
                    (String) cmboCategory.getValue(), txtSupplier.getText(), tempImage));
            
            alert.setTitle("Success!");
            alert.setHeaderText("The product has been added!");
            alert.showAndWait();
            //insert code to add product to object
            
            thirdStage.close();
            
            prodData.clear();
            for(Product prod: prodList)
            {
                prodData.add(prod);
            }
            
        });
        
        btnRemoveProd.setOnAction(e -> {
            thirdPane.getChildren().clear();
            thirdStage.setTitle("Remove a Product");
            thirdStage.show();
            thirdPane.setAlignment(Pos.TOP_CENTER);
            
            thirdPane.add(lblRemoveProd, 0, 0);
            thirdPane.add(lblSelectProd, 0, 1);
            thirdPane.add(cmboProd, 0, 2);
            thirdPane.add(lblRQuantity, 0, 3);
            thirdPane.add(txtRQuantity, 0, 4);
            
            thirdPane.add(removeProd, 0, 5);
            
        });
        
        removeProd.setOnAction(e -> {
            
            int removeProd1 = cmboProd.getSelectionModel().getSelectedIndex(); 
            Product tempProd = prodList.get(removeProd1);
            
            int remove = Integer.parseInt(txtRQuantity.getText());
            
            tempProd.setQuantity(tempProd.getQuantity()-remove);
            
            for(int i = 0; i<prodList.size(); i++){
                if(tempProd.getProductID() == prodList.get(i).getProductID()){
                    prodList.remove(i);
                    prodList.add(i, tempProd);
                }
            }
            prodData.clear();
            for(Product prod: prodList)
            {
                prodData.add(prod);
            }
            
            
            // insert code to let you remove certain amount
            
            alert.setTitle("Success!");
            alert.setHeaderText("The product has been removed!");
            alert.showAndWait();
            
            thirdStage.close();
        });
        btnImage.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose Image");
            File file = fileChooser.showOpenDialog(thirdStage);
            
            try{
                tempImage = ImageIO.read(file);
                WritableImage iImage = SwingFXUtils.toFXImage(tempImage, null);
                
                myImageView = new ImageView();
                myImageView.setImage(iImage);
                thirdPane.add(myImageView, 1, 11, 4, 4);
                myImageView.setFitHeight(130);
                myImageView.setFitWidth(130);
                imageList.add(iImage); 
                
            }
            catch(Exception ex){
                
            }
           
        });
        
        btnHandleImage.setOnAction(e -> {
            
            fourthPane.getChildren().clear();
            fourthStage.setTitle("View/Edit an Image");
            fourthStage.show();
            fourthPane.setAlignment(Pos.TOP_LEFT);
            
            
            fourthPane.add(lblSelectProd, 0, 0);
            fourthPane.add(cmboProd, 1, 0);
            fourthPane.add(btnViewImage, 0, 2);
            fourthPane.add(btnChangeImage, 1, 2);
            
            imageChosen = cmboProd.getSelectionModel().getSelectedIndex();
            
            
            
        
        });
        /*btnChangeImage.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose Image");
            File file = fileChooser.showOpenDialog(thirdStage);
            
            try{
                tempImage = ImageIO.read(file);
                WritableImage iImage = SwingFXUtils.toFXImage(tempImage, null);
                
                myImageView = new ImageView();
                myImageView.setImage(iImage);
                fourthPane.add(myImageView, 0, 11, 6, 5);
                imageList.remove(imageChosen);
                for (int i = 0; i < imageList.size(); i++)
                {
                    if (i == imageChosen)
                        imageList.remove(i); 
                        imageList.set(imageChosen, iImage);
                        
                }
                     
                myImageView.setFitHeight(130);
                myImageView.setFitWidth(130);
                
            }
            catch(Exception ex){
                
            }
        });*/
        btnViewImage.setOnAction(e -> {
            imageChosen = cmboProd.getSelectionModel().getSelectedIndex(); 
            WritableImage theImage = imageList.get(imageChosen); 
            imageViewTwo = new ImageView();
            imageViewTwo.setImage(theImage);
            fourthPane.add(imageViewTwo, 0, 11, 6, 5); 

        }); 
           
        
        
        
        empTable.getColumns().addAll(tbEmpFName, tbEmpLName, tbEmpEmail,
                tbEmpPhone, tbEmpAddress, tbEmpSalary, tbEmpJTitle, tbEmpType, tbEmpCat, tbEmpLoc);
        
        
        menuAdmin.getMenus().get(1).getItems().get(0).setOnAction(e -> {
            // Employee Management
              
            
            secondPane.getChildren().clear();
            formPane.getChildren().clear();
            border.getChildren().clear();
            secondPane.setAlignment(Pos.TOP_CENTER);
            secondStage.setTitle("Employee Management");
            
            cmboEmp.setMaxWidth(Double.MAX_VALUE);
            btnEditEmp.setMaxWidth(Double.MAX_VALUE);
            btnAddEmp.setMaxWidth(Double.MAX_VALUE);

            VBox empVB = new VBox();
            empVB.setSpacing(10);
            empVB.setPadding(new Insets(10, 20, 10 , 20));
            
            empVB.getChildren().addAll(lblEmp, lblEmpMan, cmboEmp, btnEditEmp, btnAddEmp);
            
            border.setLeft(empVB);
            empTable.setItems(empData);
            //secondPane.add(empTable, 3, 9);
            empTable.setMinWidth(secondScene.getWidth());
            
            border.setTop(menuAdmin);
            border.setCenter(empTable);
            
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
            thirdPane.add(cmboJob, 1, 6);
            thirdPane.add(lblSalary, 0, 7);
            thirdPane.add(txtSalary, 0, 8);
            thirdPane.add(addEmp, 0, 13);
//            thirdPane.add(lblWorkPay, 1, 7);
//            thirdPane.add(txtWorkPay, 1, 8);
            thirdPane.add(empType, 1, 7); //full time or part time 
            thirdPane.add(cmboEType, 1, 8); 
            thirdPane.add(empCat, 0, 9); //manager or associate 
            thirdPane.add(cmboEmpCat, 0, 10);
            //thirdPane.add (cmboEmp, 1, 13); 
            thirdPane.add(storeLoc, 1, 9); 
            thirdPane.add(cmboStore, 1, 10); 
            
            cmboJob.setEditable(true);
            
            
        });
        
        addEmp.setOnAction(e -> {
            //employee added clicked
            
                
            empList.add(new Employee(txtFName.getText(), txtLName.getText(), txtEmail.getText(), txtPhone.getText(), 
            txtAddress.getText(), Double.valueOf(txtSalary.getText()),  
                    (String)cmboJob.getValue(), (String)cmboEType.getValue(), (String)cmboEmpCat.getValue(), (String) cmboStore.getValue())); 
            //changed parse to valueof
            for(int i = 0; i<job.size(); i++){
                if((job.get(i).equalsIgnoreCase((String)cmboJob.getValue())))
                {
                    break;
                }
                else if(i == job.size()-1 && job.get(i).equalsIgnoreCase((String)cmboJob.getValue()) == false)
                {
                    job.add((String)cmboJob.getValue());
                }
                
            }
            alert.setTitle("Success!");
            alert.setHeaderText("The employee has been added!");
            alert.showAndWait();
            
            empData.clear();
            for(Employee emp: empList)
            {
                empData.add(emp);
            }
            
            txtFName.clear();
            txtLName.clear();
            txtEmail.clear();
            txtPhone.clear();
            txtAddress.clear();
            txtSalary.clear();
            cmboJob.getSelectionModel().clearSelection();
            cmboStore.getSelectionModel().clearSelection();
            cmboEType.getSelectionModel().clearSelection();
            cmboEmpCat.getSelectionModel().clearSelection();
            
            
            thirdStage.close();
        });
        
        btnEditEmp.setOnAction(e -> {
            // They have no way of selecting an employee rn
            if(cmboEmp.getValue() == null){
                alert.setTitle("Error!");
                alert.setHeaderText("Please select an employee to edit!");
                alert.showAndWait();
            }

            else{
            eC = empList.get(cmboEmp.getSelectionModel().getSelectedIndex());
            thirdPane.getChildren().clear();
            thirdStage.setTitle("Edit Employee");
            thirdStage.show();
            thirdPane.setAlignment(Pos.TOP_CENTER);

            // SHU - I switched txtStoreLoc to the cmboStore so do whatever accordingly to fix these
            // I adjusted for these - Shu
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
            thirdPane.add(cmboJob, 1, 6);
            thirdPane.add(lblSalary, 0, 7);
            thirdPane.add(txtSalary, 0, 8);
            thirdPane.add(editEmp, 0, 13);
            thirdPane.add(storeLoc, 1, 9); 
            thirdPane.add(cmboStore, 1, 10);
            thirdPane.add(empType, 1, 7); //full time or part time 
            thirdPane.add(cmboEType, 1, 8); 
            thirdPane.add(empCat, 0, 9); //manager or associate 
            thirdPane.add(cmboEmpCat, 0, 10);
            
            //Selecting cmboBox fields so that if unedited, will not give error
            for(int i = 0; i<job.size(); i++){
               if(eC.getJobTitle().equalsIgnoreCase((String)job.get(i)))
               {
                   cmboJob.getSelectionModel().select(i);
               }
            }
            for(int i = 0; i<storeList.size(); i++){
               if(eC.getStoreLoc().equalsIgnoreCase(storeList.get(i).getName()))
               {
                   cmboStore.getSelectionModel().select(i); 
               }
            }
            for(int i = 0; i<eType.size(); i++){
                if(eC.getEmployeeType().equalsIgnoreCase((String)eType.get(i)))
                {
                   cmboEType.getSelectionModel().select(i);
                }
            }
            for(int i = 0; i<empCategory.size(); i++){
                if(eC.getEmployeeCategory().equalsIgnoreCase((String)empCategory.get(i)))
                {
                   cmboEmpCat.getSelectionModel().select(i);
                }
            }
            
            
            txtFName.setText(eC.getFirstName());
            txtLName.setText(eC.getLastName());
            txtEmail.setText(eC.getEmail());
            txtPhone.setText(eC.getPhoneNumber());
            txtAddress.setText(eC.getAddress());
            cmboJob.setPromptText(eC.getJobTitle());
            txtSalary.setText(Double.toString(eC.getSalary()));
            cmboStore.setPromptText(eC.getStoreLoc());
            cmboEType.setPromptText(eC.getEmployeeType());
            cmboEmpCat.setPromptText(eC.getEmployeeCategory());


        }
        });
        editEmp.setOnAction(e -> {
            eC.setFName(txtFName.getText());
            eC.setLName(txtLName.getText());
            eC.setEmail(txtEmail.getText());
            eC.setAddress(txtAddress.getText());
            eC.setJobTitle((String)cmboJob.getValue());
            eC.setPhoneNumber(txtPhone.getText());
            eC.setStore((String) cmboStore.getValue());
            eC.setSalary(Double.valueOf(txtSalary.getText()));
            eC.setEmployeeCategory((String) cmboEmpCat.getValue());
            eC.setEmployeeType((String)cmboEType.getValue());
            
            for(int i = 0; i<empList.size(); i++){
                if(eC.getEmployeeID() == empList.get(i).getEmployeeID()){
                    empList.remove(i);
                    empList.add(i, eC);
                    eC = null;
                }
            }
            
            for(int i = 0; i<job.size(); i++)
            {
                if((job.get(i).equalsIgnoreCase((String)cmboJob.getValue())))
                {
                    break;
                }

                 else if(i == job.size()-1 && job.get(i).equalsIgnoreCase((String)cmboJob.getValue()) == false)
                {
                    job.add((String)cmboJob.getValue());
                }
            }

            alert.setTitle("Success!");
            alert.setHeaderText("The employee has been edited!");
            alert.showAndWait();

            empData.clear();
            for(Employee emp: empList)
            {
                empData.add(emp);
            }


            txtFName.clear();
            txtLName.clear();
            txtEmail.clear();
            txtPhone.clear();
            txtAddress.clear();
            txtSalary.clear();
            cmboJob.getSelectionModel().clearSelection();
            cmboStore.getSelectionModel().clearSelection();
            cmboEType.getSelectionModel().clearSelection();
            cmboEmpCat.getSelectionModel().clearSelection();
           

            thirdStage.close();
        });
        expTable.getColumns().addAll(tbExpID, tbExpCategory, tbExpDesc, tbExpAmount, tbExpTtlAmount, tbExpDate, tbExpStatus); 
        menuAdmin.getMenus().get(1).getItems().get(2).setOnAction(e -> {
           // Expense Management
           
           secondPane.getChildren().clear();
           formPane.getChildren().clear();
           border.getChildren().clear();
           
           secondPane.setAlignment(Pos.TOP_CENTER);
           secondStage.setTitle("Expense Management");
           
            btnAddExp.setMaxWidth(Double.MAX_VALUE);
            btnPayExp.setMaxWidth(Double.MAX_VALUE);
            btnEditExp.setMaxWidth(Double.MAX_VALUE);
            cmboExpense.setMaxWidth(Double.MAX_VALUE);
            
            expTable.setItems(expData);
            expTable.setMinWidth(secondScene.getWidth());
            
           VBox expVB = new VBox();
           expVB.setSpacing(10);
           expVB.setPadding(new Insets(10, 20, 10 , 20));
           
            
           expVB.getChildren().addAll(lblExp, lblExpMan, cmboExpense, btnAddExp, btnPayExp, btnEditExp);
           border.setTop(menuAdmin);
           border.setLeft(expVB);
           border.setCenter(expTable);
           
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
            thirdPane.add(expDate, 0, 4);
            thirdPane.add(lblCategory, 1, 1);
            thirdPane.add(cmboExpCategory, 1, 2);
            //thirdPane.add(lblStatus, 1, 3);
            //thirdPane.add(cmboStatus, 1, 4);
            thirdPane.add(lblAmount, 0, 5);
            thirdPane.add(txtAmount, 0, 6);
            thirdPane.add(lblTtlAmount, 1, 5);
            thirdPane.add(txtTtlAmount, 1, 6);
            //thirdPane.add(lblExpStore, 0, 7);
            //thirdPane.add(cmboStore, 0, 8);
            thirdPane.add(addExp, 0, 7);
        });
        
        addExp.setOnAction(e -> {
            //employee added clicked
            String description = txtExpDesc.getText();
            String category = (String)cmboCategory.getValue();
            String status = (String)cmboStatus.getValue();
            Double amountPaid = Double.parseDouble(txtAmount.getText());
            Double totalAmount = Double.parseDouble(txtTtlAmount.getText());
            String date = expDate.getValue().format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
            //Store payableStore = (obsStore.get(cmboStore.getSelectionModel().getSelectedIndex());

            obsExpense.add(new Expense(category, description, amountPaid, totalAmount, date));
            System.out.println(obsExpense.size() + " wah");
            //expList.add(new Expense(category, description, amountPaid, date, status));
            expList.add(new Expense((String)expCategory.get(cmboExpCategory.getSelectionModel().getSelectedIndex()), 
                    txtExpDesc.getText(), Double.parseDouble(txtAmount.getText()), Double.parseDouble(txtTtlAmount.getText()), 
                    expDate.getValue().format(DateTimeFormatter.ofPattern("MM-dd-yyyy"))));
            alert.setTitle("Success!");
            alert.setHeaderText("The expense has been added!");
            alert.showAndWait();
            //insert code to add employee to object

            thirdStage.close();


            expData.clear();
            for(Expense exp: expList)
            {
                expData.add(exp);
            }
            System.out.println(expList.get(0).toString());
            System.out.println(expData.get(0).toString());
            
            txtExpDesc.clear();
            txtAmount.clear();
            txtTtlAmount.clear();
            cmboExpCategory.getSelectionModel().clearSelection();
            cmboStatus.getSelectionModel().clearSelection();
            expDate.getEditor().clear();
            
            thirdStage.close();
        });
        
        btnEditExp.setOnAction(e -> {
            if(cmboExpense.getValue() == null || obsExpense.size() > 1){
                    alert.setTitle("Error!");
                alert.setHeaderText("Please select an expense to edit!");
                alert.showAndWait();
            }
            else
            {
                
                
                            // Adding an Expense
            thirdPane.getChildren().clear();
            thirdStage.setTitle("Add an Expense");
            thirdStage.show();
            thirdPane.setAlignment(Pos.TOP_CENTER);
            
            thirdPane.add(lblAddExp, 0, 0);
            thirdPane.add(lblExpDesc, 0, 1);
            thirdPane.add(txtExpDesc, 0, 2);
            thirdPane.add(lblDate, 0, 3);
            thirdPane.add(expDate, 0, 4);
            thirdPane.add(lblCategory, 1, 1);
            thirdPane.add(cmboExpCategory, 1, 2);
            //thirdPane.add(lblStatus, 1, 3);
            //thirdPane.add(cmboStatus, 1, 4);
            thirdPane.add(lblAmount, 0, 5);
            thirdPane.add(txtAmount, 0, 6);
            thirdPane.add(lblTtlAmount, 1, 5);
            thirdPane.add(txtTtlAmount, 1, 6);
            //thirdPane.add(lblExpStore, 0, 7);
            //thirdPane.add(cmboStore, 0, 8);
            thirdPane.add(editExp, 0, 7);
            
            exC = expList.get(cmboExpense.getSelectionModel().getSelectedIndex());
            txtExpDesc.setText(exC.getDescription());
            //expDate.setText(exC.getDate());
            cmboExpCategory.setPromptText(exC.getCategory());
            txtAmount.setText(String.valueOf(exC.getAmountPaid()));
            txtTtlAmount.setText(String.valueOf(exC.getTotalAmount()));
           


                
                
                /*thirdPane.getChildren().clear();
                thirdStage.setTitle("Add an Expense");
                thirdStage.show();
                thirdPane.setAlignment(Pos.TOP_CENTER);
                System.out.println(obsExpense.size());
                thirdPane.add(lblChooseExp, 0, 1); 
                //seventhPane.add(cmboExpense, 0, 2);
                //seventhPane.add(lblAddExp, 0, 0);
                thirdPane.add(lblExpDesc, 0, 3);
                thirdPane.add(txtExpDesc, 0, 4);
                thirdPane.add(lblDate, 0, 5);
                thirdPane.add(expDate, 0, 6);
                thirdPane.add(lblCategory, 1, 3);
                thirdPane.add(cmboExpCategory, 1, 4);
                thirdPane.add(lblStatus, 1, 5);
                thirdPane.add(cmboStatus, 1, 6);
                thirdPane.add(lblAmount, 0, 7);
                thirdPane.add(txtAmount, 0, 8);
                thirdPane.add(editExp, 0, 9);

                thirdStage.show();


                expCategoryList.add("Store Mortgage");
                expCategoryList.add("Utilities");
                expCategoryList.add("Lawn Care");
                expCategoryList.add("System Maintenance");
                expCategoryList.add("Payroll");
                expCategoryList.add("Inventory Purchase");
                expStatusList.add("Paid");
                expStatusList.add("Ongoing");

                expVar = cmboExpense.getSelectionModel().getSelectedIndex();

                txtExpDesc.setText(obsExpense.get(expVar).getDescription());
                //LocalDate localExpDate = LocalDate.parse(obsExpense.get(expVar).getDate());
                //expDate.setValue(localExpDate);
                cmboExpCategory.setValue(obsExpense.get(expVar).getCategory());
                cmboStatus.setValue(expStatusList.get(cmboStatus.getSelectionModel().getSelectedIndex()));
                txtAmount.setText((String.valueOf(obsExpense.get(expVar).getAmountPaid())));

                obsExpense.get(expVar).setDescription(txtExpDesc.getText());
                obsExpense.get(expVar).setCategory(expCategoryList.get(cmboExpCategory.getSelectionModel().getSelectedIndex()));
                obsExpense.get(expVar).setAmountPaid(Double.parseDouble(txtAmount.getText()));
                obsExpense.get(expVar).setStatus(expStatusList.get(cmboStatus.getSelectionModel().getSelectedIndex()));
                obsExpense.get(expVar).setDate(expDate.getValue().format(DateTimeFormatter.ofPattern("MM-dd-yyyy")));
                        alert.setTitle("Success");
                        alert.setHeaderText("This expense has been edited!");
                        alert.showAndWait();*/
                }
        });
        editExp.setOnAction(e -> {
            
                exC = expList.get(cmboExpense.getSelectionModel().getSelectedIndex());
                if (txtExpDesc.getText() != null)
                {
                exC.setDescription(txtExpDesc.getText());
                }
                if (txtAmount.getText() != null)
                {
                exC.setAmountPaid(Double.parseDouble(txtAmount.getText()));
                }
                if (txtTtlAmount.getText() != null)
                {
                exC.setTotalAmount(Double.parseDouble(txtTtlAmount.getText()));
                }
                if (cmboExpCategory.getValue() != null)
                {
                exC.setCategory((String)cmboExpCategory.getValue());
                }
                System.out.println(exC.toString() + " wahhhhhhhhhhh");
                if (exC.getAmountPaid() > exC.getTotalAmount())
                {
                    exC.setStatus("Ongoing");
                }
                if (exC.getAmountPaid() <= exC.getTotalAmount())
                {
                    exC.setStatus("Paid");
                }
                
                //exC.setDate((expDate.getValue().format(DateTimeFormatter.ofPattern("MM-dd-yyyy"))));
        

            alert.setTitle("Success!");
            alert.setHeaderText("Expense has been Edited!");
            alert.showAndWait();
            thirdStage.close();
            expData.clear();
            for(Expense exp: expList)
            {
                expData.add(exp);
            }
        
        }); 
        btnPayExp.setOnAction(e -> {
             
            if(Expense.obsExpense.size() > 1){
                    alert.setTitle("Error!");
                alert.setHeaderText("No expenses to pay!");
                alert.showAndWait();
            }
          else
            {
           
           for (int i = 0; i < expList.size(); i++)
           {
               if(expList.get(i).getStatus().equalsIgnoreCase("ONGOING"))
                       {
                           obsUnpaidExp.add(expList.get(i));
                       }
           }}
           
           
           System.out.println(obsUnpaidExp.size());
           unpaidExpVar = cmboUnpaidExp.getSelectionModel().getSelectedIndex();
           eighthPane.add(lblChooseExp, 0, 1);
           eighthPane.add(cmboUnpaidExp, 0, 2);
           eighthPane.add(lblPayExpenseHeader, 0, 0);
           eighthPane.add(lblExpCurrentAmount, 0, 3);
           eighthPane.add(txtExpCurrentAmount, 0, 4);
           eighthPane.add(lblChoosePayment, 1, 3);
           eighthPane.add(txtChoosePayment, 1, 4);
           eighthPane.add(lblDisplayExpAmount, 0, 5);
           eighthPane.add(txtDisplayExpAmount, 0, 6);
           txtExpCurrentAmount.isEditable();
           txtDisplayExpAmount.isEditable();
           eighthStage.show();
           
           txtExpCurrentAmount.setText(String.valueOf(expList.get(0).getAmountPaid()));
           //System.out.println(expList.get(0).payExpense(Double.parseDouble(txtChoosePayment.getText())));
           
       
           
       
           
       
       });

        
        
         menuAdmin.getMenus().get(4).getItems().get(0).setOnAction(e -> {
             // sign out
            secondStage.close();
            primaryPane.getChildren().clear();
            primaryPane.add(lblThrifty, 0, 0);
            primaryPane.add(lblStore, 0, 1);
            primaryPane.add(lblSignIn, 0, 3);
            primaryPane.add(lblUserName, 0, 4);
            primaryPane.add(txtUser, 0, 5);
            primaryPane.add(lblPassword, 0, 6);
            primaryPane.add(txtPass, 0, 7);
            primaryPane.add(btnSignIn, 0, 9);

            lblThrifty.setStyle("-fx-font: bold 36pt \"Comic Sans ms\"; -fx-text-fill: red;");
            lblStore.setStyle("-fx-font: bold 36pt \"Comic Sans ms\"; -fx-text-fill: blue;");
            lblSignIn.setStyle("-fx-font: bold 16pt \"Arial\";");
            primaryStage.setScene(primaryScene);
            primaryStage.setTitle("Main Form");
            primaryStage.show();
            txtUser.clear();
            txtPass.clear();
        
        });
        
        //Ring Sale Button
        //Open new window to ring up sale 
       
        btnRingSale.setOnAction(e -> {
            
            CustSaleForm newSale = new CustSaleForm(this);
            
        });
        
        menuAdmin.getMenus().get(2).getItems().get(0).setOnAction(e -> {
            // Payroll
            secondPane.getChildren().clear();
            border.getChildren().clear();
            secondStage.setTitle("Payroll");
            VBox vPay = new VBox();
            vPay.setSpacing(10);
            vPay.setPadding(new Insets(10, 20, 10 , 20));
            
            if(isAuthorized == true)
            {
                // What managers will see
                vPay.getChildren().addAll(lblPayroll, lblPEEmp, cmboEmp, btnPEEmp, btnEnterTime);
            }
            
            else
            {
                // What associates will see
                vPay.getChildren().addAll(lblPayroll, btnEnterTime);
            }
            border.setCenter(txtPayroll);
            border.setTop(menuAdmin);
            border.setLeft(vPay);
            
        });
        
        
        menuAdmin.getMenus().get(3).getItems().get(0).setOnAction(e -> {
            // Point of Sales Report
            secondPane.getChildren().clear();
            formPane.getChildren().clear();
            border.getChildren().clear();
            
            secondPane.setAlignment(Pos.TOP_CENTER);
            formPane.setAlignment(Pos.TOP_LEFT);
            formPane.setVgap(10);
            secondStage.setTitle("Point of Sales Report");
            tbPOS.getTabs().add(tabPOSText);
            tbPOS.getTabs().add(tabPOSChart);
            tbPOS.setMinWidth(secondScene.getWidth());
            
            cmboType.setMaxWidth(Double.MAX_VALUE);
            cmboStore.setMaxWidth(Double.MAX_VALUE);
            btnPOSDisplay.setMaxWidth(Double.MAX_VALUE);

            VBox vPOS = new VBox();
            vPOS.setSpacing(10);
            vPOS.setPadding(new Insets(10, 20, 10 , 20));
            
            vPOS.getChildren().addAll(lblPOS, lblReport, lblViewType, cmboType, lblLocation, cmboStore, btnPOSDisplay);
            border.setTop(menuAdmin);
            border.setLeft(vPOS);
            border.setCenter(tbPOS);
            
            
            tabPOSText.setContent(txtPOS);
        });
        
        menuAdmin.getMenus().get(3).getItems().get(1).setOnAction(e -> {
            // Employee Report
            secondPane.getChildren().clear();
            formPane.getChildren().clear();
            border.getChildren().clear();
            
            secondPane.setAlignment(Pos.TOP_CENTER);
            
            secondStage.setTitle("Employee Report");
            cmboStore.setMaxWidth(Double.MAX_VALUE);
            btnEmpDisplay.setMaxWidth(Double.MAX_VALUE);

            VBox vEmp = new VBox();
            vEmp.setSpacing(10);
            vEmp.setPadding(new Insets(10, 20, 10 , 20));
            
            vEmp.getChildren().addAll(lblEmpRep, lblEmpLocation, cmboStore, btnEmpDisplay);
            border.setTop(menuAdmin);
            border.setLeft(vEmp);
            border.setCenter(txtEmp);
            
            
        });
        
        menuAdmin.getMenus().get(3).getItems().get(2).setOnAction(e -> {
           // Monthly Expense Report
           secondPane.getChildren().clear();
           formPane.getChildren().clear();
           border.getChildren().clear();
           
           secondPane.setAlignment(Pos.TOP_CENTER);
           secondStage.setTitle("Monthly Expense Report");
           cmboStore.setMaxWidth(Double.MAX_VALUE);
           btnMonthDisplay.setMaxWidth(Double.MAX_VALUE);
           
           VBox vMonth = new VBox();
           vMonth.setSpacing(10);
           vMonth.setPadding(new Insets(10, 20, 10 , 20));
            
           vMonth.getChildren().addAll(lblMonthRep, lblReport, lblMLocation, cmboStore, btnMonthDisplay);
           border.setLeft(vMonth);
           border.setTop(menuAdmin);
           border.setCenter(txtMonth);
           
           
        });
           
        
            tbPvE.getTabs().add(tabPText);
            tbPvE.getTabs().add(tabPPie);
            tbPvE.getTabs().add(tabPBar);
            tbPvE.getTabs().add(tabPLine);
        menuAdmin.getMenus().get(3).getItems().get(3).setOnAction(e -> {
           // Profit v. Expense Report
           secondPane.getChildren().clear();
           formPane.getChildren().clear();
           textPane.getChildren().clear();
           secondPane.setAlignment(Pos.TOP_CENTER);
           textPane.setAlignment(Pos.TOP_LEFT);
           textPane.setVgap(10);
           secondStage.setTitle("Profit v. Expense Report");
           
           
           secondPane.add(menuAdmin, 0, 0, 4, 1);
           
           secondPane.add(tbPvE, 0, 1, 4, 1);
           
           tbPvE.setMinWidth(secondScene.getWidth());
           
            cmboYear.setMaxWidth(Double.MAX_VALUE);
            cmboStore.setMaxWidth(Double.MAX_VALUE);
            btnPvEDisplay.setMaxWidth(Double.MAX_VALUE);


           VBox vPVE = new VBox();
           vPVE.setSpacing(10);
           vPVE.setPadding(new Insets(10, 20, 10 , 20));
           vPVE.getChildren().addAll(lblPvE, lblReport, lblYear, cmboYear, lblPLocation, cmboStore);
           
           border.setTop(menuAdmin);
           border.setLeft(vPVE);
           border.setCenter(tbPvE);
           
           tabPText.setContent(txtPvE);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
