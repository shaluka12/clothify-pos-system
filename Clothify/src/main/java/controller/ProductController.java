package controller;

import java.net.URL;
import java.util.ResourceBundle;

import dto.CartTM;
import dto.Item;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.ImageView;

public class ProductController{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView imgItemImage;

    @FXML
    private Label lblCardName;

    @FXML
    private Label lblPrice;

    @FXML
    private Spinner<Integer> spinQty;

    private SpinnerValueFactory<Integer> spinnerValueFactory;

    private Item item;

    private ProductCallback productCallback;

    public void setProductCallback(ProductCallback productCallback) {
        this.productCallback = productCallback;
    }

    public void addData(Item item){
        lblCardName.setText(item.getName());
        lblPrice.setText(item.getPrice().toString());
        this.item = item;

        spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, item.getQty(),0);
        spinQty.setValueFactory(spinnerValueFactory);
    }

    @FXML
    void initialize() {
        assert imgItemImage != null : "fx:id=\"imgItemImage\" was not injected: check your FXML file 'view_cardProduct.fxml'.";
        assert lblCardName != null : "fx:id=\"lblCardName\" was not injected: check your FXML file 'view_cardProduct.fxml'.";
        assert lblPrice != null : "fx:id=\"lblPrice\" was not injected: check your FXML file 'view_cardProduct.fxml'.";
        assert spinQty != null : "fx:id=\"spinqty\" was not injected: check your FXML file 'view_cardProduct.fxml'.";

    }

    public void btnAddToCartonAction(ActionEvent actionEvent) {
        if (spinQty.getValue()>0){
            if (productCallback != null) {
                productCallback.onProductAction(new CartTM(item.getId(),spinQty.getValue(), item.getPrice()*spinQty.getValue()));  // Replace with actual data to pass
            }
        } else {
            new Alert(Alert.AlertType.WARNING,"Please Enter Qty!").show();
        }
    }
}
