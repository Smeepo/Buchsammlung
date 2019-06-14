package control;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import model.Collection;
import model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import repository.CollectionRepository;
import repository.ItemRepository;
import repository.PersonRepository;

import java.beans.EventHandler;
import java.util.Objects;

@Controller
public class MainController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class.getName());

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private ItemRepository itemRepository;

    @FXML
    private ChoiceBox<Collection> choiceBoxCollections = new ChoiceBox<>();

    @FXML
    private ChoiceBox<Person> choiceBoxUsers = new ChoiceBox<>();

    @FXML
    private ChoiceBox<Person> choiceBoxCollectionOwner = new ChoiceBox<>();

    @FXML
    private TextField txtFirstName = new TextField();

    @FXML
    private TextField txtLastName = new TextField();

    @FXML
    private TextField txtStreet = new TextField();

    @FXML
    private TextField txtCity = new TextField();

    @FXML
    private TextField txtZipCode = new TextField();

    @FXML
    private TextField txtHouseNumber = new TextField();

    @FXML
    private TextField txtCollectionName = new TextField();

    @FXML
    private Button btnAddPerson = new Button();

    @FXML
    private Button btnAddCollection = new Button();

    @FXML
    private void initialize(){
        //personRepository.save(new Person("Hans", "Dampf", "Hauptstraße", "121", "42069", "Dopehausen"));
        initChoiceBoxes();
        initTableData();
        initNewDataButtons();
    }

    private void initNewDataButtons() {
        LOGGER.debug("Initializing Add Person Button");
        btnAddPerson.setOnAction(event -> {
            personRepository.save(new Person(txtFirstName.getText(), txtLastName.getText(), txtStreet.getText(), txtHouseNumber.getText(), txtZipCode.getText(), txtCity.getText()));
            txtFirstName.clear();
            txtLastName.clear();
            txtCity.clear();
            txtHouseNumber.clear();
            txtStreet.clear();
            txtZipCode.clear();
            reloadPersonChoiceBox();
        });

        LOGGER.debug("Initializing Add Collection Button");
        btnAddCollection.setOnAction(event -> {
            collectionRepository.save(new Collection(txtCollectionName.getText(), choiceBoxCollectionOwner.getValue()));
            txtCollectionName.clear();
            choiceBoxCollectionOwner.setValue(null);
            reloadCollectionChoiceBox();
        });
    }

    private void reloadCollectionChoiceBox() {
        LOGGER.debug("Reloading Collection ChoiceBox");
        choiceBoxCollections.getItems().clear();
        collectionRepository.findAll().forEach(collection -> choiceBoxCollections.getItems().add(collection));
    }

    private void reloadPersonChoiceBox() {
        LOGGER.debug("Reloading Users and Collection Owner ChoiceBox");
        choiceBoxUsers.getItems().clear();
        personRepository.findAll().forEach(collection -> choiceBoxUsers.getItems().add(collection));
        choiceBoxCollectionOwner.setItems(choiceBoxUsers.getItems());
    }

    private void initChoiceBoxes() {
        LOGGER.debug("Initializing Collections ChoiceBox");
        collectionRepository.findAll().forEach(collection -> choiceBoxCollections.getItems().add(collection));
        choiceBoxCollections.setConverter(new StringConverter<Collection>() {
            @Override
            public String toString(Collection object) {
                if(Objects.isNull(object)) return "";
                return object.getName();
            }

            @Override
            public Collection fromString(String string) {
                return collectionRepository.findFirstByName(string);
            }
        });

        LOGGER.debug("Initializing Users and Collection Owner ChoiceBox");
        personRepository.findAll().forEach(person -> choiceBoxUsers.getItems().add(person));
        choiceBoxUsers.setConverter(new StringConverter<Person>() {
            @Override
            public String toString(Person object) {
                return object.getLastName() + ", " + object.getFirstName();
            }

            @Override
            public Person fromString(String string) {
                return null;
            }
        });
        choiceBoxCollectionOwner.setConverter(choiceBoxUsers.getConverter());
        choiceBoxCollectionOwner.setItems(choiceBoxUsers.getItems());
    }

    private void initTableData() {

    }

}
