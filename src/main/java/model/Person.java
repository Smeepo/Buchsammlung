package model;

import javax.persistence.*;

@Entity
@Table(name = "persons")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "person_seq_gen")
    @SequenceGenerator(name = "person_seq_gen", sequenceName = "PERSON_SEQ")
    private Long personId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "street")
    private String street;

    @Column(name = "house_number")
    private String houseNumber;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "city")
    private String city;

    @Column(name = "rent_counter")
    private Integer rentCounter;

    public Person(String firstName, String lastName, String street, String houseNumber, String zipCode, String city, Integer rentCounter) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.houseNumber = houseNumber;
        this.zipCode = zipCode;
        this.city = city;
        this.rentCounter = rentCounter;
    }
}
