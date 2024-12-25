package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import dto.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import service.ServiceFactory;
import service.SuperService;
import service.custom.ItemService;
import service.custom.OrderDetailsService;
import service.custom.OrdersService;
import service.custom.SupplierService;
import util.ServiceType;

public class DefaultUserFormController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane Cart;

    @FXML
    private AnchorPane Inventory;

    @FXML
    private AnchorPane PlaceOrder;

    @FXML
    private AnchorPane Supplier;

    @FXML
    private JFXComboBox<Integer> cmbSupplierID;

    @FXML
    private TableColumn<?, ?> colCompany;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colItemID;

    @FXML
    private TableColumn<?, ?> colItemName;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colSize;

    @FXML
    private TableColumn<?, ?> colSupplierID;

    @FXML
    private TableColumn<?, ?> colSupplierName;

    @FXML
    private TableColumn<?, ?> colcartItemID;

    @FXML
    private TableColumn<?, ?> colcartPrice;

    @FXML
    private TableColumn<?, ?> colcartQty;

    @FXML
    private TableColumn<?, ?> colitemSupplierID;

    @FXML
    private GridPane gridpane;

    @FXML
    private Label lblTotal;

    @FXML
    private Label lblchange;

    @FXML
    private ScrollPane scrollpane;

    @FXML
    private TableView<CartTM> tblCart;

    @FXML
    private TableView<Item> tblItems;

    @FXML
    private TableView<Supplier> tblSupplier;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtCompany;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtISupplierID;

    @FXML
    private TextField txtItemID;

    @FXML
    private TextField txtItemName;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQty;

    @FXML
    private JFXTextField txtSearchItem;

    @FXML
    private JFXTextField txtSearchSupplier;

    @FXML
    private TextField txtSize;

    @FXML
    private TextField txtSupplierName;

    @FXML
    private Text txtUserName;

    ItemService itemService = ServiceFactory.getInstance().getServiceType(ServiceType.ITEM);
    SupplierService supplierService = ServiceFactory.getInstance().getServiceType(ServiceType.SUPPLIER);
    OrdersService ordersService = ServiceFactory.getInstance().getServiceType(ServiceType.ORDER);
    OrderDetailsService orderDetailsService = ServiceFactory.getInstance().getServiceType(ServiceType.ORDER_DETAILS);
    ObservableList<CartTM> cartTMS = FXCollections.observableArrayList();

    @FXML
    void btnAddItemOnAction(ActionEvent event) {
        if (itemService.addItem(getItemValues())){
            new Alert(Alert.AlertType.INFORMATION,"Successfully Added!").show();
        } else {
            new Alert(Alert.AlertType.ERROR,"Couldn't Add!").show();
        }
        loadItemTable();
    }

    public void setUserName(String name){
        txtUserName.setText(name);
    }

    @FXML
    void btnAddSupplierOnAction(ActionEvent event) {
        if (supplierService.addSupplier(getSupplierValues())){
            new Alert(Alert.AlertType.INFORMATION,"Successfully Added!").show();
        } else {
            new Alert(Alert.AlertType.ERROR,"Couldn't Add!").show();
        }
        loadSupplierTable();
    }

    @FXML
    void btnClearCartOnAction(ActionEvent event) {
        cartTMS.clear();
        loadCartTable();
    }

    @FXML
    void btnDeleteItemOnAction(ActionEvent event) {
        if (itemService.deleteItem(txtItemID.getText())){
            new Alert(Alert.AlertType.INFORMATION,"Successfully Deleted!").show();
        } else {
            new Alert(Alert.AlertType.ERROR,"Couldn't Delete!").show();
        }
        loadItemTable();
    }

    @FXML
    void btnDeleteSupplierOnAction(ActionEvent event) {
        if (supplierService.deleteSupplier(txtISupplierID.getText())){
            new Alert(Alert.AlertType.INFORMATION,"Successfully Deleted!").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Couldn't Delete!").show();
        }
        loadSupplierTable();
    }

    @FXML
    void btnInvetoryOnAction(ActionEvent event) {
        Supplier.setVisible(false);
        Inventory.setVisible(true);
        PlaceOrder.setVisible(false);
        Cart.setVisible(false);

        colItemID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colitemSupplierID.setCellValueFactory(new PropertyValueFactory<>("supplier_Id"));
        cmbSupplierID.setItems(supplierService.getSupplierIds());
        tblItems.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            setItemTextToValues(newValue);
        }));
        loadItemTable();
    }

    private void setItemTextToValues(Item item) {
        txtItemID.setText(item.getId().toString());
        txtItemName.setText(item.getName());
        txtSize.setText(item.getSize());
        txtPrice.setText(item.getPrice().toString());
        txtQty.setText(item.getQty().toString());
        cmbSupplierID.setValue(item.getSupplier_Id());
    }

    private void loadItemTable() {
        tblItems.setItems(itemService.getAll());
    }

    @FXML
    void btnLogOutOnAction(ActionEvent event) {

    }

    @FXML
    void btnPayOnAction(ActionEvent event) {
        ObservableList<OrderDetails> orderDetails = FXCollections.observableArrayList();
        int orderId = ordersService.getAll().size();

        cartTMS.forEach(cartTM ->{
            orderDetails.add(new OrderDetails(
                    orderId,
                    cartTM.getItemCode(),
                    cartTM.getQty()
            ));
        });

        Orders orders = new Orders(orderId, LocalDate.now(),Double.parseDouble(lblTotal.getText()));
        ordersService.placeOrder(orders,orderDetails);
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        Supplier.setVisible(false);
        Inventory.setVisible(false);
        PlaceOrder.setVisible(true);
        Cart.setVisible(true);

        loadItemList();
    }

    @FXML
    void btnReciptOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchItemOnAction(ActionEvent event) {
        setItemTextToValues(itemService.searchItem(txtSearchItem.getText()));
    }

    @FXML
    void btnSearchSupplierOnAction(ActionEvent event) {
        setSupplierTextToValues(supplierService.searchSupplier(txtSearchSupplier.getText()));
    }

    @FXML
    void btnSupplierOnAction(ActionEvent event) {
        Supplier.setVisible(true);
        Inventory.setVisible(false);
        PlaceOrder.setVisible(false);
        Cart.setVisible(false);

        colSupplierID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colSupplierName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tblSupplier.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            setSupplierTextToValues(newValue);
        }));
        loadSupplierTable();
    }

    private void setSupplierTextToValues(Supplier supplier) {
        txtISupplierID.setText(supplier.getId().toString());
        txtSupplierName.setText(supplier.getName());
        txtCompany.setText(supplier.getCompany());
        txtEmail.setText(supplier.getEmail());
    }

    private void loadSupplierTable() {
        tblSupplier.setItems(supplierService.getAll());
    }

    @FXML
    void btnUpdateItemOnAction(ActionEvent event) {
        if (itemService.updateItem(getItemValues())){
            new Alert(Alert.AlertType.INFORMATION,"Successfully Updated!").show();
        } else {
            new Alert(Alert.AlertType.ERROR,"Couldn't Update!").show();
        }
        loadItemTable();
    }

    private Item getItemValues() {
        return new Item(
                Integer.valueOf(txtItemID.getText()),
                txtItemName.getText(),
                txtSize.getText(),
                Double.valueOf(txtPrice.getText()),
                Integer.valueOf(txtQty.getText()),
                cmbSupplierID.getValue()
        );
    }

    @FXML
    void btnUpdateSupplierOnAction(ActionEvent event) {
        if (supplierService.updateSupplier(getSupplierValues())){
            new Alert(Alert.AlertType.INFORMATION,"Successfully Updated!").show();
        } else {
            new Alert(Alert.AlertType.ERROR,"Couldn't Update!").show();
        }
        loadSupplierTable();
    }

    private Supplier getSupplierValues() {
        return new Supplier(
                Integer.valueOf(txtISupplierID.getText()),
                txtSupplierName.getText(),
                txtCompany.getText(),
                txtEmail.getText()
        );
    }

    @FXML
    void initialize() {
        assert Cart != null : "fx:id=\"Cart\" was not injected: check your FXML file 'view_Default_User_Form.fxml'.";
        assert Inventory != null : "fx:id=\"Inventory\" was not injected: check your FXML file 'view_Default_User_Form.fxml'.";
        assert PlaceOrder != null : "fx:id=\"PlaceOrder\" was not injected: check your FXML file 'view_Default_User_Form.fxml'.";
        assert Supplier != null : "fx:id=\"Supplier\" was not injected: check your FXML file 'view_Default_User_Form.fxml'.";
        assert cmbSupplierID != null : "fx:id=\"cmbSupplierID\" was not injected: check your FXML file 'view_Default_User_Form.fxml'.";
        assert colCompany != null : "fx:id=\"colCompany\" was not injected: check your FXML file 'view_Default_User_Form.fxml'.";
        assert colEmail != null : "fx:id=\"colEmail\" was not injected: check your FXML file 'view_Default_User_Form.fxml'.";
        assert colItemID != null : "fx:id=\"colItemID\" was not injected: check your FXML file 'view_Default_User_Form.fxml'.";
        assert colItemName != null : "fx:id=\"colItemName\" was not injected: check your FXML file 'view_Default_User_Form.fxml'.";
        assert colPrice != null : "fx:id=\"colPrice\" was not injected: check your FXML file 'view_Default_User_Form.fxml'.";
        assert colQty != null : "fx:id=\"colQty\" was not injected: check your FXML file 'view_Default_User_Form.fxml'.";
        assert colSize != null : "fx:id=\"colSize\" was not injected: check your FXML file 'view_Default_User_Form.fxml'.";
        assert colSupplierID != null : "fx:id=\"colSupplierID\" was not injected: check your FXML file 'view_Default_User_Form.fxml'.";
        assert colSupplierName != null : "fx:id=\"colSupplierName\" was not injected: check your FXML file 'view_Default_User_Form.fxml'.";
        assert colcartItemID != null : "fx:id=\"colcartItemID\" was not injected: check your FXML file 'view_Default_User_Form.fxml'.";
        assert colcartPrice != null : "fx:id=\"colcartPrice\" was not injected: check your FXML file 'view_Default_User_Form.fxml'.";
        assert colcartQty != null : "fx:id=\"colcartQty\" was not injected: check your FXML file 'view_Default_User_Form.fxml'.";
        assert colitemSupplierID != null : "fx:id=\"colitemSupplierID\" was not injected: check your FXML file 'view_Default_User_Form.fxml'.";
        assert lblTotal != null : "fx:id=\"lblTotal\" was not injected: check your FXML file 'view_Default_User_Form.fxml'.";
        assert lblchange != null : "fx:id=\"lblchange\" was not injected: check your FXML file 'view_Default_User_Form.fxml'.";
        assert scrollpane != null : "fx:id=\"scrollpane\" was not injected: check your FXML file 'view_Default_User_Form.fxml'.";
        assert tblCart != null : "fx:id=\"tblCart\" was not injected: check your FXML file 'view_Default_User_Form.fxml'.";
        assert tblItems != null : "fx:id=\"tblItems\" was not injected: check your FXML file 'view_Default_User_Form.fxml'.";
        assert tblSupplier != null : "fx:id=\"tblSupplier\" was not injected: check your FXML file 'view_Default_User_Form.fxml'.";
        assert txtAmount != null : "fx:id=\"txtAmount\" was not injected: check your FXML file 'view_Default_User_Form.fxml'.";
        assert txtCompany != null : "fx:id=\"txtCompany\" was not injected: check your FXML file 'view_Default_User_Form.fxml'.";
        assert txtEmail != null : "fx:id=\"txtEmail\" was not injected: check your FXML file 'view_Default_User_Form.fxml'.";
        assert txtISupplierID != null : "fx:id=\"txtISupplierID\" was not injected: check your FXML file 'view_Default_User_Form.fxml'.";
        assert txtItemID != null : "fx:id=\"txtItemID\" was not injected: check your FXML file 'view_Default_User_Form.fxml'.";
        assert txtItemName != null : "fx:id=\"txtItemName\" was not injected: check your FXML file 'view_Default_User_Form.fxml'.";
        assert txtPrice != null : "fx:id=\"txtPrice\" was not injected: check your FXML file 'view_Default_User_Form.fxml'.";
        assert txtQty != null : "fx:id=\"txtQty\" was not injected: check your FXML file 'view_Default_User_Form.fxml'.";
        assert txtSearchItem != null : "fx:id=\"txtSearchItem\" was not injected: check your FXML file 'view_Default_User_Form.fxml'.";
        assert txtSearchSupplier != null : "fx:id=\"txtSearchSupplier\" was not injected: check your FXML file 'view_Default_User_Form.fxml'.";
        assert txtSize != null : "fx:id=\"txtSize\" was not injected: check your FXML file 'view_Default_User_Form.fxml'.";
        assert txtSupplierName != null : "fx:id=\"txtSupplierName\" was not injected: check your FXML file 'view_Default_User_Form.fxml'.";
        assert txtUserName != null : "fx:id=\"txtUserName\" was not injected: check your FXML file 'view_Default_User_Form.fxml'.";

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colcartItemID.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colcartQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colcartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        loadItemList();
    }

    private void loadItemList() {
        gridpane.getRowConstraints().clear();
        gridpane.getColumnConstraints().clear();

        int row =0;
        int column =0;

        for(Item item:itemService.getAll()){
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("../view/view_cardProduct.fxml"));
                AnchorPane pane = loader.load();

                ProductController productController = loader.getController();
                productController.addData(item);

                // Set callback to handle actions in the main controller
                productController.setProductCallback(cartTM -> {
                    cartTMS.add(cartTM);
                    lblTotal.setText(String.valueOf(Double.parseDouble(lblTotal.getText())+cartTM.getPrice()));
                    loadCartTable();
                    // Handle the data passed from ProductController here
                });

                if (column == 3){
                    column = 0;
                    row += 1;
                }

                gridpane.add(pane,column++,row);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void loadCartTable() {
        tblCart.setItems(cartTMS);
    }

    public void btnChangeOnAction(ActionEvent actionEvent) {
        lblchange.setText(String.valueOf(Double.parseDouble(txtAmount.getText())-Double.parseDouble(lblTotal.getText())));
    }
}