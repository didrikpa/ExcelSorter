package excel_sorter.JavaFX;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by didrikpa on 03.11.15.
 */
public class main extends Application {
    FXMLLoader fxmlLoader;
    Parent root;
    public Stage ps;

    @Override
    public void start(Stage primaryStage) throws IOException {
        fxmlLoader = new FXMLLoader();
        root = (Parent) fxmlLoader.load(this.getClass().getResource("/excel_sorter/Views/excelsorter.fxml"));
        ps = primaryStage;
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("ExcelToExcel");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}





