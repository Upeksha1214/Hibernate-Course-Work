import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.hibernate.Session;
import util.FactoryConfiguration;

import java.io.IOException;
import java.net.URL;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        URL resource = getClass().getResource("view/DashBord.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage stage=new Stage();
        Scene scene = new Scene(load);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        /*primaryStage.initStyle(StageStyle.TRANSPARENT);*/
        primaryStage.show();

        Session session = FactoryConfiguration.getInstance().getSession();
        session.close();


    }
}
