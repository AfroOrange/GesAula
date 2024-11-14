package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RootController implements Initializable {

    private final StudentsController studentsController = new StudentsController();
    private final GroupController groupController = new GroupController();

    @FXML
    private BorderPane root;

    @FXML
    private TabPane rootTabPanes;

    @FXML
    void onNewAction(ActionEvent event) {

    }

    @FXML
    void onSaveAction(ActionEvent event) {

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
        rootTabPanes.getTabs().clear();

        Tab studentsTab = new Tab("Students");
        studentsTab.setContent(new StudentsController().getRoot());

        Tab groupTab = new Tab("Group");
        groupTab.setContent(new GroupController().getRoot());

        rootTabPanes.getTabs().addAll(studentsTab, groupTab);
    }

    public BorderPane getRoot() {
        return root;
    }

    public TabPane getRootTabPanes() {
        return rootTabPanes;
    }
}
