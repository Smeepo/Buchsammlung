package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"model", "repository", "sample"})
public class Main extends Application {

    private Parent rootNode;
    private FXMLLoader fxmlLoader;
    private ConfigurableApplicationContext springContext;

    @Override
    public void start(Stage primaryStage) throws Exception{
        fxmlLoader.setLocation(getClass().getResource("/fxml/sample.fxml"));
        rootNode = fxmlLoader.load();
        primaryStage.setTitle("Büchaz");
        primaryStage.setScene(new Scene(rootNode, 1200, 650));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
