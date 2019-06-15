package pkg484groupproj;

import java.time.LocalDate;
import javafx.application.Application;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.*;
import javafx.collections.*;
import javafx.geometry.Pos;
import java.time.format.*;


public class CustSaleForm {
    
    //Ring Sale Controls
    public String saleID;
    public static int saleCount = 0;
    public String recItems = "";
    public Alert alertError = new Alert(Alert.AlertType.ERROR);
    public Alert alert = new Alert(Alert.AlertType.INFORMATION);
    public Label lblSaleDate = new Label("Date:");
    public Label lblSaleStore = new Label("Store:");
    public Label lblSaleEmp = new Label("Employee");
    public Label lblSaleCust = new Label("Customer:");
    public Label lblClubMem = new Label("Thrifty Club Member?");
    public Label lblSaleProd = new Label("Product:");
    //public Label lblSaleProdQuan = new Label("Quantity:");
    
    //public TextField txtSaleProdQuan = new TextField();
    
    public Button btnAddToSale = new Button("Add to Sale ->");
    public Button btnCompleteSale = new Button("Complete Sale ->");
    public Button btnReceipt = new Button("Generate Receipt ->");
    public Button btnRepeatBuy = new Button ("Repeat Purchase");
    public Button btnAddMember = new Button("Add Thrifty Member");
    public TextArea txtSaleOutput = new TextArea();
    
    public TextArea receiptOutput = new TextArea();
    public ObservableList<String> isMemList = FXCollections.observableArrayList(
            "Yes",
            "No"
            );
    
    public ObservableList<ArrayList> EmployList = FXCollections.observableArrayList(
            
            );
    
    final ComboBox cmboIsMem = new ComboBox(isMemList);
    public ComboBox cmboSaleProd = new ComboBox(Product.obsProd);
    public ComboBox cmboSaleCust = new ComboBox(Customer.obsCust);
    public ComboBox cmboSaleStore = new ComboBox(Store.obsStore);
    public ComboBox cmboSaleEmp = new ComboBox(Employee.obsEmp);
    
    
    public DatePicker saleDate = new DatePicker();
    
    public ArrayList<Product> saleProducts = new ArrayList();
    
    public GUI myParent;
    //Sale Constructor
    
    //Add Member Controls
    Label lblAddMem = new Label("Add Member");
    Label lblMemFName = new Label("First Name:");
    Label lblMemLName = new Label("Last Name:");
    Label lblMemPhone = new Label("Phone:");
    Label lblMemEmail = new Label("Email:");
    
    TextField txtMemFName = new TextField();
    TextField txtMemLName = new TextField();
    TextField txtMemPhone = new TextField();
    TextField txtMemEmail = new TextField();
    
    Button btnCreateMem = new Button("Create Member");
    
