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
    private Button btnDeleteItem = new Button();

    @FXML
    private Button btnDeleteUser = new Button();

    @FXML
    private Button btnDeleteCollection = new Button();

    @FXML
    private TableView<Item> availableTable = new TableView<>();

    @FXML
    private TableView<Item> rentTable = new TableView<>();

    @FXML
    private TableView<Item> itemStatsTable = new TableView<>();

    @FXML
    private TableView<Person> userStatsTable = new TableView<>();

    @FXML
    private TableView<Person> userControlTable = new TableView<>();

    @FXML
    private TableView<Collection> collectionControlTable = new TableView<>();

    @FXML
    private TableView<Item> itemControlTable = new TableView<>();

    @FXML
    private TableView<Collection> collectionStatsTable = new TableView<>();

    @FXML
    private TableColumn<Collection, String> collectionStatsTableColumnName = new TableColumn<>();

    @FXML
    private TableColumn<Collection, String> collectionStatsTableColumnOwner = new TableColumn<>();

    @FXML
    private TableColumn<Collection, String> collectionStatsTableColumnCounter = new TableColumn<>();

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
    private TableColumn<Person, String> userStatsTableColumnName = new TableColumn<>();

    @FXML
    private TableColumn<Person, String> userStatsTableColumnCounter = new TableColumn<>();

    @FXML
    private TableColumn<Item, String> itemStatsTableColumnIsbn = new TableColumn<>();

    @FXML
    private TableColumn<Item, String> itemStatsTableColumnTitle = new TableColumn<>();

    @FXML
    private TableColumn<Item, String> itemStatsTableColumnCounter = new TableColumn<>();

    @FXML
    private TableColumn<Item, String> itemControlTableColumnIsbn = new TableColumn<>();

    @FXML
    private TableColumn<Item, String> itemControlTableColumnTitle = new TableColumn<>();

    @FXML
    private TableColumn<Item, String> itemControlTableColumnAuthor = new TableColumn<>();

    @FXML
    private TableColumn<Item, String> itemControlTableColumnType = new TableColumn<>();

    @FXML
    private TableColumn<Collection, String> collectionControlTableColumnName = new TableColumn<>();

    @FXML
    private TableColumn<Collection, String> collectionControlTableColumnOwner = new TableColumn<>();

    @FXML
    private TableColumn<Person, String> userControlTableColumnName = new TableColumn<>();

    @FXML
    private TableColumn<Person, String> userControlTableColumnAddress = new TableColumn<>();

    @FXML
    private TableColumn<Person, String> userControlTableColumnZip = new TableColumn<>();

    @FXML
    private TableColumn<Person, String> userControlTableColumnCity = new TableColumn<>();


    @FXML
    private void initialize(){
        initChoiceBoxes();
        initTableViews();
        initNewDataButtons();
        initLendButton();
        initReturnButton();
        initDeleteButtons();
        loadRentItemTable();
        loadStats();
        loadUserControl();
        loadItemControl();
        loadCollectionControl();
    }

    private void initDeleteButtons() {
        btnDeleteCollection.setOnAction(event -> {
            collectionControlTable.getSelectionModel().getSelectedItems().stream().filter(Objects::nonNull).forEach(this::deleteCollection);
            loadCollectionControl();
            reloadCollectionChoiceBox();
            loadItemControl();
            loadAvailableItemTable();
            loadRentItemTable();
            loadStats();
        });

        btnDeleteItem.setOnAction(event -> {
            itemControlTable.getSelectionModel().getSelectedItems().stream().filter(Objects::nonNull).forEach(this::deleteItem);
            loadItemControl();
            loadAvailableItemTable();
            loadRentItemTable();
            loadStats();
        });

        btnDeleteUser.setOnAction(event -> {
            userControlTable.getSelectionModel().getSelectedItems().stream().filter(Objects::nonNull).forEach(this::deleteUser);
            loadUserControl();
            reloadPersonChoiceBox();
            loadCollectionControl();
            reloadCollectionChoiceBox();
            loadItemControl();
            loadAvailableItemTable();
            loadRentItemTable();
            loadStats();
        });
    }

    private void deleteUser(Person user) {
        personRepository.delete(user);
        collectionRepository.findAllByOwner(user).forEach(this::deleteCollection);
    }

    private void deleteItem(Item item) {
        itemRepository.delete(item);
    }

    private void deleteCollection(Collection collection) {
        collectionRepository.delete(collection);
        itemRepository.findAllByCollection(collection).forEach(this::deleteItem);
    }

    private void loadUserControl() {
        userControlTable.getItems().clear();
        userControlTable.getItems().addAll(personRepository.findAll());
    }

    private void loadItemControl() {
        itemControlTable.getItems().clear();
        itemControlTable.getItems().addAll(itemRepository.findAll());
    }

    private void loadCollectionControl() {
        collectionControlTable.getItems().clear();
        collectionControlTable.getItems().addAll(collectionRepository.findAll());
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
                personRepository.save(choiceBoxUsers.getValue());
            });
            loadAvailableItemTable();
            loadRentItemTable();
            loadStats();
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
        userStatsTableColumnName.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getLastName() + ", " + param.getValue().getFirstName()));
        userStatsTableColumnCounter.setCellValueFactory(new PropertyValueFactory<>("rentCounter"));

        //Item Stats Table
        itemStatsTableColumnIsbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        itemStatsTableColumnTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        itemStatsTableColumnCounter.setCellValueFactory(new PropertyValueFactory<>("rentCounter"));

        //Collection Stats Table
        collectionStatsTableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        collectionStatsTableColumnOwner.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getOwner().getLastName() + ", " + param.getValue().getOwner().getFirstName()));
        collectionStatsTableColumnCounter.setCellValueFactory(param -> new SimpleStringProperty(Integer.toString(itemRepository.findAllByCollection(param.getValue()).stream().mapToInt(Item::getRentCounter).sum())));

        //User Control Table
        userControlTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        userControlTableColumnName.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getLastName() + ", " + param.getValue().getFirstName()));
        userControlTableColumnAddress.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getStreet() + " " + param.getValue().getHouseNumber()));
        userControlTableColumnCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        userControlTableColumnZip.setCellValueFactory(new PropertyValueFactory<>("zipCode"));

        //Collection Control Table
        collectionControlTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        collectionControlTableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        collectionControlTableColumnOwner.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getOwner().getLastName() + ", " + param.getValue().getOwner().getFirstName()));

        //Item Control Table
        itemControlTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        itemControlTableColumnIsbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        itemControlTableColumnTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        itemControlTableColumnAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        itemControlTableColumnType.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getType().getName()));
    }

    private void loadStats() {
        userStatsTable.getItems().clear();
        userStatsTable.getItems().addAll(personRepository.findAll());

        itemStatsTable.getItems().clear();
        itemStatsTable.getItems().addAll(itemRepository.findAll());

        collectionStatsTable.getItems().clear();
        collectionStatsTable.getItems().addAll(collectionRepository.findAll());
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
