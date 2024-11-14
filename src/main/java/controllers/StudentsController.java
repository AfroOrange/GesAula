package controllers;

import dad.gesaula.ui.model.Alumno;
import dad.gesaula.ui.model.Sexo;
import javafx.beans.Observable;
import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class StudentsController implements Initializable {


    private final ListProperty<Alumno> students = new SimpleListProperty<>(FXCollections.observableArrayList());
    private final AddStudentController addStudentController = new AddStudentController();
    private final ObjectProperty<Alumno> selectedStudent = new SimpleObjectProperty<>();

    @FXML
    private TableColumn<Alumno, LocalDate> birthdateColumn;

    @FXML
    private TableColumn<Alumno, String> nameColumn;

    @FXML
    private VBox newEntryRoot;

    @FXML
    private SplitPane root;

    @FXML
    private Label selectLabel;

    @FXML
    private TableView<Alumno> studentsTable;

    @FXML
    private TableColumn<Alumno, String> surnameColumn;

    public StudentsController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/StudentsControllerView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // initialize columns
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        surnameColumn.setCellValueFactory(cellData -> cellData.getValue().apellidosProperty());
        birthdateColumn.setCellValueFactory(cellData -> cellData.getValue().fechaNacimientoProperty());

        // Bind the table to the list of students
        selectedStudent.bind(studentsTable.getSelectionModel().selectedItemProperty());
        selectedStudent.addListener(this::onSelectedStudentChanged);

        // Add a listener to update bindings when selectedStudent changes
        selectedStudent.addListener((observable, oldValue, newValue) -> {
            if (oldValue != null) {
                // Unbind old value
                addStudentController.getNameField().textProperty().unbindBidirectional(oldValue.nombreProperty());
                addStudentController.getSurnameField().textProperty().unbindBidirectional(oldValue.apellidosProperty());
                addStudentController.getBirthdatePicker().valueProperty().unbindBidirectional(oldValue.fechaNacimientoProperty());
                addStudentController.getSexComboBox().valueProperty().unbindBidirectional(oldValue.sexoProperty());
                addStudentController.getDoesRepeatBox().selectedProperty().unbindBidirectional(oldValue.repiteProperty());
            }
            if (newValue != null) {
                // Bind new value
                addStudentController.getNameField().textProperty().bindBidirectional(newValue.nombreProperty());
                addStudentController.getSurnameField().textProperty().bindBidirectional(newValue.apellidosProperty());
                addStudentController.getBirthdatePicker().valueProperty().bindBidirectional(newValue.fechaNacimientoProperty());
                addStudentController.getSexComboBox().valueProperty().bindBidirectional(newValue.sexoProperty());
                addStudentController.getDoesRepeatBox().selectedProperty().bindBidirectional(newValue.repiteProperty());
            }
        });
    }

    private void onSelectedStudentChanged(ObservableValue<? extends Alumno> o, Alumno ov, Alumno nv) {
        newEntryRoot.getChildren().clear();
        if (nv == null) {
            newEntryRoot.getChildren().add(selectLabel);
            newEntryRoot.setAlignment(Pos.CENTER);
        } else {
            newEntryRoot.getChildren().add(addStudentController.getRoot());
            newEntryRoot.setAlignment(Pos.TOP_CENTER);
        }
    }

    @FXML
    void onAddStudent(ActionEvent event) {
        // adds a new entry with default name and surname values
        Alumno alumno = new Alumno();
        alumno.setNombre("Sin nombre");
        alumno.setApellidos("Sin apellidos");

        students.add(alumno);
        studentsTable.setItems(students);
        studentsTable.getSelectionModel().select(alumno);

    }

    @FXML
    void onRemoveStudent(ActionEvent event) {
        if (studentsTable.getSelectionModel().getSelectedItem() != null) {
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Eliminar alumno");
            dialog.setContentText("¿Estás seguro de eliminar el registro?");
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);

            if (dialog.showAndWait().get() == ButtonType.YES) {
                students.remove(studentsTable.getSelectionModel().getSelectedItem());
            }
        }
    }

    public SplitPane getRoot() {
        return root;
    }
}
