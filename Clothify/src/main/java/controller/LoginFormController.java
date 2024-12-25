package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dto.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import service.ServiceFactory;
import service.custom.UserService;
import util.ServiceType;

public class LoginFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField txtemail;

    @FXML
    private JFXPasswordField txtpassword;

    @FXML
    void btnAdminUserOnAction(ActionEvent event) {
        UserService userService = ServiceFactory.getInstance().getServiceType(ServiceType.USER);
        User user = userService.searchUser(txtemail.getText());
        Stage stage = new Stage();

        if(user.getPassword().equals(txtpassword.getText())){

            if (user.getIsAdmin()){
                try {
                    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/view_Admin_User_Form.fxml"))));
                    stage.setResizable(false);
                    stage.setTitle("Admin Dashboard");
                    stage.getIcons().add(new Image("view/img/user.png"));
                    stage.show();
                } catch (IOException e) {
                    new Alert(Alert.AlertType.ERROR,e.getMessage());
                }
            }else {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/view_Default_User_Form.fxml"));
                    Parent root = loader.load();

                    DefaultUserFormController controller = loader.getController();

                    controller.setUserName(user.getName());
                    stage.setScene(new Scene(root));
                    stage.setResizable(false);
                    stage.setTitle("User Dashboard");
                    stage.getIcons().add(new Image("view/img/emploers.png"));
                    stage.show();
                } catch (IOException e) {
                    new Alert(Alert.AlertType.ERROR,e.getMessage());
                }
            }

        } else {
            new Alert(Alert.AlertType.ERROR,"Wrong Password..!").show();
        }

    }

    @FXML
    void initialize() {
        assert txtemail != null : "fx:id=\"txtemail\" was not injected: check your FXML file 'view_Login_Form.fxml'.";
        assert txtpassword != null : "fx:id=\"txtpassword\" was not injected: check your FXML file 'view_Login_Form.fxml'.";
    }
}