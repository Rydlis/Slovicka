/**
 * Vážně nevím na co jsem při psaní tohohle kódu myslel.
 *
 * Tahle trida slouzi pro obsluhu celeho programu a zaroven slouzi jako obsluha testu
 * Probiha zde samotny test, kontrola a posilani vysledku tride Statistika
 */
package sample;

import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.File;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    // zavedeni všeho potřebného
    @FXML
    private Tab tab1;
    @FXML
    private Tab tab2;
    @FXML
    private Tab tab3;
    @FXML
    private MenuBar menu;
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
    @FXML
    private Label slovo;
    @FXML
    private Label pocet_slov;
    @FXML
    private RadioButton chk_cesky;
    @FXML
    private RadioButton chk_anglicky;
    @FXML
    private Slider pocet_slov_slider;

    // zavedeni promennych
    private ObservableList<String> list = FXCollections.observableArrayList();
    private int i = 0;

    // zavedeni knihoven
    private final Slovicka slovicka = new Slovicka();
    private final Dialogy dialogy = new Dialogy();
    private final FadeTransition fadeTransition = new FadeTransition();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ceska_vlajka.setImage(new Image(new File("czech.jpg").toURI().toString()));
        anglicka_vlajka.setImage(new Image(new File("uk.jpg").toURI().toString()));
        pocet_slov_slider.setBlockIncrement(1);
        pocet_slov_slider.setMin(0);
        pocet_slov_slider.setValue(0);
        pocet_slov_slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            pocet_slov.setText(String.valueOf(newValue.intValue()));
        });
        zkouseni_div.setOpacity(0);
        zkouseni_div.setDisable(true);
    }

    // ne zcela pekna funkce na import slovicek a jejich vykresleni do ListView
    public void handleImport() {
        try {
            slovicka.slova();
            int pocet_slov_db = slovicka.getCesky().size() -1;         // takovy nepekny hack toho, ze index je 0->88 a delka je 1-89, proto se odecita jednicka
            for (i = 0; i <= pocet_slov_db; ++i){
                list.add(slovicka.parser(i));                       // pridani slovicek do databaze typu ObservableList pro tisknuti do listView
            }
            listView.setItems(list);
            pocet_slov_slider.setMax(slovicka.getCesky().size());   // nastaveni maximalni hodnoty na slideru
            dialogy.info("Info", "Slovíčka byla naimportována");
        } catch (IndexOutOfBoundsException e){                      // zpracovani vyjimky kdy je index vetsi jak pole
            dialogy.chyba("Chyba", "Při výkonu akce došlo k chybě");
        } catch (NullPointerException e){                           // soubor nenalezen nebo uzivatel zadny nezvolil
            dialogy.chyba("Chyba", "Soubor nevybrán");
        }
    }

    // funkce na start testu a nastaveni promennych
    // pri startu funkce se taky znemozni kliknout na jiny Tab a ovladat menu dokud se nezavola handleUkocitTest();
    // pri stisku tlacitka "Ukoncit"
    public void handleStartTest(){
        nastaveni_div.setDisable(true);
        nastaveni_div.setOpacity(0);
        fadeIn(zkouseni_div);
        tab1.setDisable(true);
        tab3.setDisable(true);
        menu.setDisable(true);
        if ((chk_cesky.isSelected()) && (chk_anglicky.isSelected())){           // jestli jsou zvolene 2 jazyky tak se start prerusi a vrati do nastaveni a objevi se dialog s upozornenim
            handleUkoncitTest();
            dialogy.chyba("Chyba", "Zvolte pouze jeden jazyk");
        } else if ((!chk_cesky.isSelected())&& (!chk_anglicky.isSelected())){   // jestli neni zvolen zadny jazyk, start se presrusi a objevi se dialog s upozornenim
            handleUkoncitTest();
            dialogy.chyba("Chyba", "Zvolte alespon jeden jazyk");
        }
        else {
            System.out.println("zacatek testu");
            test();
        }
    }

    // funkce na ovladani testu, tato funkce po vyplneni zavola funkci kontrola()
    // mezitim tato funkce losuje slovicka z vybraneho jazyka a zobrazuje je v Labelu
    private void test(){
        for (int i = 0; i<= pocet_slov_slider.getValue(); i++) {
            if (chk_cesky.isSelected()) {
                slovo.setText(slovicka.getCesky().get(new Random().nextInt(slovicka.getCesky().size() - 1))); // nepekny hack kvuli rozdilu navratove hodnoty size() a indexem
                Kontrola();
            } else {
                slovo.setText(slovicka.getAnglicky().get(new Random().nextInt(slovicka.getAnglicky().size() -1))); // dalsi nepekny hack, stejny jako u predesle funkce, akorat pro anglicke slovicka
                Kontrola();
            }
        }
    }

    // fuknce na kontrolu spravnosti odpovedi, kdy se teto fuknci posle zodpovezene slovicko a na zaklade spravnosti
    // se pricte spravna odpoved do statistiky
    private void Kontrola(){

    }

    // funkce na ukonceni testu a vraceni se do nastaveni, opet umozni ovladat program pomoci Tabs a Menu
    public void handleUkoncitTest(){
        fadeIn(nastaveni_div);
        nastaveni_div.setDisable(false);
        zkouseni_div.setOpacity(0);
        zkouseni_div.setDisable(true);
        tab1.setDisable(false);
        tab3.setDisable(false);
        menu.setDisable(false);
    }

    // funkce na FadeIn animaci, kdy se teto funkci posle Nod na ktery ma byt animace navazana
    private void fadeIn(Node div){
        fadeTransition.setNode(div);
        div.setDisable(false);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(100);
        fadeTransition.play();
    }

    // funkce pro ukončení aplikace
    public void handleExit (){
        System.exit(0);
    }
}
