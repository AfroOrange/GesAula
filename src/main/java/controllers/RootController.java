package controllers;

import dad.gesaula.ui.model.Grupo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RootController implements Initializable {

    private StudentsController studentsController;
    private GroupController groupController;

    private Grupo grupo;

    @FXML
    private TextField filenameTextField;

    @FXML
    private BorderPane root;

    @FXML
    private TabPane rootTabPanes;

    @FXML
    void onNewAction(ActionEvent event) {
        // initialize tabs
        initializeTabs();

    }

    @FXML
    void onSaveAction(ActionEvent event) {

        // retrieve data from controllers
        grupo = new Grupo();
        grupo.setDenominacion(groupController.getDenominationField().getText());
        grupo.setIniCurso(groupController.getStartDate().getValue());
        grupo.setFinCurso(groupController.getEndDate().getValue());
        grupo.setPesos(groupController.getPesos());
        grupo.getAlumnos().addAll(studentsController.getStudents());

        if (filenameTextField.getText().isEmpty()) {
            Dialog<String> dialog = new Dialog<>();
            dialog.setContentText("Please enter a file name");
            dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
            dialog.showAndWait();

        } else {
            String fileName = filenameTextField.getText();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Selecciona");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML", "*.xml"));
            fileChooser.setInitialFileName(fileName);
            fileChooser.setInitialDirectory(new File(System.getProperty("user.dir"))); //// current directory

            // save group
            try {
                grupo.save(fileChooser.showSaveDialog(null));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public RootController () {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RootControllerView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        grupo = new Grupo();
        initializeTabs();
    }

    public BorderPane getRoot() {
        return root;
    }

    public TabPane getRootTabPanes() {
        return rootTabPanes;
    }

    private void initializeTabs() {
        studentsController = new StudentsController();
        groupController = new GroupController();

        rootTabPanes.getTabs().clear();

        Tab studentsTab = new Tab("Students");
        studentsTab.setContent(studentsController.getRoot());

        Tab groupTab = new Tab("Group");
        groupTab.setContent(groupController.getRoot());

        rootTabPanes.getTabs().addAll(studentsTab, groupTab);
    }
}
