package control;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "model")
@ComponentScan(basePackages = {"model", "repository", "control"})
@EnableJpaRepositories("repository")
public class Main extends Application {

    private Parent rootNode;
    private FXMLLoader fxmlLoader;
    private ConfigurableApplicationContext springContext;
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class.getName());

    @Override
    public void start(Stage primaryStage) throws Exception{

        SpringApplicationBuilder builder = new SpringApplicationBuilder(Main.class);

        this.springContext = builder.headless(false).run();
        fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/sample.fxml"));
        fxmlLoader.setControllerFactory(springContext::getBean);
        rootNode = fxmlLoader.load();
        primaryStage.setTitle("Büchaz");
        primaryStage.setScene(new Scene(rootNode, 1200, 650));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
