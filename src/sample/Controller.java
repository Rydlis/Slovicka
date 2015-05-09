/**
 * Vážně nevím na co jsem při psaní tohohle kódu myslel.
 */
package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    // zavdeni všeho potřebného
    @FXML
    private ListView<String> listView;
    @FXML
    private GridPane nastaveni_div;
    @FXML
    private GridPane zkouseni_div;
    @FXML
    private GridPane tlacitka_div;
    @FXML
    private ImageView ceska_vlajka;
    @FXML
    private ImageView anglicka_vlajka;

    private ObservableList<String> list = FXCollections.observableArrayList();
    int i = 0;

    Slovicka slovicka = new Slovicka();
    Dialogy dialogy = new Dialogy();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ceska_vlajka.setImage(new Image(new File("czech.jpg").toURI().toString()));
        anglicka_vlajka.setImage(new Image(new File("uk.jpg").toURI().toString()));
        zkouseni_div.setOpacity(0);
        zkouseni_div.setDisable(true);
        tlacitka_div.setOpacity(0);
        tlacitka_div.setDisable(true);
    }

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
        nastaveni_div.setDisable(true);
        nastaveni_div.setOpacity(0);
        zkouseni_div.setOpacity(100);
        zkouseni_div.setDisable(false);
        tlacitka_div.setOpacity(100);
        tlacitka_div.setDisable(false);
    }

    // funkce pro ukončení aplikace
    public void handleExit (){
        System.exit(0);
    }
}
