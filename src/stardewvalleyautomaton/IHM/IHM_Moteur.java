/*
 * Moteur de l'IHM
 */
package stardewvalleyautomaton.IHM;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import stardewvalleyautomaton.IHM.Scenes.Scene_Principale;

/**
 *
 * @author simonetma
 */
public class IHM_Moteur extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        //création du panel principal
        StackPane root = new StackPane();
        //création de la scène principale
        Scene_Principale scenePrincipale = new Scene_Principale(root);

        //adapte la scène à l'écran
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX(primaryScreenBounds.getMinX());
        primaryStage.setY(primaryScreenBounds.getMinY());
        primaryStage.setWidth(primaryScreenBounds.getWidth());
        primaryStage.setHeight(primaryScreenBounds.getHeight());

        //accessoire de la fenêtre
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("Stardew Valley - Automaton");

        //affiche la fenêtre
        primaryStage.setScene(scenePrincipale);
        primaryStage.show();

        //lance le timer
        Timeline timer = new Timeline(new KeyFrame(Duration.seconds(0.05), new TimerIAHandler(scenePrincipale)));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }

    public static void demarrer() {

        launch();

    }

}
