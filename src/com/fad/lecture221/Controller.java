package com.fad.lecture221;

import com.fad.lecture221.model.Contact;
import com.fad.lecture221.model.ContactData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import lombok.Setter;

import java.io.IOException;
import java.util.Optional;

public class Controller {
    @FXML
    private BorderPane mainStage;
    @FXML
    private TableView<Contact> tableView;
    private ContactData contactData;

    public void initialize() {
        contactData = new ContactData();
        contactData.loadContacts();
        if (!contactData.getContacts().isEmpty()) {
            this.tableView.setItems(contactData.getContacts());
        }
    }

    @FXML
    public void showAddContactDialogue() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(this.mainStage.getScene().getWindow());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("NewContactDialog.fxml"));
        dialog.setTitle("New contact");

        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Couldn't load new item dialogue");
            e.printStackTrace();
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        Optional<ButtonType> res = dialog.showAndWait();

        if(res.isPresent() && res.get().equals(ButtonType.OK)) {
            System.out.println("OK clicked");
            NewContactDialogController dialogController = fxmlLoader.getController();
            Contact newContact = dialogController.processForm();
            this.contactData.addContact(newContact);
            this.contactData.saveContacts();
            this.tableView.setItems(this.contactData.getContacts());
            this.tableView.getSelectionModel().select(newContact);
        } else {
            // close the dialog and do nothing
        }
    }

    @FXML
    public void showEditContactDialogue() {
        Optional<Contact> selectedContact = Optional.ofNullable(this.tableView.getSelectionModel().getSelectedItem());
        if (selectedContact.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("No contact selected");
            alert.setContentText("Please select a contact to edit.");
            alert.showAndWait();
            return;
        }
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(this.mainStage.getScene().getWindow());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("NewContactDialog.fxml"));
        dialog.setTitle("Edit contact");

        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Couldn't load new item dialogue");
            e.printStackTrace();
        }
        NewContactDialogController dialogController = fxmlLoader.getController();
        dialogController.editForm(selectedContact.get());
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        Optional<ButtonType> res = dialog.showAndWait();

        if(res.isPresent() && res.get().equals(ButtonType.OK)) {
            System.out.println("OK clicked");
            dialogController.updateContact(selectedContact.get());
            this.contactData.saveContacts();
            this.tableView.setItems(this.contactData.getContacts());
            this.tableView.getSelectionModel().select(selectedContact.get());
        } else {
            // close the dialog and do nothing
        }
    }

    @FXML
    public void handleDeleteContact() {
        Optional<Contact> selectedContact = Optional.ofNullable(this.tableView.getSelectionModel().getSelectedItem());
        if (selectedContact.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("No contact selected");
            alert.setContentText("Please select a contact to delete.");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete contact");
        alert.setHeaderText(null);
        alert.setContentText("Hit 'OK' if you want to delete '" + selectedContact.get().getFirstName() + "' ?");
        Optional<ButtonType> res = alert.showAndWait();

        if (res.isPresent() && res.get().equals(ButtonType.OK)) {
            contactData.deleteContact(selectedContact.get());
            contactData.saveContacts();
        }

    }
}
