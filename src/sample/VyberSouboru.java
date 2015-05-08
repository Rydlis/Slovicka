package sample;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 * Created by david on 2.4.15.
 */
public class VyberSouboru {
    FileChooser fileChooser = new FileChooser();                                                                        // zavedeni tridy FileChooseru

    Stage stage;                                                                                                        // zavdeni nove Stage

    public File vyber(){
        fileChooser.setTitle("Otevři soubory");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Excel", "*.xls")
        );
        return fileChooser.showOpenDialog(stage);
    }
}
