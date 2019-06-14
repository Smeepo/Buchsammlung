package model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "persons")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "person_seq_gen")
    @SequenceGenerator(name = "person_seq_gen", sequenceName = "PERSON_SEQ")
    private Long personId;

    @Column(name = "first_name")
    private String firstName;

    @Override
    public String toString() {
        return "Person{" +
                "personId=" + personId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", street='" + street + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", city='" + city + '\'' +
                ", rentCounter=" + rentCounter +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(getPersonId(), person.getPersonId()) &&
                Objects.equals(getFirstName(), person.getFirstName()) &&
                Objects.equals(getLastName(), person.getLastName()) &&
                Objects.equals(getStreet(), person.getStreet()) &&
                Objects.equals(getHouseNumber(), person.getHouseNumber()) &&
                Objects.equals(getZipCode(), person.getZipCode()) &&
                Objects.equals(getCity(), person.getCity()) &&
                Objects.equals(getRentCounter(), person.getRentCounter());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPersonId(), getFirstName(), getLastName(), getStreet(), getHouseNumber(), getZipCode(), getCity(), getRentCounter());
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getRentCounter() {
        return rentCounter;
    }

    public void setRentCounter(Integer rentCounter) {
        this.rentCounter = rentCounter;
    }

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

    private Person() {}

    public Person(String firstName, String lastName, String street, String houseNumber, String zipCode, String city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.houseNumber = houseNumber;
        this.zipCode = zipCode;
        this.city = city;
        this.rentCounter = 0;
    }
}
