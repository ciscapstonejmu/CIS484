package pkg484groupproj;

import javafx.application.Application;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.*;
import javafx.collections.*;
import javafx.geometry.Pos;

public class CustSaleForm {
    
    public String saleID;
    public static int saleCount = 0;
    
    public Label lblSaleDate = new Label("Date:");
    public Label lblSaleStore = new Label("Store:");
    public Label lblSaleCust = new Label("Customer:");
    public Label lblClubMem = new Label("Thrifty Club Member?");
    public Label lblSaleProd = new Label("Product:");
    //public Label lblSaleProdQuan = new Label("Quantity:");
    
    //public TextField txtSaleProdQuan = new TextField();
    
    public Button btnAddToSale = new Button("Add to Sale ->");
    public Button btnCompleteSale = new Button("Complete Sale ->");
    public Button btnReceipt = new Button("Generate Receipt ->");
    
    public TextArea txtSaleOutput = new TextArea();
    public TextArea receiptOutput = new TextArea();
    public ObservableList<String> isMemList = FXCollections.observableArrayList(
            "Yes",
            "No"
            );
    final ComboBox cmboIsMem = new ComboBox(isMemList);
    public ComboBox cmboSaleProd = new ComboBox(Product.obsProd);
    public ComboBox cmboSaleCust = new ComboBox(Customer.obsCust);
    public ComboBox cmboSaleStore = new ComboBox(Store.obsStore);
    public ComboBox cmboSaleEmployee = new ComboBox(Employee.obsEmp);
    
    public DatePicker saleDate = new DatePicker();
    
    public ArrayList<Product> saleProducts = new ArrayList();
    
    public GUI myParent;
    //Sale Constructor
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
        primaryPane.add(lblSaleCust, 0, 2);
        primaryPane.add(cmboSaleCust, 1, 2);
        cmboSaleCust.setMinWidth(4);
        primaryPane.add(lblClubMem, 0, 3);
        primaryPane.add(cmboIsMem, 1, 3);
        cmboIsMem.setMinWidth(4);
        primaryPane.add(lblSaleProd, 0, 4);
        primaryPane.add(cmboSaleProd, 1, 4);
        cmboSaleProd.setMinWidth(4);
        //primaryPane.add(lblSaleProdQuan, 0, 5);
        //primaryPane.add(txtSaleProdQuan, 1, 5);
        primaryPane.add(btnAddToSale, 0, 5, 2, 2);
        primaryPane.add(btnCompleteSale, 0, 6, 2, 2);
        primaryPane.add(txtSaleOutput, 2, 0, 4, 6);
        
        //txtSaleProdQuan.setPrefWidth(20);
        saleDate.setPrefWidth(100);
        cmboSaleStore.setPrefWidth(100);
        cmboSaleCust.setPrefWidth(100);
        cmboIsMem.setPrefWidth(100);
        cmboSaleProd.setPrefWidth(100);
        
        Scene primaryScene = new Scene(primaryPane, 800, 600); Stage primaryStage = new Stage();
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("Customer Sale Form");
        primaryStage.show();
        
        
        btnAddToSale.setOnAction(e -> {
                     
            int saleProd = myParent.cmboProd.getSelectionModel().getSelectedIndex();
            this.saleProducts.add(GUI.prodList.get(saleProd));
            
            txtSaleOutput.appendText(GUI.prodList.get(saleProd).toString());
            
            cmboSaleProd.getSelectionModel().clearSelection();
            });
        
        //complete the sale
        btnCompleteSale.setOnAction(e -> {
        primaryPane.add(receiptOutput, 5, 6);
        receiptOutput.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        receiptOutput.setText("Receipt: " + "\n");
            for (CustSale saleList : GUI.saleList) {
                receiptOutput.appendText(saleList.toString());
            }
            if(GUI.saleList.isEmpty()){
                receiptOutput.appendText("No Sale to Complete");
            }
            });
        
        
    }
    
}
