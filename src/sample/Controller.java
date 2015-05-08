/**
 * Vážně nevím na co jsem při psaní tohohle kódu myslel.
 */
package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class Controller {

    @FXML
    private ListView<String> list;

    int i = 0;

    slovicka slovicka = new slovicka();

    public void handleImport(ActionEvent event) {
        slovicka.slova();
        // ObservableList<String> seznam = slovicka.parser();
        // list.setItems(seznam);
    }

    public void handleExit (){
        System.exit(0);
    }

}
