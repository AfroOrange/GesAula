package controllers;

import dad.gesaula.ui.model.Alumno;
import dad.gesaula.ui.model.Grupo;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
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
        alumno.setFechaNacimiento(LocalDate.now());

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

            // adds icon to dialog
            ImageView icon = new ImageView(Objects.requireNonNull(getClass().getResource("/images/app-icon-64x64.png")).toString());
            dialog.setGraphic(icon);

            if (dialog.showAndWait().get() == ButtonType.YES) {
                students.remove(studentsTable.getSelectionModel().getSelectedItem());
            }
        }
    }

    public SplitPane getRoot() {
        return root;
    }

    public ObservableList<Alumno> getStudents() {
        return students.get();
    }

    public ListProperty<Alumno> studentsProperty() {
        return students;
    }

    public AddStudentController getAddStudentController() {
        return addStudentController;
    }

    public Alumno getSelectedStudent() {
        return selectedStudent.get();
    }

    public ObjectProperty<Alumno> selectedStudentProperty() {
        return selectedStudent;
    }

    public TableColumn<Alumno, LocalDate> getBirthdateColumn() {
        return birthdateColumn;
    }

    public void setBirthdateColumn(TableColumn<Alumno, LocalDate> birthdateColumn) {
        this.birthdateColumn = birthdateColumn;
    }

    public TableColumn<Alumno, String> getNameColumn() {
        return nameColumn;
    }

    public void setNameColumn(TableColumn<Alumno, String> nameColumn) {
        this.nameColumn = nameColumn;
    }

    public VBox getNewEntryRoot() {
        return newEntryRoot;
    }

    public void setNewEntryRoot(VBox newEntryRoot) {
        this.newEntryRoot = newEntryRoot;
    }

    public void setRoot(SplitPane root) {
        this.root = root;
    }

    public Label getSelectLabel() {
        return selectLabel;
    }

    public void setSelectLabel(Label selectLabel) {
        this.selectLabel = selectLabel;
    }

    public TableView<Alumno> getStudentsTable() {
        return studentsTable;
    }

    public void setStudentsTable(TableView<Alumno> studentsTable) {
        this.studentsTable = studentsTable;
    }

    public TableColumn<Alumno, String> getSurnameColumn() {
        return surnameColumn;
    }

    public void setSurnameColumn(TableColumn<Alumno, String> surnameColumn) {
        this.surnameColumn = surnameColumn;
    }
}
