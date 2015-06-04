package sample;

import javafx.stage.FileChooser;

import java.io.File;

/**
 * Trida na otevirani a ukladani souboru
 */
class FileManager {

    private final FileChooser fileChooser = new FileChooser();                // zavedeni tridy FileChooseru

    // funkce pro open souboru ve formatu xls
    public File open(){

        fileChooser.setTitle("Otevři soubory");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Excel", "*.xls")
        );
        return fileChooser.showOpenDialog(Main.stage.getScene().getWindow());
    }

    public File save(){
        fileChooser.setTitle("Uložit soubor jako");
        return fileChooser.showSaveDialog(Main.stage.getScene().getWindow());
    }

}
