package pkg484groupproj;

import pkg484groupproj.Store;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.time.Month;
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
import javafx.scene.image.*;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import static javafx.application.Application.launch;
import java.util.ArrayList;
import static pkg484groupproj.GUI.expList;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import oracle.jdbc.pool.*;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;

public class GUI extends Application 
{
    // Sign In Menu
    Label lblThrifty = new Label("Thrifty");
    Label lblStore = new Label("$tore");
    Label lblSignIn = new Label("Sign In");
    Label lblUserName = new Label("Username");
    Label lblPassword = new Label("Password");
    Button btnSignIn = new Button("Sign In");
    TextField txtUser = new TextField();
    PasswordField txtPass = new PasswordField(); 
    Label storeLoc = new Label("Store Location"); 
    TextField txtStoreLoc = new TextField();
    boolean isAuthorized = false;
    boolean found = false;
    static Employee currentUser;
    
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
    static ArrayList<String> categoryList = new ArrayList<>();

    
    //Observabble Lists/Combo Boxes
    ObservableList<Employee> obsEmp = FXCollections.observableArrayList();
    ComboBox cmboEmp = new ComboBox(Employee.obsEmp); 
    ObservableList<Product> obsProd = FXCollections.observableArrayList();
    ComboBox cmboProd = new ComboBox(Product.obsProd); 
    ComboBox cmboProd2 = new ComboBox(Product.obsProd);
    ObservableList<Customer> obsCust = FXCollections.observableArrayList();
    ComboBox cmboCust = new ComboBox(obsCust); 
    static ObservableList<Store> obsStore = FXCollections.observableArrayList();
    static ComboBox cmboStore = new ComboBox(Store.obsStore);
    ObservableList<BufferedImage> imageList = FXCollections.observableArrayList();
    static ObservableList<ProductOrder> obsProdOrder = FXCollections.observableArrayList();
    static ComboBox cmboProdOrder = new ComboBox(ProductOrder.obsProdOrder);
    static ComboBox cmboProdOrder2 = new ComboBox(ProductOrder.obsProdOrder);
    public static ObservableList<CustSale> obsSale = FXCollections.observableArrayList();
    public static ComboBox cmboSale = new ComboBox(obsSale);
    public static ObservableList<Supplier> obsSupp = FXCollections.observableArrayList(); 
    public static ComboBox cmboSupp = new ComboBox(Supplier.obsSupp);
    public static ComboBox cmboSupp2 = new ComboBox(Supplier.obsSupp);
    public static ObservableList<Member> obsMem = FXCollections.observableArrayList();
    public static ComboBox<Member> cmboMem = new ComboBox(Member.obsMem);
    static ObservableList<ProductOrder> obsRecOrder = FXCollections.observableArrayList();
//    ObservableList 
    static ObservableList<Expense> obsExpense = FXCollections.observableArrayList();
    ComboBox cmboExpense = new ComboBox(Expense.obsExpense);
    ObservableList<Store> obsExpStore = FXCollections.observableArrayList();
    ComboBox cmboExpStore = new ComboBox(obsExpStore);
    public static ObservableList<String> empPayroll  = FXCollections.observableArrayList();
    
    int expVar = 0;
    int unpaidExpVar = 0;
    String payExpVar = "";
    int expCatVar = 0;
    int expStoreVar = 0;
    // Main Menu Buttons
    Button btnRingSale = new Button("Ring Sale");
    
    //Tables
    TableView<Employee> empTable = new TableView<>();
    ObservableList<Employee> empData = FXCollections.observableArrayList(empList);
    TableView<Product> prodTable = new TableView<>();
    ObservableList<Product> prodData = FXCollections.observableArrayList(prodList);
    TableView<Expense> expTable = new TableView<>();
    ObservableList<Expense> expData = FXCollections.observableArrayList(expList);
    TableView<Store> storeTable = new TableView<>();
    ObservableList<Store> storeData = FXCollections.observableArrayList(storeList);

    
    
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
    Label lblSuppContactEmail = new Label("Supplier Contact Email Address: "); 
    TextField txtSuppContactEmail = new TextField(); 
    
    //Inventory Management
    Label lblInvMan = new Label("Management");
    Label lblInv = new Label("Inventory");
    Button btnAddProd = new Button("Add Product");
    Button btnRemoveProd = new Button("Edit/Remove Product");
    Button btnHandleImage = new Button("View/Edit Product Image");
    Label lblHandleImage = new Label("View/Edit Product Image");
    
    Button btnEditSupp = new Button("Edit a Supplier"); 
    Button btnEditPO = new Button("Edit/Cancel a Product Order"); 
    Button btnDisProd = new Button("Discontinue Product"); 
    Button btnConfirmSuppChanges = new Button("Save Changes"); 
    Button btnSavePO = new Button("Save Changes"); 
    Button btnCancelPO = new Button("Cancel Product Order"); 
    Label lblProdO = new Label("Product Order: "); 
    Button btnviewSupp = new Button("View Suppliers");
    Button viewSupp = new Button("View Supplier"); 
    Button btnViewAllSupp = new Button("View All"); 
    Label lblChooseSupp = new Label("Choose a Supplier: "); 
    Label lblViewSupp = new Label("View Supplier Information"); 
    TextArea suppOutput = new TextArea(); 
    

    //Add a Product
    Label lblEPO = new Label("Edit or Cancel a Product Order");
    Label lblPO = new Label("Create a Product Order");
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
    Button btnAddImage2 = new Button("Add Image");
    ImageView myImageView = new ImageView();
    ImageView myImageViewTwo = new ImageView();
    BufferedImage tempImage = null;
    Button addProd = new Button("Add Product");
    TextField txtSupplier = new TextField(); 
    Label lblSupplier = new Label("Supplier"); 
    Label lblProdStore = new Label("Store"); 
    Button btnCalcProdSubTotal = new Button("Calculate Subtotal");
    
    Button createSupp = new Button("Add a Supplier"); 
    Label lblViewPO = new Label("View Product Order");

    //Discontinue a Product 
    Label lblDisProd = new Label("Discontinue a Product"); 
    Label lblChooseProd = new Label("Choose Product: "); 
    Label lblChooseStore = new Label("Choose Store: "); 
    Button btnConfirmChange = new Button("Save Changes"); 
    Label lblOrderDate = new Label("Order Date"); 
    DatePicker orderDate = new DatePicker(); 
    Button btnAddOrder = new Button("Create Product Order"); 
    Button btnViewOrder = new Button("View Product Orders");
    Button btnCompleteOrder = new Button("Complete Product Order"); 
    TextArea prodOrderOutput = new TextArea(); 
    Button btnDisplayPO = new Button("Display Product Order Information");
    Button btnRecShip = new Button("Receive Product Order Shipment"); 
    Button btnViewAllPO = new Button("View All"); 
    Button btnRemoveSupp = new Button("Remove Supplier"); 
    
    int imageChosen;
    
    //Remove a Product
    Label lblRemoveProd = new Label("Remove or Edit a Product");
    Label lblSelectProd = new Label("Select a Product");
    Button btnChangeImage = new Button("Change Image");
    Button btnViewImage = new Button("View Image");
    // select a product
    Label lblRQuantity = new Label("Select quantity to remove");
    TextField txtRQuantity = new TextField();
    Button removeProd = new Button("Remove Product");
    Button editProd = new Button("Save Changes"); 
    Label lblEName = new Label("Name: "); 
    TextField txtEName = new TextField(); 
    Label lblEPrice = new Label("Unit Price: "); 
    TextField txtEPrice = new TextField(); 
    Label lblEStore = new Label("Store: "); 
    Label lblEDesc = new Label("Description: "); 
    TextField txtEDesc = new TextField(); 
    Label lblECat = new Label("Category: "); 
    Label lblESupp = new Label("Supplier: "); 
    Label lblESupplier = new Label("Edit Supplier"); 
    
    // Employee Management
    Label lblEmpMan = new Label("Management");
    Label lblEmp = new Label("Employee");
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
    Employee eC = null;
    Product pC = null; 
    Supplier sC = null; 
    Store stC = null;
    ProductOrder poC = null;    
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
    Button removeEmp = new Button("Remove Employee");
    
    // Expense Management
    Label lblExpMan = new Label("Management");
    Label lblExp = new Label("Expense");
    Button btnAddExp = new Button("Add Expense");
    Button btnPayExp = new Button("Pay Expense");
    Button btnEditExp = new Button("Edit Expense");
    Button btnAddExpType = new Button("Add Expense Category");
    Button btnRemExp = new Button("Remove Expense");
    Button btnRemExpType = new Button("Remove Expense Category");
    
    
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
    Label lblAmount = new Label("Amount Paid");
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
    Label lblEditExp = new Label("Edit Expense");
    Button editExp = new Button("Edit Expense");
    static ObservableList<Expense> obsUnpaidExp = FXCollections.observableArrayList();
    ComboBox cmboUnpaidExp = new ComboBox(unpaidExp);
    static ObservableList unpaidExp = FXCollections.observableArrayList();
    Button btnConfirmPayExp = new Button("Pay Expense");
    Button btnSelectExpense = new Button("Select This Expense");

    Label lblPayExpenseHeader = new Label("Pay Expense");
    Label lblExpCurrentAmount = new Label("Current Balance for this Expense:");
    TextField txtExpCurrentAmount = new TextField();
    Label lblChoosePayment = new Label("Select an Amount to Pay");
    TextField txtChoosePayment = new TextField();
    Label lblDisplayExpAmount = new Label("Remaining Payment");
    TextField txtDisplayExpAmount = new TextField();

    Label lblNewExpenseTypeHeader = new Label("Add New Expense Type");
    Label lblAddNewExpenseType = new Label("Enter an Expense Type to add");
    TextField txtAddNewExpenseType = new TextField();
    Button btnConfirmNewExp = new Button("Add New Expense Type");

    Label lblRemoveExpTypeHeader = new Label("Remove Expense Type");
    Label lblChooseExpType = new Label("Choose Expense Type to Remove");
    Button btnConfirmRemExpType = new Button("Remove Expense Type");

    Label lblStoreMan = new Label("Store Management");
    Button btnAddStore = new Button("Add Store");
    Button btnEditStore = new Button("Edit Store");
    Button btnViewStore = new Button ("View Store");
    Button btnRemoveStore = new Button("Remove Store");
    Button btnViewAllStore = new Button("View All Stores");

    Label lblStoreName = new Label("Enter Store Name");
    TextField txtStoreName = new TextField();
    Label lblStoreAddress = new Label("Enter Store Address");
    TextField txtStoreAddress = new TextField();

    Button btnConfirmAddStore = new Button("Add Store");

    Button btnConfirmEditStore = new Button("Edit Store");

    Label lblRemoveStore = new Label("Select a Store to Remove");
    Button btnConfirmRemoveStore = new Button("Remove Store");

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
     Payroll tempPay;
    double hourStart = 7.0;
    double hourEnd = 7.5;
    double cutOff = 22.0;
    ArrayList<Double> hoursIn = new ArrayList<>();
    ArrayList<Double> hoursOut = new ArrayList<>();
    static ObservableList<String> timeI = FXCollections.observableArrayList("07:00am", "07:30am", "08:00am", "08:30am", "09:00am", "09:30am", "10:00am", "10:30am", "11:00am", "11:30am",
            "12:00pm", "12:30pm", "01:00pm", "01:30pm", "02:00pm", "02:30pm", "03:00pm", "03:30pm", "04:00pm", "04:30pm", "05:00pm", "05:30pm", "06:00pm", "06:30pm", "07:00pm", "07:30pm",
            "08:00pm", "08:30pm", "09:00pm", "09:30pm", "10:00pm");
    static ObservableList<String> timeO = FXCollections.observableArrayList("07:30am", "08:00am", "08:30am", "09:00am", "09:30am", "10:00am", "10:30am", "11:00am", "11:30am",
            "12:00pm", "12:30pm", "01:00pm", "01:30pm", "02:00pm", "02:30pm", "03:00pm", "03:30pm", "04:00pm", "04:30pm", "05:00pm", "05:30pm", "06:00pm", "06:30pm", "07:00pm", "07:30pm",
            "08:00pm", "08:30pm", "09:00pm", "09:30pm", "10:00pm");
    Label lblSun = new Label("Sunday");
    Label lblMon = new Label("Monday");
    Label lblTues = new Label("Tuesday");
    Label lblWed = new Label("Wednesday");
    Label lblThur = new Label("Thursday");
    Label lblFri = new Label("Friday");
    Label lblSat = new Label("Saturday");
    Button addTimeSheet = new Button("Create New TimeSheet");
    
    Label lblEnterTimes = new Label("Enter Times");
    Label lblTimeIn = new Label("Time In");
    Label lblTimeOut = new Label("Time Out");
    ComboBox<String> cmboPeriod = new ComboBox<String>();
    Button btnSelect = new Button("Select");
    Button showHours = new Button("Show Hours");
    Label day1 = new Label();
    Label day2 = new Label();
    Label day3 = new Label();
    Label day4 = new Label();
    Label day5 = new Label();
    Label day6 = new Label();
    Label day7 = new Label();
    static ComboBox cmboSunTimeIn = new ComboBox(timeI);
    static ComboBox cmboSunTimeOut = new ComboBox(timeO);
    static ComboBox cmboMonTimeIn = new ComboBox(timeI);
    static ComboBox cmboMonTimeOut = new ComboBox(timeO);
    static ComboBox cmboTuesTimeIn = new ComboBox(timeI);
    static ComboBox cmboTuesTimeOut = new ComboBox(timeO);
    static ComboBox cmboWedTimeIn = new ComboBox(timeI);
    static ComboBox cmboWedTimeOut = new ComboBox(timeO);
    static ComboBox cmboThurTimeIn = new ComboBox(timeI);
    static ComboBox cmboThurTimeOut = new ComboBox(timeO);
    static ComboBox cmboFriTimeIn = new ComboBox(timeI);
    static ComboBox cmboFriTimeOut = new ComboBox(timeO);
    static ComboBox cmboSatTimeIn = new ComboBox(timeI);
    static ComboBox cmboSatTimeOut = new ComboBox(timeO);
    Button btnEnterTimes = new Button("Submit");
    Button btnSavePay = new Button("Save");
    
    //Edit Member Discount
    Label lblMngDiscount = new Label("Manage Member Discount");
    Label lblCDiscount = new Label("Current Discount:");
    Label lblSetDiscount = new Label("Set Discount:");
    
    TextField txtCDiscount = new TextField();
    TextField txtSetDiscount = new TextField();
    Button  btnNewDiscount = new Button("Change Discount");
    Button btnSetDiscount = new Button("Set Discount");
    
    //POS Report
    Label lblPOS = new Label("Point of Sales");
    Label lblViewType = new Label("View Type");
    public static ArrayList<CustSale> reportSales = new ArrayList<>();
    public static ArrayList<Integer> catSales = new ArrayList<>();
    public static ArrayList<Double> catMoney = new ArrayList<>();
    ObservableList<String> type = FXCollections.observableArrayList("Week - daily",
            "Month - weekly", "Year - monthly", "Total sales today");
    ComboBox cmboType = new ComboBox(type);
    Label lblLocation = new Label("Location");
    // make combobox for locations (dont know what they are
    Button btnPOSDisplay = new Button("Display");
    TabPane tbPOS = new TabPane();
    Tab tabPOSText = new Tab("Text");
    Tab tabPOSChart = new Tab("Chart");
    GridPane formPane = new GridPane();
    TextArea txtPOS = new TextArea();
    LocalDate currentDate;
    String cDate;
    String yDate;
    String mDate;
    String dDate;
    LocalDate firstDay;
    LocalDate firstMonth;
    LocalDate firstYear;
    LocalDate endDate;
    LocalDate productDate;
    ArrayList<String> monthNames = new ArrayList<>();
    
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
    Label lblMonth = new Label("Month");
    TextArea txtMonth = new TextArea();
    // combobox for location
    // something to see past months
    Button btnMonthDisplay = new Button("Display");
    ObservableList<String> month = FXCollections.observableArrayList("January", "February", "March", "April", "May",
            "June", "July", "August", "September", "October", "November", "December");
    ComboBox cmboMonth = new ComboBox(month);
    
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
    Tab tabPBar = new Tab("Bar Graph");
    GridPane textPane = new GridPane();
    TextArea txtPvE = new TextArea();
    String pveOutput = "";	

    
    // Menu Bar for Admin
    MenuBar menuAdmin = new MenuBar();
    Menu menuHome = new Menu("Main Menu");
    Menu menuManage = new Menu("Management");
    Menu menuPayroll = new Menu("Payroll");
    Menu menuReports = new Menu("Reports");
    Menu menuSystem = new Menu("System");
    
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    
    // Our Database Connection method needs these 
    // objects. We declare them here and point them
    // to instance objects below.
    Connection dbConn;
    Statement commStmt;
    ResultSet dbResults;
    String sqlQuery;
    
