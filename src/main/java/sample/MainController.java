package sample;

import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import repository.PersonRepository;

@Controller
public class MainController {

    @Autowired
    private PersonRepository personRepository;

    @FXML
    private void initialize(){

    }

}
