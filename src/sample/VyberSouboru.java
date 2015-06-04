package sample;

import javafx.stage.FileChooser;

import java.io.File;

/**
 * Trida na vyber souboru, vyber je uskutecnen pres knihovnu FileChooser
 */
class VyberSouboru {

    // funkce pro vyber souboru ve formatu xls
    public File vyber(){
        FileChooser fileChooser = new FileChooser();                // zavedeni tridy FileChooseru
        fileChooser.setTitle("Otevři soubory");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Excel", "*.xls")
        );
        return fileChooser.showOpenDialog(Main.stage.getScene().getWindow());
    }
}
