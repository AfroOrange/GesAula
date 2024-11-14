package controllers;

import dad.gesaula.ui.model.Alumno;
import dad.gesaula.ui.model.Sexo;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddStudentController implements Initializable {

    @FXML
    private DatePicker birthdatePicker;

    @FXML
    private CheckBox doesRepeatBox;

    @FXML
    private TextField nameField;

    @FXML
    private GridPane root;

    @FXML
    private ComboBox<Sexo> sexComboBox;

    @FXML
    private TextField surnameField;

    public AddStudentController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AddStudentView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sexComboBox.setItems(FXCollections.observableArrayList(Sexo.values()));
    }

    public GridPane getRoot() {
        return root;
    }

    public void addStudent(Alumno alumno) {
        nameField.getText();
        surnameField.getText();
        birthdatePicker.getValue();
        sexComboBox.getValue();
        doesRepeatBox.isSelected();
    }

    public DatePicker getBirthdatePicker() {
        return birthdatePicker;
    }

    public void setBirthdatePicker(DatePicker birthdatePicker) {
        this.birthdatePicker = birthdatePicker;
    }

    public CheckBox getDoesRepeatBox() {
        return doesRepeatBox;
    }

    public void setDoesRepeatBox(CheckBox doesRepeatBox) {
        this.doesRepeatBox = doesRepeatBox;
    }

    public TextField getNameField() {
        return nameField;
    }

    public void setNameField(TextField nameField) {
        this.nameField = nameField;
    }

    public void setRoot(GridPane root) {
        this.root = root;
    }

    public ComboBox<Sexo> getSexComboBox() {
        return sexComboBox;
    }

    public void setSexComboBox(ComboBox<Sexo> sexComboBox) {
        this.sexComboBox = sexComboBox;
    }

    public TextField getSurnameField() {
        return surnameField;
    }

    public void setSurnameField(TextField surnameField) {
        this.surnameField = surnameField;
    }
}
