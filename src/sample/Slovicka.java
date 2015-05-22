/**
 * Jenom Bůh ví co se v této třídě doopravdy děje, ale dal mi dar prozření a tak se to pokusím vysvětlit
 * tato třída importuje ceske a anglicke slovicka ze souboru který ji byl přidělen.
 * Děje se tak za pomoci funkce "slova()", která nic nevrací, pouze vyhazuje vyjimky
 * Dále je tu parser který dělá to, že vezme české slovicko, spočítá jeho délku a od 50 prazdych mist odecte vysledek,
 * a vysledek je pocet prazdych znaku ktere se dosadi za ceske slovicko, za tohle vsechno se dosadí anglické slovíčko
 * tato trida je package-local
 * */
package sample;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Slovicka {

    // zavedeni potrebnych trid
    private final VyberSouboru vyberSouboru = new VyberSouboru();
    private final Dialogy dialog = new Dialogy();

    // zavedeni databazi na ceske a anglicke slovicka
    private final ArrayList<String> cesky = new ArrayList<>();
    private final ArrayList<String> anglicky = new ArrayList<>();

    // funkce na import slovicek do databazi
    public void slova(){
        try {
            Workbook workbook = Workbook.getWorkbook(vyberSouboru.vyber());      // vyber souboru pres tridu VyberSouboru
            Sheet sheet = workbook.getSheet(0);
            for(int j = 0; j < sheet.getColumns(); ++j) {
                for(int i = 0; i < sheet.getRows(); ++i) {
                    Cell cell = sheet.getCell(j, i);
                    if(j == 0) {
                        cesky.add(cell.getContents());
                    } else if(j == 1) {
                        anglicky.add(cell.getContents());
                    }
                }
            }
        } catch (IOException e) {
            dialog.Error("Chyba", "Soubor nemohl být otevřen");
        } catch (BiffException e) {
            System.out.println("Neco se pokazilo behem zapisu do databaze");
        }
    }

    // funkce pro vytvoření dvojic slovicek do listView, vezme delku ceskeho slova, prida k nemu (50 - delka_cesky) mezeru a potom prida anglicke slovo
    public String parser(int i){
        StringBuilder stringBuffer = new StringBuilder();
        for(int j = 0; j < (50 - cesky.get(i).length()); ++j) {
            stringBuffer.append(" ");
        }
        return cesky.get(i) + stringBuffer + anglicky.get(i);
    }

    public List<String> getCesky() {
        return cesky;
    }

    public List<String> getAnglicky() {
        return anglicky;
    }

}
