/**
 * Vážně nevím na co jsem při psaní tohohle kódu myslel.
 */
package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class Controller {
    // zavdeni všeho potřebného
    @FXML
    private ListView<String> listView;

    private ObservableList<String> list = FXCollections.observableArrayList();
    int i = 0;

    Slovicka slovicka = new Slovicka();
    Dialogy dialogy = new Dialogy();

    // ne zcela pekna funkce na import slovicek a jejich vykresleni do ListView
    public void handleImport(ActionEvent event) {
        try {
            slovicka.slova();
            int pocet_slov = slovicka.getCesky().size() -1;         // takovy nepekny hack toho, ze index zacina je 0->88 a delka je 1-89, proto se odecita jednicka
            for (i = 0; i <= pocet_slov; i++){
                list.add(slovicka.parser(i));                       // pridani slovicek do databaze typu ObservableList pro tisknuti do listView
            }
            listView.setItems(list);
            dialogy.info("Info", "Slovíčka byla naimportována");
        } catch (IndexOutOfBoundsException e){                      // zpracovani vyjimky kdy je index vetsi jak pole
            dialogy.chyba("Chyba", "Při výkonu akce došlo k chybě");
        } catch (NullPointerException e){                           // soubor nenalezen nebo uzivatel zadny nezvolil
            System.out.println("soubor nevybran");
        }
    }

    public void handleStartTest(){

    }

    // funkce pro ukončení aplikace
    public void handleExit (){
        System.exit(0);
    }

}
