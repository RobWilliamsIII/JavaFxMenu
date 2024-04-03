package javafxmenu.javafxmenu;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class JavaFXMenu extends Application {
    @Override
    public void start(Stage stage) throws IOException {



        // Create an array of greens using RGB values found from W3 schools
        Color[] greens = {
                Color.rgb(191, 255, 0),
                Color.rgb(128, 255, 0),
                Color.rgb(64, 255, 0),
                Color.rgb(0, 255, 0),
                Color.rgb(0, 255, 64),
                Color.rgb(0, 255, 128),
                Color.rgb(0, 255, 191)
        };

        // Create elements
        BorderPane layout = new BorderPane();
        MenuBar menuBar = new MenuBar();
        menuBar.getStyleClass().add("menuBar");
        Menu menu = new Menu("Menu");
        menu.getStyleClass().add("menu");
        layout.setTop(menuBar);
        menuBar.getMenus().addAll(menu);
        TextField dateTime = new TextField("");
        Label dateLabel = new Label("Current Date and Time: ");
        dateLabel.getStyleClass().add("dateLabel");

        // Four Menu Options and associated actionEvents
        //Show current date and time
        MenuItem Date = new MenuItem("Date");
        Date.setOnAction(actionEvent -> {
            LocalDateTime currentDate = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
            String formattedDate = currentDate.format(formatter);
            dateTime.setText(formattedDate);
            dateTime.setEditable(false);
        });

        //Export to log.txt
        MenuItem Export = new MenuItem("Export");
        Export.setOnAction(actionEvent -> {
            String text = dateTime.getText();
            System.out.println("Exporting text: " + text);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt"))) {
                writer.write(text);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // Show alert to user that file was exported
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Exported");
            alert.setHeaderText(null);
            alert.setContentText("Exported!");
            alert.showAndWait();
        });

        //Change background color to random hues of green from colors array
        MenuItem changeBackground = new MenuItem("Background");
        changeBackground.setOnAction(actionEvent -> {
            Random random = new Random();
            Color randomGreen = greens[random.nextInt(greens.length)];
            layout.setBackground(new Background(new javafx.scene.layout.BackgroundFill(randomGreen, null, null)));

            //Console check to verify random green hues
            System.out.println(randomGreen);
        });

        //Exit program
        MenuItem Exit = new MenuItem("Exit");
        Exit.setOnAction(actionEvent -> {
            Platform.exit();
        });

        //Layout elements in horizontal row
        HBox hBox = new HBox();
        hBox.getChildren().addAll(dateLabel, dateTime);
        hBox.setAlignment(Pos.CENTER);


        // Add Menu items to menu
        menu.getItems().addAll(Date, Export, changeBackground, Exit);

        //create Scene
        layout.setCenter(hBox);
        Scene scene = new Scene(layout, 350, 350);
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("stylesheet.css").toExternalForm());
        stage.setTitle("JavaFX Menu");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}