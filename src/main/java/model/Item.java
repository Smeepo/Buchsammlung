package model;




import javax.persistence.*;

@Entity
@Table(name = "items")
public class Item {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "item_seq_gen")
    @SequenceGenerator(name = "item_seq_gen", sequenceName = "ITEM_SEQ")
    private Long itemId;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "author")
    private String author;

    @Column(name = "type")
    private ItemType type;

    @ManyToOne
    private Person rentedBy;

    @Column(name = "rentCounter")
    private Integer rentCounter;

    @ManyToOne
    private Collection collection;

    private Item() {}

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public Person getRentedBy() {
        return rentedBy;
    }

    public void setRentedBy(Person rentedBy) {
        this.rentedBy = rentedBy;
    }

    public Integer getRentCounter() {
        return rentCounter;
    }

    public void setRentCounter(Integer rentCounter) {
        this.rentCounter = rentCounter;
    }

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    public void rentItem(Person user) {
        if (this.getType() != ItemType.EBOOK) this.setRentedBy(user);
        this.setRentCounter(this.getRentCounter()+1);
        user.incrementRentCounter();
    }

    public void returnItem() {
        setRentedBy(null);
    }

    public Item(String isbn, String title, String description, String author, ItemType type, Collection collection) {
        this.isbn = isbn;
        this.title = title;
        this.description = description;
        this.author = author;
        this.type = type;
        this.rentCounter = 0;
        this.collection = collection;
    }
}
