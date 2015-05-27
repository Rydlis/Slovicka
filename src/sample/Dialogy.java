/**
 * Vytvořeno David Rejdlem za ucelem zprehledneni kodu
 * Tato trida byla použita u více projektu a rad bych ji i zverejnil jako API
 * TODO predelat na podporu javy 7, predelat na dialogy.jar a pak jenom importovat do projektu
 * navic, komu by se to chtelo porad psat
 * Tato trida bylo optimalizovana pomoci IntelliJ Idea Analyzator
 * trida je package-local, proto chybi "public" pred "class"
 */
package sample;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;

class Dialogy {

    // zavedeni tridy Alert
    private final Alert alert = new Alert(Alert.AlertType.ERROR);

    // funkce na tisk alertu, nechtelo se mi psat dalsi 3 radky kodu ke kazdemu try/catch, navic tohle zprehlednuje kod
    public void Error(String nadpis, String popis){
        alert.setTitle(nadpis);                // nastaveni nadpisu
        alert.setHeaderText(popis);                // nastaveni popisu chyby
        alert.setContentText(null);                   // nastavi prazdnou hodnotu pro ContentText
        alert.showAndWait();                        // zobrazeni dialogu s nasim textem
    }

    // funkce na tisk alertu, nechtelo se mi psat dalsi 3 radky kodu ke kazdemu try/catch, navic tohle zprehlednuje kod
    public void Info(String nadpis, String popis) {
        alert.setAlertType(Alert.AlertType.INFORMATION);
        alert.setTitle(nadpis);                // nastaveni nadpisu
        alert.setHeaderText(popis);                // nastaveni popisu chyby
        alert.setContentText(null);                   // nastavi prazdnou hodnotu pro ContentText
        alert.showAndWait();                        // zobrazeni dialogu s nasim textem
    }

    public void Dialog (String popis, String obsah){
        alert.setAlertType(Alert.AlertType.INFORMATION);
        alert.setHeaderText(popis);
        alert.setContentText(obsah);
        alert.showAndWait();
    }

    // funkce na potvrzovaci dialog
    public Optional<ButtonType> Confirm(String nadpis, String dotaz){
        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        alert.setTitle(nadpis);
        alert.setHeaderText(dotaz);
        alert.setContentText(null);
        return alert.showAndWait();
    }

    public void Message(String nadpis, String popis, String vyjimka, Exception ex){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(nadpis);
        alert.setHeaderText(popis);
        alert.setContentText(vyjimka);

// Create expandable Exception.
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("The exception stacktrace was:");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

// Set expandable Exception into the dialog pane.
        alert.getDialogPane().setExpandableContent(expContent);

        alert.showAndWait();
    }

}
