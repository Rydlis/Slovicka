/**
 * Vážně nevím na co jsem při psaní tohohle kódu myslel.
 */
package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.List;

public class Controller {

    @FXML
    private ListView<String> listView;

    private ObservableList<String> list = FXCollections.observableArrayList();
    int i = 0;

    Slovicka slovicka = new Slovicka();
    Dialogy dialogy = new Dialogy();

    public void handleImport(ActionEvent event) {
        try {
            slovicka.slova();
            for (i = 0; i <= slovicka.getCesky().size(); ++i){
                list.add(slovicka.parser(i));
            }
            listView.setItems(list);
            dialogy.info("Info", "Slovíčka byla naimportována");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void handleStartTest(){

    }

    public void handleExit (){
        System.exit(0);
    }

}
