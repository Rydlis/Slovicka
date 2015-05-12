/**
 * Vytvořeno David Rejdlem za ucelem zprehledneni kodu
 * Tato trida byla použita u více projektu a rad bych ji i zverejnil jako API
 * TODO predelat na podporu javy 7
 * navic, komu by se to chtelo porad psat
 * Tato trida bylo optimalizovana pomoci IntelliJ Idea Analyzator
 * trida je package-local, proto chybi "public" pred "class"
 */
package sample;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

class Dialogy {

    // zavedeni tridy Alert
    private final Alert alert = new Alert(Alert.AlertType.ERROR);

    // funkce na tisk alertu, nechtelo se mi psat dalsi 3 radky kodu ke kazdemu try/catch, navic tohle zprehlednuje kod
    public void chyba(String nadpis, String popis){
        alert.setTitle(nadpis);                // nastaveni nadpisu
        alert.setHeaderText(popis);                // nastaveni popisu chyby
        alert.showAndWait();                        // zobrazeni dialogu s nasim textem
    }

    // funkce na tisk alertu, nechtelo se mi psat dalsi 3 radky kodu ke kazdemu try/catch, navic tohle zprehlednuje kod
    public void info(String nadpis, String popis){
        alert.setAlertType(Alert.AlertType.INFORMATION);
        alert.setTitle(nadpis);                // nastaveni nadpisu
        alert.setHeaderText(popis);                // nastaveni popisu chyby
        alert.showAndWait();                        // zobrazeni dialogu s nasim textem
    }

    // funkce na potvrzovaci dialog
    public Optional<ButtonType> potvrd(String nadpis, String dotaz){
        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        alert.setTitle(nadpis);
        alert.setHeaderText(dotaz);
        return alert.showAndWait();
    }

}
