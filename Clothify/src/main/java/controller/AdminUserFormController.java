package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import dto.Orders;
import dto.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import service.ServiceFactory;
import service.custom.OrdersService;
import service.custom.UserService;
import util.ServiceType;

public class AdminUserFormController implements Initializable {

    @FXML
    private AnchorPane Employees;

    @FXML
    private AnchorPane DashBoard;

    @FXML
    private TableView<User> tblEmployee;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXComboBox<String> cmbIsAdmin;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colEmployeeID;

    @FXML
    private TableColumn<?, ?> colIsAdmin;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPassword;

    @FXML
    private Text lblEarned;

    @FXML
    private Text lblEmployers;

    @FXML
    private Text lblOrders;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtEmployeeID;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;

    @FXML
    private JFXTextField txtSearchEmployee;

    public UserService userService = ServiceFactory.getInstance().getServiceType(ServiceType.USER);

    ObservableList<User> userObservableList = userService.getAll();

    @FXML
    void btnAddOnAction(ActionEvent event) {
        User user = new User(
                Integer.valueOf(txtEmployeeID.getText()),
                txtName.getText(),
                txtEmail.getText(),
                txtPassword.getText(),
                cmbIsAdmin.getValue().equals("Admin")
        );
        if(userService.addUser(user)){
            new Alert(Alert.AlertType.INFORMATION,"Employee Added !!").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Employee Not Added :(").show();
        }
        loadTable();
    }

    @FXML
    void btnDashBordOnAction(ActionEvent event) {
        Employees.setVisible(false);
        DashBoard.setVisible(true);
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        if (userService.deleteUser(txtEmployeeID.getText())){
            new Alert(Alert.AlertType.INFORMATION,"Employee Deleted !!").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Can,t Delete Employee !").show();
        }
        loadTable();
    }

    @FXML
    void btnEmployeeOnAction(ActionEvent event) {
        DashBoard.setVisible(false);
        Employees.setVisible(true);

        colEmployeeID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colIsAdmin.setCellValueFactory(new PropertyValueFactory<>("isAdmin"));

        ObservableList<String> titles = FXCollections.observableArrayList();
        titles.add("Admin");
        titles.add("Default");
        cmbIsAdmin.setItems(titles);
        tblEmployee.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            setTextToValues(newValue);
        }));
        loadTable();
    }

    @FXML
    void btnLogOutOnAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.WARNING,"Do you want to exit ?");
        alert.setTitle("Confirmation Massage");
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.get() == ButtonType.OK){
        }
    }

    @FXML
    void btnSearchEmployeeOnAction(ActionEvent event) {
        setTextToValues(userService.searchUser(txtSearchEmployee.getText()));
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        User user = new User(
                Integer.valueOf(txtEmployeeID.getText()),
                txtName.getText(),
                txtEmail.getText(),
                txtPassword.getText(),
                cmbIsAdmin.getValue().equals("Admin")
        );
        if(userService.updateUser(user)){
            new Alert(Alert.AlertType.INFORMATION,"Employee Updated !!").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Employee Not Updated :(").show();
        }
        loadTable();
    }

    @FXML
    void initialize() {
        assert cmbIsAdmin != null : "fx:id=\"cmbIsAdmin\" was not injected: check your FXML file 'view_Admin_User_Form.fxml'.";
        assert colEmail != null : "fx:id=\"colEmail\" was not injected: check your FXML file 'view_Admin_User_Form.fxml'.";
        assert colEmployeeID != null : "fx:id=\"colEmployeeID\" was not injected: check your FXML file 'view_Admin_User_Form.fxml'.";
        assert colIsAdmin != null : "fx:id=\"colIsAdmin\" was not injected: check your FXML file 'view_Admin_User_Form.fxml'.";
        assert colName != null : "fx:id=\"colName\" was not injected: check your FXML file 'view_Admin_User_Form.fxml'.";
        assert colPassword != null : "fx:id=\"colPassword\" was not injected: check your FXML file 'view_Admin_User_Form.fxml'.";
        assert lblEarned != null : "fx:id=\"lblEarned\" was not injected: check your FXML file 'view_Admin_User_Form.fxml'.";
        assert lblEmployers != null : "fx:id=\"lblEmployers\" was not injected: check your FXML file 'view_Admin_User_Form.fxml'.";
        assert lblOrders != null : "fx:id=\"lblOrders\" was not injected: check your FXML file 'view_Admin_User_Form.fxml'.";
        assert txtEmail != null : "fx:id=\"txtEmail\" was not injected: check your FXML file 'view_Admin_User_Form.fxml'.";
        assert txtEmployeeID != null : "fx:id=\"txtEmployeeID\" was not injected: check your FXML file 'view_Admin_User_Form.fxml'.";
        assert txtName != null : "fx:id=\"txtName\" was not injected: check your FXML file 'view_Admin_User_Form.fxml'.";
        assert txtPassword != null : "fx:id=\"txtPassword\" was not injected: check your FXML file 'view_Admin_User_Form.fxml'.";
        assert txtSearchEmployee != null : "fx:id=\"txtSearchEmployee\" was not injected: check your FXML file 'view_Admin_User_Form.fxml'.";
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        OrdersService ordersService = ServiceFactory.getInstance().getServiceType(ServiceType.ORDER);
        ObservableList<Orders> ordersObservableList = ordersService.getAll();

        ordersObservableList.forEach(orders -> lblEarned.setText(String.valueOf(Double.parseDouble(lblEarned.getText())+orders.getCost())));
        lblOrders.setText(String.valueOf(ordersObservableList.size()));
        lblEmployers.setText(String.valueOf(userObservableList.size()-1));
    }

    private void setTextToValues(User newValue) {
        txtEmployeeID.setText(newValue.getId().toString());
        txtName.setText(newValue.getName());
        txtEmail.setText(newValue.getEmail());
        txtPassword.setText(newValue.getPassword());
        cmbIsAdmin.setValue(newValue.getIsAdmin() ? "Admin" : "User");
    }

    private void loadTable() {
        tblEmployee.setItems(userService.getAll());
    }
}
