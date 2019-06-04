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
    
    public ObservableList<String> isMemList = FXCollections.observableArrayList(
            "Yes",
            "No"
            );
    final ComboBox cmboIsMem = new ComboBox(isMemList);
    
    public ComboBox cmboSaleProd = new ComboBox(GUI.obsProd);
    public ComboBox cmboSaleCust = new ComboBox(GUI.obsCust);
    public ComboBox cmboSaleStore = new ComboBox(GUI.obsStore);
    
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
        saleDate.setMinWidth(3);
        primaryPane.add(lblSaleStore, 0, 1);
        primaryPane.add(myParent.cmboStore, 1, 1);
        primaryPane.add(lblSaleCust, 0, 2);
        primaryPane.add(myParent.cmboCust, 1, 2);
        myParent.cmboCust.setMinWidth(3);
        primaryPane.add(lblClubMem, 0, 3);
        primaryPane.add(cmboIsMem, 1, 3);
        cmboIsMem.setMinWidth(3);
        primaryPane.add(lblSaleProd, 0, 4);
        primaryPane.add(myParent.cmboProd, 1, 4);
        myParent.cmboProd.setMinWidth(3);
        //primaryPane.add(lblSaleProdQuan, 0, 5);
        //primaryPane.add(txtSaleProdQuan, 1, 5);
        primaryPane.add(btnAddToSale, 0, 5, 2, 2);
        primaryPane.add(btnCompleteSale, 0, 6, 2, 2);
        primaryPane.add(txtSaleOutput, 2, 0, 4, 6);
        
        //txtSaleProdQuan.setPrefWidth(20);
        saleDate.setPrefWidth(100);
        
        Scene primaryScene = new Scene(primaryPane, 800, 600);
        
        Stage primaryStage = new Stage();
        primaryStage.setScene(primaryScene);
        primaryStage.setTitle("Customer Sale Form");
        primaryStage.show();
        
        
        //Set Add Product Button
        btnAddToSale.setOnAction(e -> {
            
            int saleProd = myParent.cmboProd.getSelectionModel().getSelectedIndex();
            this.saleProducts.add(GUI.prodList.get(saleProd));
            
            txtSaleOutput.appendText(GUI.prodList.get(saleProd).toString());
            
            cmboSaleProd.getSelectionModel().clearSelection();
            
        });
        
        //Complete the Sale
        btnCompleteSale.setOnAction(e -> {
        primaryPane.add(receiptOutput, 5, 0);
        receiptOutput.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            for (CustSale saleList : GUI.saleList) {
                receiptOutput.appendText(saleList.toString());
            }
            });
        
    }
    
}