    @Override
    public void start(Stage primaryStage) 
    {
        /*
        //Database example from class (Unsure how to imlement yet)
        String jdbcConnectionURL = "jdbc:oracle:thin:@localhost:1521:XE";
        String userID = "javauser";
        String userPass = "javapass";

        try
        {
           // dbConn = getDBConnection(jdbcConnectionURL, userID, userPass);
            stmt = dbConn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            sqlQuery = "Select * from TestTable";
            //sqlQueery = "INSERT INTO TESTTABLE (ID, TESTNAME) VALUES (78, 'HELLO THERE')";
            rset = stmt.executeQuery(sqlQuery);

            while (rset.next()){
                System.out.println(rset.getString(1) + " " + rset.getString(2));
            }

        }
        catch (SQLException e)
        {
            System.out.println(e.toString());
        }

        */ 
        
        currentDate = LocalDate.now();        
        cDate = currentDate.format(DateTimeFormatter.ISO_DATE);
        yDate = cDate.substring(0, 4);
        mDate = cDate.substring(5, 7);
        dDate = cDate.substring(8, cDate.length());
        GridPane primaryPane = new GridPane();
        primaryPane.setAlignment(Pos.CENTER);
        
        // "yyyy-mm-dd"
        
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
            obsExpStore.add(store1);	
            obsExpStore.add(store2);	
            obsExpStore.add(store3);	
            obsExpStore.add(store4);	
            obsExpStore.add(store5);
            storeList.addAll(obsStore);
            
            
            categoryList.add("PRODUCE");
            categoryList.add("DAIRY");
            categoryList.add("BEVERAGES");
            categoryList.add("CANDY");
            categoryList.add("CANNED FOODS");
            categoryList.add("PASTA");
            categoryList.add("SNACK FOODS");
            categoryList.add("BAKED GOODS");
            categoryList.add("FROZEN FOODS");
            categoryList.add("OTHER");
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
            
            expCategoryList.add("Store Mortgage");
            expCategoryList.add("Utilities");
            expCategoryList.add("Lawn Care");
            expCategoryList.add("System Maintenance");
            expCategoryList.add("Payroll");
            expCategoryList.add("Inventory Purchase");
            expStatusList.add("Paid");
            expStatusList.add("Ongoing");
            //Populate ArrayLists to calculate hoursWorked for payroll
        for(int i = 0; i <31; i++)
        {
           hoursIn.add(hourStart);
           hourStart += .5;
        }
        for(int i = 0; i <30; i++)
        {
           hoursOut.add(hourEnd);
           hourEnd += .5;
        } 
        
        primaryPane.add(lblThrifty, 0, 0);
        primaryPane.add(lblStore, 0, 1);
        primaryPane.add(lblSignIn, 0, 3);
        primaryPane.add(lblUserName, 0, 4);
        primaryPane.add(txtUser, 0, 5);
        primaryPane.add(lblPassword, 0, 6);
        primaryPane.add(txtPass, 0, 7);
        primaryPane.add(btnSignIn, 0, 9);

        
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
        
        GridPane ninthPane = new GridPane();
        ninthPane.setAlignment(Pos.TOP_CENTER);
        ninthPane.setVgap(10);
        ninthPane.setHgap(10);

        Scene ninthScene = new Scene(ninthPane, 500, 500);
        Stage ninthStage = new Stage();
        ninthStage.setScene(ninthScene);

        GridPane tenthPane = new GridPane();
        tenthPane.setAlignment(Pos.TOP_CENTER);
        tenthPane.setVgap(10);
        tenthPane.setHgap(10);

        Scene tenthScene = new Scene(tenthPane, 500, 500);
        Stage tenthStage = new Stage();
        tenthStage.setScene(tenthScene);
       
        lblThrifty.setStyle("-fx-font: bold 36pt \"Comic Sans ms\"; -fx-text-fill: red;");
        lblStore.setStyle("-fx-font: bold 36pt \"Comic Sans ms\"; -fx-text-fill: blue;");
        lblSignIn.setStyle("-fx-font: bold 16pt \"bookman\";");
        
        btnSignIn.setStyle("-fx-font:10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        
        //BUTTONS secondPane ones (12pt font)
        btnAddEmp.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        btnEditEmp.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        btnAddExp.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        btnAddOrder.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        btnAddProd.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        btnEditExp.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        btnEmpDisplay.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        btnHandleImage.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        btnMonthDisplay.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        btnPEEmp.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        btnPOSDisplay.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        btnPayExp.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        btnPvEDisplay.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        btnRemoveProd.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        btnViewOrder.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        createSupp.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        btnAddStore.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        btnEditStore.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        btnRemoveStore.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        btnViewStore.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        btnViewAllStore.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        btnConfirmAddStore.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        btnDisProd.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        createSupp.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        btnEditSupp.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        btnEditPO.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        btnviewSupp.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        btnConfirmSuppChanges.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        editProd.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        btnEnterTime.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        btnSelectExpense.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        btnConfirmPayExp.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        btnSetDiscount.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        btnRingSale.setStyle("-fx-font: 12pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        removeEmp.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        btnRecShip.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        btnRemExp.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        btnRemExpType.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        btnAddExpType.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        btnConfirmNewExp.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        btnConfirmRemExpType.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        
        
        
        btnEnterTime.setStyle("-fx-font: 12pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        
        //BUTTONS third + pane (10pt font)
        addEmp.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        addExp.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        addProd.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        btnCalcProdSubTotal.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        btnChangeImage.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        btnCompleteOrder.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        btnDisplayPO.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        btnImage.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        btnNewSupp.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        btnViewImage.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        editEmp.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        editExp.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        removeProd.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        btnEnterTimes.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        btnViewAllPO.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        viewSupp.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        btnViewAllSupp.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        btnSelect.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        showHours.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        addTimeSheet.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        btnSavePay.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        btnConfirmRemoveStore.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        btnConfirmEditStore.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        btnSavePO.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        btnCancelPO.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        btnRemoveSupp.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill: white;");
        //small form labels and combobox
        empCat.setStyle("-fx-font: 9pt \"bookman\";");
        empType.setStyle("-fx-font: 9pt \"bookman\";");
        cmboCategory.setStyle("-fx-font: 9pt \"bookman\"; -fx-background-color: lightskyblue; -fx-text-fill:white;");
        cmboEType.setStyle("-fx-font: 9pt \"bookman\"; -fx-background-color: lightskyblue; -fx-text-fill:white;");
        cmboEmpCat.setStyle("-fx-font: 9pt \"bookman\"; -fx-background-color: lightskyblue; -fx-text-fill:white;");
        cmboExpCategory.setStyle("-fx-font: 9pt \"bookman\"; -fx-background-color: lightskyblue; -fx-text-fill:white;");
        cmboFriTimeIn.setStyle("-fx-font: 9pt \"bookman\"; -fx-background-color: lightskyblue; -fx-text-fill:white;");
        cmboFriTimeOut.setStyle("-fx-font: 9pt \"bookman\"; -fx-background-color: lightskyblue; -fx-text-fill:white;");
        cmboJob.setStyle("-fx-font: 9pt \"bookman\"; -fx-background-color: lightskyblue; -fx-text-fill:white;");
        cmboMonTimeIn.setStyle("-fx-font: 9pt \"bookman\"; -fx-background-color: lightskyblue; -fx-text-fill:white;");
        cmboMonTimeOut.setStyle("-fx-font: 9pt \"bookman\"; -fx-background-color: lightskyblue; -fx-text-fill:white;");
        cmboProdOrder.setStyle("-fx-font: 9pt \"bookman\"; -fx-background-color: lightskyblue; -fx-text-fill:white;");
        cmboSatTimeIn.setStyle("-fx-font: 9pt \"bookman\"; -fx-background-color: lightskyblue; -fx-text-fill:white;");
        cmboSatTimeOut.setStyle("-fx-font: 9pt \"bookman\"; -fx-background-color: lightskyblue; -fx-text-fill:white;");
        cmboStatus.setStyle("-fx-font: 9pt \"bookman\"; -fx-background-color: lightskyblue; -fx-text-fill:white;");
        cmboSunTimeIn.setStyle("-fx-font: 9pt \"bookman\"; -fx-background-color: lightskyblue; -fx-text-fill:white;");
        cmboSunTimeOut.setStyle("-fx-font: 9pt \"bookman\"; -fx-background-color: lightskyblue; -fx-text-fill:white;");
        cmboThurTimeIn.setStyle("-fx-font: 9pt \"bookman\"; -fx-background-color: lightskyblue; -fx-text-fill:white;");
        cmboThurTimeOut.setStyle("-fx-font: 9pt \"bookman\"; -fx-background-color: lightskyblue; -fx-text-fill:white;");
        cmboTuesTimeIn.setStyle("-fx-font: 9pt \"bookman\"; -fx-background-color: lightskyblue; -fx-text-fill:white;");
        cmboTuesTimeOut.setStyle("-fx-font: 9pt \"bookman\"; -fx-background-color: lightskyblue; -fx-text-fill:white;");
        cmboWedTimeIn.setStyle("-fx-font: 9pt \"bookman\"; -fx-background-color: lightskyblue; -fx-text-fill:white;");
        cmboWedTimeOut.setStyle("-fx-font: 9pt \"bookman\"; -fx-background-color: lightskyblue; -fx-text-fill:white;");
        cmboSupp.setStyle("-fx-font: 9pt \"bookman\"; -fx-background-color: lightskyblue; -fx-text-fill:white;");
        cmboPeriod.setStyle("-fx-font: 9pt \"bookman\"; -fx-background-color: lightskyblue; -fx-text-fill:white;");
        cmboUnpaidExp.setStyle("-fx-font: 9pt \"bookman\"; -fx-background-color: lightskyblue; -fx-text-fill:white;");
        cmboExpStore.setStyle("-fx-font: 9pt \"bookman\"; -fx-background-color: lightskyblue; -fx-text-fill:white;");
        
        cmboType.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: lightskyblue; -fx-text-fill:white;");
        cmboYear.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: lightskyblue; -fx-text-fill:white;");
        cmboMonth.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: lightskyblue; -fx-text-fill:white;");
        cmboEmp.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: lightskyblue; -fx-text-fill: white;");
        cmboStore.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: lightskyblue; -fx-text-fill: white;");
        cmboExpense.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: lightskyblue; -fx-text-fill: white;");
        cmboSupp2.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: lightskyblue; -fx-text-fill: white;");
        cmboProd.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: lightskyblue; -fx-text-fill: white;");
        cmboProd2.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: lightskyblue; -fx-text-fill: white;");
        cmboProdOrder2.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: lightskyblue; -fx-text-fill: white;");
        
        lblInvMan.setStyle("-fx-font: bold 20pt \"bookman\";");
        lblInv.setStyle("-fx-font: bold 20pt \"bookman\";");
        lblEmpMan.setStyle("-fx-font: bold 20pt \"bookman\";");
        lblEmp.setStyle("-fx-font: bold 20pt \"bookman\";");
        
        lblExpMan.setStyle("-fx-font: bold 20pt \"bookman\";");
        lblExp.setStyle("-fx-font: bold 20pt \"bookman\";");
        lblPOS.setStyle("-fx-font: bold 20pt \"bookman\";");
        lblReport.setStyle("-fx-font: bold 20pt \"bookman\";");
        lblPayroll.setStyle("-fx-font: bold 20pt \"bookman\";");
        lblEmpRep.setStyle("-fx-font: bold 20pt \"bookman\";");
        lblMonthRep.setStyle("-fx-font: bold 20pt \"bookman\";");
        lblPvE.setStyle("-fx-font: bold 20pt \"bookman\";");
        lblStoreMan.setStyle("-fx-font: bold 20pt \"bookman\";");
        lblAddProd.setStyle("-fx-font: bold 14pt \"bookman\";");
        lblAddEmp.setStyle("-fx-font: bold 14pt \"bookman\";");
        lblAddExp.setStyle("-fx-font: bold 14pt \"bookman\";");
        lblEditExp.setStyle("-fx-font: bold 14pt \"bookman\";");
        lblRemoveProd.setStyle("-fx-font: bold 14pt \"bookman\";");
        lblAddSupp.setStyle("-fx-font: bold 14pt \"bookman\";"); 
        lblEnterTimes.setStyle("-fx-font: bold 14pt \"bookman\";");
        lblPayExpenseHeader.setStyle("-fx-font: bold 14pt \"bookman\";");
        lblMngDiscount.setStyle("-fx-font: bold 14pt \"bookman\";");
        lblPO.setStyle("-fx-font: bold 14pt \"bookman\";");
        lblEPO.setStyle("-fx-font: bold 14pt \"bookman\";");
        lblNewExpenseTypeHeader.setStyle("-fx-font: bold 14pt \"bookman\";");
        lblRemoveExpTypeHeader.setStyle("-fx-font: bold 14pt \"bookman\";");
        lblViewSupp.setStyle("-fx-font: bold 14pt \"bookman\";");
        lblViewPO.setStyle("-fx-font: bold 14pt \"bookman\";");
        lblEditEmp.setStyle("-fx-font: bold 14pt \"bookman\";");
        lblESupplier.setStyle("-fx-font: bold 14pt \"bookman\";");
        lblHandleImage.setStyle("-fx-font: bold 14pt \"bookman\";");
        
        GridPane secondPane = new GridPane();
        
        secondPane.setVgap(10);
        secondPane.setHgap(15);
            
        Label lblMain = new Label("Main Menu");
        lblMain.setStyle("-fx-font: bold 16pt \"bookman\";");
        Label lblSelect = new Label("Select an option from the Menu Bar above.");
        lblSelect.setStyle("-fx-font: 12pt \"bookman\";");
        
        
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
        
        Scene fifthScene = new Scene(fifthPane, 750, 500); 
        Stage fifthStage = new Stage(); 
        fifthStage.setScene(fifthScene); 
        
        menuAdmin.getMenus().addAll(menuHome, menuManage, menuPayroll, menuReports, menuSystem);
        menuHome.getItems().add(new MenuItem("Main Menu"));
        menuManage.getItems().add(new MenuItem("Employee Management"));
        menuManage.getItems().add(new MenuItem("Inventory Management"));
        menuManage.getItems().add(new MenuItem("Expense Management"));
        menuManage.getItems().add(new MenuItem("Store Management"));
        menuManage.getItems().add(new MenuItem("Discount Management"));
        menuPayroll.getItems().add(new MenuItem("Payroll"));
        menuReports.getItems().add(new MenuItem("POS Report"));
        menuReports.getItems().add(new MenuItem("Employee Report"));
        menuReports.getItems().add(new MenuItem("Monthly Expense Report"));
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
        txtPayroll.setEditable(false);
        
        Scene fourthScene = new Scene(fourthPane, 500, 500);
        Stage fourthStage = new Stage();
        fourthStage.setScene(fourthScene);
        
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
        TableColumn tbProdCost = new TableColumn("Cost");
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
        tbProdCost.setCellValueFactory(new PropertyValueFactory<Product, Double>("cost"));
        tbProdDesc.setCellValueFactory(new PropertyValueFactory<Product, String>("foodDescription"));
        tbProdCategory.setCellValueFactory(new PropertyValueFactory<Product, String>("category"));
        tbProdLocation.setCellValueFactory(new PropertyValueFactory<Product, String>("location"));
         tbProdSupplier.setCellValueFactory(new PropertyValueFactory<Product, String>("Supplier"));
        tbProdImage.setCellValueFactory(new PropertyValueFactory<Product, BufferedImage>("image"));
        
        // Expense Table
        TableColumn tbExpID = new TableColumn("ID");
        TableColumn tbExpCategory = new TableColumn("Category");
        TableColumn tbExpDesc = new TableColumn("Description");
        TableColumn tbExpAmountPaid = new TableColumn("Amount Paid");
        TableColumn tbExpAmountTotal = new TableColumn("Total Amount");
        TableColumn tbExpRemainBalance = new TableColumn("Amount Remaining");
        TableColumn tbExpDate = new TableColumn("Date Paid");        
        TableColumn tbExpStatus = new TableColumn("Status");
        TableColumn tbExpStore = new TableColumn("Store");
        

        //ValueFactory for Expense Table
        tbExpID.setCellValueFactory(new PropertyValueFactory<Expense, Integer>("expenseID"));
        tbExpCategory.setCellValueFactory(new PropertyValueFactory<Expense, String>("category"));
        tbExpDesc.setCellValueFactory(new PropertyValueFactory<Expense, String>("description"));
        tbExpAmountPaid.setCellValueFactory(new PropertyValueFactory<Expense, Double>("amountPaid"));
        tbExpAmountTotal.setCellValueFactory(new PropertyValueFactory<Expense, Double>("totalAmount"));
         tbExpRemainBalance.setCellValueFactory(new PropertyValueFactory<Expense, Double>("remainingBalance"));
        tbExpDate.setCellValueFactory(new PropertyValueFactory<Expense, String>("date"));
        tbExpStatus.setCellValueFactory(new PropertyValueFactory<Expense, String>("status"));
         tbExpStore.setCellValueFactory(new PropertyValueFactory<Expense, String>("store"));
        
        //Store Table       
        //TableColumn tbStoreID = new TableColumn("ID");
        TableColumn tbStoreName = new TableColumn("Name");
        TableColumn tbStoreAddress = new TableColumn("Address");

        //ValueFactory for Store Table
        //tbStoreID.setCellValueFactory(new PropertyValueFactory<Store, Integer>("storeID"));
        tbStoreName.setCellValueFactory(new PropertyValueFactory<Store, String>("storeName"));
        tbStoreAddress.setCellValueFactory(new PropertyValueFactory<Store, String>("storeAddress"));

        
        
        //main menu bottom
        Label lblMenu1 = new Label("123 Lane   Harrisonburg, VA 22801");
        Label lblMenu2 = new Label("(540) 123-1234   (540) 123-1244 (fax)");
        Label lblMenu3 = new Label("http://www.thrifty-store.com");
        lblMenu1.setStyle("-fx-font: 9pt \"bookman\"; -fx-text-fill: gray;");
        lblMenu2.setStyle("-fx-font: 9pt \"bookman\"; -fx-text-fill: gray;");
        lblMenu3.setStyle("-fx-font: 9pt \"bookman\"; -fx-text-fill: gray;");
        
        Label lblLeft1 = new Label("Welcome to Thrifty $tore!");
        Label lblLeft2 = new Label("Providing 'Thrifty Saves!' all around the");
        Label lblLeft3 = new Label("Shenandoah!");
        Label lblLeft4 = new Label("Stores include: Harrisonburg, New Market,");
        Label lblLeft5 = new Label("Stauton, Waynesboro, & Charlottesville!");
        lblLeft1.setStyle("-fx-font: bold 14pt \"bookman\";");
        lblLeft2.setStyle("-fx-font: 12pt \"bookman\";");
        lblLeft3.setStyle("-fx-font: 12pt \"bookman\";");
        lblLeft4.setStyle("-fx-font: 12pt \"bookman\";");
        lblLeft5.setStyle("-fx-font: 12pt \"bookman\";");
        
        
        Label lblRight1 = new Label("Latest Updates");
        Label lblRight2 = new Label("Company-Wide Picnic this Saturday!");
        Label lblRight3 = new Label("We will be having a picnic this Saturday");
        Label lblRight4 = new Label("at UPark from 3:00pm-7:00pm.");
        Label lblRight5 = new Label("Employee of the Month Announced!");
        Label lblRight6 = new Label("Our employee of the month is Jeremy Ezell!");
        lblRight1.setStyle("-fx-font: bold 14pt \"bookman\"; ");
        lblRight2.setStyle("-fx-font: bold 12pt \"bookman\"; ");
        lblRight5.setStyle("-fx-font: bold 12pt \"bookman\"; ");
        lblRight3.setStyle("-fx-font: 10pt \"bookman\";");
        lblRight4.setStyle("-fx-font: 10pt \"bookman\";");
        lblRight6.setStyle("-fx-font: 10pt \"bookman\";");
        
        GridPane leftPane = new GridPane();
        leftPane.setVgap(10);
        leftPane.setHgap(15);
        leftPane.setAlignment(Pos.TOP_CENTER);
        
        GridPane rightPane = new GridPane();
        rightPane.setVgap(10);
        rightPane.setHgap(15);
        rightPane.setAlignment(Pos.TOP_CENTER);
        
        Scene primaryScene = new Scene(primaryPane, 500, 500);
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("Main Form");
        primaryStage.show();
        
        BorderPane border = new BorderPane();
        border.setTop(menuAdmin);
        
        GridPane mainPane = new GridPane();
        
        mainPane.setVgap(10);
        mainPane.setHgap(15);
        
        
        mainPane.setAlignment(Pos.TOP_CENTER);
        secondPane.setAlignment(Pos.TOP_CENTER);
        
        
            secondPane.add(Thrifty, 3, 2);
            secondPane.add(Store, 3, 3);
            secondPane.add(lblMain, 3, 4);
            secondPane.add(lblSelect, 3, 5);
            secondPane.add(btnRingSale, 3, 6);
            secondPane.add(btnEnterTime, 3, 7);
            mainPane.add(lblMenu1, 0, 0);
            mainPane.add(lblMenu2, 1, 0);
            mainPane.add(lblMenu3, 0, 1);
            leftPane.add(lblLeft1, 0, 0);
            leftPane.add(lblLeft2, 0, 1);
            leftPane.add(lblLeft3, 0, 2);
            leftPane.add(lblLeft4, 0, 3);
            leftPane.add(lblLeft5, 0, 4);
            rightPane.add(lblRight1, 0, 0);
            rightPane.add(lblRight2, 0, 1);
            rightPane.add(lblRight3, 0, 2);
            rightPane.add(lblRight4, 0, 3);
            rightPane.add(lblRight5, 0, 5);
            rightPane.add(lblRight6, 0, 6);
            
        border.setCenter(secondPane);
        border.setBottom(mainPane);
        border.setLeft(leftPane);
        border.setRight(rightPane);
        rightPane.setStyle("-fx-background-color: lightskyblue;");
        leftPane.setStyle("-fx-background-color: lightskyblue;");
        Scene secondScene = new Scene(border, 1125, 700);
        Stage secondStage = new Stage();
        secondStage.setScene(secondScene);
        menuAdmin.setMinWidth(secondScene.getWidth());
        
        border.setStyle("-fx-background-color: white;");
        
        GridPane discountPane = new GridPane();
        discountPane.setAlignment(Pos.TOP_CENTER);
        discountPane.setVgap(10);
        discountPane.setHgap(10);

        Scene discountScene = new Scene(discountPane, 400, 200);
        Stage discountStage = new Stage();
        discountStage.setScene(discountScene);
        
        //Import data from files
        try
        {
            ObjectInputStream instanceObjectInput = new ObjectInputStream(new FileInputStream("employees.dat"));

            empList.addAll((ArrayList<Employee>)(instanceObjectInput.readObject()));
            instanceObjectInput.close();
            for(Employee emp : empList){
                Employee.nextID++;
                emp.setStore(store1);
                Employee.obsEmp.add(emp.getEmployeeID()+ " " + emp.getFirstName() + " " + emp.getLastName());
                empData.add(emp);
           }
           
        }
        catch(Exception ex)
        {
            System.out.println("Given:" + ex + "Employees were not imported");
        }
        try
        {
            ObjectInputStream instanceObjectInput = new ObjectInputStream(new FileInputStream("suppliers.dat"));

            suppList.addAll((ArrayList<Supplier>)(instanceObjectInput.readObject()));
            for(Supplier supp : suppList)
            {
               Supplier.nextID++;
               Supplier.obsSupp.add(supp.getName());
            }
            instanceObjectInput.close();
        }
        catch(Exception ex)
        {
            System.out.println("Given:" + ex + "Suppliers were not imported");
        }
        try
        {
            ObjectInputStream instanceObjectInput = new ObjectInputStream(new FileInputStream("products.dat"));

            prodList.addAll((ArrayList<Product>)(instanceObjectInput.readObject()));
            for(Product prod : prodList){
                Product.nextID++;
                Product.obsProd.add(prod.getProductID() +": "+ prod.getProductName());
                prodData.add(prod);
           }

           instanceObjectInput.close();
        }
        catch(Exception ex)
        {
            System.out.println("Given:" + ex + "Products were not imported");
        }
        btnSignIn.setOnAction(e -> {
            // code to validate log in, has to have two handlers for admin and worker
            // error handling

   //RUN LoadData.java BEFORE TRYING TO LOG IN.
           for(int i = 0; i< empList.size();i++)
           {
                Employee temp = empList.get(i);
                if(temp.getUsername().equalsIgnoreCase(txtUser.getText()) && temp.getPassword().equals(txtPass.getText()))
                {
                    currentUser = temp;
                    found = true;
                    if(temp.getEmployeeCategory().equalsIgnoreCase("Manager"))
                   {
                       isAuthorized = true;
                   }
               }
           }

            if(!found) 
            { // added the contents of the previously existing else statement here, outside the while
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
            
            if(isAuthorized == false)
            {
                menuAdmin.getMenus().get(1).getItems().get(0).setDisable(true); //Employee management
                menuAdmin.getMenus().get(1).getItems().get(2).setDisable(true); //Expense Management
                menuAdmin.getMenus().get(1).getItems().get(3).setDisable(true); //Store Management
                menuAdmin.getMenus().get(1).getItems().get(4).setDisable(true); //Discount Management
                menuAdmin.getMenus().get(2).getItems().get(0).setDisable(true); //Payroll

                createSupp.setDisable(true); //disable creat supplier button
                btnEditSupp.setDisable(true); //disable edit Supplier
                btnAddOrder.setDisable(true); //disable create product order
                btnViewOrder.setDisable(true); //disable view product order

                if(createSupp.isPressed())
                {
                    alert.setTitle("Not Authorized");
                    alert.setHeaderText("Access Only For Management");
                    alert.showAndWait();
                }
                if(btnAddOrder.isPressed())
                {
                    alert.setTitle("Not Authorized");
                    alert.setHeaderText("Access Only For Management");
                    alert.showAndWait();
                }
                if(btnViewOrder.isPressed())
                {
                    alert.setTitle("Not Authorized");
                    alert.setHeaderText("Access Only For Management");
                    alert.showAndWait();
                }

                if(btnEditSupp.isPressed())
                {
                    alert.setTitle("Not Authorized");
                    alert.setHeaderText("Access Only For Management");
                    alert.showAndWait();
                }

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
            
            secondPane.add(Thrifty, 3, 2);
            secondPane.add(Store, 3, 3);
            secondPane.add(lblMain, 3, 4);
            secondPane.add(lblSelect, 3, 5);
            secondPane.add(btnRingSale, 3, 6);
            secondPane.add(btnEnterTime, 3, 7);
            
            border.setTop(menuAdmin);
            border.setCenter(secondPane);
            border.setBottom(mainPane);
            border.setLeft(leftPane);
            border.setRight(rightPane);
            
        });
        
            prodTable.getColumns().addAll(tbProdName, tbProdQuantity, tbProdPrice,tbProdCost, tbProdDesc, tbProdCategory, tbProdLocation, tbProdSupplier);        
            menuAdmin.getMenus().get(1).getItems().get(1).setOnAction(e -> {
            // Inventory Management
            
            
            secondPane.getChildren().clear();
            formPane.getChildren().clear();
            border.getChildren().clear();
            cmboProd.getSelectionModel().clearSelection();
            cmboSupp2.getSelectionModel().clearSelection();
            cmboProdOrder2.getSelectionModel().clearSelection();
            secondPane.setAlignment(Pos.TOP_CENTER);
            secondStage.setTitle("Inventory Management");
            
            btnAddProd.setMaxWidth(Double.MAX_VALUE);
            btnRemoveProd.setMaxWidth(Double.MAX_VALUE);
            btnHandleImage.setMaxWidth(Double.MAX_VALUE);
            btnViewOrder.setMaxWidth(Double.MAX_VALUE);
            btnAddOrder.setMaxWidth(Double.MAX_VALUE);
            createSupp.setMaxWidth(Double.MAX_VALUE); 
            btnDisProd.setMaxWidth(Double.MAX_VALUE); 
            btnEditSupp.setMaxWidth(Double.MAX_VALUE); 
            btnEditPO.setMaxWidth(Double.MAX_VALUE); 
            cmboSupp2.setMaxWidth(Double.MAX_VALUE);
            btnviewSupp.setMaxWidth(Double.MAX_VALUE);
            cmboProd.setMaxWidth(Double.MAX_VALUE);
            cmboProdOrder2.setMaxWidth(Double.MAX_VALUE);
            btnRecShip.setMaxWidth(Double.MAX_VALUE);
            
            VBox vbButtons = new VBox();
            vbButtons.setSpacing(10);
            vbButtons.setPadding(new Insets(10, 20, 10 , 20));
            
            vbButtons.getChildren().addAll(lblInv, lblInvMan, createSupp, cmboSupp2,
                    btnEditSupp, btnviewSupp, btnAddProd, cmboProd, btnRemoveProd, 
                    btnDisProd, btnHandleImage, btnAddOrder, cmboProdOrder2, btnEditPO, btnViewOrder, btnRecShip);


            //secondPane.add(vbButtons, 3, 3);
            prodTable.setItems(prodData);
            //secondPane.add(prodTable, 5, 3);
            border.setTop(menuAdmin);
            border.setLeft(vbButtons);
            border.setCenter(prodTable);
            prodTable.setMinWidth(secondScene.getWidth());
//            secondPane.setMaxWidth(secondScene.getWidth());
            
        });
        btnEditPO.setOnAction(e -> {
            //Edit Product Order
            if(cmboProdOrder2.getValue() == null)
            {
                alert.setTitle("Error!");
                alert.setHeaderText("Please select a product order to Edit!");
                alert.showAndWait();
            }
            else 
            {
                poC = prodOrderList.get(cmboProdOrder2.getSelectionModel().getSelectedIndex());
                cmboProdOrder2.getSelectionModel().clearSelection();

                fifthPane.getChildren().clear();
                fifthStage.setTitle("Edit or Cancel a Product Order");
                fifthStage.show();
                fifthPane.setAlignment(Pos.TOP_CENTER);

                fifthPane.add(lblEPO, 0, 0);
                fifthPane.add(lblProdName, 0, 1);
                fifthPane.add(txtProdName, 0, 2);
                fifthPane.add(lblUnitCost, 1,5); 
                fifthPane.add(txtUnitCost, 1, 6); 
                fifthPane.add(lblCategory, 0, 3);
                fifthPane.add(cmboCategory, 0, 4);
                fifthPane.add(lblSubTotal, 1, 7); 
                fifthPane.add(btnCalcProdSubTotal, 0, 9);
                fifthPane.add(txtSubTotal, 1, 8); 
                fifthPane.add(lblOrderDate, 0, 7); 
                fifthPane.add(orderDate, 0, 8);
                //need to add order date into the pane and use the constructor with it 


                fifthPane.add(lblDelDate, 0, 5);  

                fifthPane.add(calendar, 0, 6);
                fifthPane.add(lblQuantity, 1, 1);
                fifthPane.add(txtProdQuantity, 1, 2);
                fifthPane.add(lblSupplier, 1, 3); 
                fifthPane.add(cmboSupp, 1, 4);
                fifthPane.add(lblProdStore, 2,3);
                fifthPane.add(cmboStore, 2, 4); //when picking which store the product is for, should be selected from already existing stores created 


                fifthPane.add(btnSavePO, 0, 10);
                fifthPane.add(btnCancelPO, 1, 10); 

                txtSubTotal.setEditable(false); 
                txtProdName.setText(poC.getProdOName()); 
                Double poUnitCost2 = poC.getUnitCost(); 
                String poUnitCost = Double.toString(poUnitCost2); 
                txtUnitCost.setText(poUnitCost);
                cmboStore.setPromptText(poC.getStore());
                cmboCategory.setPromptText(poC.getCategory());
                cmboSupp.setPromptText(poC.getSupplier());
                Double poSubTotal = poC.getSubtotal(); 
                String poSubTotal2 = Double.toString(poSubTotal); 
                txtSubTotal.setText(poSubTotal2);
                int poQ = poC.getQuantity(); 
                String poQ2 = Integer.toString(poQ); 
                txtProdQuantity.setText(poQ2);
                String date = calendar.getValue().format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
                String orderDate2 = orderDate.getValue().format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
                orderDate2 = poC.getDate(); 
                date = poC.getDeliveryDate(); 
                
                txtProdName.clear();
                txtUnitCost.clear();
                cmboCategory.getSelectionModel().clearSelection();
                orderDate.getEditor().clear();
                calendar.getEditor().clear();
                txtProdQuantity.clear();
                cmboSupp.getSelectionModel().clearSelection();
                cmboStore.getSelectionModel().clearSelection();
            }
            
        }); 
        
        btnCancelPO.setOnAction(e -> {
            
            int removeProd1 = cmboProdOrder.getSelectionModel().getSelectedIndex(); 
            ProductOrder tempProd = prodOrderList.get(removeProd1);

            for(int i = 0; i<prodOrderList.size(); i++){
                if(tempProd.getID() == prodOrderList.get(i).getID()){
                    prodOrderList.remove(i);
                    cmboProdOrder.getItems().remove(i);
                }
            alert.setTitle("Success!");
            alert.setHeaderText("The product order has been canceled!");
            alert.showAndWait();

            }
             

        }); 
        
        btnSavePO.setOnAction(e -> {
            
            String date = calendar.getValue().format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
            String orderDate2 = orderDate.getValue().format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
            poC = prodOrderList.get(cmboProdOrder2.getSelectionModel().getSelectedIndex());
            poC.setProdOName(txtProdName.getText()); 
            poC.setUnitCost((Double.parseDouble(txtUnitCost.getText())));  
            poC.setCategory((String)cmboCategory.getValue());
            poC.setQuantity(Integer.parseInt(txtProdQuantity.getText()));
            poC.setSubtotal(Double.parseDouble(txtSubTotal.getText())); 
            poC.setDeliveryDate(date); 
            poC.setOrderDate(orderDate2); 

            poC.setStore((String) cmboStore.getValue());

            poC.setSupplier((String)cmboSupp.getValue());

            for(int i = 0; i<prodOrderList.size(); i++)
            {
                if(poC.getID() == prodOrderList.get(i).getID())
                {
                    prodOrderList.remove(i);
                    prodOrderList.add(i, poC);
                    cmboProdOrder.getItems().remove(i); 
                    cmboProdOrder.getItems().add(i, poC.getProdOName()); 
                    cmboProdOrder2.getItems().remove(i);
                    cmboProdOrder2.getItems().add(i, poC.getProdOName()); 
                    poC = null;
                }
            }

            for(int i = 0; i<storeList.size(); i++)
            {
                if((storeList.get(i).getStoreName().equalsIgnoreCase((String)cmboStore.getValue())))
                {
                    break;
                }

                 else if(i == storeList.size()-1 && storeList.get(i).getStoreName().equalsIgnoreCase((String)cmboStore.getValue()) == false)
                {
                    storeList.add((Store)cmboStore.getValue());
                }
            }
           // for (int i = 0; i < obsCategory.size(); i++) {

            //}

            alert.setTitle("Success!");
            alert.setHeaderText("The product has been edited!");
            alert.showAndWait();
            
        }); 
        
        createSupp.setOnAction(e -> {
            // Create Supplier
            eighthPane.getChildren().clear();
            txtSuppName.clear();
            txtSuppPhone.clear();
            txtSuppEmail.clear();
            txtSuppAdd.clear();
            txtSuppContactName.clear();
            txtSuppContactPhone.clear();
            txtSuppContactEmail.clear(); 
            
            eighthStage.setTitle("Add a Supplier");
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
            if (txtSuppName.getText().trim().length() == 0 || txtSuppPhone.getText().trim().length() == 0 ||
                    txtSuppEmail.getText().trim().length() == 0 || txtSuppAdd.getText().trim().length() == 0 ||
                    txtSuppContactName.getText().trim().length() == 0 || txtSuppContactPhone.getText().trim().length() == 0 ||
                    txtSuppContactEmail.getText().trim().length() == 0)
            {
                alert.setTitle("Error!");
                alert.setHeaderText("Error! Please Enter Data!");
                alert.showAndWait();
            }
            else
            {
//                String email = "";
//                if(txtSuppEmail.getText().contains(email))
                
                suppList.add(new Supplier(txtSuppName.getText(), 
                        txtSuppPhone.getText(), txtSuppEmail.getText(), txtSuppAdd.getText(), txtSuppContactName.getText(), 
                        txtSuppContactPhone.getText(), txtSuppContactEmail.getText())); 
                Supplier.obsSupp.add(txtSuppName.getText());
                System.out.println(suppList.get(0).toString());
                alert.setTitle("Success!");
                alert.setHeaderText("The new supplier has been created!");
                alert.showAndWait();
                //insert code to add product to object

                txtSuppName.clear();
                txtSuppPhone.clear();
                txtSuppEmail.clear();
                txtSuppAdd.clear();
                txtSuppContactName.clear();
                txtSuppContactPhone.clear();
                txtSuppContactEmail.clear(); 

                eighthStage.close();  

            }
        }); 
        
        btnEditSupp.setOnAction (e -> {
            if(cmboSupp2.getValue() == null){
                alert.setTitle("Error!");
                alert.setHeaderText("Please select a Supplier to Edit!");
                alert.showAndWait();
            }
            else 
            {
                sC = suppList.get(cmboSupp2.getSelectionModel().getSelectedIndex());
                cmboSupp2.getSelectionModel().clearSelection();
                eighthPane.getChildren().clear();
                eighthStage.setTitle("Edit a Supplier");
                eighthStage.show();
                eighthPane.setAlignment(Pos.TOP_CENTER);   
                eighthPane.add(lblESupplier, 0, 0); 
                //eighthPane.add(lblESupp, 0, 1); 
                //eighthPane.add(cmboSupp, 1, 1);
                eighthPane.add(lblSuppName, 0,2); 
                eighthPane.add(txtSuppName, 0, 3); 
                eighthPane.add(lblSuppPhone, 1,2); 
                eighthPane.add(txtSuppPhone, 1,3); 
                eighthPane.add(lblSuppEmail, 0, 4);
                eighthPane.add(txtSuppEmail, 0, 5);
                eighthPane.add(lblSuppAdd, 0, 6);
                eighthPane.add(txtSuppAdd, 0, 7);
                eighthPane.add(lblSuppContactName, 1, 4);
                eighthPane.add(txtSuppContactName, 1, 5);
                eighthPane.add(lblSuppContactPhone, 1, 6);
                eighthPane.add(txtSuppContactPhone, 1, 7);
                eighthPane.add(lblSuppContactEmail, 1, 8);
                eighthPane.add(txtSuppContactEmail, 1, 9); 

                eighthPane.add(btnConfirmSuppChanges, 0, 9);
                eighthPane.add(btnRemoveSupp, 0, 10);

                txtSuppName.setText(sC.getName());
                txtSuppPhone.setText(sC.getSupplierPhone());
                txtSuppEmail.setText(sC.getSupplierEmail());
                txtSuppAdd.setText(sC.getSupplierAddress());
                txtSuppContactName.setText(sC.getContactName());
                txtSuppContactPhone.setText(sC.getContactPhone()); 
                txtSuppContactEmail.setText(sC.getContactEmail()); 
            }

        }); 
        
        btnConfirmSuppChanges.setOnAction(e -> {

            //change of supp name needs to show up in combo box and on associated product's table 
            //sC = suppList.get(cmboSupp.getSelectionModel().getSelectedIndex());
            sC.setName(txtSuppName.getText());
            sC.setSupplierPhone(txtSuppPhone.getText());
            sC.setSupplierEmail(txtSuppEmail.getText()); 
            sC.setSupplierAddress(txtSuppAdd.getText());
            sC.setContactName(txtSuppContactName.getText());
            sC.setContactPhone(txtSuppContactPhone.getText());
            sC.setContactEmail(txtSuppContactEmail.getText());
            Supplier temp = sC;
            for(int i = 0; i<suppList.size(); i++)
            {
                if(temp.getID() == suppList.get(i).getID())
                {
                    suppList.remove(i);
                    suppList.add(i, sC);
                    cmboSupp.getItems().remove(i); 
                    cmboSupp.getItems().add(i, sC.getName()); 
//                    cmboSupp2.getItems().remove(i); 
//                    cmboSupp2.getItems().add(i, sC.getName());
                    //this doesnt change the supplier name on the product table when product name is updated
                    sC = null;

                }
            }

            alert.setTitle("Success!");
            alert.setHeaderText("The supplier has been edited!");
            alert.showAndWait();
//            for (int i = 0; i < suppList.size(); i++) 
//            {
//                System.out.println(suppList.get(i)); 
//            }
            prodData.clear(); //keeping this so if supplier name is updated it shows on the product table? 
            for(Product prod: prodList)
            {   
             if(prod.getSupp().getID() == temp.getID())
                {
                    prod.updateSupp(temp);
                }
                prodData.add(prod);
            }
            txtSuppName.clear();
            txtSuppPhone.clear();
            txtSuppEmail.clear();
            txtSuppAdd.clear();
            txtSuppContactName.clear();
            txtSuppContactPhone.clear();
            txtSuppContactEmail.clear(); 

        eighthStage.close();
            
        });
        btnRemoveSupp.setOnAction(e -> {
            Supplier tempSupp = sC; 
            for(int i = 0; i<suppList.size(); i++)
            {
                if(tempSupp.getID() == suppList.get(i).getID())
                {
                    suppList.remove(i);
                    cmboSupp2.getItems().remove(i);
                    break;                    
                }           
            }
            alert.setTitle("Success!");
            alert.setHeaderText("The supplier has been removed!");
            alert.showAndWait();
            
            eighthStage.close();
        });
        
        btnviewSupp.setOnAction(e -> {
            // View Suppliers
            fifthPane.getChildren().clear();
            cmboSupp.getSelectionModel().clearSelection();
            fifthStage.setTitle("View Suppliers");
            fifthStage.show();
            suppOutput.setPrefWidth(500);
            fifthPane.setAlignment(Pos.TOP_CENTER);
            fifthPane.add(lblViewSupp, 0, 0);
            fifthPane.add(lblChooseSupp, 0, 1); 
            fifthPane.add(cmboSupp, 0, 2); 
            fifthPane.add(viewSupp, 0, 3); 
            fifthPane.add(btnViewAllSupp, 0, 4); 
            fifthPane.add(suppOutput, 0, 5, 40, 20);  
        }); 

        viewSupp.setOnAction(e -> {
            if(suppList.size() <= 0) 
            {
                alert.setTitle("Error!");	           
                alert.setHeaderText("There are no suppliers in the system!");	          
                alert.showAndWait();	 
            }
            else if(cmboSupp.getValue() == null)
            {	            
                alert.setTitle("Error!");	           
                alert.setHeaderText("Please select a supplier to view!");	          
                alert.showAndWait();	           
            }	
            else 
            {
            Supplier tempSupp = suppList.get(cmboSupp.getSelectionModel().getSelectedIndex());            
            suppOutput.setText(tempSupp.toString());   
            cmboSupp.getSelectionModel().clearSelection();
            }
            
        }); 
        btnViewAllSupp.setOnAction(e -> {
            String suppOutput2 = ""; 
            for(int i =0; i < suppList.size(); i++) {
                suppOutput2 += suppList.get(i).toString(); 
                suppOutput2 += "\n";
            }
            suppOutput.setText(suppOutput2); 
        });
        
        btnDisProd.setOnAction(e -> {
            if(cmboProd.getValue() == null){	            
                alert.setTitle("Error!");	           
                alert.setHeaderText("Please select a product to discontinue!");	          
                alert.showAndWait();	           
            }	
            else
            {
            int remove = cmboProd.getSelectionModel().getSelectedIndex();	           
            cmboProd.getSelectionModel().clearSelection();
            prodList.remove(remove);
            cmboProd.getItems().remove(remove);            
            }
            prodData.clear();
            for(Product prod: prodList)
            {
                prodData.add(prod);
            }
            alert.setTitle("Success!");
            alert.setHeaderText("The product has been discontinued!");
            alert.showAndWait();

        }); 
        
        btnConfirmChange.setOnAction(e -> {
            int remove = cmboProd.getSelectionModel().getSelectedIndex(); 

            for (int i = 0; i < prodList.size(); i++) 
            {
                prodList.remove(remove); 
                cmboProd.getItems().remove(remove); 
                alert.setTitle("Success!");
                alert.setHeaderText("The product has been dicontinued!");
                alert.showAndWait();

            }

            prodData.clear();
            for(Product prod: prodList)
            {
                prodData.add(prod);
            }

        });
        
        btnAddOrder.setOnAction(e -> {
            // Create a Product Order
            fifthPane.getChildren().clear();
            txtProdName.clear();
            txtUnitCost.clear();
            txtUnitPrice.clear();
            cmboCategory.getSelectionModel().clearSelection();
            orderDate.getEditor().clear();
            calendar.getEditor().clear();
            txtProdQuantity.clear();
            txtSubTotal.clear();
            txtProdDesc.clear();
            cmboSupp.getSelectionModel().clearSelection();
            cmboStore.getSelectionModel().clearSelection();
            
            fifthStage.setTitle("Create a Product Order");
            fifthStage.show();
            fifthPane.setAlignment(Pos.TOP_CENTER);

            fifthPane.add(lblPO, 0, 0, 3, 1); 
            fifthPane.add(lblProdName, 0, 1);
            fifthPane.add(txtProdName, 0, 2);
            fifthPane.add(lblUnitCost, 1,5); //how much the product costs thrifty to purchase
            fifthPane.add(txtUnitCost, 1, 6); 
            fifthPane.add(lblCategory, 0, 3);
            fifthPane.add(cmboCategory, 0, 4);
            fifthPane.add(lblProdDesc, 0, 5); 
            fifthPane.add(txtProdDesc, 0, 6); 
            fifthPane.add(lblSubTotal, 1, 9); 
            fifthPane.add(btnCalcProdSubTotal, 0, 12);
            fifthPane.add(txtSubTotal, 1, 10); 
            fifthPane.add(lblUnitPrice, 1, 7); //how much thrify sells the product for 
            fifthPane.add(txtUnitPrice, 1, 8); 
            fifthPane.add(lblOrderDate, 0, 7); 
            fifthPane.add(orderDate, 0, 8); 
            fifthPane.add(lblDelDate, 0, 9); 
            fifthPane.add(calendar, 0, 10);
            fifthPane.add(lblQuantity, 1, 1);
            fifthPane.add(txtProdQuantity, 1, 2);
            //need to add order date into the pane and use the constructor with it 
            
            fifthPane.add(lblSupplier, 1, 3); 
            fifthPane.add(cmboSupp, 1, 4);
            
            fifthPane.add(lblProdStore, 2,3);
            fifthPane.add(cmboStore, 2, 4); //when picking which store the product is for, should be selected from already existing stores created 
            txtSubTotal.setEditable(false);
            orderDate.setValue(LocalDate.now());
            fifthPane.add(btnCompleteOrder, 0, 11);
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
            if(txtProdName.getText().trim().length() == 0 || txtUnitCost.getText().trim().length() == 0 || txtUnitPrice.getText().trim().length()== 0||
                    cmboCategory.getValue() == null || orderDate.getValue() == null || calendar.getValue() == null ||
                    txtProdQuantity.getText().trim().length() == 0 || cmboSupp.getValue() == null || cmboStore.getValue() == null)
            {
                alert.setTitle("Error!");
                alert.setHeaderText("Error! Please Enter Data!");
                alert.showAndWait();
            }
            else
            {
                double quantity = Integer.parseInt(txtProdQuantity.getText());
                double cost = Double.parseDouble(txtUnitCost.getText()); 
                double subTotal = quantity * cost; 
                String subTotal2 = ""; 
                subTotal2 = Double.toString(subTotal); 
                txtSubTotal.setText(subTotal2); 
                String date = calendar.getValue().format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
                String orderDate2 = orderDate.getValue().format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
                prodOrderList.add(new ProductOrder(Integer.parseInt(txtProdQuantity.getText()), 
                        txtProdName.getText(), Double.parseDouble(txtUnitCost.getText()), Double.parseDouble(txtUnitPrice.getText()),
                        Double.parseDouble(txtSubTotal.getText()), date, orderDate2, 
                        suppList.get(cmboSupp.getSelectionModel().getSelectedIndex()), 
                        storeList.get(cmboStore.getSelectionModel().getSelectedIndex()), (String)cmboCategory.getValue(), txtProdDesc.getText())); 
                ProductOrder.obsProdOrder.add(txtProdName.getText());

                alert.setTitle("Success!");
                alert.setHeaderText("The product order has been created!");
                alert.showAndWait();
                //insert code to add product to object

                 fifthStage.close();  
                //for (int i = 0; i < prodOrderList.size(); i++) {
                    //System.out.println(prodOrderList.get(i)); 
                //}
                txtProdName.clear();
                txtUnitCost.clear();
                cmboCategory.getSelectionModel().clearSelection();
                orderDate.getEditor().clear();
                calendar.getEditor().clear();
                txtProdQuantity.clear();
                cmboSupp.getSelectionModel().clearSelection();
                cmboStore.getSelectionModel().clearSelection();
                txtProdDesc.clear();
            
            }
        }); 
        
        btnViewOrder.setOnAction(e -> {
            fifthPane.getChildren().clear();
            cmboProdOrder.getSelectionModel().clearSelection();
            fifthStage.setTitle("View Product Orders");
            fifthStage.show();
            fifthPane.setAlignment(Pos.TOP_CENTER);

            fifthPane.add(lblViewPO, 0, 0);
            fifthPane.add(cmboProdOrder, 0, 1); 
            fifthPane.add(btnDisplayPO, 0, 2); 
            fifthPane.add(btnViewAllPO, 0, 3); 
            fifthPane.add(prodOrderOutput, 0, 4, 45, 15);  
        }); 
        
        
        btnDisplayPO.setOnAction(e -> {
            if (prodOrderList.size() <= 0) 
            {
                alert.setTitle("Error!");
                alert.setHeaderText("There are no product orders to display!");
                alert.showAndWait();
            }
            else if (cmboProdOrder.getValue() == null)
            {	            
                alert.setTitle("Error!");	           
                alert.setHeaderText("Please select a product order to display!");	          
                alert.showAndWait();	     
            }
            else 
            {
                ProductOrder tempProdOrder = prodOrderList.get(cmboProdOrder.getSelectionModel().getSelectedIndex());
                //String prodOrderSelect2 = Integer.toString(prodOrderSelect); 
                prodOrderOutput.setText(tempProdOrder.toString());   
            }

        }); 
    btnViewAllPO.setOnAction(e -> {
        if(prodOrderList.size() <= 0) 
        {
            alert.setTitle("Error!");	           
            alert.setHeaderText("There are no product orders in the system!");	          
            alert.showAndWait();	 
        }
        else 
        {
            String poOutput2 = ""; 
            for(int i =0; i < prodOrderList.size(); i++) 
            {
                poOutput2 += suppList.get(i).toString(); 
                poOutput2 += "\n";
            }
            prodOrderOutput.setText(poOutput2); 
        }
        
        }); 
        
        btnAddProd.setOnAction(e -> {
            // Add a Product
            thirdPane.getChildren().clear();
            txtProdName.clear();	
            txtProdQuantity.clear();	
            txtUnitPrice.clear();
            txtUnitCost.clear();
            cmboStore.getSelectionModel().clearSelection();	
            txtProdDesc.clear();	
            cmboCategory.getSelectionModel().clearSelection();	
            cmboSupp.getSelectionModel().clearSelection();
            
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
            thirdPane.add(lblUnitPrice, 0, 5); //how much thrifty sells item for
            thirdPane.add(txtUnitPrice, 0, 6);
            thirdPane.add(lblUnitCost, 1, 5); //how much thrifty bought item for 
            thirdPane.add(txtUnitCost, 1, 6); 
            thirdPane.add(lblQuantity, 1, 3);
            thirdPane.add(txtProdQuantity, 1, 4);
            thirdPane.add(lblSupplier, 0, 7); 
            thirdPane.add(cmboSupp, 0, 8); 
            thirdPane.add(lblImage, 0, 9);
            thirdPane.add(btnImage, 0, 10);
            thirdPane.add(lblProdStore, 1,7 );
            thirdPane.add(cmboStore, 1, 8 );  //when picking which store the product is for, should be selected from already existing stores created 
            
            cmboCategory.setEditable(true);
            thirdPane.add(addProd, 0, 16);
        });
        addProd.setOnAction(e -> {
            //product added clicked
            if(txtProdName.getText().trim().length() == 0 || txtProdDesc.getText().trim().length() == 0 ||
                    cmboCategory.getValue() == null || txtUnitPrice.getText().trim().length() == 0 || txtUnitCost.getText().trim().length() == 0|| 
                    txtProdQuantity.getText().trim().length() == 0 || cmboSupp.getValue() == null ||
                    cmboStore.getValue() == null)
            {
                alert.setTitle("Error!");
                alert.setHeaderText("Error! Please Enter Data!");
                alert.showAndWait();
            }
            else
            {
                int storeLocation = cmboStore.getSelectionModel().getSelectedIndex(); 
                String storeLocation2 = Integer.toString(storeLocation); 
                int productCategory = cmboCategory.getSelectionModel().getSelectedIndex();  

    //           int suppLoc = cmboSupp.getSelectionModel().getSelectedIndex();
        if(prodList.size()>0){
            for(int i = 0; i<prodList.size(); i++)
            {
                
                if(txtProdName.getText().equalsIgnoreCase(prodList.get(i).getProductName()) && 
                        storeList.get(cmboStore.getSelectionModel().getSelectedIndex()).getStoreName().equalsIgnoreCase(prodList.get(i).getLocation()) && 
                        categoryList.get(cmboCategory.getSelectionModel().getSelectedIndex()).equalsIgnoreCase(prodList.get(i).getCategory()) &&
                        suppList.get(cmboSupp.getSelectionModel().getSelectedIndex()).getName().equalsIgnoreCase(prodList.get(i).getSupp().getName()) &&
                        txtProdDesc.getText().equalsIgnoreCase(prodList.get(i).getFoodDescription()))
                {
                    Product temp = prodList.get(i);
                    temp.addQuantity(Integer.parseInt(txtProdQuantity.getText()));
                    prodList.remove(i);
                    prodList.add(i,temp);
                    break;
                }
                else if(i == prodList.size() - 1 && !(txtProdName.getText().equalsIgnoreCase(prodList.get(i).getProductName()) && 
                        storeList.get(storeLocation).equals(prodList.get(i).getStore()) && 
                        categoryList.get(productCategory).equalsIgnoreCase(prodList.get(i).getCategory()) &&
                        suppList.get(cmboSupp.getSelectionModel().getSelectedIndex()).equals(prodList.get(i).getSupp()) &&
                        txtProdDesc.getText().equalsIgnoreCase(prodList.get(i).getFoodDescription())))
                {
                    prodList.add(new Product(txtProdName.getText(), 
                    Integer.valueOf(txtProdQuantity.getText()),
                    Double.valueOf(txtUnitPrice.getText()), Double.valueOf(txtUnitCost.getText()),
                    storeList.get(cmboStore.getSelectionModel().getSelectedIndex()), txtProdDesc.getText(), 
                    (String) cmboCategory.getValue(), suppList.get(cmboSupp.getSelectionModel().getSelectedIndex()), tempImage));
                    obsProd.add(prodList.get(prodList.size() - 1));
                    for(int k = 0; k<categoryList.size(); k++)
                    {
                        if((categoryList.get(k).equalsIgnoreCase((String)cmboCategory.getValue())))
                        {
                            break;
                        }
                        else if(k == categoryList.size()-1 && categoryList.get(k).equalsIgnoreCase((String)cmboCategory.getValue()) == false)
                        {
                            categoryList.add((String)cmboCategory.getValue());
                            obsCategory.add((String)cmboCategory.getValue());
                        }
                    }
                }
            
            }
        }
        else{
                    prodList.add(new Product(txtProdName.getText(), 
                    Integer.valueOf(txtProdQuantity.getText()),
                    Double.valueOf(txtUnitPrice.getText()), Double.valueOf(txtUnitCost.getText()),
                    storeList.get(cmboStore.getSelectionModel().getSelectedIndex()), txtProdDesc.getText(), 
                    (String) cmboCategory.getValue(), suppList.get(cmboSupp.getSelectionModel().getSelectedIndex()), tempImage));
                     obsProd.add(prodList.get(prodList.size() - 1));
                    for(int k = 0; k<categoryList.size(); k++)
                    {
                        if((categoryList.get(k).equalsIgnoreCase((String)cmboCategory.getValue())))
                        {
                            break;
                        }
                        else if(k == categoryList.size()-1 && categoryList.get(k).equalsIgnoreCase((String)cmboCategory.getValue()) == false)
                        {
                            categoryList.add((String)cmboCategory.getValue());
                            obsCategory.add((String)cmboCategory.getValue());
                        }
                    }
                    
            }
                prodData.clear();
                for(Product prod: prodList)
                {
                    prodData.add(prod);
                }
                alert.setTitle("Success!");
                alert.setHeaderText("The product has been added!");
                alert.showAndWait();              

                thirdStage.close();
                txtProdName.clear();	
                txtProdQuantity.clear();	
                txtUnitPrice.clear();	
                cmboStore.getSelectionModel().clearSelection();	
                txtProdDesc.clear();	
                cmboCategory.getSelectionModel().clearSelection();	
                cmboSupp.getSelectionModel().clearSelection();
                tempImage = null;

                
            }
        });
        
        btnRemoveProd.setOnAction(e -> {
            if(cmboProd.getValue() == null)
            {	
                alert.setTitle("Error!");	
                alert.setHeaderText("Please select a product to edit!");	
                alert.showAndWait();	
            }	
            else
            {

            pC = prodList.get(cmboProd.getSelectionModel().getSelectedIndex());
            cmboProd.getSelectionModel().clearSelection();
            thirdPane.getChildren().clear();
            thirdStage.setTitle("Edit/Remove a Product");
            thirdStage.show();
            thirdPane.setAlignment(Pos.TOP_CENTER);
            
            thirdPane.add(lblRemoveProd, 0, 0);
            //thirdPane.add(lblSelectProd, 0, 1);
            //thirdPane.add(cmboProd, 0, 2);
            thirdPane.add(lblRQuantity, 0, 3);
            thirdPane.add(txtRQuantity, 0, 4);
            
            thirdPane.add(lblEName, 1, 3); 
            thirdPane.add(txtProdName, 1, 4); 
            thirdPane.add(lblEPrice, 0, 6); 
            thirdPane.add(txtUnitPrice, 0, 7); 
            thirdPane.add(lblEStore, 1, 6); 
            thirdPane.add(cmboStore, 1, 7); 
            thirdPane.add(lblEDesc, 0, 8); 
            thirdPane.add(txtProdDesc, 0, 9); 
            thirdPane.add(lblECat, 1, 8); 
            thirdPane.add(cmboCategory, 1,9 );
            thirdPane.add(lblESupp, 0, 10); 
            thirdPane.add(cmboSupp, 0, 11); 
            thirdPane.add(removeProd, 0, 5);
            thirdPane.add(editProd, 0, 12);
            
//            cmboCategory.setEditable(true); 

           //i CANNOT GET THE RIGHT BOXES TO BE PREFILLED WITH THE RIGHT INFO FROM THE PRODUCT SELECTED SO WHEN YOU 
           //EDIT IT YOU DONT HAVE TO EDIT EACH FIELD OR SO IT DOESNT GO BLANK 
            for(int i = 0; i<obsCategory.size(); i++)
            {
               if(pC.getCategory().equalsIgnoreCase((String)obsCategory.get(i)))
               {
                   cmboCategory.getSelectionModel().select(i);
               }
            }
            for(int i = 0; i<storeList.size(); i++)
            {
               if(pC.getStore() == storeList.get(i))
               {
                   cmboStore.getSelectionModel().select(i); 
               }
            }
            for(int i = 0; i<obsSupp.size(); i++)
            {
                String s2 = String.valueOf(obsSupp.get(i));
                if(pC.getSupplier().equalsIgnoreCase(s2))
                {
                   cmboSupp.getSelectionModel().select(i);
                }
            }

            txtProdName.setText(pC.getProductName());
            txtUnitPrice.setText(Double.toString(pC.getPrice()));
            txtProdDesc.setText(pC.getFoodDescription());
            cmboStore.setPromptText(pC.getLocation()); 
            cmboSupp.setPromptText(pC.getSupplier());
            cmboCategory.setPromptText(pC.getCategory());
            }
        });
        
        removeProd.setOnAction(e -> {            
            Product tempProd = pC;            
            int remove = Integer.parseInt(txtRQuantity.getText());
            
            int removeOrig = pC.getQuantity(); 
            if (remove > removeOrig) 
            {
                alert.setTitle("Error!");
                alert.setHeaderText("The quantity entered is greater than total product Quantity! Please enter another amount.");
                alert.showAndWait();
            }

            else 
            {
            tempProd.setQuantity(tempProd.getQuantity()-remove);
            
            for(int i = 0; i<prodList.size(); i++)
            {
                if(tempProd.getProductID() == prodList.get(i).getProductID())
                {
                    prodList.remove(i);
                    prodList.add(i, tempProd);
                    break;
                }
            alert.setTitle("Success!");
            alert.setHeaderText("The product has been removed!");
            alert.showAndWait();

            }
            }
            prodData.clear();
            for(Product prod: prodList)
            {
                prodData.add(prod);
            }
            txtRQuantity.clear();
        });
        editProd.setOnAction(e -> { 
            //for(storeList.size()
                //If storeList.get(i).getName().eIC(pC.getStore.getName())
                    //cmboStore.getSelectionMethod.select(i);
                    //break;
            pC.setProductName(txtProdName.getText());
            //if (txtUnitPrice.getText() != null) {
            //pC.setPrice((Double.parseDouble(txtUnitPrice.getText())));
            pC.setPrice((Double.parseDouble(txtUnitPrice.getText()))); 

            pC.setFoodDescription(txtProdDesc.getText());

            pC.setLocation((String)cmboStore.getValue());

            pC.setCategory((String) cmboCategory.getValue());
            pC.setSupplier((String)cmboSupp.getValue());
            for(int i = 0; i<prodList.size(); i++)
            {
                if(pC.getProductID() == prodList.get(i).getProductID())
                {
                    prodList.remove(i);
                    prodList.add(i, pC);
                    cmboProd.getItems().remove(i);
                    cmboProd.getItems().add(i, pC.getProductID()+": " + pC.getProductName());
                    pC = null;
                    break;
                }
            }
            for(int i = 0; i<storeList.size(); i++)
            {
                if((storeList.get(i).getStoreName().equalsIgnoreCase((String)cmboStore.getValue())))
                {
                    break;
                }

                 else if(i == storeList.size()-1 && storeList.get(i).getStoreName().equalsIgnoreCase((String)cmboStore.getValue()) == false)
                {
                    storeList.add((Store)cmboStore.getValue());
                }
            }
           // for (int i = 0; i < obsCategory.size(); i++) {

            //}
            alert.setTitle("Success!");
            alert.setHeaderText("The product has been edited!");
            alert.showAndWait();
            
            prodData.clear();
            for(Product prod: prodList)
            {
                prodData.add(prod);
            }
            
            thirdStage.close();
            
        });
        btnImage.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose Image");
            File file = fileChooser.showOpenDialog(thirdStage);
            
            try{
                tempImage = ImageIO.read(file);
                WritableImage iImage = SwingFXUtils.toFXImage(tempImage, null);
                
                
                myImageView.setImage(iImage);
                thirdPane.add(myImageView, 1, 11, 4, 4);
                myImageView.setFitHeight(130);
                myImageView.setFitWidth(130);
                imageList.add(tempImage); 
                
            }
            catch(Exception ex)
            {
                
            }
           
        });
        
        btnHandleImage.setOnAction(e -> {
            
            fourthPane.getChildren().clear();
            fourthStage.setTitle("View/Edit an Image");
            fourthStage.show();
            fourthPane.setAlignment(Pos.TOP_LEFT);
            
            
            fourthPane.add(lblSelectProd, 0, 0);
            fourthPane.add(cmboProd2, 1, 0);
            fourthPane.add(btnViewImage, 0, 2);
            fourthPane.add(btnChangeImage, 1, 2);
            fourthPane.add(btnAddImage2, 2, 2); 
            
            

        });
        btnChangeImage.setOnAction(e -> {
            if(cmboProd2.getValue() == null){
                alert.setTitle("Error!");
                alert.setHeaderText("Please select a product order to change the image!");
                alert.showAndWait();
            }
             else {
            imageChosen = cmboProd2.getSelectionModel().getSelectedIndex();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose Image");
            File file = fileChooser.showOpenDialog(thirdStage);
            
            try{
                tempImage = ImageIO.read(file);
                prodList.get(imageChosen).setImage(tempImage);
                obsProd.get(imageChosen).setImage(tempImage);
                             
                for(int i = 0; i < imageList.size(); i++)
                {
                    if (i == imageChosen)
                        imageList.remove(i); 
                        imageList.set(imageChosen, tempImage);                        
                }
                     
                
                
            }
            catch(Exception ex){
                
            }
            alert.setTitle("Success!");
            alert.setHeaderText("The product image has been changed!");
            alert.showAndWait();
                myImageViewTwo.setImage(SwingFXUtils.toFXImage(tempImage, null));
                myImageViewTwo.setFitHeight(130);
                myImageViewTwo.setFitWidth(130);
                fourthPane.getChildren().clear();
                fourthStage.setTitle("View/Edit an Image");
                fourthStage.show();
                fourthPane.setAlignment(Pos.TOP_LEFT);
            
            
                fourthPane.add(lblSelectProd, 0, 0);
                fourthPane.add(cmboProd2, 1, 0);
                fourthPane.add(btnViewImage, 0, 2);
                fourthPane.add(btnChangeImage, 1, 2);
                fourthPane.add(btnAddImage2, 2, 2); 
                fourthPane.add(myImageViewTwo, 1, 11, 6, 5);
            }
        });
        
        btnViewImage.setOnAction(e -> {
            int prodSelect = cmboProd2.getSelectionModel().getSelectedIndex(); 
            Product tempProd2 = prodList.get(prodSelect); 
              if(cmboProd2.getValue() == null){
                alert.setTitle("Error!");
                alert.setHeaderText("Please select a product to view the image!");
                alert.showAndWait();
            }
              else if(tempProd2.getImage() == null) {
                  alert.setTitle("Error!");
                  alert.setHeaderText("This product does not have an image!");
                  alert.showAndWait();
              }
              else {
            
            imageChosen = cmboProd2.getSelectionModel().getSelectedIndex(); 
            WritableImage theImage =  SwingFXUtils.toFXImage(prodList.get(imageChosen).getImage(), null);
            
            myImageViewTwo.setImage(theImage);
            fourthPane.getChildren().clear();
            fourthStage.setTitle("View/Edit an Image");
            fourthStage.show();
            fourthPane.setAlignment(Pos.TOP_LEFT);
            
            
            fourthPane.add(lblSelectProd, 0, 0);
            fourthPane.add(cmboProd2, 1, 0);
            fourthPane.add(btnViewImage, 0, 2);
            fourthPane.add(btnChangeImage, 1, 2);
            fourthPane.add(btnAddImage2, 2, 2); 
            fourthPane.add(myImageViewTwo, 1, 11, 6, 5);
              }
              

        }); 
        btnAddImage2.setOnAction(e -> {
            int prodSelect = cmboProd2.getSelectionModel().getSelectedIndex(); 
            Product tempProd2 = prodList.get(prodSelect); 
             if(cmboProd2.getValue() == null){
                alert.setTitle("Error!");
                alert.setHeaderText("Please select a product to add the image!");
                alert.showAndWait();
            }
             else if(tempProd2.getImage() != null) {
                  alert.setTitle("Error!");
                  alert.setHeaderText("This product already has an image! To change, please select Change image.");
                  alert.showAndWait();
              }
             else {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Choose Image");
                File file = fileChooser.showOpenDialog(thirdStage);
            
            try{
              
                tempImage = ImageIO.read(file);
                prodList.get(imageChosen).setImage(tempImage);
                obsProd.get(imageChosen).setImage(tempImage);
                
                
                
            }
            catch(Exception ex){
                
            }
            alert.setTitle("Success!");
            alert.setHeaderText("The product image has been added!");
            alert.showAndWait();
            myImageViewTwo.setImage(SwingFXUtils.toFXImage(tempImage, null));
                fourthPane.getChildren().clear();
                fourthStage.setTitle("View/Edit an Image");
                fourthStage.show();
                fourthPane.setAlignment(Pos.TOP_LEFT);
            
            
                fourthPane.add(lblSelectProd, 0, 0);
                fourthPane.add(cmboProd2, 1, 0);
                fourthPane.add(btnViewImage, 0, 2);
                fourthPane.add(btnChangeImage, 1, 2);
                fourthPane.add(btnAddImage2, 2, 2); 
                
                
                fourthPane.add(myImageViewTwo, 1, 11, 6, 5);
                myImageViewTwo.setFitHeight(130);
                myImageViewTwo.setFitWidth(130);
                imageList.add(tempImage); 
             } 
        }); 
           
        btnRecShip.setOnAction(e -> {
            

            if(cmboProdOrder2.getValue() == null)
            {
               alert.setTitle("Error!");
               alert.setHeaderText("Please select a product order to Recieve!");
               alert.showAndWait();
            }
            else 
            {
               int prodORemove = cmboProdOrder2.getSelectionModel().getSelectedIndex(); 
               poC = prodOrderList.get(prodORemove);
               int poQ = poC.getQuantity();
               int prodQ;
               int totalQuantity;
               obsRecOrder.add(poC);
               expList.add(new Expense("Inventory Purchase", poC.getProdOName() + ", " + poC.getStore(), 0, poC.getSubtotal(),currentDate.format(DateTimeFormatter.ofPattern("MM-dd-yyyy")), poC.getStoreLoc()));
               obsExpense.add(expList.get(expList.size()-1));
               for (int j = 0; j < prodList.size(); j++) 
               {
                     if(poC.getProdOName().equalsIgnoreCase(prodList.get(j).getProductName()) && poC.getSupplier().equalsIgnoreCase(prodList.get(j).getSupplier())
                             && poC.getStore().equals(prodList.get(j).getLocation()) && poC.getCategory().equalsIgnoreCase(prodList.get(j).getCategory()) 
                             && poC.getPODesc().equalsIgnoreCase(prodList.get(j).getFoodDescription()))
                     {
                         prodQ = prodList.get(j).getQuantity();
                         pC = prodList.get(j);
                         totalQuantity = poQ + prodQ;
                         pC.setQuantity(totalQuantity);
                         prodList.remove(j);
                         prodList.add(j,pC);
                         break;
                     }
                     else if(j == prodList.size()-1 && poC.getProdOName().equalsIgnoreCase(prodList.get(j).getProductName())== false) 
                     {
                       prodList.add(new Product(poC.getProdOName(), poC.getUnitCost(), poC.getUnitPrice(), poC.getQuantity(), poC.getStoreLoc(), poC.getPODesc(), poC.getCategory(),
                       poC.getAssociatedSupplier())); 
                       break;
                     }
               }
               prodOrderList.remove(prodORemove);
               cmboProdOrder.getItems().remove(prodORemove);
               prodData.clear();
               for(Product prod: prodList)
               {
                   prodData.add(prod);
               }
               expData.clear();
               for(Expense exp: expList)
               {
                   expData.add(exp);
               }
               alert.setTitle("Success!");
               alert.setHeaderText("The product order has been received!");
               alert.showAndWait(); 
            }
        });
        
        
        empTable.getColumns().addAll(tbEmpFName, tbEmpLName, tbEmpEmail,
                tbEmpPhone, tbEmpAddress, tbEmpSalary, tbEmpJTitle, tbEmpType, tbEmpCat, tbEmpLoc);
        
        
        menuAdmin.getMenus().get(1).getItems().get(0).setOnAction(e -> {
            // Employee Management
            
            secondPane.getChildren().clear();
            formPane.getChildren().clear();
            border.getChildren().clear();
            cmboEmp.getSelectionModel().clearSelection();
            secondPane.setAlignment(Pos.TOP_CENTER);
            secondStage.setTitle("Employee Management");
            
            cmboEmp.setMaxWidth(Double.MAX_VALUE);
            btnEditEmp.setMaxWidth(Double.MAX_VALUE);
            btnAddEmp.setMaxWidth(Double.MAX_VALUE);

            VBox empVB = new VBox();
            empVB.setSpacing(10);
            empVB.setPadding(new Insets(10, 20, 10 , 20));
            
            empVB.getChildren().addAll(lblEmp, lblEmpMan, btnAddEmp, cmboEmp, btnEditEmp);
            
            border.setLeft(empVB);
            empTable.setItems(empData);
            //secondPane.add(empTable, 3, 9);
            empTable.setMinWidth(secondScene.getWidth());
            
            border.setTop(menuAdmin);
            border.setCenter(empTable);
            
        });
        
        btnAddEmp.setOnAction(e -> {
            thirdPane.getChildren().clear();
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
            if(txtFName.getText().trim().length() == 0 || txtLName.getText().trim().length() == 0 ||
                    txtEmail.getText().trim().length() == 0 || txtPhone.getText().trim().length() == 0 ||
                    txtAddress.getText().trim().length() == 0 || cmboJob.getValue() == null ||
                    txtSalary.getText().trim().length() == 0 || cmboEType.getValue() == null ||
                    cmboEmpCat.getValue() == null || cmboStore.getValue() == null)
            {
                alert.setTitle("Error!");
                alert.setHeaderText("Error! Please Enter Data!");
                alert.showAndWait();
            }
            else
            {
                empList.add(new Employee(txtFName.getText(), txtLName.getText(), txtEmail.getText(), txtPhone.getText(), 
                txtAddress.getText(), Double.valueOf(txtSalary.getText()),  
                        (String)cmboJob.getValue(), (String)cmboEType.getValue(), (String)cmboEmpCat.getValue(), storeList.get(cmboStore.getSelectionModel().getSelectedIndex()))); 
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
            }
        });
        removeEmp.setOnAction(e -> {
                if(cmboEmp.getValue() == null)
            {
                alert.setTitle("Error!");
                alert.setHeaderText("Please select an employee to edit!");
                alert.showAndWait();
            }
            else
            {
                int remove = cmboEmp.getSelectionModel().getSelectedIndex();
                cmboEmp.getSelectionModel().clearSelection();
                empList.remove(remove);
                cmboEmp.getItems().remove(remove);            
            }
            empData.clear();
            for(Employee emp: empList)
            {
                empData.add(emp);
            }            
        });
        btnEditEmp.setOnAction(e -> {
            // They have no way of selecting an employee rn
            if(cmboEmp.getValue() == null)
            {
                alert.setTitle("Error!");
                alert.setHeaderText("Please select an employee to edit!");
                alert.showAndWait();
            }

            else
            {
                eC = empList.get(cmboEmp.getSelectionModel().getSelectedIndex());
                cmboEmp.getSelectionModel().clearSelection();

                thirdPane.getChildren().clear();
                thirdStage.setTitle("Edit Employee");
                thirdStage.show();
                thirdPane.setAlignment(Pos.TOP_CENTER);

                // SHU - I switched txtStoreLoc to the cmboStore so do whatever accordingly to fix these
                // I adjusted for these - Shu
                thirdPane.add(lblEditEmp, 0, 0);
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

                cmboJob.setEditable(true);

                //Selecting cmboBox fields so that if unedited, will not give error
                for(int i = 0; i<job.size(); i++)
                {
                   if(eC.getJobTitle().equalsIgnoreCase((String)job.get(i)))
                   {
                       cmboJob.getSelectionModel().select(i);
                   }
                }
                for(int i = 0; i<job.size(); i++)
                {                   
                   if(i == job.size()-1 && eC.getJobTitle().equalsIgnoreCase((String)job.get(i))== false)
                   {
                       cmboJob.getItems().add(eC.getJobTitle());
                       cmboJob.getSelectionModel().selectLast();                       
                   }
                }
                for(int i = 0; i<storeList.size(); i++)
                {
                   if(eC.getStoreLoc().equalsIgnoreCase(storeList.get(i).getStoreName()))
                   {
                       cmboStore.getSelectionModel().select(i); 
                   }
                }
                for(int i = 0; i<eType.size(); i++)
                {
                    if(eC.getEmployeeType().equalsIgnoreCase((String)eType.get(i)))
                    {
                       cmboEType.getSelectionModel().select(i);
                    }
                }
                for(int i = 0; i<empCategory.size(); i++)
                {
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
            eC.setStore(storeList.get(cmboStore.getSelectionModel().getSelectedIndex()));
            eC.setSalary(Double.valueOf(txtSalary.getText()));
            eC.setEmployeeCategory((String) cmboEmpCat.getValue());
            eC.setEmployeeType((String)cmboEType.getValue());

            for(int i = 0; i<empList.size(); i++)
            {
                if(empList.get(i).getEmployeeID()== eC.getEmployeeID())
               {
                    Employee temp = eC;
                    empList.remove(i);
                    empList.add(i, temp);
                    cmboEmp.getItems().remove(i);
                    cmboEmp.getItems().add(i, temp.getEmployeeID() + " " + temp.getFirstName() + " " + eC.getLastName());
                    eC = null;
               
                    break;
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
        expTable.getColumns().addAll(tbExpID, tbExpCategory, tbExpDesc, tbExpAmountPaid, tbExpAmountTotal, tbExpDate, tbExpStatus, tbExpStore);  
        menuAdmin.getMenus().get(1).getItems().get(2).setOnAction(e -> {
           // Expense Management
           
            secondPane.getChildren().clear();
            formPane.getChildren().clear();
            border.getChildren().clear();
            cmboExpense.getSelectionModel().clearSelection();
           
            secondPane.setAlignment(Pos.TOP_CENTER);
            secondStage.setTitle("Expense Management");
           
            btnAddExp.setMaxWidth(Double.MAX_VALUE);
            btnPayExp.setMaxWidth(Double.MAX_VALUE);
            btnEditExp.setMaxWidth(Double.MAX_VALUE);
            btnAddExpType.setMaxWidth(Double.MAX_VALUE);	
            btnRemExp.setMaxWidth(Double.MAX_VALUE);	
            btnRemExpType.setMaxWidth(Double.MAX_VALUE);
            cmboExpense.setMaxWidth(Double.MAX_VALUE);
            
            expTable.setItems(expData);
            expTable.setMinWidth(secondScene.getWidth());
            
            VBox expVB = new VBox();
            expVB.setSpacing(10);
            expVB.setPadding(new Insets(10, 20, 10 , 20));
           
            
            expVB.getChildren().addAll(lblExp, lblExpMan, cmboExpense, btnAddExp, btnPayExp, btnEditExp
                    , btnRemExp, btnAddExpType, btnRemExpType);
            // remove expense is currently not in here
            border.setTop(menuAdmin);
            border.setLeft(expVB);
            border.setCenter(expTable);
           
        });
        
        btnAddExp.setOnAction(e -> {
            // Adding an Expense
            thirdPane.getChildren().clear();
            txtExpDesc.clear();
            txtAmount.clear();
            txtTtlAmount.clear();
            cmboExpCategory.getSelectionModel().clearSelection();
            cmboStatus.getSelectionModel().clearSelection();
            expDate.getEditor().clear();
            
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
//            thirdPane.add(lblStatus, 1, 3);
//            thirdPane.add(cmboStatus, 1, 4);
            thirdPane.add(lblExpStore, 1, 3);	
            thirdPane.add(cmboExpStore, 1, 4);
            thirdPane.add(lblAmount, 0, 5);
            thirdPane.add(txtAmount, 0, 6);
            thirdPane.add(lblTtlAmount, 1, 5);
            thirdPane.add(txtTtlAmount, 1, 6);
            thirdPane.add(addExp, 0, 7);
            expDate.setValue(LocalDate.now());
        });
        
        addExp.setOnAction(e -> {
            //expense added clicked
            if(txtExpDesc.getText().trim().length() == 0 ||
                    cmboExpCategory.getValue() == null || cmboExpStore.getValue() == null ||
                    txtAmount.getText().trim().isEmpty() || txtTtlAmount.getText().trim().isEmpty() || expDate.getValue() == null)
            {
                alert.setTitle("Error!");
                alert.setHeaderText("Error! Please Enter Data!");
                alert.showAndWait();
            }
            else
            {
                String description = txtExpDesc.getText();
                String category = (String)cmboCategory.getValue();
                String status = (String)cmboStatus.getValue();
                Double amountPaid = Double.parseDouble(txtAmount.getText());
                Double totalAmount = Double.parseDouble(txtTtlAmount.getText());
                String date = expDate.getValue().format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));

                //System.out.println(obsExpense.size());
                //expList.add(new Expense(category, description, amountPaid, totalAmount, date));
                Expense temp = new Expense((String) expCategory.get(cmboExpCategory.getSelectionModel().getSelectedIndex()),
                    txtExpDesc.getText(), Double.parseDouble(txtAmount.getText()), Double.parseDouble(txtTtlAmount.getText()),
                    expDate.getValue().format(DateTimeFormatter.ofPattern("MM-dd-yyyy")), (Store) obsExpStore.get(cmboExpStore.getSelectionModel().getSelectedIndex()));
                if(amountPaid > 0.0){
                    temp.datesPaid.add(date);
                    temp.pmtsMade.add(amountPaid);
                }
                expList.add(temp);
                obsExpense.add(temp);
                
                expStoreVar = cmboExpStore.getSelectionModel().getSelectedIndex();
                
                storeList.get(expStoreVar).addExpense(temp);
                
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
                
                txtExpDesc.clear();
                txtAmount.clear();
                txtTtlAmount.clear();
                cmboExpCategory.getSelectionModel().clearSelection();
                cmboStatus.getSelectionModel().clearSelection();
                expDate.getEditor().clear();

                thirdStage.close();
            }
        });
        
        btnEditExp.setOnAction(e -> {
            if (cmboExpense.getValue() == null || obsExpense.size() < 1) {
                alert.setTitle("Error!");
                alert.setHeaderText("Please select an expense to edit!");
                alert.showAndWait();
            }
            else
            {
                // Adding an Expense
                thirdPane.getChildren().clear();
                thirdStage.setTitle("Edit an Expense");
                thirdStage.show();
                thirdPane.setAlignment(Pos.TOP_CENTER);

                thirdPane.add(lblEditExp, 0, 0);
                thirdPane.add(lblExpDesc, 0, 1);
                thirdPane.add(txtExpDesc, 0, 2);
                thirdPane.add(lblDate, 0, 3);
                thirdPane.add(expDate, 0, 4);
                thirdPane.add(lblCategory, 1, 1);
                thirdPane.add(cmboExpCategory, 1, 2);
                //thirdPane.add(lblStatus, 1, 3);
                //thirdPane.add(cmboStatus, 1, 4);
                thirdPane.add(lblExpStore, 1, 3);
                thirdPane.add(cmboExpStore, 1, 4);
                thirdPane.add(lblAmount, 0, 5);
                thirdPane.add(txtAmount, 0, 6);
                thirdPane.add(lblTtlAmount, 1, 5);
                thirdPane.add(txtTtlAmount, 1, 6);
                //thirdPane.add(lblExpStore, 0, 7);
                //thirdPane.add(cmboStore, 0, 8);
                thirdPane.add(editExp, 0, 7);
                expDate.setValue(LocalDate.now());
                exC = expList.get(cmboExpense.getSelectionModel().getSelectedIndex());
                txtExpDesc.setText(exC.getDescription());
                //expDate.setText(exC.getDate());
                cmboExpCategory.setPromptText(exC.getCategory());
                txtAmount.setText(String.valueOf(exC.getAmountPaid()));
                txtTtlAmount.setText(String.valueOf(exC.getTotalAmount()));                
            

                

                expVar = cmboExpense.getSelectionModel().getSelectedIndex();

                txtExpDesc.setText(obsExpense.get(expVar).getDescription());
                //LocalDate localExpDate = LocalDate.parse(obsExpense.get(expVar).getDate());
                //expDate.setValue(localExpDate);
                cmboExpCategory.setValue(obsExpense.get(expVar).getCategory());                
                txtAmount.setText((String.valueOf(obsExpense.get(expVar).getAmountPaid())));

                obsExpense.get(expVar).setDescription(txtExpDesc.getText());
                obsExpense.get(expVar).setCategory(expCategoryList.get(cmboExpCategory.getSelectionModel().getSelectedIndex()));
                obsExpense.get(expVar).setAmountPaid(Double.parseDouble(txtAmount.getText()));                
                obsExpense.get(expVar).setDate(expDate.getValue().format(DateTimeFormatter.ofPattern("MM-dd-yyyy")));
                        
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
                exC.setCategory((String) cmboExpCategory.getValue());
            }
            
            if (exC.getAmountPaid() > exC.getTotalAmount()) 
            {
                exC.setStatus("Ongoing");
            }
            if (exC.getAmountPaid() <= exC.getTotalAmount()) 
            {
                exC.setStatus("Paid");
            }
            exC.setRemainingBalance(exC.getTotalAmount() - exC.getAmountPaid());
            //exC.setDate((expDate.getValue().format(DateTimeFormatter.ofPattern("MM-dd-yyyy"))));
            alert.setTitle("Success!");
            alert.setHeaderText("Expense has been Edited!");
            alert.showAndWait();
            thirdStage.close();
            expData.clear();
            for (Expense exp : expList) 
            {
                expData.add(exp);
            }
            txtExpDesc.clear();
            txtAmount.clear();
            cmboExpCategory.getSelectionModel().clearSelection();
            cmboStatus.getSelectionModel().clearSelection();
            expDate.getEditor().clear();
        });
        
        btnPayExp.setOnAction(e -> {
            eighthPane.getChildren().clear();
            if (Expense.obsExpense.size() < 1) 
            {
                alert.setTitle("Error!");
                alert.setHeaderText("No expenses to pay!");
                alert.showAndWait();
            } 
            else 
            {

                for (int i = 0; i < expList.size(); i++) 
                {
                    if (expList.get(i).getStatus().equalsIgnoreCase("ONGOING")) 
                    {
                        obsUnpaidExp.add(expList.get(i));
                        unpaidExp.add(expList.get(i).getExpenseID() + ": " + expList.get(i).getDescription() + ", " + expList.get(i).getRemainingBalance());
                    }
                }
            System.out.println(expList.size() + " wap");
            System.out.println(obsUnpaidExp.size() + " wop");
            eighthPane.add(lblChooseExp, 0, 1);
            eighthPane.add(cmboUnpaidExp, 0, 2);
            eighthPane.add(btnSelectExpense, 0, 3);
            eighthPane.add(lblPayExpenseHeader, 0, 0);
            eighthPane.add(lblExpCurrentAmount, 0, 4);
            eighthPane.add(txtExpCurrentAmount, 0, 5);
            eighthPane.add(lblChoosePayment, 1, 4);
            eighthPane.add(txtChoosePayment, 1, 5);
            eighthPane.add(lblDisplayExpAmount, 0, 6);
            eighthPane.add(txtDisplayExpAmount, 0, 7);
            eighthPane.add(btnConfirmPayExp, 0, 8);
            txtExpCurrentAmount.setEditable(false);
            txtDisplayExpAmount.setEditable(false);
            txtExpCurrentAmount.clear();
            txtChoosePayment.clear();
            txtDisplayExpAmount.clear();
            cmboUnpaidExp.getSelectionModel().clearSelection();
            cmboStatus.getSelectionModel().clearSelection();
            expDate.getEditor().clear();
            eighthStage.show();

            System.out.println(unpaidExpVar + " / " + obsUnpaidExp.size());
            }
            //System.out.println(expList.get(0).payExpense(Double.parseDouble(txtChoosePayment.getText())));
        });

        btnSelectExpense.setOnAction(e -> {
            if (cmboUnpaidExp.getValue() == null)	
            {	
            alert.setTitle("Error!");	
            alert.setHeaderText("Please Select an Expense!");	
            alert.showAndWait();	
            }	
            else	
            {
            unpaidExpVar = cmboUnpaidExp.getSelectionModel().getSelectedIndex();
            txtExpCurrentAmount.setText(String.valueOf(obsUnpaidExp.get(unpaidExpVar).getAmountPaid()));
            txtDisplayExpAmount.setText(String.valueOf(obsUnpaidExp.get(unpaidExpVar).getRemainingBalance()));
            }
        });
        btnConfirmPayExp.setOnAction(e -> {

            obsUnpaidExp.get(unpaidExpVar).payExpense(Double.valueOf(txtChoosePayment.getText()));
            obsUnpaidExp.get(unpaidExpVar).datesPaid.add(LocalDate.now().format(DateTimeFormatter.ofPattern("MM-dd-yyyy")));
            obsUnpaidExp.get(unpaidExpVar).pmtsMade.add(Double.valueOf(txtChoosePayment.getText()));
            txtDisplayExpAmount.setText(String.valueOf(obsUnpaidExp.get(unpaidExpVar).getRemainingBalance()));

            if (obsUnpaidExp.get(unpaidExpVar).getAmountPaid() < obsUnpaidExp.get(unpaidExpVar).getTotalAmount()) 
            {
                obsUnpaidExp.get(unpaidExpVar).setStatus("Ongoing");
            }
            if (obsUnpaidExp.get(unpaidExpVar).getAmountPaid() >= obsUnpaidExp.get(unpaidExpVar).getTotalAmount()) 
            {
                obsUnpaidExp.get(unpaidExpVar).setStatus("Paid");
            }

            expData.clear();
            for (Expense exp : expList) 
            {
                expData.add(exp);
            }
            if (obsUnpaidExp.get(unpaidExpVar).getRemainingBalance() > 0) 
            {
                alert.setTitle("Success!");
                alert.setHeaderText("Your payment has been recorded!");
                alert.showAndWait(); 
                txtExpDesc.clear();
                txtAmount.clear();
                cmboExpCategory.getSelectionModel().clearSelection();
                cmboStatus.getSelectionModel().clearSelection();
                expDate.getEditor().clear();
                eighthStage.close();
            }
            if (obsUnpaidExp.get(unpaidExpVar).getRemainingBalance() == 0) 
            {
                alert.setTitle("Success!");
                alert.setHeaderText("You have fully paid this expense!");
                alert.showAndWait();
                txtExpDesc.clear();
                txtAmount.clear();
                cmboExpCategory.getSelectionModel().clearSelection();
                cmboStatus.getSelectionModel().clearSelection();
                expDate.getEditor().clear();
                eighthStage.close();
            }
           
            eighthStage.close();

        });

        btnAddExpType.setOnAction(e -> {
            ninthPane.getChildren().clear();
            txtAddNewExpenseType.clear();
            ninthStage.setTitle("Add Expense Type");
            ninthPane.add(lblNewExpenseTypeHeader, 0, 0);
            ninthPane.add(lblAddNewExpenseType, 0, 1);
            ninthPane.add(txtAddNewExpenseType, 0, 2);
            ninthPane.add(btnConfirmNewExp, 0, 3);
            ninthStage.show();

        });
        
        btnConfirmNewExp.setOnAction(e -> {

            expCategory.add(txtAddNewExpenseType.getText());

            alert.setTitle("Success!");
            alert.setHeaderText("The Expense Type has been added!");
            alert.showAndWait();
            txtAddNewExpenseType.clear();

            ninthStage.close();

        });

        btnRemExp.setOnAction(e -> {
            if (Expense.obsExpense.size() > 1 || cmboExpense.getValue() == null) {	
                alert.setTitle("Error!");	
                alert.setHeaderText("No expense selected!");	
                alert.showAndWait();	
            }	
            else	
            {
                expVar = cmboExpense.getSelectionModel().getSelectedIndex();
                obsExpense.remove(obsExpense.get(expVar));
                expList.remove(expList.get(expVar));
                cmboExpense.getItems().remove(expVar);

                alert.setTitle("Success!");
                alert.setHeaderText("Expense has been Removed!");
                alert.showAndWait();
                thirdStage.close();
                expData.clear();
                for (Expense exp : expList) 
                {
                    expData.add(exp);
                }
            }
        });

        btnRemExpType.setOnAction(e -> {
            cmboExpCategory.getSelectionModel().clearSelection();
            ninthPane.getChildren().clear();
            ninthPane.add(lblRemoveExpTypeHeader, 0, 0);
            ninthPane.add(lblChooseExpType, 0, 1);
            ninthPane.add(cmboExpCategory, 0, 2);
            ninthPane.add(btnConfirmRemExpType, 0, 3);
            ninthStage.show();
        });

        btnConfirmRemExpType.setOnAction(e -> {
            if (cmboExpCategory.getValue() == null)	
            {	
                alert.setTitle("Error!");	
                alert.setHeaderText("No category selected!");	
                alert.showAndWait();	
            }	
            else	
            {
                expCatVar = cmboExpCategory.getSelectionModel().getSelectedIndex();
                expCategory.remove(expCatVar);
                alert.setTitle("Success!");
                alert.setHeaderText("The expense type has been removed!");
                alert.showAndWait();
                cmboExpCategory.getSelectionModel().clearSelection();
                ninthStage.close();
            }
        });
        
        storeTable.getColumns().addAll(tbStoreName, tbStoreAddress);
        menuAdmin.getMenus().get(1).getItems().get(3).setOnAction(e -> {
            // Store Management

            tenthPane.getChildren().clear();
            formPane.getChildren().clear();
            border.getChildren().clear();
            cmboStore.getSelectionModel().clearSelection();

            secondPane.setAlignment(Pos.TOP_CENTER);
            secondStage.setTitle("Store Management");

            btnAddStore.setMaxWidth(Double.MAX_VALUE);
            btnEditStore.setMaxWidth(Double.MAX_VALUE);
            btnRemoveStore.setMaxWidth(Double.MAX_VALUE);
            cmboStore.setMaxWidth(Double.MAX_VALUE);

            storeTable.setItems(storeData);
            storeTable.setMinWidth(tenthScene.getWidth());
            storeData.clear();
            for (Store s : storeList) {
                storeData.add(s);
            }   

            VBox storeVB = new VBox();
            storeVB.setSpacing(10);
            storeVB.setPadding(new Insets(10, 20, 10, 20));

            storeVB.getChildren().addAll(lblStoreMan, btnAddStore, cmboStore, btnEditStore, btnRemoveStore);
            border.setTop(menuAdmin);
            border.setLeft(storeVB);
            border.setCenter(storeTable);
            
            //tenthStage.show();

        });

           btnAddStore.setOnAction(e -> {

            tenthPane.getChildren().clear();
            tenthPane.add(lblStoreName, 0, 1);
            tenthPane.add(txtStoreName, 0, 2);
            tenthPane.add(lblStoreAddress, 1, 1);
            tenthPane.add(txtStoreAddress, 1, 2);
            tenthPane.add(btnConfirmAddStore, 0, 3);
            tenthStage.show();

        });

        btnConfirmAddStore.setOnAction(e -> {
            if (txtStoreName.getText().isEmpty() || txtStoreAddress.getText().isEmpty())
            {
                alert.setTitle("Error");
                alert.setHeaderText("All Fields Must Be Filled!");
                alert.showAndWait();
            }
            else{
            
            Store store6 = new Store(txtStoreName.getText(), txtStoreAddress.getText());
            obsStore.add(store6);
            storeList.add(store6);
            storeData.clear();
            for (Store s : storeList) {
                storeData.add(s);
            }

            alert.setTitle("Success!");
            alert.setHeaderText("The store has been added!");
            alert.showAndWait();
            txtStoreName.clear();
            txtStoreAddress.clear();
            //insert code to add employee to object

            tenthStage.close();
            for (int i = 0; i < storeList.size(); i++)
            {
                System.out.println(storeList.get(i).toString() + " storeList");
            }
            for (int i = 0; i < storeData.size(); i++)
            {
                System.out.println(storeData.get(i).toString() + " storeData");
            }
            /*for (int i = 0; i < obsStore.size(); i++)
            {
            System.out.println(obsStore.get(i).toString());
            }
            */
            }
        });

        btnEditStore.setOnAction(e -> {
            if (cmboStore.getValue() == null) 
            {
                alert.setTitle("Error!");
                alert.setHeaderText("Please select a store to edit!");
                alert.showAndWait();
            }
            else
            {
                tenthPane.getChildren().clear();
                tenthPane.add(lblStoreName, 0, 1);
                tenthPane.add(txtStoreName, 0, 2);
                tenthPane.add(lblStoreAddress, 1, 1);
                tenthPane.add(txtStoreAddress, 1, 2);
                tenthPane.add(btnConfirmEditStore, 0, 3);
                tenthStage.show();
                stC = storeList.get(cmboStore.getSelectionModel().getSelectedIndex());
                txtStoreName.setText(stC.getStoreName());
                txtStoreAddress.setText(stC.getStoreAddress());                
            }            
        });

        btnConfirmEditStore.setOnAction(e -> {
            if (txtStoreName.getText().isEmpty() || txtStoreAddress.getText().isEmpty())
            {
                alert.setTitle("Error");
                alert.setHeaderText("All Fields Must Be Filled!");
                alert.showAndWait();
            }
            else
            {            
                stC.setName(txtStoreName.getText());
                stC.setAddress(txtStoreAddress.getText());            
            for (int i = 0; i < storeList.size(); i++)
            {
                if (stC.getStoreID() == storeList.get(i).getStoreID())  
                {
                    storeList.remove(i);
                    obsStore.remove(i);
                    storeList.add(i, stC);
                    obsStore.add(i, stC);
                    cmboStore.getItems().remove(i);
                    cmboStore.getItems().add(i, stC.getStoreName());
                    stC = null;
                    break;
                }
            }

            storeData.clear();
            for (Store s : storeList) {
                storeData.add(s);
            }
            
            alert.setTitle("Success!");
            alert.setHeaderText("The store has been edited!");
            alert.showAndWait();
            tenthStage.close();
            }
        });

        btnRemoveStore.setOnAction(e -> {

            if (cmboStore.getValue() == null) {
                alert.setTitle("Error!");
                alert.setHeaderText("Please select a store to remove!");
                alert.showAndWait();
            }
            else
            {
            stC = storeList.get(cmboStore.getSelectionModel().getSelectedIndex());
            for (int i = 0; i < storeList.size(); i++)
            {
                if (stC.getStoreID() == storeList.get(i).getStoreID())  
            {
                    storeList.remove(i);
                    obsStore.remove(i);
                    cmboStore.getItems().remove(i);
                    stC = null;
                    break;
            }
            }    
            storeData.clear();
            for (Store s : storeList) {
                storeData.add(s);
            }    
                
            //obsStore.remove(obsStore.get(cmboStore.getSelectionModel().getSelectedIndex()));
            alert.setTitle("Success!");
            alert.setHeaderText("The store has been removed!");
            alert.showAndWait();
            //insert code to add employee to object
            }

        });


        
        
        btnViewAllStore.setOnAction(e -> {
         
        for (int i = 0; i < storeList.size(); i++)
        {
        System.out.println(storeList.get(i).toString());
                }
        storeData.clear();
        for (Store s: storeList)
        {
            storeData.add(s);
        }
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
            prodData.clear();
                for(Product prod: prodList)
                {
                    prodData.add(prod);
                }
            
        });
        
        menuAdmin.getMenus().get(1).getItems().get(4).setOnAction(e -> {
            // Discount Management
            discountPane.getChildren().clear();
            txtCDiscount.setText(String.valueOf(CustSale.getDiscPct()));
            discountStage.setTitle("Set Member Discount");
            discountStage.show();
            txtSetDiscount.clear();
            discountPane.setAlignment(Pos.TOP_CENTER);

            discountPane.add(lblMngDiscount, 0, 0, 3, 1);
            discountPane.add(lblCDiscount, 0, 1);
            discountPane.add(txtCDiscount, 0, 2);
            discountPane.add(lblSetDiscount, 1, 1);
            discountPane.add(txtSetDiscount, 1, 2);
            discountPane.add(btnSetDiscount, 1, 3);

            txtCDiscount.setEditable(false);

        });
        
        btnSetDiscount.setOnAction(e -> {

            CustSale.setDiscPct(Double.valueOf(txtSetDiscount.getText()));

            alert.setTitle("Success!");
            alert.setHeaderText("Discount Updated!");
            alert.showAndWait();
            
            txtSetDiscount.clear();
            discountStage.close();

        });
        
        menuAdmin.getMenus().get(2).getItems().get(0).setOnAction(e -> {
            // Payroll
            secondPane.getChildren().clear();
            border.getChildren().clear();
            secondStage.setTitle("Payroll");
            VBox vPay = new VBox();
            vPay.setSpacing(10);
            vPay.setPadding(new Insets(10, 20, 10 , 20));
            
            cmboEmp.setMaxWidth(Double.MAX_VALUE);
            btnPEEmp.setMaxWidth(Double.MAX_VALUE);
            btnSelect.setMaxWidth(Double.MAX_VALUE);
            showHours.setMaxWidth(Double.MAX_VALUE);
            addTimeSheet.setMaxWidth(Double.MAX_VALUE);
            btnSavePay.setMaxWidth(Double.MAX_VALUE);
            
                vPay.getChildren().addAll(lblPayroll, lblPEEmp, cmboEmp, btnPEEmp);
            
            border.setCenter(txtPayroll);
            border.setTop(menuAdmin);
            border.setLeft(vPay);
            
        });
        
        btnEnterTime.setOnAction(e -> {


            tempPay = null;
            cmboPeriod.getItems().clear();
            if(currentUser.payRoll.isEmpty())
            {
                currentUser.payRoll.add(new Payroll(currentUser));
                tempPay = currentUser.payRoll.get(0);
                cmboPeriod.getItems().add(tempPay.getPayrollPeriod());
                cmboPeriod.getSelectionModel().select(0);
            }
            else
            {
                for(int i = 0; i<currentUser.payRoll.size(); i++)
                {
                    cmboPeriod.getItems().add(i,currentUser.payRoll.get(i).getPayrollPeriod());
                }
                cmboPeriod.getSelectionModel().select(currentUser.payRoll.size()-1);
                tempPay = currentUser.payRoll.get(currentUser.payRoll.size()-1);
            }
            lblSun.setText(tempPay.schedule.get(0) + ": " + tempPay.scheduleDay.get(0));
            lblMon.setText(tempPay.schedule.get(1)+ ": " + tempPay.scheduleDay.get(1));
            lblTues.setText(tempPay.schedule.get(2)+ ": " + tempPay.scheduleDay.get(2));
            lblWed.setText(tempPay.schedule.get(3)+ ": " + tempPay.scheduleDay.get(3));
            lblThur.setText(tempPay.schedule.get(4)+ ": " + tempPay.scheduleDay.get(4));
            lblFri.setText(tempPay.schedule.get(5)+ ": " + tempPay.scheduleDay.get(5));
            lblSat.setText(tempPay.schedule.get(6)+ ": " + tempPay.scheduleDay.get(6));

            if(!tempPay.arrTimeIn.isEmpty())
            {
                try
                {
                    cmboSunTimeIn.getSelectionModel().select(timeI.get(tempPay.arrTimeIn.get(0)));
                    cmboMonTimeIn.getSelectionModel().select(timeI.get(tempPay.arrTimeIn.get(1)));
                    cmboTuesTimeIn.getSelectionModel().select(timeI.get(tempPay.arrTimeIn.get(2)));
                    cmboWedTimeIn.getSelectionModel().select(timeI.get(tempPay.arrTimeIn.get(3)));
                    cmboThurTimeIn.getSelectionModel().select(timeI.get(tempPay.arrTimeIn.get(4)));
                    cmboFriTimeIn.getSelectionModel().select(timeI.get(tempPay.arrTimeIn.get(5)));
                    cmboSatTimeIn.getSelectionModel().select(timeI.get(tempPay.arrTimeIn.get(6)));
                }
                catch(Exception ex)
                {

                }
            }
            if(!tempPay.arrTimeOut.isEmpty())
            {
                try
                {
                    
                    cmboSunTimeOut.getSelectionModel().select(timeO.get(tempPay.arrTimeOut.get(0)));
                    cmboMonTimeOut.getSelectionModel().select(timeO.get(tempPay.arrTimeOut.get(1)));
                    cmboTuesTimeOut.getSelectionModel().select(timeO.get(tempPay.arrTimeOut.get(2)));
                    cmboWedTimeOut.getSelectionModel().select(timeO.get(tempPay.arrTimeOut.get(3)));
                    cmboThurTimeOut.getSelectionModel().select(timeO.get(tempPay.arrTimeOut.get(4)));
                    cmboFriTimeOut.getSelectionModel().select(timeO.get(tempPay.arrTimeOut.get(5)));
                    cmboSatTimeOut.getSelectionModel().select(timeO.get(tempPay.arrTimeOut.get(6)));
                }
                catch(Exception ex)
                {

                }
            }
            thirdPane.getChildren().clear();
            thirdStage.setTitle("Enter Time");
            thirdStage.show();
            thirdPane.setAlignment(Pos.TOP_CENTER);

            thirdPane.add(cmboPeriod,0,0);
            thirdPane.add(btnSelect, 1,0);
            thirdPane.add(addTimeSheet, 2, 0,2,1);
            thirdPane.add(lblTimeIn, 1, 1);
            thirdPane.add(lblTimeOut, 2, 1);
            thirdPane.add(lblSun, 0, 2);
            thirdPane.add(cmboSunTimeIn, 1, 2);
            thirdPane.add(cmboSunTimeOut, 2, 2);
            thirdPane.add(lblMon, 0, 3);
            thirdPane.add(cmboMonTimeIn, 1, 3);
            thirdPane.add(cmboMonTimeOut, 2, 3);
            thirdPane.add(lblTues, 0, 4);
            thirdPane.add(cmboTuesTimeIn, 1, 4);
            thirdPane.add(cmboTuesTimeOut, 2, 4);
            thirdPane.add(lblWed, 0, 5);
            thirdPane.add(cmboWedTimeIn, 1, 5);
            thirdPane.add(cmboWedTimeOut, 2, 5);
            thirdPane.add(lblThur, 0, 6);
            thirdPane.add(cmboThurTimeIn, 1, 6);
            thirdPane.add(cmboThurTimeOut, 2, 6);
            thirdPane.add(lblFri, 0, 7);
            thirdPane.add(cmboFriTimeIn, 1, 7);
            thirdPane.add(cmboFriTimeOut, 2, 7);
            thirdPane.add(lblSat, 0, 8);
            thirdPane.add(cmboSatTimeIn, 1, 8);
            thirdPane.add(cmboSatTimeOut, 2, 8);
            thirdPane.add(btnEnterTimes, 1, 9);
            thirdPane.add(btnSavePay, 1,10);
            thirdPane.add(showHours,2,9);
            thirdPane.add(day1,3,2);
            thirdPane.add(day2,3,3);
            thirdPane.add(day3,3,4);
            thirdPane.add(day4,3,5);
            thirdPane.add(day5,3,6);
            thirdPane.add(day6,3,7);
            thirdPane.add(day7,3,8);


        });
            
            btnSelect.setOnAction(e-> {
            String select = (String)cmboPeriod.getValue();
            int checkEmptyIn = 0;
            int checkEmptyOut = 0;
            for(int i = 0; i<currentUser.payRoll.size(); i++)
            {
                if(currentUser.payRoll.get(i).getPayrollPeriod().equalsIgnoreCase(select))
                {
                    tempPay = currentUser.payRoll.get(i);
                    break;
                }
            }

            //Changing labels to match the week of payroll
            lblSun.setText(tempPay.schedule.get(0) + ": " + tempPay.scheduleDay.get(0));
            lblMon.setText(tempPay.schedule.get(1)+ ": " + tempPay.scheduleDay.get(1));
            lblTues.setText(tempPay.schedule.get(2)+ ": " + tempPay.scheduleDay.get(2));
            lblWed.setText(tempPay.schedule.get(3)+ ": " + tempPay.scheduleDay.get(3));
            lblThur.setText(tempPay.schedule.get(4)+ ": " + tempPay.scheduleDay.get(4));
            lblFri.setText(tempPay.schedule.get(5)+ ": " + tempPay.scheduleDay.get(5));
            lblSat.setText(tempPay.schedule.get(6)+ ": " + tempPay.scheduleDay.get(6));

            if(!tempPay.arrTimeIn.isEmpty())
            {
                try
                {
                    cmboSunTimeIn.getSelectionModel().select(timeI.get(tempPay.arrTimeIn.get(0)));
                    cmboMonTimeIn.getSelectionModel().select(timeI.get(tempPay.arrTimeIn.get(1)));
                    cmboTuesTimeIn.getSelectionModel().select(timeI.get(tempPay.arrTimeIn.get(2)));
                    cmboWedTimeIn.getSelectionModel().select(timeI.get(tempPay.arrTimeIn.get(3)));
                    cmboThurTimeIn.getSelectionModel().select(timeI.get(tempPay.arrTimeIn.get(4)));
                    cmboFriTimeIn.getSelectionModel().select(timeI.get(tempPay.arrTimeIn.get(5)));
                    cmboSatTimeIn.getSelectionModel().select(timeI.get(tempPay.arrTimeIn.get(6)));
                }
                catch(Exception ex)
                {
                    
                }
            }
            if(!tempPay.arrTimeOut.isEmpty())
            {
                try
                {
                    cmboSunTimeOut.getSelectionModel().select(timeO.get(tempPay.arrTimeOut.get(0)));
                    cmboMonTimeOut.getSelectionModel().select(timeO.get(tempPay.arrTimeOut.get(1)));
                    cmboTuesTimeOut.getSelectionModel().select(timeO.get(tempPay.arrTimeOut.get(2)));
                    cmboWedTimeOut.getSelectionModel().select(timeO.get(tempPay.arrTimeOut.get(3)));
                    cmboThurTimeOut.getSelectionModel().select(timeO.get(tempPay.arrTimeOut.get(4)));
                    cmboFriTimeOut.getSelectionModel().select(timeO.get(tempPay.arrTimeOut.get(5)));
                    cmboSatTimeOut.getSelectionModel().select(timeO.get(tempPay.arrTimeOut.get(6)));
                }
                catch(Exception ex)
                {

                }
            }

            thirdPane.getChildren().clear();
            thirdStage.setTitle("Enter Time");
            thirdStage.show();
            thirdPane.setAlignment(Pos.TOP_CENTER);
            
            thirdPane.add(cmboPeriod,0,0);
            thirdPane.add(btnSelect, 1,0);
            thirdPane.add(addTimeSheet, 2, 0,2,1);
            thirdPane.add(lblTimeIn, 1, 1);
            thirdPane.add(lblTimeOut, 2, 1);
            thirdPane.add(lblSun, 0, 2);
            thirdPane.add(cmboSunTimeIn, 1, 2);
            thirdPane.add(cmboSunTimeOut, 2, 2);
            thirdPane.add(lblMon, 0, 3);
            thirdPane.add(cmboMonTimeIn, 1, 3);
            thirdPane.add(cmboMonTimeOut, 2, 3);
            thirdPane.add(lblTues, 0, 4);
            thirdPane.add(cmboTuesTimeIn, 1, 4);
            thirdPane.add(cmboTuesTimeOut, 2, 4);
            thirdPane.add(lblWed, 0, 5);
            thirdPane.add(cmboWedTimeIn, 1, 5);
            thirdPane.add(cmboWedTimeOut, 2, 5);
            thirdPane.add(lblThur, 0, 6);
            thirdPane.add(cmboThurTimeIn, 1, 6);
            thirdPane.add(cmboThurTimeOut, 2, 6);
            thirdPane.add(lblFri, 0, 7);
            thirdPane.add(cmboFriTimeIn, 1, 7);
            thirdPane.add(cmboFriTimeOut, 2, 7);
            thirdPane.add(lblSat, 0, 8);
            thirdPane.add(cmboSatTimeIn, 1, 8);
            thirdPane.add(cmboSatTimeOut, 2, 8);
            thirdPane.add(btnEnterTimes, 1, 9);
            thirdPane.add(btnSavePay, 1,10);
            thirdPane.add(showHours,2,9);
            thirdPane.add(day1,3,2);
            thirdPane.add(day2,3,3);
            thirdPane.add(day3,3,4);
            thirdPane.add(day4,3,5);
            thirdPane.add(day5,3,6);
            thirdPane.add(day6,3,7);
            thirdPane.add(day7,3,8);


        });
           
        addTimeSheet.setOnAction(e -> {
            Payroll addPayroll = new Payroll(currentUser);
            if(tempPay.payrollPeriod.equalsIgnoreCase(addPayroll.payrollPeriod))
            {
                alert.setTitle("Error!");
                alert.setHeaderText("A Timesheet for this period already exists!");
                alert.showAndWait();
            }
            else
            {
                currentUser.payRoll.add(new Payroll(currentUser));
            }

        });
        showHours.setOnAction(e -> {
            try
            {
            day1.setText(Double.toString(tempPay.hoursWorked.get(0)));
            day2.setText(Double.toString(tempPay.hoursWorked.get(1)));
            day3.setText(Double.toString(tempPay.hoursWorked.get(2)));
            day4.setText(Double.toString(tempPay.hoursWorked.get(3)));
            day5.setText(Double.toString(tempPay.hoursWorked.get(4)));
            day6.setText(Double.toString(tempPay.hoursWorked.get(5)));
            day7.setText(Double.toString(tempPay.hoursWorked.get(6)));
            }
            catch(Exception ex)
            {   
            }
        });

        btnEnterTimes.setOnAction(e ->{
            if(tempPay.submitted == true)
            {
             alert.setTitle("Repeat Submission!");
             alert.setHeaderText("This timesheet has already been submitted");
             alert.showAndWait();   
            }
            else
            {
            double tI = 0;
            double tO = 0;
            tempPay.arrTimeIn.clear();
            tempPay.arrTimeOut.clear();
            tempPay.hoursWorked.clear();
            if(cmboSunTimeIn.getValue() != null && cmboSunTimeOut.getValue() != null)
            {               
               tempPay.arrTimeIn.add(0,cmboSunTimeIn.getSelectionModel().getSelectedIndex());
               tempPay.arrTimeOut.add(0,cmboSunTimeOut.getSelectionModel().getSelectedIndex());
               tI = hoursIn.get(cmboSunTimeIn.getSelectionModel().getSelectedIndex());
               tO = hoursOut.get(cmboSunTimeOut.getSelectionModel().getSelectedIndex());
               tempPay.hoursWorked.add(0,tO-tI);

            }
            if(cmboMonTimeIn.getValue() != null && cmboMonTimeOut.getValue() != null)
            {
               tempPay.arrTimeIn.add(1,cmboMonTimeIn.getSelectionModel().getSelectedIndex());
               tempPay.arrTimeOut.add(1,cmboMonTimeOut.getSelectionModel().getSelectedIndex());
               tI = hoursIn.get(cmboMonTimeIn.getSelectionModel().getSelectedIndex());
               tO = hoursOut.get(cmboMonTimeOut.getSelectionModel().getSelectedIndex());
               tempPay.hoursWorked.add(1,tO-tI);

            }

            if(cmboTuesTimeIn.getValue() != null && cmboTuesTimeOut.getValue() != null)
            {
               tempPay.arrTimeIn.add(2,cmboTuesTimeIn.getSelectionModel().getSelectedIndex());
               tempPay.arrTimeOut.add(2,cmboTuesTimeOut.getSelectionModel().getSelectedIndex());
               tI = hoursIn.get(cmboTuesTimeIn.getSelectionModel().getSelectedIndex());
               tO = hoursOut.get(cmboTuesTimeOut.getSelectionModel().getSelectedIndex());
               tempPay.hoursWorked.add(2,tO-tI);

            }

            if(cmboWedTimeIn.getValue() != null && cmboWedTimeOut.getValue() != null)
            {
               tempPay.arrTimeIn.add(3,cmboWedTimeIn.getSelectionModel().getSelectedIndex());
               tempPay.arrTimeOut.add(3,cmboWedTimeOut.getSelectionModel().getSelectedIndex());
               tI = hoursIn.get(cmboWedTimeIn.getSelectionModel().getSelectedIndex());
               tO = hoursOut.get(cmboWedTimeOut.getSelectionModel().getSelectedIndex());
               tempPay.hoursWorked.add(3,tO-tI);

            }

            if(cmboThurTimeIn.getValue() != null && cmboThurTimeOut.getValue() != null)
            {
               tempPay.arrTimeIn.add(4,cmboThurTimeIn.getSelectionModel().getSelectedIndex());
               tempPay.arrTimeOut.add(4,cmboThurTimeOut.getSelectionModel().getSelectedIndex());
               tI = hoursIn.get(cmboThurTimeIn.getSelectionModel().getSelectedIndex());
               tO = hoursOut.get(cmboThurTimeOut.getSelectionModel().getSelectedIndex());
               tempPay.hoursWorked.add(4,tO-tI);

            }

            if(cmboFriTimeIn.getValue() != null && cmboFriTimeOut.getValue() != null)
            {
               tempPay.arrTimeIn.add(5,cmboFriTimeIn.getSelectionModel().getSelectedIndex());
               tempPay.arrTimeOut.add(5,cmboFriTimeOut.getSelectionModel().getSelectedIndex());
               tI = hoursIn.get(cmboFriTimeIn.getSelectionModel().getSelectedIndex());
               tO = hoursOut.get(cmboFriTimeOut.getSelectionModel().getSelectedIndex());
               tempPay.hoursWorked.add(5,tO-tI);

            }

            if(cmboSatTimeIn.getValue() != null && cmboSatTimeOut.getValue() != null)
            {
               tempPay.arrTimeIn.add(6,cmboSatTimeIn.getSelectionModel().getSelectedIndex());
               tempPay.arrTimeOut.add(6,cmboSatTimeOut.getSelectionModel().getSelectedIndex());
               tI = hoursIn.get(cmboSatTimeIn.getSelectionModel().getSelectedIndex());
               tO = hoursOut.get(cmboSatTimeOut.getSelectionModel().getSelectedIndex());
               tempPay.hoursWorked.add(6,tO-tI);               
            }
            tempPay.updateWork();
            tempPay.submitted = true;
            alert.setTitle("Success!");
            alert.setHeaderText("The timesheet has been submitted!");
            alert.showAndWait();
            System.out.println(currentUser.getFirstName() + " has worked " + currentUser.getTimeWorked() + "hours during " + tempPay.getPayrollPeriod());
            tempPay = null;
            
            thirdStage.close();
            cmboSunTimeIn.getSelectionModel().clearSelection();
            cmboMonTimeIn.getSelectionModel().clearSelection();
            cmboTuesTimeIn.getSelectionModel().clearSelection();
            cmboWedTimeIn.getSelectionModel().clearSelection();
            cmboThurTimeIn.getSelectionModel().clearSelection();
            cmboFriTimeIn.getSelectionModel().clearSelection();
            cmboSatTimeIn.getSelectionModel().clearSelection();
            cmboSunTimeOut.getSelectionModel().clearSelection();
            cmboMonTimeOut.getSelectionModel().clearSelection();
            cmboTuesTimeOut.getSelectionModel().clearSelection();
            cmboWedTimeOut.getSelectionModel().clearSelection();
            cmboThurTimeOut.getSelectionModel().clearSelection();
            cmboFriTimeOut.getSelectionModel().clearSelection();
            cmboSatTimeOut.getSelectionModel().clearSelection();
            }
        });
        btnSavePay.setOnAction(e -> {
            double tI = 0;
            double tO = 0;
            tempPay.arrTimeIn.clear();
            tempPay.arrTimeOut.clear();
            tempPay.hoursWorked.clear();
            if(cmboSunTimeIn.getValue() != null && cmboSunTimeOut.getValue() != null)
            {               
               tempPay.arrTimeIn.add(0,cmboSunTimeIn.getSelectionModel().getSelectedIndex());
               tempPay.arrTimeOut.add(0,cmboSunTimeOut.getSelectionModel().getSelectedIndex());
               tI = hoursIn.get(cmboSunTimeIn.getSelectionModel().getSelectedIndex());
               tO = hoursOut.get(cmboSunTimeOut.getSelectionModel().getSelectedIndex());
               tempPay.hoursWorked.add(0,tO-tI);

            }
            if(cmboMonTimeIn.getValue() != null && cmboMonTimeOut.getValue() != null)
            {
               tempPay.arrTimeIn.add(1,cmboMonTimeIn.getSelectionModel().getSelectedIndex());
               tempPay.arrTimeOut.add(1,cmboMonTimeOut.getSelectionModel().getSelectedIndex());
               tI = hoursIn.get(cmboMonTimeIn.getSelectionModel().getSelectedIndex());
               tO = hoursOut.get(cmboMonTimeOut.getSelectionModel().getSelectedIndex());
               tempPay.hoursWorked.add(1,tO-tI);

            }

            if(cmboTuesTimeIn.getValue() != null && cmboTuesTimeOut.getValue() != null)
            {
               tempPay.arrTimeIn.add(2,cmboTuesTimeIn.getSelectionModel().getSelectedIndex());
               tempPay.arrTimeOut.add(2,cmboTuesTimeOut.getSelectionModel().getSelectedIndex());
               tI = hoursIn.get(cmboTuesTimeIn.getSelectionModel().getSelectedIndex());
               tO = hoursOut.get(cmboTuesTimeOut.getSelectionModel().getSelectedIndex());
               tempPay.hoursWorked.add(2,tO-tI);

            }

            if(cmboWedTimeIn.getValue() != null && cmboWedTimeOut.getValue() != null)
            {
               tempPay.arrTimeIn.add(3,cmboWedTimeIn.getSelectionModel().getSelectedIndex());
               tempPay.arrTimeOut.add(3,cmboWedTimeOut.getSelectionModel().getSelectedIndex());
               tI = hoursIn.get(cmboWedTimeIn.getSelectionModel().getSelectedIndex());
               tO = hoursOut.get(cmboWedTimeOut.getSelectionModel().getSelectedIndex());
               tempPay.hoursWorked.add(3,tO-tI);

            }

            if(cmboThurTimeIn.getValue() != null && cmboThurTimeOut.getValue() != null)
            {
               tempPay.arrTimeIn.add(4,cmboThurTimeIn.getSelectionModel().getSelectedIndex());
               tempPay.arrTimeOut.add(4,cmboThurTimeOut.getSelectionModel().getSelectedIndex());
               tI = hoursIn.get(cmboThurTimeIn.getSelectionModel().getSelectedIndex());
               tO = hoursOut.get(cmboThurTimeOut.getSelectionModel().getSelectedIndex());
               tempPay.hoursWorked.add(4,tO-tI);

            }

            if(cmboFriTimeIn.getValue() != null && cmboFriTimeOut.getValue() != null)
            {
               tempPay.arrTimeIn.add(5,cmboFriTimeIn.getSelectionModel().getSelectedIndex());
               tempPay.arrTimeOut.add(5,cmboFriTimeOut.getSelectionModel().getSelectedIndex());
               tI = hoursIn.get(cmboFriTimeIn.getSelectionModel().getSelectedIndex());
               tO = hoursOut.get(cmboFriTimeOut.getSelectionModel().getSelectedIndex());
               tempPay.hoursWorked.add(5,tO-tI);

            }

            if(cmboSatTimeIn.getValue() != null && cmboSatTimeOut.getValue() != null)
            {
               tempPay.arrTimeIn.add(6,cmboSatTimeIn.getSelectionModel().getSelectedIndex());
               tempPay.arrTimeOut.add(6,cmboSatTimeOut.getSelectionModel().getSelectedIndex());
               tI = hoursIn.get(cmboSatTimeIn.getSelectionModel().getSelectedIndex());
               tO = hoursOut.get(cmboSatTimeOut.getSelectionModel().getSelectedIndex());
               tempPay.hoursWorked.add(6,tO-tI);               
            }
            tempPay.updateWork();
            });
        
        
        menuAdmin.getMenus().get(3).getItems().get(0).setOnAction(e -> {
            // Point of Sales Report
            secondPane.getChildren().clear();
            formPane.getChildren().clear();
            border.getChildren().clear();
            cmboType.getSelectionModel().clearSelection();
            cmboStore.getSelectionModel().clearSelection();
            
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
            
            String output = "";
           
            output += "Point of Sales Report:";
            output += "\n========================================================\n";

            txtPOS.setText(output);

        });
        
        btnPOSDisplay.setOnAction(e -> {
           
            currentDate = LocalDate.now();
            firstDay = currentDate.with(WeekFields.of(Locale.US).dayOfWeek(),1);
            firstMonth = currentDate.with(TemporalAdjusters.firstDayOfMonth());
            firstYear = currentDate.with(TemporalAdjusters.firstDayOfYear());
            String currentCat = "";
            
            
            monthNames.add("January");
            monthNames.add("February");
            monthNames.add("March");
            monthNames.add("April");
            monthNames.add("May");
            monthNames.add("June");
            monthNames.add("July");
            monthNames.add("August");
            monthNames.add("September");
            monthNames.add("October");
            monthNames.add("November");
            monthNames.add("December");
            
            reportSales.clear();
            catSales.clear();
            catMoney.clear();
            for(int i =0; i<categoryList.size(); i++)
                {
                    catSales.add(0);
                } 
            //Create Array for Week-Daily
           
            if(cmboType.getValue() == null || cmboStore.getValue() == null)
            {
                alert.setTitle("Error!");
                alert.setHeaderText("Error! Please Enter Data!");
                alert.showAndWait();
            }
            else
            {
            
            String output = "";
           
            output += "Point of Sales Report:";
            output += "\n========================================================\n";
            int type = 0;
            type = cmboType.getSelectionModel().getSelectedIndex();
            int storeloc = 0;
            String saleStore = "";
            String storeName = "";
            storeloc = cmboStore.getSelectionModel().getSelectedIndex();
            storeName = storeList.get(storeloc).getStoreName();
            int totalSales = 0;
            double totalMoney = 0.0;
            
            System.out.println(saleList.get(0).getStoreName() + " vs " + storeName);
            
            if(type == 0)
            {
                output += "Week Daily Total Sales\n";
                output += "Store: " + cmboStore.getValue().toString();
                output += "\n------------------------------------------------------\n";             
            
            for(int b = 0; b<7; b++)
            {
                endDate = firstDay.plusDays(1);
                
                for(int i = 0; i<saleList.size(); i++)
                {
                    productDate = LocalDate.parse(saleList.get(i).getSaleDate(), DateTimeFormatter.ofPattern("MM-dd-yyyy"));
                    saleStore = saleList.get(i).getStoreName();
                    
                    if( productDate.isBefore(endDate)&& productDate.isEqual(firstDay)&& saleStore.equals(storeName))
                    {   //yyyy-mm-dd
                         reportSales.add(saleList.get(i));
                         totalSales += 1;
                         totalMoney += saleList.get(i).getSubtotal();
                    }  
                }
                output +=( "Total Number of Sales on " + firstDay.format(DateTimeFormatter.ISO_DATE) + ": " + totalSales + " for a total revenue of $" + totalMoney + "\n");
                firstDay = endDate;
                totalSales = 0;
                totalMoney = 0;
            }
                totalMoney = 0;                
                output += "\n------------------------------------------------------\n";
                output += "Product Categories Sold\n";
                for(int i = 0; i < categoryList.size(); i++)
                    {                        
                        for(int k = 0; k<reportSales.size(); k++)
                        {
                           for(int m = 0; m<reportSales.get(k).saleProducts.size(); m++)
                           {
                              currentCat = reportSales.get(k).saleProducts.get(m).getCategory();
                              if(currentCat.equalsIgnoreCase(categoryList.get(i)))
                              {
                               totalMoney += reportSales.get(k).saleProducts.get(m).getPrice();
                               int temp = catSales.get(i);
                               temp += 1;
                               catSales.remove(i);
                               catSales.add(i, temp);
                              }
                           }                       
                        }
                        catMoney.add(totalMoney);
                        totalMoney = 0;
                        output += categoryList.get(i) + ": " + catSales.get(i) + ", Sales: " + catMoney.get(i) + "\n";
                    }
            }   
            else if (type == 1)
            {
                output += "Month Weekly Total Sales\n";
                output += "Store: " + cmboStore.getValue().toString();
                output += "\n------------------------------------------------------\n";
                for(int b = 0; b<6; b++)
                {
                    endDate = firstMonth.plusWeeks(1);
                    for(int i = 0; i<saleList.size(); i++)
                    {
                        productDate = LocalDate.parse(saleList.get(i).getSaleDate(), DateTimeFormatter.ofPattern("MM-dd-yyyy"));
                        saleStore = saleList.get(i).getStoreName();

                        if( productDate.isBefore(endDate)&& (productDate.isAfter(firstMonth)|| productDate.isEqual(firstMonth))&& saleStore.equals(storeName))
                        {   //yyyy-mm-dd
                             reportSales.add(saleList.get(i));
                             totalSales++;
                             totalMoney += saleList.get(i).getSubtotal();
                        }  
                    }
                    output +=( "Total Number of Sales for " + firstMonth.format(DateTimeFormatter.ISO_DATE) + " is: " + totalSales + " for a total revenue of $" + totalMoney + "\n");
                    firstMonth = endDate;
                    totalSales = 0;
                    totalMoney = 0;
                    if(firstMonth.getMonthValue()!= currentDate.getMonthValue())
                    {
                        break;
                    }
                }
                totalMoney = 0;
                output += "\n------------------------------------------------------\n";
                output += "Product Categories Sold\n";
                for(int i = 0; i < categoryList.size(); i++)
                    {                        
                        for(int k = 0; k<reportSales.size(); k++)
                        {
                           for(int m = 0; m<reportSales.get(k).saleProducts.size(); m++)
                           {
                              currentCat = reportSales.get(k).saleProducts.get(m).getCategory();
                              if(currentCat.equalsIgnoreCase(categoryList.get(i)))
                              {
                               totalMoney += reportSales.get(k).saleProducts.get(m).getPrice();
                               int temp = catSales.get(i);
                               temp += 1;
                               catSales.remove(i);
                               catSales.add(i, temp);
                              }
                           }                       
                        }
                        catMoney.add(totalMoney);
                        totalMoney = 0;
                        output += categoryList.get(i) + ": " + catSales.get(i) + ", Sales: " + catMoney.get(i) + "\n";
                    }
            }  
            else if (type == 2)
            {
                output += "Year Monthly Total Sales\n";
                output += "Store: " + cmboStore.getValue().toString();
                output += "\n------------------------------------------------------\n";                
                for(int i =0; i<categoryList.size(); i++)
                {
                    catSales.add(0);
                }  
                for(int b = 0; b<12; b++)
                {
                    endDate = firstYear.plusMonths(1);
                    
                    for(int i = 0; i<saleList.size(); i++)
                    {
                        productDate = LocalDate.parse(saleList.get(i).getSaleDate(), DateTimeFormatter.ofPattern("MM-dd-yyyy"));
                        saleStore = saleList.get(i).getStoreName();

                        if( productDate.isBefore(endDate)&& (productDate.isAfter(firstYear)|| productDate.isEqual(firstYear)) && saleStore.equals(storeName))
                        {   //yyyy-mm-dd
                             reportSales.add(saleList.get(i));
                             totalSales++;
                             totalMoney += saleList.get(i).getSubtotal();
                        }  
                    }
                    output += ("Total Number of Sales for the month of: " + monthNames.get(b) + ": " + totalSales + " for a total revenue of $" + totalMoney + "\n");
                    firstYear = endDate;
                    totalSales = 0;
                    totalMoney = 0;
                    
                }
                totalMoney = 0;
                output += "\n------------------------------------------------------\n";
                output += "Product Categories Sold\n";
                for(int i = 0; i < categoryList.size(); i++)
                    {                        
                        for(int k = 0; k<reportSales.size(); k++)
                        {
                           for(int m = 0; m<reportSales.get(k).saleProducts.size(); m++)
                           {
                              currentCat = reportSales.get(k).saleProducts.get(m).getCategory();
                              if(currentCat.equalsIgnoreCase(categoryList.get(i)))
                              {
                               totalMoney += reportSales.get(k).saleProducts.get(m).getPrice();
                               int temp = catSales.get(i);
                               temp += 1;
                               catSales.remove(i);
                               catSales.add(i, temp);
                              }

                           }                       
                        }
                       // System.out.println(totalMoney + " given " + catSales.get(i) + " for" + categoryList.get(i));
                        catMoney.add(totalMoney);
                        totalMoney = 0;
                    }
            }
            }
                    }); 
       btnPOSDisplay.setOnAction(e -> {
           
            currentDate = LocalDate.now();
            firstDay = currentDate.with(WeekFields.of(Locale.US).dayOfWeek(),1);
            firstMonth = currentDate.with(TemporalAdjusters.firstDayOfMonth());
            firstYear = currentDate.with(TemporalAdjusters.firstDayOfYear());
            String currentCat = "";
            
            
            monthNames.add("January");
            monthNames.add("February");
            monthNames.add("March");
            monthNames.add("April");
            monthNames.add("May");
            monthNames.add("June");
            monthNames.add("July");
            monthNames.add("August");
            monthNames.add("September");
            monthNames.add("October");
            monthNames.add("November");
            monthNames.add("December");
            
            reportSales.clear();
            catSales.clear();
            catMoney.clear();
            for(int i =0; i<categoryList.size(); i++)
                {
                    catSales.add(0);
                } 
            //Create Array for Week-Daily
           
            if(cmboType.getValue() == null || cmboStore.getValue() == null)
            {
                alert.setTitle("Error!");
                alert.setHeaderText("Error! Please Enter Data!");
                alert.showAndWait();
            }
            else if (saleList.isEmpty())
            {
                        String output = "";
                        output += "Point of Sales Report:";
                        output += "\n========================================================\n";
                        output += "No Sales For This Time Period";
                        txtPOS.setText(output);

            }
            else
            {
            
            String output = "";
           
            output += "Point of Sales Report:";
            output += "\n========================================================\n";
            int type = 0;
            type = cmboType.getSelectionModel().getSelectedIndex();
            int storeloc = 0;
            String saleStore = "";
            String storeName = "";
            storeloc = cmboStore.getSelectionModel().getSelectedIndex();
            storeName = storeList.get(storeloc).getStoreName();
            int totalSales = 0;
            double totalMoney = 0.0;
            
            System.out.println(saleList.get(0).getStoreName() + " vs " + storeName);

            if(type == 0)
            {
                output += "Week Daily Total Sales\n";
                output += "Store: " + cmboStore.getValue().toString();
                output += "\n------------------------------------------------------\n";             
            
            for(int b = 0; b<7; b++)
            {
                endDate = firstDay.plusDays(1);
                
                for(int i = 0; i<saleList.size(); i++)
                {
                    productDate = LocalDate.parse(saleList.get(i).getSaleDate(), DateTimeFormatter.ofPattern("MM-dd-yyyy"));
                    saleStore = saleList.get(i).getStoreName();
                    
                    if( productDate.isBefore(endDate)&& productDate.isEqual(firstDay)&& saleStore.equals(storeName))
                    {   //yyyy-mm-dd
                         reportSales.add(saleList.get(i));
                         totalSales += 1;
                         totalMoney += saleList.get(i).getSubtotal();
                    }  
                }
                output +=( "Total Number of Sales on " + firstDay.format(DateTimeFormatter.ISO_DATE) + ": " + totalSales + " for a total revenue of $" + totalMoney + "\n");
                firstDay = endDate;
                totalSales = 0;
                totalMoney = 0;
            }
                totalMoney = 0;                
                output += "\n------------------------------------------------------\n";
                output += "Product Categories Sold\n";
                for(int i = 0; i < categoryList.size(); i++)
                    {                        
                        for(int k = 0; k<reportSales.size(); k++)
                        {
                           for(int m = 0; m<reportSales.get(k).saleProducts.size(); m++)
                           {
                              currentCat = reportSales.get(k).saleProducts.get(m).getCategory();
                              if(currentCat.equalsIgnoreCase(categoryList.get(i)))
                              {
                               totalMoney += reportSales.get(k).saleProducts.get(m).getPrice();
                               int temp = catSales.get(i);
                               temp += 1;
                               catSales.remove(i);
                               catSales.add(i, temp);
                              }
                           }                       
                        }
                        catMoney.add(totalMoney);
                        totalMoney = 0;
                        output += categoryList.get(i) + ": " + catSales.get(i) + ", Sales: " + catMoney.get(i) + "\n";
                    }
            }   
            else if (type == 1)
            {
                output += "Month Weekly Total Sales\n";
                output += "Store: " + cmboStore.getValue().toString();
                output += "\n------------------------------------------------------\n";
                for(int b = 0; b<6; b++)
                {
                    endDate = firstMonth.plusWeeks(1);
                    for(int i = 0; i<saleList.size(); i++)
                    {
                        productDate = LocalDate.parse(saleList.get(i).getSaleDate(), DateTimeFormatter.ofPattern("MM-dd-yyyy"));
                        saleStore = saleList.get(i).getStoreName();

                        if( productDate.isBefore(endDate)&& (productDate.isAfter(firstMonth)|| productDate.isEqual(firstMonth))&& saleStore.equals(storeName))
                        {   //yyyy-mm-dd
                             reportSales.add(saleList.get(i));
                             totalSales++;
                             totalMoney += saleList.get(i).getSubtotal();
                        }  
                    }
                    output +=( "Total Number of Sales for " + firstMonth.format(DateTimeFormatter.ISO_DATE) + " is: " + totalSales + " for a total revenue of $" + totalMoney + "\n");
                    firstMonth = endDate;
                    totalSales = 0;
                    totalMoney = 0;
                    if(firstMonth.getMonthValue()!= currentDate.getMonthValue())
                    {
                        break;
                    }
                }
                totalMoney = 0;
                output += "\n------------------------------------------------------\n";
                output += "Product Categories Sold\n";
                for(int i = 0; i < categoryList.size(); i++)
                    {                        
                        for(int k = 0; k<reportSales.size(); k++)
                        {
                           for(int m = 0; m<reportSales.get(k).saleProducts.size(); m++)
                           {
                              currentCat = reportSales.get(k).saleProducts.get(m).getCategory();
                              if(currentCat.equalsIgnoreCase(categoryList.get(i)))
                              {
                               totalMoney += reportSales.get(k).saleProducts.get(m).getPrice();
                               int temp = catSales.get(i);
                               temp += 1;
                               catSales.remove(i);
                               catSales.add(i, temp);
                              }
                           }                       
                        }
                        catMoney.add(totalMoney);
                        totalMoney = 0;
                        output += categoryList.get(i) + ": " + catSales.get(i) + ", Sales: " + catMoney.get(i) + "\n";
                    }
            }  
            else if (type == 2)
            {
                output += "Year Monthly Total Sales\n";
                output += "Store: " + cmboStore.getValue().toString();
                output += "\n------------------------------------------------------\n";                
                for(int i =0; i<categoryList.size(); i++)
                {
                    catSales.add(0);
                }  
                for(int b = 0; b<12; b++)
                {
                    endDate = firstYear.plusMonths(1);
                    
                    for(int i = 0; i<saleList.size(); i++)
                    {
                        productDate = LocalDate.parse(saleList.get(i).getSaleDate(), DateTimeFormatter.ofPattern("MM-dd-yyyy"));
                        saleStore = saleList.get(i).getStoreName();

                        if( productDate.isBefore(endDate)&& (productDate.isAfter(firstYear)|| productDate.isEqual(firstYear)) && saleStore.equals(storeName))
                        {   //yyyy-mm-dd
                             reportSales.add(saleList.get(i));
                             totalSales++;
                             totalMoney += saleList.get(i).getSubtotal();
                        }  
                    }
                    output += ("Total Number of Sales for the month of: " + monthNames.get(b) + ": " + totalSales + " for a total revenue of $" + totalMoney + "\n");
                    firstYear = endDate;
                    totalSales = 0;
                    totalMoney = 0;
                    
                }
                totalMoney = 0;
                output += "\n------------------------------------------------------\n";
                output += "Product Categories Sold\n";
                for(int i = 0; i < categoryList.size(); i++)
                    {                        
                        for(int k = 0; k<reportSales.size(); k++)
                        {
                           for(int m = 0; m<reportSales.get(k).saleProducts.size(); m++)
                           {
                              currentCat = reportSales.get(k).saleProducts.get(m).getCategory();
                              if(currentCat.equalsIgnoreCase(categoryList.get(i)))
                              {
                               totalMoney += reportSales.get(k).saleProducts.get(m).getPrice();
                               int temp = catSales.get(i);
                               temp += 1;
                               catSales.remove(i);
                               catSales.add(i, temp);
                              }

                           }                       
                        }
                       // System.out.println(totalMoney + " given " + catSales.get(i) + " for" + categoryList.get(i));
                        catMoney.add(totalMoney);
                        totalMoney = 0;
                        output += categoryList.get(i) + ": " + catSales.get(i) + ", Sales: " + catMoney.get(i) + "\n";
                    }
            }
            else if (type == 3)
            {
                
                output += "Total Sales Today\n";
                output += "Store: " + cmboStore.getValue().toString();
                output += "\n------------------------------------------------------\n";                
                 
                
                for(int i = 0; i<saleList.size(); i++)
                {
                     productDate = LocalDate.parse(saleList.get(i).getSaleDate(), DateTimeFormatter.ofPattern("MM-dd-yyyy"));
                     saleStore = saleList.get(i).getStoreName();
                    if(productDate.isEqual(currentDate) && saleStore.equalsIgnoreCase(storeName))
                    {   //yyyy-mm-dd
                         reportSales.add(saleList.get(i));
                         totalSales++;
                         totalMoney += saleList.get(i).getSubtotal();
                    }  
                }
               // System.out.println(reportSales.get(0).toStringNoMem());
                output += "Total Number of Sales Today: " + totalSales + " for a total revenue of $" + totalMoney + "\n";
                totalMoney = 0;
                output += "\n------------------------------------------------------\n";
                output += "Product Categories Sold\n";
                System.out.println(reportSales.get(0).saleProducts.get(0).toString());
                for(int i = 0; i < categoryList.size(); i++)
                    {                        
                        for(int k = 0; k<reportSales.size(); k++)
                        {
                           for(int m = 0; m<reportSales.get(k).saleProducts.size(); m++)
                           {
                              currentCat = reportSales.get(k).saleProducts.get(m).getCategory();
                              if(currentCat.equalsIgnoreCase(categoryList.get(i)))
                              {
                               totalMoney += reportSales.get(k).saleProducts.get(m).getPrice();
                               int temp = catSales.get(i);
                               temp += 1;
                               catSales.remove(i);
                               catSales.add(i, temp);
                              }
                           }                       
                        }
                        catMoney.add(totalMoney);
                        totalMoney = 0;
                        output += categoryList.get(i) + ": " + catSales.get(i) + ", Sales: " + catMoney.get(i) + "\n";
                    }                    
            }

            //create loop or whatever to print out PvE based on store
            txtPOS.setText(output);
            alert.setTitle("Success!");
            alert.setHeaderText("Data Displayed!");
            alert.showAndWait();
            
            cmboType.getSelectionModel().clearSelection();
            cmboStore.getSelectionModel().clearSelection();
            }
    
        });
        
        menuAdmin.getMenus().get(3).getItems().get(1).setOnAction(e -> {
            // Employee Report
            secondPane.getChildren().clear();
            formPane.getChildren().clear();
            border.getChildren().clear();
            cmboStore.getSelectionModel().clearSelection();
            
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
            
            String output = "";
           
            output += "Employee Report:";
            output += "\n========================================================\n";

            //create loop or whatever to print out Employee based on store
            txtEmp.setText(output);

            
        });
        
        btnEmpDisplay.setOnAction(e -> {
            if(cmboStore.getValue() == null)
            {
                alert.setTitle("Error!");
                alert.setHeaderText("Error! Please Enter Data!");
                alert.showAndWait();
            }
            else
            {
                String output = "";
                DecimalFormat decimal = new DecimalFormat("0.00");
                output += "Employee Report:";
                output += "\n========================================================\n";
                output += "Store: " + cmboStore.getValue().toString(); //use store var
                output += "\n------------------------------------------------------\n";
                for(int i = 0; i<empList.size(); i++)
                { //get the for loop to loop through only certain stores
                    if(cmboStore.getValue().toString() == empList.get(i).getStoreLoc())
                    {
                        output += "Employee ID#: " + empList.get(i).getEmployeeID();
                        output += ", Name: " + empList.get(i).getFirstName() + " " + empList.get(i).getLastName();
                        output += ", Job Title: " + empList.get(i).getJobTitle();
                        output += ", Salary: $" + decimal.format(empList.get(i).getSalary());
                        output += ", Hours Worked: " + empList.get(i).getTimeWorked();
                        //wages earned instead of salary
                        // how many sales rang up if on POS

                        output += "\n";
                    }
                //NEED HOURS WORKED (per week)
                // WAGES EARNED
                // for those on POS, how many sales rang up

                //create loop or whatever to print out Employee based on store
                }
                txtEmp.setText(output);


                cmboStore.getSelectionModel().clearSelection();
            }
        });
        
        menuAdmin.getMenus().get(3).getItems().get(2).setOnAction(e -> {
           // Monthly Expense Report
           secondPane.getChildren().clear();
           formPane.getChildren().clear();
           border.getChildren().clear();
           cmboYear.getSelectionModel().clearSelection();
           cmboMonth.getSelectionModel().clearSelection();
           
           secondPane.setAlignment(Pos.TOP_CENTER);
           secondStage.setTitle("Monthly Expense Report");
           cmboYear.setMaxWidth(Double.MAX_VALUE);
           cmboMonth.setMaxWidth(Double.MAX_VALUE);
           btnMonthDisplay.setMaxWidth(Double.MAX_VALUE);
           
           VBox vMonth = new VBox();
           vMonth.setSpacing(10);
           vMonth.setPadding(new Insets(10, 20, 10 , 20));
            
           vMonth.getChildren().addAll(lblMonthRep, lblReport, lblMonth, cmboMonth, lblYear, cmboYear, btnMonthDisplay);
           border.setLeft(vMonth);
           border.setTop(menuAdmin);
           border.setCenter(txtMonth);
           
           String output = "";
           
           output += "Monthly Expense Report:";
           output += "\n========================================================\n";
           
           txtMonth.setText(output);
           
           
        });
        
        btnMonthDisplay.setOnAction(e -> {
            txtMonth.clear();
            if(cmboMonth.getValue() == null || cmboYear.getValue() == null)
            {
                alert.setTitle("Error!");
                alert.setHeaderText("Error! Please Enter Data!");
                alert.showAndWait();
            }
            else
            {
                String output = "";

                ArrayList <Expense> expppp = new ArrayList<>();
                output += "Monthly Expense Report:";
                output += "\n========================================================\n";
                output += "Month: " + cmboMonth.getValue().toString();
                output += "\nYear: " + cmboYear.getValue().toString();
                output += "\n------------------------------------------------------\n";
                //create loop or whatever to print out Montly Expense based on store
                for(int i = 0; i < expList.size(); i++)
                {
                    //DO SOMETHING WITH COMPARING MONTHS
                    
                    // "MM-dd-yyyy"
                    String currentMonth = expList.get(i).getDate().substring(0,2);
                    String currentYear = expList.get(i).getDate().substring(6,expList.get(i).getDate().length());
                    if(currentMonth.equalsIgnoreCase(mDate) && (currentYear.equalsIgnoreCase(yDate)))
                    {
                        output += "Category: " + expList.get(i).getCategory();
                        output += ", Description: " + expList.get(i).getDescription();
                        if (expList.get(i).getStatus().equalsIgnoreCase("Paid"))
                        {
                        output += ", Status: " + expList.get(i).getStatus();
                        output += ", Total Paid: " + expList.get(i).getTotalAmount();
                        }
                        else if (expList.get(i).getStatus().equalsIgnoreCase("Ongoing"))
                        {
                        output += ", Status: " + expList.get(i).getStatus();
                        output += ", Amount Paid: " + expList.get(i).getAmountPaid();
                        output += ", Amount Total: " + expList.get(i).getTotalAmount();
                        }

                        output += "\n";
                    }
                }
                txtMonth.setText(output);
                alert.setTitle("Success!");
                alert.setHeaderText("Data Displayed!");
                alert.showAndWait();


                cmboMonth.getSelectionModel().clearSelection();
                cmboYear.getSelectionModel().clearSelection();
            }
        });
           
        
            tbPvE.getTabs().add(tabPText);
            tbPvE.getTabs().add(tabPBar);
            
        menuAdmin.getMenus().get(3).getItems().get(3).setOnAction(e -> {
            // Profit v. Expense Report
            secondPane.getChildren().clear();
            formPane.getChildren().clear();
            textPane.getChildren().clear();
            cmboYear.getSelectionModel().clearSelection();
            
            secondPane.setAlignment(Pos.TOP_CENTER);
            textPane.setAlignment(Pos.TOP_LEFT);
            textPane.setVgap(10);
            secondStage.setTitle("Profit v. Expense Report");

           
            tbPvE.setMinWidth(secondScene.getWidth());
           
            cmboYear.setMaxWidth(Double.MAX_VALUE);
            btnPvEDisplay.setMaxWidth(Double.MAX_VALUE);


            VBox vPVE = new VBox();
            vPVE.setSpacing(10);
            vPVE.setPadding(new Insets(10, 20, 10 , 20));
            vPVE.getChildren().addAll(lblPvE, lblReport, lblYear, cmboYear, btnPvEDisplay);
           
            border.setTop(menuAdmin);
            border.setLeft(vPVE);
            border.setCenter(tbPvE);
           
            String output = "";
           
            output += "Profit v. Expense Report:";
            output += "\n========================================================\n";
           
            txtPvE.setText(output);
           
            tabPText.setContent(txtPvE);
        });
        
 btnPvEDisplay.setOnAction(e -> { //only expenses right now, figuring out profit is the main issue
            if(cmboYear.getValue() == null)
            {
                alert.setTitle("Error!");
                alert.setHeaderText("Error! Please Enter Data!");
                alert.showAndWait();
            }
            else
            {

                String output = "";
            double expTotal = 0.0;
            output += "Profit v. Expense Report:";
            output += "\n========================================================\n";
            output += "Year: " + cmboYear.getValue().toString();
            output += "------------------------------------------------------\n";
            for (int i = 0; i < obsStore.size(); i++)
            {
                output += "\n";
                output += "\n" + storeList.get(i).getStoreName() + " : ";
                output += "\n";
                
            for (int j = 0; j < expCategory.size(); j++)
           {
               output += "\n";
               output += expCategory.get(j) + " : ";
               for (int k = 0; k < obsExpense.size(); k++)
               {
                   if (obsExpense.get(k).getCategory().equalsIgnoreCase(expCategory.get(j)))
                   {
                       expTotal += obsExpense.get(k).getTotalAmount();
                       output += expTotal;
                       
                   }            
               }        

           }
//                output += "Total Expense:" + expTotal;
//               expTotal = 0.0; incorrect
                
                //create loop or whatever to print out PvE based on store and year


                txtPvE.setText(output);
                alert.setTitle("Success!");
                alert.setHeaderText("Data Displayed!");
                alert.showAndWait();

                tabPText.setContent(txtPvE);

                cmboStore.getSelectionModel().clearSelection();
                cmboYear.getSelectionModel().clearSelection();
            }
            }
        });
    }
    
    
        //get database connection method (useable if oracle is on your computer
    /*
    public static Connection getDBConnection(String url, String user, String pass) throws SQLException
    {
        OracleDataSource ds = new OracleDataSource();
        ds.SETURL(url);
        return ds.getConnection(user, pass);
    }
*/
    
    public static void main(String[] args) {
        launch(args);
    }
    
      @Override
    public void stop(){


        String cstData; //Customer
        String emData; //Employee
        String exData; //Expense
        String memData; //Member
        String payData; //Payroll
        String prData; //Product
        String poData; //ProductOrder
        String strData; //Store
        String spData; //Supplier

        String cstQuery;
        String emQuery;
        String exQuery;
        String memQuery;
        String payQuery;
        String prQuery;
        String poQuery;
        String strQuery;
        String spQuery;



        for (int i = 0; i < obsCust.size(); i++)
        {
            Customer cst = (Customer) obsCust.get(i);
            cstData = " ('" + cst.getID() + "' , '" + cst.getName() + "' , '" + cst.getEmail() + "' , '" + cst.getPhoneNumber() + "' , '" +
                    cst.getAddress() + "' , '" + cst.getPaymentMethod() + "')";
            cstQuery = "INSERT INTO CUSTOMER (CUSTOMERID, NAME, EMAIL, PHONENUMBER, ADDRESS, PAYMENTMETHOD) VALUES" + cstData;

        }

        for (int i = 0; i < empData.size(); i++)
        {
            Employee em = (Employee) empData.get(i);
            Store str = (Store) em.getStore();
            emData = "('" + em.getEmployeeID() + "' , '" + em.getFirstName() + 
                    "' , '" + em.getLastName() + "' , '" + em.getEmail() + "' , '" + 
                    em.getPhoneNumber() + "' , '" + em.getAddress() + "' , '" + em.getSalary() +
                    "' , '" + em.getJobTitle() + "' , '" + em.getEmployeeType() + "' , '" + em.getEmployeeCategory() +
                    "' , '" + em.getStore() +  "' )";
            emQuery = "INSERT INTO EMPLOYEE (EMPLOYEEID, FIRSTNAME, LASTNAME, EMAIL, PHONENUMBER, ADDRESS, SALARY, JOBTITLE, EMPLOYEETYPE, EMPLOYEECATEGORY, STORE) VALUES" + emData;
            strData = "('" + str.getStoreID() + "' , '" + str.getStoreName() + "' , '" + str.getStoreAddress() + "' )";
            strQuery = "INSERT INTO STORE (STOREID, STORENAME, STOREADDRESS) VALUES" + strData;
        }

        for (int i = 0; i < expData.size(); i++)
        {
            Expense ex = (Expense) expData.get(i);
            exData = " ('" + ex.getExpenseID() + "' , '" + ex.getCategory() + "' , '" + ex.getDescription() + 
                    "' , '" + ex.getAmountPaid() + "' , '" + ex.getTotalAmount() + "' , '" + ex.getDate() + "' )";
            exQuery = "INSERT INTO EXPENSE (EXPENSEID, CATEGORY, DESCRIPTION, AMOUNTPAID, TOTALAMOUNT, DATE) VALUES" + exData;
        }

        for (int i = 0; i < obsMem.size(); i++)
        {
            Member mem = (Member) obsMem.get(i);
            memData = " ('" + mem.getID() + "' , '" + mem.getFName() + "' , '" + mem.getLName() + "' , '" + mem.getPhone() + "' , '" +
                    mem.getEmail() + "' )";
            memQuery = "INSERT INTO MEMBER (MEMBERID, FNAME, LNAME, PHONENUMBER, EMAIL) VALUES" + memData;
        }

        for (int i = 0; i < empData.size(); i++)
        {
            for (int j = 0; j < empData.get(i).payRoll.size(); j++)
            {
                Payroll pay = (Payroll) empData.get(i).payRoll.get(j);
                Employee em = (Employee) empData.get(i).payRoll.get(j).getEmp();
                /*payData = " ('" + pay.getID() + "' , '" + pay.getPayrollPeriod() + "' , '" + pay.getStart() + "' , '" + pay.end + "' , '" + pay.hoursWorked + 
                        "' , '" + pay.schedule + "' , '" + pay.scheduleDay + "' , '" + pay.arrTimeIn + "' , '" + pay.arrTimeOut + "' , '" + pay.totalWork +
                        "' , '" + pay.getAmountPaid() + "' )";
                payQuery = "INSERT INTO PAYROLL (PAYROLLID, PAYROLLPERIOD, START, END, HOURSWORKED, SCHEDULE, SCHEDULEDAY, ARRTIMEIN, ARRTIMEOUT, TOTALWORK, AMOUNTPAID) VALUES" + payData;
            */
                payData = " ('" + pay.getID() + "' , '" + pay.getEmp() + "' )";
                payQuery = "INSERT INTO PAYROLL (PAYROLLID, EMPLOYEE) VALUES" + payData;
                }
        }


        for (int i = 0; i < prodData.size(); i++)
        {
            Product pr = (Product) prodData.get(i);
            Supplier sp = (Supplier) pr.getSupp();
            prData = " ('" + pr.getProductID() + "' , '" + pr.getProductName() + "' , '" + pr.getQuantity() + "' , '" + pr.getPrice() +
                    "' , '" + pr.getStore() + "' , '" + pr.getFoodDescription() + "' , '" + pr.getCategory() + "' , '" + pr.getSupplier() +
                    "' , '" + pr.getImage() + "' )";
            prQuery = "INSERT INTO PRODUCT (PRODUCTID, NAME, QUANTITY, PRICE, STORE, DESCRIPTION, CATEGORY, SUPPLIER) VALUES" + prData;
            spData = " ('" + sp.getID() + "' , '" + sp.getName() + "' , '" + sp.getSupplierPhone() + "' , '" + sp.getSupplierPhone() + "' , '" +
                    sp.getSupplierEmail() + "' , '" + sp.getSupplierAddress() + "' , '" + sp.getContactName() + "' , '" + sp.getContactPhone() + "' , '" +
                    sp.getContactEmail() + " ' )";
            spQuery = "INSERT INTO SUPPLIER (SUPPLIERID, SUPPLIERNAME, SUPPLIERPHONE, SUPPLIEREMAIL, SUPPLIERADDRESS, CONTACTNAME, CONTACTPHONE, CONTACTEMAIL) VALUES" + spData;
        }

        for (int i = 0; i < obsProdOrder.size(); i++)
        {
            ProductOrder po = (ProductOrder) obsProdOrder.get(i);
            Supplier sp = (Supplier) po.getAssociatedSupplier();
            Store str = (Store) po.getStoreLoc();
            poData = " ('" + po.getID() + "' , '" + po.getQuantity() + "' , '" + po.getProdOName() + "' , '" + po.getUnitCost() + "' , '" + po.getSubtotal() +
                    "' , '" + po.getDeliveryDate() + "' , '" + po.getDate() + "' , '" + po.getSupplier() + "' , '" + po.getStore() + "' , '" + po.getCategory() + "' )";
            poQuery = "INSERT INTO PRODUCTORDER (PRODUCTORDERID, QUANTITY, NAME, UNITCOST, SUBTOTAL, DELIVERYDATE, DATE, SUPPLIER, STORE, CATEGORY) VALUES" + poData;
            spData = " ('" + sp.getID() + "' , '" + sp.getName() + "' , '" + sp.getSupplierPhone() + "' , '" + sp.getSupplierPhone() + "' , '" +
                    sp.getSupplierEmail() + "' , '" + sp.getSupplierAddress() + "' , '" + sp.getContactName() + "' , '" + sp.getContactPhone() + "' , '" +
                    sp.getContactEmail() + " ' )";
            spQuery = "INSERT INTO SUPPLIER (SUPPLIERID, SUPPLIERNAME, SUPPLIERPHONE, SUPPLIEREMAIL, SUPPLIERADDRESS, CONTACTNAME, CONTACTPHONE, CONTACTEMAIL) VALUES" + spData;
            strData = "('" + str.getStoreID() + "' , '" + str.getStoreName() + "' , '" + str.getStoreAddress() + "' )";
            strQuery = "INSERT INTO STORE (STOREID, STORENAME, STOREADDRESS) VALUES" + strData;
        }

        for (int i = 0; i < storeData.size(); i++)
        {
            Store str = (Store) obsStore.get(i);
            strData = "('" + str.getStoreID() + "' , '" + str.getStoreName() + "' , '" + str.getStoreAddress() + "' )";
            strQuery = "INSERT INTO STORE (STOREID, STORENAME, STOREADDRESS) VALUES" + strData;
        }

        for (int i = 0; i < obsSupp.size(); i++)
        {
            Supplier sp = (Supplier) obsSupp.get(i);
            spData = " ('" + sp.getID() + "' , '" + sp.getName() + "' , '" + sp.getSupplierPhone() + "' , '" + sp.getSupplierPhone() + "' , '" +
                    sp.getSupplierEmail() + "' , '" + sp.getSupplierAddress() + "' , '" + sp.getContactName() + "' , '" + sp.getContactPhone() + "' , '" +
                    sp.getContactEmail() + " ' )";
            spQuery = "INSERT INTO SUPPLIER (SUPPLIERID, SUPPLIERNAME, SUPPLIERPHONE, SUPPLIEREMAIL, SUPPLIERADDRESS, CONTACTNAME, CONTACTPHONE, CONTACTEMAIL) VALUES" + spData;
        }
        
       
for (int i = 0; i< suppList.size(); i++)
        {
           String suppData = " "; 
           String suppQuery = " "; 

            suppData = " ('" + suppList.get(i).getID() + "' , '" + suppList.get(i).getName() + "' , '" + suppList.get(i).getSupplierPhone() + "' , '" + suppList.get(i).getSupplierPhone() + "' , '" +
                    suppList.get(i).getSupplierEmail() + "' , '" + suppList.get(i).getSupplierAddress() + "' , '" + suppList.get(i).getContactName() + "' , '" + suppList.get(i).getContactPhone() + "' , '" +
                    suppList.get(i).getContactEmail() + " ' )";
            suppQuery = "INSERT INTO SUPPLIER (SUPPLIERID, SUPPLIERNAME, SUPPLIERPHONE, SUPPLIEREMAIL, SUPPLIERADDRESS, CONTACTNAME, CONTACTPHONE, CONTACTEMAIL) VALUES" + suppData;
            DBCommand(suppQuery);
        }
        for (int i = 0; i < storeList.size(); i++) {
            String storData = " ";
            String storQuery = " ";
            
            storData = " ('" + storeList.get(i).getStoreID() + "' , '" + storeList.get(i).getStoreName() + "' , '" + storeList.get(i).getStoreAddress() + " ')";
            storQuery = "INSERT INTO STORE (STOREID, STORENAME, STOREADDRESS) VALUES" + storData;
            DBCommand(storQuery);
            
        
        }
        
        for (int i = 0; i < expList.size(); i++)
        {
            String expnData = " ";
            String expnQuery = " ";
            
            expnData = " ('" + expList.get(i).getExpenseID() + "' , '" + expList.get(i).getCategory() + "' , '" + 
                    expList.get(i).getDescription() + "' , '" + expList.get(i).getStatus() + "' , '" + expList.get(i).getAmountPaid() +
                    "' , '" + expList.get(i).getTotalAmount() + "' , '" + expList.get(i).getRemainingBalance() + "' , '" + expList.get(i).getDate() +
                    "' , '" + expList.get(i).getPayableStore().getStoreID() + " )'";
            expnQuery = "INSERT INTO EXPENSE (EXPENSEID, CATEGORY, DESCRIPTION, STATUS, AMOUNTPAID, TOTALAMOUNT, REMAININGBALANCE, EXPDATE, STOREID) VALUES" + expnData;
            DBCommand(expnQuery);
            
        }
        for (Employee emp: empList) {
            String emplData = " ";
            String emplQuery = " ";
            
            emplData = " ('" + emp.getEmployeeID() + "' , '" + emp.getFirstName() + "' , '" + emp.getLastName() + "' , '" + emp.getEmail() + "' , '" +
                    emp.getPhoneNumber() + "' , '" + emp.getAddress() + "' , '" + emp.getHourly() + "' , '" + emp.getJobTitle() +
                    "' , '" + emp.getTimeWorked() + emp.getEmployeeType() + "' , '" + emp.getEmployeeCategory() + "' , '" + emp.getUsername() +
                    "' , '" + emp.getPassword() + emp.getStore().getStoreID() + "' )";
            emplQuery = "INSERT INTO EMPLOYEE (EMPLOYEEID, EMPLOYFNAME, EMPLOYLNAME, EMPLOYEMAIL, EMPLOYPHONENUMBER, EMPLOYADDRESS, EMPLOYHOURLY, EMPLOYJOBTITLE, EMPLOYTIMEWORKED, EMPLOYTYPE, EMPLOYCATEGORY, EMPLOYUSERNAME, EMPLOYPASSWORD, STOREID) VALUES" + emplData;
         }
        
        for (int i = 0; i < memList.size(); i++)
        {
            String membData = " ";
            String membQuery = " ";

            membData = " ('" + memList.get(i).getID() + "' , '" + memList.get(i).getFName() + "' , '" + memList.get(i).getLName() + "' , '" + memList.get(i).getPhone() + 
                    "' , '" + memList.get(i).getEmail() + " ')";
            membQuery = "INSERT INTO MEMBER (MEMBERID, FNAME, LNAME, PHONENUMBER, EMAIL) VALUES" + membData;
            DBCommand(membQuery);

        }

        for (int i = 0; i < prodList.size(); i++)
        {
            String prodtData = " ";
            String prodtQuery = " ";

            prodtData = " ('" + prodList.get(i).getProductID() + "' , '" + prodList.get(i).getProductName() + "' , '" + prodList.get(i).getQuantity() + "' , '" + prodList.get(i).getPrice() +
                    "' , '" + prodList.get(i).getStore().getStoreName() + "' , '" + prodList.get(i).getFoodDescription() + "' , '" + prodList.get(i).getCategory() + "' , '" + prodList.get(i).getStore().getStoreID() +
                    "' , '" + prodList.get(i).getSupp().getID() + "' )";
            prodtQuery = "INSERT INTO PRODUCT (PRODUCTID, NAME, QUANTITY, PRICE, STORE, DESCRIPTION, CATEGORY, STOREID, SUPPLIERID) VALUES" + prodtData;
            DBCommand(prodtQuery);
        }

        for (int i = 0; i < prodOrderList.size(); i++)
        {
            String prodOrdData = " ";
            String prodOrdQuery = " ";

            prodOrdData = " ('" + prodOrderList.get(i).getID() + "' , '" + prodOrderList.get(i).getQuantity() + "' , '" + prodOrderList.get(i).getProdOName() + "' , '" + prodOrderList.get(i).getUnitCost() + "' , '" + prodOrderList.get(i).getSubtotal() +
                    "' , '" + prodOrderList.get(i).getDeliveryDate() + "' , '" + prodOrderList.get(i).getDate() + "' , '" + prodOrderList.get(i).getCategory() + "' , '" + prodOrderList.get(i).getStoreLoc().getStoreID() + "' , '" + prodOrderList.get(i).getAssociatedSupplier().getID() + "' )";
            prodOrdQuery = "INSERT INTO PRODUCTORDER (PRODUCTORDERID, QUANTITY, NAME, UNITCOST, SUBTOTAL, DELIVERYDATE, PODATE, SUPPLIER, CATEGORY, STOREID, SUPPLIERID) VALUES" + prodOrdData;
            DBCommand(prodOrdQuery);
        }


        for (Employee emp: empList) {
            String emplData = " ";
            String emplQuery = " ";

            emplData = " ('" + emp.getEmployeeID() + "' , '" + emp.getFirstName() + "' , '" + emp.getLastName() + "' , '" + emp.getEmail() + "' , '" +
                    emp.getPhoneNumber() + "' , '" + emp.getAddress() + "' , '" + emp.getHourly() + "' , '" + emp.getJobTitle() +
                    "' , '" + emp.getTimeWorked() + emp.getEmployeeType() + "' , '" + emp.getEmployeeCategory() + "' , '" + emp.getUsername() +
                    "' , '" + emp.getPassword() + emp.getStore().getStoreID() + "' )";
            emplQuery = "INSERT INTO EMPLOYEE (EMPLOYEEID, EMPLOYFNAME, EMPLOYLNAME, EMPLOYEMAIL, EMPLOYPHONENUMBER, EMPLOYADDRESS, EMPLOYHOURLY, EMPLOYJOBTITLE, EMPLOYTIMEWORKED, EMPLOYTYPE, EMPLOYCATEGORY, EMPLOYUSERNAME, EMPLOYPASSWORD, STOREID) VALUES" + emplData;
         }

    }

    public ResultSet DBCommand(String sqlQuery)
        {
            String URL = "jdbc:oracle:thin:@localhost:1521:XE";
            String userID = "javauser";
            String userPASS = "javapass";
            OracleDataSource ds;


        try
        {
            ds = new OracleDataSource();
            ds.setURL(URL);
            Connection dbConn = ds.getConnection(userID,userPASS);
            Statement commStmt = dbConn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            dbResults = commStmt.executeQuery(sqlQuery);

        }
        catch (SQLException e) {
                {
                    System.out.println(e.toString());
                }
        }
        return dbResults; 
        }


    public void loadCustSaleFromDB() 
    {
    String query = "SELECT * FROM JAVAUSER.CUSTSALE"; 
    ResultSet resultset = DBCommand(query); 

        try
        {
            while(resultset.next())
            {
                //obsSale.add(new CustSale ( resultset.getString ( "SALEDATE"), resultset.getObject("STORE"), resultset.getObject("EMPLOYEE"), resultset.get
            }

        }
        catch(SQLException e) 
        {
            System.out.println(e.toString()); 
        }

    }



    public void loadExpensesFromDB() 
    {
//        String query = "SELECT * FROM JAVAUSER.EXPENSE"; 
//        ResultSet resultset = DBCommand(query); 
//
//        try
//        {
//            while(resultset.next())
//            {
//                obsExpense.add(new Expense ( resultset.getString ( "CATEGORY"), resultset.getString("DESCRIPTION"), resultset.getDouble("AMOUNTPAID"), resultset.getDouble("TOTALAMOUNT"), resultset.getString("DATE")));
//            }
//
//        }
//        catch(SQLException e) {
//            System.out.println(e.toString()); 
//        }
    }
        public void loadEmployeesfromDB()
        {
//        String query = "SELECT * FROM JAVAUSER.EMPLOYEE"; 
//        ResultSet resultset = DBCommand(query); 
//
//        try
//        {
//            while(resultset.next())
//            {
//                obsEmp.add(new Employee ( resultset.getString ( "FIRSTNAME"), resultset.getString("LASTNAME"), resultset.getString("EMAIL"), resultset.getString("PHONENUMBER"), resultset.getString("ADDRESS"), resultset.getDouble("SALARY"), resultset.getString("JOBTITLE"), resultset.getString("EMPLOYEETYPE"), resultset.getString("EMPLOYEETYPE"), resultset.getObject("STORE")));
//            }
//
//        }
//        catch(SQLException e) 
//        {
//            System.out.println(e.toString()); 
//        }
        }

            public void loadEmployeesFromDB()
            {
//
//                String sdQuery;
//                ResultSet employeeResults;
//
//                try
//                {
//                    sdQuery = "SELECT * FROM SYSTEM.STUDENT;";
//                    employeeResults = sendDBCommand(sdQuery);
//
//
//                    while(employeeResults.next())
//                    {
//                        empData.add(new Employee(Integer.parseInt(employeeResults.getString(1)),
//                                employeeResults.getString(2), employeeResults.getString(3),
//                                employeeResults.getString(4), employeeResults.getString(5),
//                                Double.parseDouble(employeeResults.getString(6)), employeeResults.getString(7), employeeResults.getString(8), employeeResults.getString(9))
//                                );
//
//                    }
//                }
//                catch(SQLException e)
//                {
//                    System.out.println(e.toString());
//
//                }
            }
        public void loadMembersfromDB()
        {
        String query = "SELECT * FROM JAVAUSER.MEMBER"; 
        ResultSet resultset = DBCommand(query); 

        try
        {
            while(resultset.next())
            {
                obsMem.add(new Member ( resultset.getString ( "FNAME"), resultset.getString("LNAME"), resultset.getString("PHONENUMBER"), resultset.getString("EMAIL")));
            }

        }
        catch(SQLException e) 
        {
            System.out.println(e.toString()); 
        }
        }

       /* public void loadPayrollfromDB()
        {
        String query = "SELECT * FROM JAVAUSER.PAYROLL"; 
        ResultSet resultset = DBCommand(query); 

        try{
            while(resultset.next()){
                obsEmp.add(new Payroll (resultset.getObject("EMPLOYEE")));
            }

        }
        catch(SQLException e) {
            System.out.println(e.toString()); 
        }
        }
*/
/*
        public void loadProductsfromDB()
        {
        String query = "SELECT * FROM JAVAUSER.PRODUCT"; 
        ResultSet resultset = DBCommand(query); 

        try
        {
            while(resultset.next())
            {
                obsProd.add(new Product ( resultset.getString("NAME"), resultset.getInt("QUANTITY"), resultset.getDouble("PRICE"), resultset.getObject("STORE"), 
                        resultset.getString("DESCRIPTION"), resultset.getString("CATEGORY"), resultset.getObject("SUPPLIER"), resultset.getObject("IMAGE")));
            }

        }
        catch(SQLException e) 
        {
            System.out.println(e.toString()); 
        }
        }

        public void loadProductOrdersfromDB()
        {
        String query = "SELECT * FROM JAVAUSER.PRODUCTORDER"; 
        ResultSet resultset = DBCommand(query); 

        try
        {
            while(resultset.next())
            {
                obsProdOrder.add(new ProductOrder ( resultset.getString("ORDERDATE"), resultset.getInt("QUANTITY"), 
                        resultset.getDouble("UNITCOST"), resultset.getDouble("SUBTOTAL"), resultset.getString("DELIVERYDATE")));
            }

        }
        catch(SQLException e) 
        {
            System.out.println(e.toString()); 
        }
        }

        public void loadStoresfromDB()
        {
        String query = "SELECT * FROM JAVAUSER.STORE"; 
        ResultSet resultset = DBCommand(query); 

        try
        {
            while(resultset.next())
            {
                obsStore.add(new Store ( resultset.getString("NAME"), resultset.getString("ADDRESS")));

            }
        }
        catch(SQLException e) 
        {
            System.out.println(e.toString()); 
        }
        }

        public void loadSuppliersfromDB()
        {
        String query = "SELECT * FROM JAVAUSER.SUPPLIER"; 
        ResultSet resultset = DBCommand(query); 

        try
        {
            while(resultset.next())
            {
                obsSupp.add(new Supplier ( resultset.getString("SUPPLIERNAME"), resultset.getString("SUPPLIERPHONE"),
                        resultset.getString("SUPPLIEREMAIL"), resultset.getString("SUPPLIERADDRESS"), resultset.getString("SALESCONTACTNAME"),
                        resultset.getString("SALESCONTACTNUMBER"), resultset.getString("SALESCONTACTEMAIL")));
            }

        }
        catch(SQLException e) 
        {
            System.out.println(e.toString()); 
        }
        }
*/
}
