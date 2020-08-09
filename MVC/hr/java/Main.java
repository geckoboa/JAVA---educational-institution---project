package hr.java.vjezbe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.java.vjezbe.niti.DatumRodjenjaNit;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	private static final Logger logger = LoggerFactory.getLogger(Main.class);
	private static BorderPane root;

	@Override
	public void start(Stage primaryStage) {
		try {
			root = (BorderPane) FXMLLoader.load(getClass().getResource("Pocetna.fxml"));
			Scene scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();

			Timeline prikazSlavljenika = new Timeline(
					new KeyFrame(Duration.seconds(10), new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
							Platform.runLater(new DatumRodjenjaNit());
						}
					}));
			prikazSlavljenika.setCycleCount(Timeline.INDEFINITE);
			prikazSlavljenika.play();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void setMainPage(BorderPane root1) {

		root.setCenter(root1);

	}

	public static void main(String[] args) {
		launch(args);

	}

	public static void main1(String[] args) {
		Application.launch(args);
	}
}
