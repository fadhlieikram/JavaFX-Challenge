package com.fad.lecture221.model;

import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Contact {
    private SimpleStringProperty firstName = new SimpleStringProperty("");
    private SimpleStringProperty lastName = new SimpleStringProperty("");
    private SimpleStringProperty phoneNumber = new SimpleStringProperty("");
    private SimpleStringProperty notes = new SimpleStringProperty("");

    public Contact(String firstName, String lastName, String phoneNumber, String notes) {
        this.firstName.set(firstName);
        this.lastName.set(lastName);
        this.phoneNumber.set(phoneNumber);
        this.notes.set(notes);
    }

    public Contact() {}

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public void setNotes(String notes) {
        this.notes.set(notes);
    }

    public String getNotes() {
        return notes.get();
    }

    public SimpleStringProperty notesProperty() {
        return notes;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public SimpleStringProperty phoneNumberProperty() {
        return phoneNumber;
    }
}
