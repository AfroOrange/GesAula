package controllers;

import dad.gesaula.ui.model.Grupo;
import dad.gesaula.ui.model.Pesos;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GroupController implements Initializable {

    private Pesos pesos = new Pesos();

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

    @FXML
    private Label examLabel;

    @FXML
    private Label practiceLabel;

    @FXML
    private Label behaviourLabel;

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
        examSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            examLabel.setText(String.format("%.2f", newValue));
        });

        practiceSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            practiceLabel.setText(String.format("%.2f", newValue));
        });

        behaviourSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            behaviourLabel.setText(String.format("%.2f", newValue));
        });

        pesos.actitudProperty().bind(behaviourSlider.valueProperty());
        pesos.examenesProperty().bind(examSlider.valueProperty());
        pesos.practicasProperty().bind(practiceSlider.valueProperty());

    }

    public GridPane getRoot() {
        return root;
    }

    public Slider getBehaviourSlider() {
        return behaviourSlider;
    }

    public void setBehaviourSlider(Slider behaviourSlider) {
        this.behaviourSlider = behaviourSlider;
    }

    public TextField getDenominationField() {
        return denominationField;
    }

    public void setDenominationField(TextField denominationField) {
        this.denominationField = denominationField;
    }

    public DatePicker getEndDate() {
        return endDate;
    }

    public void setEndDate(DatePicker endDate) {
        this.endDate = endDate;
    }

    public Slider getExamSlider() {
        return examSlider;
    }

    public void setExamSlider(Slider examSlider) {
        this.examSlider = examSlider;
    }

    public Slider getPracticeSlider() {
        return practiceSlider;
    }

    public void setPracticeSlider(Slider practiceSlider) {
        this.practiceSlider = practiceSlider;
    }

    public void setRoot(GridPane root) {
        this.root = root;
    }

    public DatePicker getStartDate() {
        return startDate;
    }

    public void setStartDate(DatePicker startDate) {
        this.startDate = startDate;
    }

    public Label getExamLabel() {
        return examLabel;
    }

    public void setExamLabel(Label examLabel) {
        this.examLabel = examLabel;
    }

    public Label getPracticeLabel() {
        return practiceLabel;
    }

    public void setPracticeLabel(Label practiceLabel) {
        this.practiceLabel = practiceLabel;
    }

    public Label getBehaviourLabel() {
        return behaviourLabel;
    }

    public void setBehaviourLabel(Label behaviourLabel) {
        this.behaviourLabel = behaviourLabel;
    }

    public Pesos getPesos() {
        return pesos;
    }
}

