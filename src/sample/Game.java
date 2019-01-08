package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Game extends Application {
    public Button first;
    public Button second;
    private static final Integer STARTTIME = 10;
    private Timeline timeline;
    private Label timerLabel = new Label();
    private Integer timeSeconds = STARTTIME;
    private int counter = 0;
    private Label count = new Label("Count: ");
    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox();
        Scene scene = new Scene(root, 500 ,500);

        primaryStage.setTitle("FastClicker");
        primaryStage.setScene(scene);

        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);

        HBox buttonContainer = new HBox();
        buttonContainer.setAlignment(Pos.CENTER);
        buttonContainer.setSpacing(100);


        first = new Button("Click!!!");
        second = new Button("Start");

        buttonContainer.getChildren().addAll(first, second);

        root.getChildren().addAll(buttonContainer, timerLabel, count);

        timerLabel.setText(timeSeconds.toString());
        timerLabel.setTextFill(Color.RED);
        timerLabel.setStyle("-fx-font-size: 8em;");

        second.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(timeline != null){
                    timeline.stop();
                }

                timerLabel.setText(timeSeconds.toString());
                timeline = new Timeline();
                timeline.setCycleCount(Timeline.INDEFINITE);
                timeline.getKeyFrames().add(
                        new KeyFrame(Duration.seconds(1),
                                new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent event) {
                                        timeSeconds--;
                                        timerLabel.setText(timeSeconds.toString());
                                        first.setOnAction(e -> {
                                            count.setText("Count: " + Integer.toString(counter));
                                            counter++;
                                            if(timeSeconds <= 0)
                                            {
                                                counter--;
                                            }
                                        });
                                        if(timeSeconds <= 0)
                                        {
                                            timeline.stop();

                                        }
                                    }
                                }));
                timeline.playFromStart();
            }
        });


        scene.getStylesheets().add(this.getClass().getResource("Background.css").toExternalForm());
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}