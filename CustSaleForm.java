package pkg484groupproj;

import java.io.Serializable;
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
import javafx.geometry.Insets;
import javafx.scene.DepthTest;


public class CustSaleForm{
    
    //Ring Sale Controls
    public String saleID;
    public static int saleCount = 0;
    public String recItems = "";
    public Alert alertError = new Alert(Alert.AlertType.ERROR);
    public Alert alert = new Alert(Alert.AlertType.INFORMATION);
    public Label lblSaleDate = new Label("Date:");
    public Label lblSaleStore = new Label("Store:");
    public Label lblSaleEmp = new Label("Employee:");
    public Label lblSaleCust = new Label("Customer:");
    public Label lblClubMem = new Label("Thrifty Club Member?");
    public Label lblMem = new Label("Club Member:");
    public Label lblYes = new Label("Yes");
    public Label lblNo = new Label("No");
    public Label lblSaleProd = new Label("Product:");
    public Label lblSaleProdQuan = new Label("Quantity:");
    public Label lblRingSale = new Label("Customer Sale Form");
    
    public TextField txtSaleProdQuan = new TextField();
    
    public Button btnAddToSale = new Button("Add to Sale ->");
    public Button btnCompleteSale = new Button("Complete Sale ->");
    public Button btnReceipt = new Button("Generate Receipt ->");
    public Button btnRepeatBuy = new Button ("Repeat Purchase");
    public Button btnSearchMem = new Button("Search Member");
    public Button btnAddMember = new Button("Add New Member");
    public Button btnViewReceipt = new Button("Preview Receipt");
    public Button btnNewSale = new Button("New Sale");
    public TextArea txtSaleOutput = new TextArea();
    
    public TextArea receiptOutput = new TextArea();
  
    
    public ComboBox cmboSaleProd = new ComboBox(Product.obsProd);
    public ComboBox cmboSaleStore = new ComboBox(Store.obsStore);
    public ComboBox cmboSaleEmp = new ComboBox(Employee.obsEmp);
    
    public DatePicker saleDate = new DatePicker();
    public ArrayList<Product> productsAvailable = new ArrayList<>();
    public ArrayList<Product> currentSale = new ArrayList<>();
    
    public ArrayList<Product> saleProducts = new ArrayList();
    
    public CheckBox cbYes = new CheckBox();
    public CheckBox cbNo = new CheckBox();
    
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
    
    //Search Member Controls
    Label lblSearchMem = new Label("Search Member");
    Label lblSCustPhone = new Label("Phone Number: ");
    
    TextField txtSCustPhone = new TextField();
    
    Button btnFindMem = new Button("Find Member ->");
    
