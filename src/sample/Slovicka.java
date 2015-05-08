/**
 * Jenom Bůh ví co se v této třídě doopravdy děje, ale dal mi dar prozření a tak se to pokusím vysvětlit
 * tato třída importuje ceske a anglicke slovicka ze souboru který ji byl přidělen.
 * Děje se tak za pomoci funkce "slova()" jejiž návratový typ je List.
 * Dále je tu parser který dělá to, že vezme české slovicko, spočítá jeho délku a od 60 prazdych mist odecte vysledek, za tohle vsechno se dosadí abglické slovíčko
 * */
package sample;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Slovicka {

    private final VyberSouboru vyberSouboru = new VyberSouboru();
    private final Dialogy dialog = new Dialogy();

    private ArrayList<String> cesky;
    private ArrayList<String> anglicky;
    private ArrayList soupis;

    public ArrayList slova(){
        try {
            Workbook workbook = Workbook.getWorkbook(vyberSouboru.vyber());
            Sheet sheet = workbook.getSheet(0);
            for(int j = 0; j < sheet.getColumns(); ++j) {
                for(int i = 0; i < sheet.getRows(); ++i) {
                    Cell cell = sheet.getCell(j, i);
                    if(j == 0) {
                        System.out.println(cell.getContents());
                        cesky.add(cell.getContents());
                    } else if(j == 1) {
                        anglicky.add(cell.getContents());
                    }
                }
            }
            dialog.info("Import", "Import byl proveden správně");
        } catch (IOException e) {
            dialog.chyba("Chyba", "Import neprobehl správně");
        } catch (BiffException e) {
            System.out.println("");
        }
        return soupis;
    }


    public List<String> getCesky() {
        return cesky;
    }

    public List<String> getAnglicky() {
        return anglicky;
    }

}
