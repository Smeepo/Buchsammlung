package control;

import com.aquafx_project.AquaFx;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import model.Collection;
import model.Item;
import model.ItemType;
import model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import repository.CollectionRepository;
import repository.ItemRepository;
import repository.PersonRepository;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
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
    private ChoiceBox<ItemType> newItemType = new ChoiceBox<>();

    @FXML
    private ChoiceBox<Collection> newItemCollection = new ChoiceBox<>();

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
    private TextField txtIsbn = new TextField();

    @FXML
    private TextField txtTitle = new TextField();

    @FXML
    private TextField txtDescription = new TextField();

    @FXML
    private TextField txtAuthor = new TextField();

    @FXML
    private Button btnAddPerson = new Button();

    @FXML
    private Button btnAddCollection = new Button();

    @FXML
    private Button btnAddItem = new Button();

    @FXML
    private Button btnLendItem = new Button();

    @FXML
    private Button btnReturnItem = new Button();

    @FXML
    private TableView<Item> availableTable = new TableView<>();

    @FXML
    private TableView<Item> rentTable = new TableView<>();

    @FXML
    private TableColumn<Item, String> availableTableColumnIsbn = new TableColumn<>();

    @FXML
    private TableColumn<Item, String> availableTableColumnTitle = new TableColumn<>();

    @FXML
    private TableColumn<Item, String> availableTableColumnDescription = new TableColumn<>();

    @FXML
    private TableColumn<Item, String> availableTableColumnAuthor = new TableColumn<>();

    @FXML
    private TableColumn<Item, String> availableTableColumnType = new TableColumn<>();

    @FXML
    private TableColumn<Item, String> rentTableColumnIsbn = new TableColumn<>();

    @FXML
    private TableColumn<Item, String> rentTableColumnTitle = new TableColumn<>();

    @FXML
    private TableColumn<Item, String> rentTableColumnCollection = new TableColumn<>();

    @FXML
    private TableColumn<Item, String> rentTableColumnAuthor = new TableColumn<>();

    @FXML
    private TableColumn<Item, String> rentTableColumnType = new TableColumn<>();

    @FXML
    private TableColumn<Item, String> rentTableColumnUser = new TableColumn<>();

    @FXML
    private void initialize(){
        initChoiceBoxes();
        initTableViews();
        initNewDataButtons();
        initLendButton();
        initReturnButton();
        loadRentItemTable();
    }

    private void initReturnButton() {
        btnReturnItem.setOnAction(event -> {
            rentTable.getSelectionModel().getSelectedItems().stream().filter(Objects::nonNull).forEach(item -> {
                item.returnItem();
                itemRepository.save(item);
            });
            loadRentItemTable();
            loadAvailableItemTable();
        });
    }

    private void initLendButton() {
        btnLendItem.setOnAction(event -> {
            availableTable.getSelectionModel().getSelectedItems().stream().filter(Objects::nonNull).forEach(item -> {
                item.rentItem(choiceBoxUsers.getValue());
                itemRepository.save(item);
                personRepository.save(item.getRentedBy());
            });
            loadAvailableItemTable();
            loadRentItemTable();
        });

    }

    private void initTableViews() {
        //Available Items Table
        availableTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        availableTableColumnIsbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        availableTableColumnIsbn.prefWidthProperty().bind(availableTable.widthProperty().divide(10));
        availableTableColumnTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        availableTableColumnTitle.prefWidthProperty().bind(availableTable.widthProperty().multiply(2).divide(10));
        availableTableColumnAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        availableTableColumnAuthor.prefWidthProperty().bind(availableTable.widthProperty().multiply(2).divide(10));
        availableTableColumnDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        availableTableColumnDescription.prefWidthProperty().bind(availableTable.widthProperty().multiply(4).divide(10));
        availableTableColumnType.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getType().getName()));
        availableTableColumnType.prefWidthProperty().bind(availableTable.widthProperty().divide(10).subtract(2));

        //Rent Items Table
        rentTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        rentTableColumnIsbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        rentTableColumnAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        rentTableColumnTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        rentTableColumnType.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getType().getName()));
        rentTableColumnCollection.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getCollection().getName()));
        rentTableColumnUser.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getRentedBy().getLastName() + ", " + param.getValue().getRentedBy().getFirstName()));

        //User Stats Table



        //Item Stats Table
        //TODO
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

        LOGGER.debug("Inititalizing Add Item Button");
        btnAddItem.setOnAction(event -> {
            itemRepository.save(new Item(txtIsbn.getText(), txtTitle.getText(), txtDescription.getText(), txtAuthor.getText(), newItemType.getValue(), newItemCollection.getValue()));
            txtIsbn.clear();
            txtTitle.clear();
            txtDescription.clear();
            txtAuthor.clear();
            loadAvailableItemTable();
        });
    }

    private void loadAvailableItemTable() {
        availableTable.getItems().clear();
        availableTable.getItems().addAll(itemRepository.findAllByCollectionAndRentedByIsNull(choiceBoxCollections.getValue()));
    }

    private void loadRentItemTable() {
        rentTable.getItems().clear();
        rentTable.getItems().addAll(itemRepository.findAllByRentedByIsNotNull());
    }

    private void reloadCollectionChoiceBox() {
        LOGGER.debug("Reloading Collection ChoiceBox");
        choiceBoxCollections.getItems().clear();
        collectionRepository.findAll().forEach(collection -> choiceBoxCollections.getItems().add(collection));
        newItemCollection.setItems(choiceBoxCollections.getItems());
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
        choiceBoxCollections.setOnAction(event -> loadAvailableItemTable());
        newItemCollection.setItems(choiceBoxCollections.getItems());
        newItemCollection.setConverter(choiceBoxCollections.getConverter());

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

        LOGGER.debug("Initializing ItemType ChoiceBox");
        newItemType.setConverter(new StringConverter<ItemType>() {
            @Override
            public String toString(ItemType object) {
                return object.getName();
            }

            @Override
            public ItemType fromString(String string) {
                return null;
            }
        });
        for (ItemType value : ItemType.values()) {
            newItemType.getItems().add(value);
        }

    }

}