    public CustSaleForm(GUI parentForm)
    {
        this.myParent = parentForm;
        this.saleID = "Sale " + saleCount++;
        this.productsAvailable.addAll(GUI.prodList);
        this.currentSale.addAll(productsAvailable);
        GridPane primaryPane = new GridPane();
        
        primaryPane.setAlignment(Pos.TOP_CENTER);
        
        //Set Controls
        primaryPane.setVgap(10);
        primaryPane.setHgap(10);
        primaryPane.setPadding(new Insets(10, 20, 10 , 20));
        
        primaryPane.add(lblRingSale, 0, 0, 3, 1);
        primaryPane.add(lblSaleDate, 0, 1,2,1);
        primaryPane.add(saleDate, 2, 1);
        saleDate.setMinWidth(2);
        primaryPane.add(lblSaleStore, 0, 2,2,1);
        primaryPane.add(cmboSaleStore, 2, 2);
        cmboSaleStore.setMinWidth(2);
        primaryPane.add(lblSaleEmp, 0, 3,2,1);
        primaryPane.add(cmboSaleEmp, 2, 3);
        primaryPane.add(lblClubMem, 0, 4, 2,1);
        primaryPane.add(lblYes, 0, 5);
        
        primaryPane.add(lblNo, 0, 6);
        primaryPane.add(cbYes, 1, 5);
        primaryPane.add(cbNo, 1, 6);
        primaryPane.add(lblMem, 1, 7,1,1);
        primaryPane.add(GUI.cmboMem, 2, 7);
        GUI.cmboMem.setMaxWidth(Double.MAX_VALUE);
        primaryPane.add(btnSearchMem, 2, 8);
        primaryPane.add(btnAddMember, 1, 8,1,1);
        primaryPane.add(lblSaleProd, 1, 10,1,1);
        primaryPane.add(cmboSaleProd, 2, 10);
        cmboSaleProd.setMaxWidth(Double.MAX_VALUE);
        primaryPane.add(btnAddToSale, 2, 11, 1, 1);
        primaryPane.add(btnRepeatBuy, 0, 11, 2, 1);
        primaryPane.add(btnCompleteSale, 0, 12, 2, 1);
        primaryPane.add(btnNewSale, 0,13,2,1);
        primaryPane.add(txtSaleOutput, 4, 1, 4, 6);
        
        btnAddMember.setMaxWidth(Double.MAX_VALUE);
        btnSearchMem.setMaxWidth(Double.MAX_VALUE);
        btnAddToSale.setMaxWidth(Double.MAX_VALUE);
        btnRepeatBuy.setMaxWidth(Double.MAX_VALUE);
        btnCompleteSale.setMaxWidth(Double.MAX_VALUE);
        btnNewSale.setMaxWidth(Double.MAX_VALUE);
        
        lblSaleDate.setStyle("-fx-font: 12pt \"bookman\";");
        lblSaleStore.setStyle("-fx-font: 12pt \"bookman\";");
        lblSaleEmp.setStyle("-fx-font: 12pt \"bookman\";");
        lblMem.setStyle("-fx-font: 12pt \"bookman\";");
        lblSaleProd.setStyle("-fx-font: 12pt \"bookman\";");
        lblMemFName.setStyle("-fx-font: 12pt \"bookman\";");
        lblMemLName.setStyle("-fx-font: 12pt \"bookman\";");
        lblMemPhone.setStyle("-fx-font: 12pt \"bookman\";");
        lblMemEmail.setStyle("-fx-font: 12pt \"bookman\";");
        lblSearchMem.setStyle("-fx-font: 12pt \"bookman\";");
        lblSCustPhone.setStyle("-fx-font: 12pt \"bookman\";");
        lblClubMem.setStyle("-fx-font: 12pt \"bookman\";");   
        lblYes.setStyle("-fx-font: 11pt \"bookman\";");
        lblNo.setStyle("-fx-font: 11pt \"bookman\";");
        
        receiptOutput.setStyle("-fx-font: bold 9pt \"bookman\";");
        txtSaleOutput.setStyle("-fx-font: bold 9pt \"bookman\";");
        btnNewSale.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill:white;");
        btnRepeatBuy.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill:white;");
        btnAddToSale.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill:white;");
        btnCompleteSale.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill:white;");
        btnSearchMem.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill:white;");
        btnAddMember.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill:white;");
        btnCreateMem.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill:white;");
        btnFindMem.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: dodgerblue; -fx-text-fill:white;");
        cbYes.setStyle("-fx-font: 10pt \"bookman\";");
        cbNo.setStyle("-fx-font: 10pt \"bookman\";");
        lblRingSale.setStyle("-fx-font: bold 20pt \"bookman\";");
                   
        saleDate.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: lightskyblue; -fx-text-fill:white;");
        cmboSaleStore.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: lightskyblue; -fx-text-fill:white;");
        cmboSaleEmp.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: lightskyblue; -fx-text-fill:white;");
        cmboSaleProd.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: lightskyblue; -fx-text-fill:white;");
        GUI.cmboMem.setStyle("-fx-font: 10pt \"bookman\"; -fx-background-color: lightskyblue; -fx-text-fill:white;");
        
        saleDate.setValue(LocalDate.now());
        saleDate.setPrefWidth(118);
        cmboSaleStore.setPrefWidth(118);
        cmboSaleEmp.setPrefWidth(118);
        GUI.cmboMem.setPrefWidth(118);
        
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
        
        //SearchMemPane
        GridPane searchMemPane = new GridPane();
        searchMemPane.setAlignment(Pos.TOP_CENTER);
        searchMemPane.setVgap(10);
        searchMemPane.setHgap(10);
        
        Scene searchMemScene = new Scene(searchMemPane, 400, 200);
        Stage searchMemStage = new Stage();
        searchMemStage.setScene(searchMemScene);
        
        //Set Output Perminant Text
        txtSaleOutput.appendText("Sale Items: " + "\n");
        txtSaleOutput.appendText("--------------------------"
                + "----------------------------------------\n");
        
        Scene primaryScene = new Scene(primaryPane, 800, 600); Stage primaryStage = new Stage();
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("Customer Sale Form");
        primaryStage.show();
        

        cbNo.setOnAction(e -> {
            cbYes.setSelected(false);
            btnAddMember.setDisable(true);
            btnAddMember.setDisable(true);
            btnSearchMem.setDisable(true);
            GUI.cmboMem.setDisable(true);

                });
        
        //enable member options if selected yes
        cbYes.setOnAction(e -> {
            cbNo.setSelected(false);
            btnAddMember.setDisable(false);
            btnAddMember.setDisable(false);
            btnSearchMem.setDisable(false);
            GUI.cmboMem.setDisable(false);
        });
           
        btnAddToSale.setOnAction(e -> {
            //Find products in sale      
            try{
            int saleProd = cmboSaleProd.getSelectionModel().getSelectedIndex();
            Product prodSale = currentSale.get(saleProd);
            String saleOut = "";
            
            if (cmboSaleEmp.getSelectionModel().isEmpty() || cmboSaleStore.getSelectionModel().isEmpty() || 
                    cmboSaleProd.getSelectionModel().isEmpty())
            {
                alertError.setTitle("Error");
                alertError.setHeaderText("Fields Filled Incorrectly, Try again!");
                alertError.showAndWait();
            }
            else{
                    if(prodSale.getQuantity()>= 1)
                    {
                        this.saleProducts.add(prodSale);
                        currentSale.get(saleProd).sellQuantity(1);
                        saleOut += currentSale.get(saleProd).toStringSale();
                        saleOut += "\n";
                        txtSaleOutput.appendText(saleOut);            
                        recItems += saleOut;
                    }
                    else
                    {
                        alertError.setTitle("Error");
                        alertError.setHeaderText("Sale Quantity exceeds Inventory!"
                        + "\nPlease Order More!");
                        alertError.showAndWait();
                    }    
                }
            }
            catch (Exception ex)
            {
                System.out.println("Given: " + ex + "Exception in Adding Sale");
            }
            
            });
        
        btnRepeatBuy.setOnAction(e -> {
            //Repeat the purchase of the same item repeatedly
            try{
            int saleProd = cmboSaleProd.getSelectionModel().getSelectedIndex();
            Product prodSale = currentSale.get(saleProd);
            String saleOut = "";
           if(prodSale.getQuantity()>= 1)
                    {
                        this.saleProducts.add(prodSale);
                        prodSale.sellQuantity(1);
                        saleOut += GUI.prodList.get(saleProd).toStringSale();
                        saleOut += "\n";
                        txtSaleOutput.appendText(saleOut);            
                        recItems += saleOut;
                    }
                    else
                    {
                        alertError.setTitle("Error");
                        alertError.setHeaderText("Sale Quantity exceeds Inventory!"
                        + "\nPlease Order More!");
                        alertError.showAndWait();
                    }
            }
            catch (Exception ex)
            {
                System.out.println("Given: " + ex + "Exception in Repeating Sale");
            }
        });
        
        btnAddMember.setOnAction(e -> {
            addMemPane.getChildren().clear();
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
            try{
            if (txtMemFName.getText().isEmpty() || txtMemLName.getText().isEmpty() || 
                txtMemPhone.getText().isEmpty() || txtMemEmail.getText().isEmpty())
            {
                alertError.setTitle("Error");
                alertError.setHeaderText("Please Enter All Requested Information!");
                alertError.showAndWait();
            }
            else{
            GUI.memList.add(new Member(txtMemFName.getText(), 
                    txtMemLName.getText(), txtMemPhone.getText(), 
                    txtMemEmail.getText()));
            alert.setTitle("Success!");
            alert.setHeaderText("New Member Added!");
            alert.showAndWait();
            
            addMemStage.close();
            
            Member.obsMem.add(txtMemLName.getText() + ", " + txtMemFName.getText());
            GUI.cmboMem.getSelectionModel().select(Member.memCount-1);
            
            txtMemFName.clear();
            txtMemLName.clear();
            txtMemPhone.clear();
            txtMemEmail.clear();
            }
            }
            catch(Exception ex)
            {
                System.out.println("Given: " + ex + "Exception in Creating Member");
            }
        });
        
        btnSearchMem.setOnAction(e -> {
            searchMemPane.getChildren().clear();
            searchMemStage.setTitle("Search Member by Phone Number");
            searchMemStage.show();
            searchMemPane.setAlignment(Pos.TOP_CENTER);
            
            searchMemPane.add(lblSearchMem, 0, 0);
            searchMemPane.add(lblSCustPhone, 0, 1);
            searchMemPane.add(txtSCustPhone, 1, 1);
            searchMemPane.add(btnFindMem, 1, 2);
            
        });
        
        btnFindMem.setOnAction(e -> {
            if(GUI.memList.isEmpty())
            {
                alert.setTitle("Error!");
                alert.setHeaderText("No members added!");
                alert.showAndWait();
                
                searchMemStage.close();
            }
            try{
            for(int i=0; i<GUI.memList.size(); i++)
            {
                if(GUI.memList.get(i).getPhone().contains(txtSCustPhone.getText()))
                {
                    int thisMem = i;
                    GUI.cmboMem.getSelectionModel().select(i);
                    
                    alert.setTitle("Success!");
                    alert.setHeaderText("Member Found!");
                    alert.showAndWait();
            
                    searchMemStage.close();
                }
                else if(i == GUI.memList.size()-1 && GUI.memList.get(i).getPhone().contains(txtSCustPhone.getText())== false)
                {
                    alertError.setTitle("Error");
                    alertError.setHeaderText("Phone Number Not Found!");
                    alertError.showAndWait();
                }
            }
            }
            catch(Exception ex)
            {
                System.out.println("Given: " + ex + "Exception in Finding Member");
            }
        });
        
        //Complete the sale
        btnCompleteSale.setOnAction(e -> {
            try{
            
            receiptOutput.clear();
            primaryPane.add(receiptOutput, 4,7,4,6);
            receiptOutput.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            receiptOutput.setStyle("-fx-font: bold 9pt \"bookman\";");
            receiptOutput.appendText("Thrifty $tore: " + "\n");
            receiptOutput.appendText("Receipt: " + "\n");
            receiptOutput.appendText("--------------------------"
                + "----------------------------------------\n");
            int saleStore = cmboSaleStore.getSelectionModel().getSelectedIndex();
            int saleEmp = cmboSaleEmp.getSelectionModel().getSelectedIndex();
            int saleMem = GUI.cmboMem.getSelectionModel().getSelectedIndex();
            GUI.prodList.clear();
            GUI.prodList.addAll(this.currentSale);
            String dateOfSale = saleDate.getValue().format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
            
            if(GUI.cmboMem.getSelectionModel().isEmpty())
            {
                GUI.saleList.add(new CustSale(dateOfSale, GUI.storeList.get(saleStore),
                    GUI.empList.get(saleEmp), this.saleProducts));
                
                
                
                CustSale thisSale = GUI.saleList.get(saleCount-1);
                GUI.storeList.get(saleStore).associatedRevenue.add(thisSale);
                
                
                
                receiptOutput.appendText(recItems);
                receiptOutput.appendText("\n" + thisSale.toStringNoMem());
                receiptOutput.appendText("\n--------------------------"
                    + "----------------------------------------\n");
                receiptOutput.appendText("\n" + "Thank You!"
                        + "\nJoin Our Rewards Program For More Savings!");
                alert.setTitle("Success!");
                alert.setHeaderText("Sale Completed!");
                alert.showAndWait();
                receiptOutput.setEditable(false);
                
                //clear TextFields
                cmboSaleStore.getSelectionModel().clearSelection();
                cmboSaleEmp.getSelectionModel().clearSelection();
                cmboSaleProd.getSelectionModel().clearSelection();
                GUI.cmboMem.getSelectionModel().clearSelection();
                
            }
            else
            {
                GUI.saleList.add(new CustSale(dateOfSale, GUI.storeList.get(saleStore),
                        GUI.empList.get(saleEmp), GUI.memList.get(saleMem),
                        this.saleProducts));
                CustSale thisSale = GUI.saleList.get(saleCount-1);
                GUI.storeList.get(saleStore).associatedRevenue.add(thisSale);
               
                
                receiptOutput.appendText(recItems);
                receiptOutput.appendText("\n" + thisSale.toString());
                receiptOutput.appendText("\n--------------------------"
                    + "----------------------------------------\n");
                receiptOutput.appendText("\n" + "Thank You " + 
                        GUI.memList.get(saleMem).getFName()  + "!");
                alert.setTitle("Success!");
                alert.setHeaderText("Sale Completed!");
                alert.showAndWait();
                receiptOutput.setEditable(false);
                
                //clear TextFields
                cmboSaleStore.getSelectionModel().clearSelection();
                cmboSaleEmp.getSelectionModel().clearSelection();
                cmboSaleProd.getSelectionModel().clearSelection();
                GUI.cmboMem.getSelectionModel().clearSelection();
            }
            }
            catch (Exception ex)
            {
                System.out.println("Given: " + ex + "Exception in Completing Sale");
            }
                
            });
        btnNewSale.setOnAction(e -> {
            this.productsAvailable.clear();
            this.productsAvailable.addAll(currentSale);
            this.currentSale.clear();
            this.currentSale.addAll(this.productsAvailable);
            cmboSaleStore.getSelectionModel().clearSelection();
            cmboSaleEmp.getSelectionModel().clearSelection();
            cmboSaleProd.getSelectionModel().clearSelection();
            GUI.cmboMem.getSelectionModel().clearSelection();
            receiptOutput.clear();
            txtSaleOutput.clear();
            cbYes.setSelected(false);
            cbNo.setSelected(false);
            btnAddMember.setDisable(false);
            btnAddMember.setDisable(false);
            btnSearchMem.setDisable(false);
            GUI.cmboMem.setDisable(false);
            
            
            
            primaryPane.getChildren().clear();
            
            txtSaleOutput.appendText("Sale Items: " + "\n");
            txtSaleOutput.appendText("--------------------------"
                + "----------------------------------------\n");
            
            primaryPane.add(lblRingSale, 0, 0, 3, 1);
            primaryPane.add(lblSaleDate, 0, 1,2,1);
            primaryPane.add(saleDate, 2, 1);
            saleDate.setMinWidth(2);
            primaryPane.add(lblSaleStore, 0, 2,2,1);
            primaryPane.add(cmboSaleStore, 2, 2);
            cmboSaleStore.setMinWidth(2);
            primaryPane.add(lblSaleEmp, 0, 3,2,1);
            primaryPane.add(cmboSaleEmp, 2, 3);
            primaryPane.add(lblClubMem, 0, 4, 2,1);
            primaryPane.add(lblYes, 0, 5);
        
            primaryPane.add(lblNo, 0, 6);
            primaryPane.add(cbYes, 1, 5);
            primaryPane.add(cbNo, 1, 6);
            primaryPane.add(lblMem, 1, 7,1,1);
            primaryPane.add(GUI.cmboMem, 2, 7);
            GUI.cmboMem.setMaxWidth(Double.MAX_VALUE);
            primaryPane.add(btnSearchMem, 2, 8);
            primaryPane.add(btnAddMember, 1, 8,1,1);
            primaryPane.add(lblSaleProd, 1, 10,1,1);
            primaryPane.add(cmboSaleProd, 2, 10);
            cmboSaleProd.setMaxWidth(Double.MAX_VALUE);
            primaryPane.add(btnAddToSale, 2, 11, 1, 1);
            primaryPane.add(btnRepeatBuy, 0, 11, 2, 1);
            primaryPane.add(btnCompleteSale, 0, 12, 2, 1);
            primaryPane.add(btnNewSale, 0,13,2,1);
            primaryPane.add(txtSaleOutput, 4, 1, 4, 6);
            
        });
    }   
}
