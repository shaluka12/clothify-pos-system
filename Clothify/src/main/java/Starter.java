import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Starter extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("view/view_Login_Form.fxml"))));
        stage.setResizable(false);
        stage.setTitle("Login");
        stage.getIcons().add(new Image("view/img/login.png"));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
