package control;

import javafx.fxml.FXML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import repository.PersonRepository;

@Controller
public class MainController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class.getName());

    @Autowired
    private PersonRepository personRepository;

    @FXML
    private void initialize(){
        initChoiceBoxes();
        initTableData();
    }

    private void initChoiceBoxes() {

    }

    private void initTableData() {

    }

}
