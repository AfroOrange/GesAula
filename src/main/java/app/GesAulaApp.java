package app;

import controllers.RootController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class GesAulaApp extends Application {

    private final RootController rootController = new RootController();

    @Override
    public void start(Stage stage) throws Exception {

        Scene gesAulaScene = new Scene(rootController.getRoot());

        Stage gesAulaStage = new Stage();
        Image appIcon = new Image(Objects.requireNonNull(getClass().getResource("/images/app-icon-64x64.png")).toString());

        gesAulaStage.getIcons().add(appIcon);
        gesAulaStage.setTitle("Ges Aula");
        gesAulaStage.setScene(gesAulaScene);
        gesAulaStage.show();

    }
}
