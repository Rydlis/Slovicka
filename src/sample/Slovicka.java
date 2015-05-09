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

    private ArrayList<String> cesky = new ArrayList<>();
    private ArrayList<String> anglicky = new ArrayList<>();
    private ArrayList soupis = new ArrayList();

    public void slova(){
        try {
            Workbook workbook = Workbook.getWorkbook(vyberSouboru.vyber());
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
            System.out.println("Neco se pokazilo behem zapisu do databaze");
        } catch (BiffException e) {
            System.out.println("Neco se pokazilo behem zapisu do databaze");
        }
    }

    public String parser(int i){
        int delka_cesky = cesky.get(i).length();
        int mezera = 60 - delka_cesky;
        StringBuffer stringBuffer = new StringBuffer(mezera);
        for(i = 0; i < mezera; ++i) {
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
