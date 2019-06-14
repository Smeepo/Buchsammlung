package model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "collections")
public class Collection {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "coll_seq_gen")
    @SequenceGenerator(name = "coll_seq_gen", sequenceName = "COLL_SEQ")
    private Long collectionId;

    @Column
    private String name;

    @OneToOne
    private Person owner;

    @Override
    public String toString() {
        return "Collection{" +
                "collectionId=" + collectionId +
                ", name='" + name + '\'' +
                ", owner=" + owner +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Collection)) return false;
        Collection that = (Collection) o;
        return Objects.equals(getCollectionId(), that.getCollectionId()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getOwner(), that.getOwner());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCollectionId(), getName(), getOwner());
    }

    public Long getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Long collectionId) {
        this.collectionId = collectionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }
}
