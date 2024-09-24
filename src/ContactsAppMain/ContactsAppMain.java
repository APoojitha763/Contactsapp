package ContactsAppMain;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ContactsAppMain extends Application {
    private ListView<Contact> contactsListView;
    private TextField firstNameField;
    private TextField lastNameField;
    private TextField emailField;
    private TextField phoneField;
    private ObservableList<Contact> contacts = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Contacts App");

       
        contactsListView = new ListView<>();
        contactsListView.setItems(contacts);

        firstNameField = new TextField();
        lastNameField = new TextField();
        emailField = new TextField();
        phoneField = new TextField();

        Button addButton = new Button("Add Contact");
        addButton.setOnAction(e -> addContact());

        Button deleteButton = new Button("Delete Contact");
        deleteButton.setOnAction(e -> deleteContact());

        
        VBox contactForm = new VBox(10);
        contactForm.getChildren().addAll(
                new Label("First Name:"),
                firstNameField,
                new Label("Last Name:"),
                lastNameField,
                new Label("Email:"),
                emailField,
                new Label("Phone:"),
                phoneField,
                addButton,
                deleteButton
        );

        HBox root = new HBox(10);
        root.getChildren().addAll(contactsListView, contactForm);
        root.setPadding(new Insets(10));

       
        contactsListView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> displayContactDetails(newValue));

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void displayContactDetails(Contact contact) {
        if (contact != null) {
            firstNameField.setText(contact.getFirstName());
            lastNameField.setText(contact.getLastName());
            emailField.setText(contact.getEmail());
            phoneField.setText(contact.getPhone());
        } else {
            clearContactDetails();
        }
    }

    private void clearContactDetails() {
        firstNameField.clear();
        lastNameField.clear();
        emailField.clear();
        phoneField.clear();
    }
    private void addContact() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        if (!firstName.isEmpty() && !lastName.isEmpty()) {
            Contact newContact = new Contact(firstName, lastName, email, phone);
            contacts.add(newContact);
            clearContactDetails();
        } }
    private void deleteContact() {
        Contact selectedContact = contactsListView.getSelectionModel().getSelectedItem();
        if (selectedContact != null) {
            contacts.remove(selectedContact);
            clearContactDetails();
        } }
    public static class Contact {
        private String firstName;
        private String lastName;
        private String email;
        private String phone;

        public Contact(String firstName, String lastName, String email, String phone) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.phone = phone;
        }
        public String getFirstName() {
            return firstName;
        }
        public String getLastName() {
            return lastName;
        }
        public String getEmail() {
            return email;
        }
        public String getPhone() {
            return phone;
        }
        @Override
        public String toString() {
            return lastName;
        }
    }
}