    public CustSaleForm(GUI parentForm)
    {
        this.myParent = parentForm;
        this.saleID = "Sale" + saleCount++;
        GridPane primaryPane = new GridPane();
        
        primaryPane.setAlignment(Pos.CENTER);
        
        //Set Controls
        
        primaryPane.add(lblSaleDate, 0, 0);
        primaryPane.add(saleDate, 1, 0);
        saleDate.setMinWidth(4);
        primaryPane.add(lblSaleStore, 0, 1);
        primaryPane.add(cmboSaleStore, 1, 1);
        cmboSaleStore.setMinWidth(4);
        primaryPane.add(lblSaleEmp, 0, 2);
        primaryPane.add(cmboSaleEmp, 1, 2);
        cmboSaleCust.setMinWidth(4);
        primaryPane.add(lblClubMem, 0, 3);
        primaryPane.add(cmboIsMem, 1, 3);
        cmboIsMem.setMinWidth(4);
        primaryPane.add(GUI.cmboMem, 1, 4);
        GUI.cmboMem.setMinWidth(4);
        primaryPane.add(btnAddMember, 1, 5);
        primaryPane.add(lblSaleProd, 0, 6);
        primaryPane.add(cmboSaleProd, 1, 6);
        cmboSaleProd.setMinWidth(4);
        //primaryPane.add(lblSaleProdQuan, 0, 5);
        //primaryPane.add(txtSaleProdQuan, 1, 5);
        primaryPane.add(btnAddToSale, 0, 7, 1, 1);
        primaryPane.add(btnRepeatBuy, 0, 8, 1, 1);
        primaryPane.add(btnCompleteSale, 0, 10, 2, 2);
        primaryPane.add(txtSaleOutput, 2, 0, 4, 6);
        
        //txtSaleProdQuan.setPrefWidth(20);
        saleDate.setValue(LocalDate.now());
        saleDate.setPrefWidth(118);
        cmboSaleStore.setPrefWidth(118);
        cmboSaleCust.setPrefWidth(118);
        cmboSaleEmp.setPrefWidth(118);
        cmboSaleEmp.getSelectionModel().selectFirst();
        
        
        for(int i = 0; i< GUI.savedEmp.size();i++)
           {
               if(GUI.empList.get(i).getUsername().equalsIgnoreCase(GUI.currentUser))
                   cmboSaleEmp.getSelectionModel().select(i);
//               Employee temp = GUI.savedEmp.get(i);
//               if (temp.getUsername().equalsIgnoreCase(GUI.txtUser.toString())){
//               String userTemp = temp.getUsername();
//               cmboSaleEmp.getSelectionModel().select(userTemp);
//               }
           }        
        
        cmboIsMem.setPrefWidth(118);
        cmboSaleProd.setPrefWidth(118);
        
        lblAddMem.setStyle("-fx-font: bold 14pt \"Arial\";");
        
        //Outputs cannot be edited by User
        txtSaleOutput.setEditable(false);
        receiptOutput.setEditable(false);
        
        //AddMemPane
        GridPane addMemPane = new GridPane();
        addMemPane.setAlignment(Pos.TOP_CENTER);
        addMemPane.setVgap(10);
        addMemPane.setHgap(10);
        
        Scene addMemScene = new Scene(addMemPane, 500, 300);
        Stage addMemStage = new Stage();
        addMemStage.setScene(addMemScene);
        
        //Diable Add Member button unless not already a memeber
        
        //Not sure how to do this^^
        
        //Set Output Perminant Text
        txtSaleOutput.appendText("Sale Items: " + "\n");
        txtSaleOutput.appendText("--------------------------"
                + "----------------------------------------\n");
        
        Scene primaryScene = new Scene(primaryPane, 800, 600); Stage primaryStage = new Stage();
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("Customer Sale Form");
        primaryStage.show();
        
        
        btnAddToSale.setOnAction(e -> {
            //Find products in sale      

            int saleProd = cmboSaleProd.getSelectionModel().getSelectedIndex();

            this.saleProducts.add(GUI.prodList.get(saleProd));
            
            if(saleProducts.size() > GUI.prodList.size())
            {
                saleProducts.remove(saleProd);
                alertError.setTitle("Error");
                alertError.setHeaderText("Sale Quantity exceeds Inventory!");
                alertError.showAndWait();
            }
            else{
            String saleOut = "";
            saleOut += GUI.prodList.get(saleProd).toStringSale();
            saleOut += "\n";
            txtSaleOutput.appendText(saleOut);            
            recItems += saleOut;
            
            alert.setTitle("Success!");
            alert.setHeaderText("Sale Has Been Added!");
            alert.showAndWait();
            txtSaleOutput.setEditable(false);
            
            }
            });
        
        btnRepeatBuy.setOnAction(e -> {
            //Repeat the purchase of the same item repeatedly
            int saleProd = cmboSaleProd.getSelectionModel().getSelectedIndex();
            this.saleProducts.add(GUI.prodList.get(saleProd));
             String saleOut = "";
            saleOut += GUI.prodList.get(saleProd).toStringSale();
            saleOut += "\n";
            txtSaleOutput.appendText(saleOut);   
            recItems += saleOut;
            alert.setTitle("Success!");
            alert.setHeaderText("Same Product Purchased!");
            alert.showAndWait();
            txtSaleOutput.setEditable(false);
        });
        
        btnAddMember.setOnAction(e -> {
            
            addMemStage.setTitle("Add a Thrifty Member");
            addMemStage.show();
            addMemPane.setAlignment(Pos.TOP_CENTER);
            
            addMemPane.add(lblAddMem, 0, 0);
            addMemPane.add(lblMemFName, 0, 1);
            addMemPane.add(txtMemFName, 0, 2);
            addMemPane.add(lblMemLName, 1, 1);
            addMemPane.add(txtMemLName, 1, 2);
            addMemPane.add(lblMemPhone, 0, 3);
            addMemPane.add(txtMemPhone, 0, 4);
            addMemPane.add(lblMemEmail, 1, 3);
            addMemPane.add(txtMemEmail, 1, 4);
            addMemPane.add(btnCreateMem, 1, 5);
            
        });
        
        btnCreateMem.setOnAction(e -> {
            
            GUI.memList.add(new Member(txtMemFName.getText(), 
                    txtMemLName.getText(), txtMemPhone.getText(), 
                    txtMemEmail.getText()));
            
            alert.setTitle("Success!");
            alert.setHeaderText("New Member Added!");
            alert.showAndWait();
            
            addMemStage.close();
            
            Member.obsMem.add(txtMemLName.getText() + ", " + txtMemFName.getText());
            
        });
        
        //Complete the sale
        btnCompleteSale.setOnAction(e -> {
            receiptOutput.clear();
            primaryPane.add(receiptOutput, 5, 6);
            receiptOutput.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            receiptOutput.appendText("Thrifty $tore: " + "\n");
            receiptOutput.appendText("Receipt: " + "\n");
            receiptOutput.appendText("--------------------------"
                + "----------------------------------------\n");
            int saleStore = cmboSaleStore.getSelectionModel().getSelectedIndex();
            int saleEmp = cmboSaleEmp.getSelectionModel().getSelectedIndex();
            int isMem = cmboIsMem.getSelectionModel().getSelectedIndex();
            
            String dateOfSale = saleDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            
            
            
            GUI.saleList.add(new CustSale(dateOfSale, GUI.storeList.get(saleStore),
                    GUI.empList.get(saleEmp), cmboIsMem.getValue().toString(),
                    this.saleProducts));
            
            CustSale thisSale = GUI.saleList.get(saleCount-1);
            receiptOutput.appendText(recItems);
            receiptOutput.appendText("\n" + thisSale.toString());
            //need to include the customer 
            receiptOutput.appendText("\n" + "--------------------------"
                + "----------------------------------------\n");
            receiptOutput.appendText("\n" + "Thank You ");
            if(txtMemFName.getText().isEmpty())
            {
                receiptOutput.appendText("Customer!");
            }
            else
            {
                receiptOutput.appendText(txtMemFName.getText() + "!");
            }
            alert.setTitle("Success!");
            alert.setHeaderText("Sale Completed!");
            alert.showAndWait();
            receiptOutput.setEditable(false);
            cmboSaleProd.getSelectionModel().clearSelection();
            });
        
        
    }
    
    
    
}
