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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

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
    private Label sprav_odpoved;
    @FXML
    private Label spat_odpoved;
    @FXML
    private Label celk_odpoved;
    @FXML
    private Label uspesnost;
    @FXML
    private RadioButton chk_cesky;
    @FXML
    private RadioButton chk_anglicky;
    @FXML
    private Slider pocet_slov_slider;
    @FXML
    private TextField input_slovo;

    // zavedeni promennych
    private ObservableList<String> list = FXCollections.observableArrayList();
    private int opakovani;
    private int pocet_opak = 0;

    // zavedeni knihoven
    private final Slovicka slovicka = new Slovicka();
    private final Dialogy dialogy = new Dialogy();
    private final Statistika statistika = new Statistika();
    private final FadeTransition fadeTransition = new FadeTransition();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ceska_vlajka.setImage(new Image(getClass().getResource("czech.jpg").toExternalForm()));
        anglicka_vlajka.setImage(new Image(getClass().getResource("uk.jpg").toExternalForm()));
        // prvotni nastaveni seekbaru po kazdem spusteni aplikace
        pocet_slov_slider.setBlockIncrement(1);
        pocet_slov_slider.setMin(0);
        pocet_slov_slider.setMax(0);
        pocet_slov_slider.setValue(0);
        // novy handler pro seekbar, predelan do levelu 8 lambda
        pocet_slov_slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            pocet_slov.setText(String.valueOf(newValue.intValue()));
            opakovani = newValue.intValue();
        });
        input_slovo.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER){
                    handleDalsi();
                }
            }
        });
        zkouseni_div.setOpacity(0);
        zkouseni_div.setDisable(true);
    }

    // ne zcela pekna funkce na import slovicek a jejich vykresleni do ListView
    public void handleImport() {
        try {
            slovicka.slova();
            int pocet_slov_db = slovicka.getCesky().size() -1;      // takovy nepekny hack toho, ze index je 0->88 a delka je 1-89, proto se odecita jednicka
            for (int i = 0; i <= pocet_slov_db; ++i){
                list.add(slovicka.parser(i));                       // pridani slovicek do databaze typu ObservableList pro tisknuti do listView
            }
            listView.setItems(list);
            pocet_slov_slider.setMax(slovicka.getCesky().size());   // nastaveni maximalni hodnoty na slideru
            dialogy.Info("Info", "Slovíčka byla naimportována");
        } catch (IndexOutOfBoundsException e){                      // zpracovani vyjimky kdy je index vetsi jak pole
            dialogy.Error("Chyba", "Při výkonu akce došlo k chybě");
        } catch (NullPointerException e){                           // soubor nenalezen nebo uzivatel zadny nezvolil
            dialogy.Error("Chyba", "Soubor nevybrán");
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
            dialogy.Error("Chyba", "Zvolte pouze jeden jazyk");
        } else if ((!chk_cesky.isSelected()) && (!chk_anglicky.isSelected())){   // jestli neni zvolen zadny jazyk, start se presrusi a objevi se dialog s upozornenim
            handleUkoncitTest();
            dialogy.Error("Chyba", "Zvolte alespon jeden jazyk");
        }
        else {
            test();
        }
    }

    // funkce na ovladani testu, tato funkce po vyplneni zavola funkci kontrola()
    // mezitim tato funkce losuje slovicka z vybraneho jazyka a zobrazuje je v Labelu
    private void test(){
        if (opakovani > pocet_opak){
            int vylosovany_index = new Random().nextInt(slovicka.getCesky().size() - 1);
            if (chk_cesky.isSelected()) {
                slovo.setText(slovicka.getCesky().get(vylosovany_index)); // gettovani slovicka na indexu "nahodne vybranem" ze zacatku funkce
            } else {
                slovo.setText(slovicka.getAnglicky().get(vylosovany_index)); // gettovani slovicka na indexu "nahodne vybranem" ze zacatku funkce
            }
        } else {
            sprav_odpoved.setText(String.valueOf(statistika.getSpravne_odpovedi()));
            spat_odpoved.setText(String.valueOf(statistika.getSpatne_odpovedi()));
            celk_odpoved.setText(String.valueOf(statistika.getCelkove_odpovedi()));
            System.out.println(statistika.vypocetStatistiky());
            uspesnost.setText(String.valueOf(statistika.vypocetStatistiky()));
            statistika.setPOCET_ZKOUSENI(statistika.getPOCET_ZKOUSENI() +1);
            handleUkoncitTest();
        }
    }

    // fuknce na kontrolu spravnosti odpovedi, kdy se teto fuknci posle zodpovezene slovicko a na zaklade spravnosti
    // se pricte spravna odpoved do statistiky
    public void handleDalsi(){
        if (chk_cesky.isSelected()){
            if (slovicka.getAnglicky().contains(input_slovo.getText())){
                statistika.setSpravne_odpovedi(statistika.getSpravne_odpovedi() +1);
            } else {
                statistika.setSpatne_odpovedi(statistika.getSpatne_odpovedi() + 1);
            }
        }
        else if (chk_anglicky.isSelected()){
            if (slovicka.getCesky().contains(input_slovo.getText())){
                statistika.setSpravne_odpovedi(statistika.getSpravne_odpovedi() +1);
            } else {
                statistika.setSpatne_odpovedi(statistika.getSpatne_odpovedi() +1);
            }
        }
        input_slovo.clear();
        statistika.setCelkove_odpovedi(statistika.getCelkove_odpovedi() +1);
        pocet_opak++;
        test();
    }

    // funkce pro predbezne ukonceni s potvrzujicim dialogem
    public void handlePredbezneUkoncit() {
        ButtonType volba = dialogy.Confirm("Potvrďte", "Opravdu chcete ukončit testování?").get();
        if (volba == ButtonType.OK){
            handleUkoncitTest();
        }
    }

    // funkce na ukonceni testu a vraceni se do nastaveni, opet umozni ovladat program pomoci Tabs a Menu a taky vraci hodnoty do vychozi hodnoty
    public void handleUkoncitTest(){
        fadeIn(nastaveni_div);
        nastaveni_div.setDisable(false);
        zkouseni_div.setOpacity(0);
        zkouseni_div.setDisable(true);
        tab1.setDisable(false);
        tab3.setDisable(false);
        menu.setDisable(false);
        pocet_opak = 0;
    }

    // funkce na volani vymazani statistiky, volana funkce se nachazi ve tride STatistika
    public void handleVymazatStatistiku(){
        statistika.smazatStatistiku();
    }

    // funkce na FadeIn animaci, kdy se teto funkci posle Nod na ktery ma byt animace navazana
    private void fadeIn(Node div){
        fadeTransition.setNode(div);
        div.setDisable(false);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(100);
        fadeTransition.play();
    }

    // vyhodi dialog s popisem ovladani aplikace
    public void handleJakPouzit(){
        dialogy.Info("Jak používat", "Před startem aplikace vyber soubor se slovíčky\n" +
                "aplikace je automaticky naimportuje a můžes se začít testovat\n" +
                "Na začátku si vyber jazyk ze kterého se chceš zkoušet a klikni na začít");
    }

    // vyhodi dialog s informacemi o aplikaci
    public void handleOAppce(){
        dialogy.Info("Info o aplikace", "Tuto aplikaci naprogramoval David Rejdl\n" +
                "Více informací na www.github.com/Rydlis\n" +
                "Verze aplikace Beta 1, rok 2015");
    }

    // funkce pro ukončení aplikace, pred ukoncenim vola exportDat() pro ulozeni statistiky do textoveho souboru
    public void handleExit (){
        System.exit(0);
    }
}
