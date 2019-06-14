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

    public Item(String isbn, String title, String description, String author, ItemType type, Person rentedBy, Integer rentCounter, Collection collection) {
        this.isbn = isbn;
        this.title = title;
        this.description = description;
        this.author = author;
        this.type = type;
        this.rentedBy = rentedBy;
        this.rentCounter = rentCounter;
        this.collection = collection;
    }
}
