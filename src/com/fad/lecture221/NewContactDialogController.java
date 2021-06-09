package com.fad.lecture221;

import com.fad.lecture221.model.Contact;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class NewContactDialogController {
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField phoneNumberTextField;
    @FXML
    private TextField notesTextField;

    public Contact processForm() {
        String firstName =  this.firstNameTextField.getText().trim();
        String lastName = this.lastNameTextField.getText().trim();
        String phoneNumber = this.phoneNumberTextField.getText().trim();
        String notes = this.notesTextField.getText().trim();

        Contact contact = new Contact(firstName, lastName, phoneNumber, notes);
        return contact;
    }

    public void editForm(Contact contact) {
        this.firstNameTextField.setText(contact.getFirstName());
        this.lastNameTextField.setText(contact.getLastName());
        this.notesTextField.setText(contact.getNotes());
        this.phoneNumberTextField.setText(contact.getPhoneNumber());
    }

    public void updateContact(Contact contact) {
        contact.setFirstName(this.firstNameTextField.getText().trim());
        contact.setLastName(this.lastNameTextField.getText().trim());
        contact.setPhoneNumber(this.phoneNumberTextField.getText().trim());
        contact.setNotes(this.notesTextField.getText().trim());
    }
}
