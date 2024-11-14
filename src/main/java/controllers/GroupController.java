package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GroupController implements Initializable {
    @FXML
    private Slider behaviourSlider;

    @FXML
    private TextField denominationField;

    @FXML
    private DatePicker endDate;

    @FXML
    private Slider examSlider;

    @FXML
    private Slider practiceSlider;

    @FXML
    private GridPane root;

    @FXML
    private DatePicker startDate;

    public GroupController () {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GroupControllerView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // initialize sliders
        
    }

    public GridPane getRoot() {
        return root;
    }
}

