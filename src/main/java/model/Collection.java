package model;

import javax.persistence.*;

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
}
