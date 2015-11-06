package excel_sorter.Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;

/**
 * Created by didrikpa on 03.11.15.
 */
public class GUIController {
    private String file;
    private String saveLocation;

    @FXML
    private TextField filelocation;
    @FXML
    private TextField savetext;
    @FXML
    private Button run;

    @FXML
    private void selectFile(ActionEvent event) throws Exception{
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile!=null){
            String[] s = selectedFile.toString().split("\\.");
            if (s[s.length-1].equals("xlsx")){
                file = selectedFile.getAbsolutePath();
                filelocation.setText(file);
                filelocation.setDisable(true);
            }
        }
    }
    @FXML
    public void selectSavinglocation(ActionEvent event) throws Exception{
        FileChooser fc = new FileChooser();
        File selectedLocation = fc.showSaveDialog(null);
        if (selectedLocation!=null){
            String[] s = selectedLocation.toString().split("\\.");
            if (s[s.length-1].equals("xlsx")){
                saveLocation = selectedLocation.getAbsolutePath();
                savetext.setText(saveLocation);
                savetext.setDisable(true);
            }
        }

    }
    @FXML
    public void run(ActionEvent event) throws Exception{
        if (!savetext.getText().isEmpty()&&!filelocation.getText().isEmpty()){
            ExcelReader er = new ExcelReader(1, file);
            ExcelWriter ew = new ExcelWriter(saveLocation, "Sammendrag");
            ew.makeNewExcel(er.readContestantsFile(file));
            Platform.exit();
        }
    }
}
