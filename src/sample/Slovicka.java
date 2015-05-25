/**
 * Jenom Bůh ví co se v této třídě doopravdy děje, ale dal mi dar prozření a tak se to pokusím vysvětlit
 * tato třída importuje ceske a anglicke slovicka ze souboru který ji byl přidělen.
 * Děje se tak za pomoci funkce "import_sVyslovnosti()", která nic nevrací, pouze vyhazuje vyjimky
 * Dále je tu parser který dělá to, že vezme české slovicko, spočítá jeho délku a od 50 prazdych mist odecte vysledek,
 * a vysledek je pocet prazdych znaku ktere se dosadi za ceske slovicko, za tohle vsechno se dosadí anglické slovíčko
 * tato trida je package-local
 *
 * TODO dodelat import slovicek, zjistit de se sekne, osetrit proti chybam
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
    private Workbook workbook;

    // zavedeni databazi na ceske a anglicke slovicka
    private final ArrayList<String> jazyk1 = new ArrayList<>();
    private final ArrayList<String> jazyk2 = new ArrayList<>();
    private final ArrayList<String> vyslovnost = new ArrayList<>();

    String prvniJazyk;
    String druhyJazyk;

    // funkce na rozhodnuti mezi importem s vyslovnosti nebo bez
    public void import_rozhodnuti(){
        try {
            workbook = Workbook.getWorkbook(vyberSouboru.vyber());
            Sheet sheet = workbook.getSheet(0);
            prvniJazyk = sheet.getCell(0, 0).getContents();
            druhyJazyk = sheet.getCell(1, 0).getContents();
            String jeVyslovnost = sheet.getCell(2, 0).getContents();
            System.out.println(jeVyslovnost);
            if (jeVyslovnost != null){
                switch (jeVyslovnost) {
                    case "false":
                        import_bezVyslovnosti();
                        break;
                    case "true":
                        import_sVyslovnosti();
                        break;
                    default:
                        dialog.Error("Chyba", "Soubor neodpovida novemu formatu");
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }

    }

    // funkce na import slovicek bez vyslovnosti
    public void import_bezVyslovnosti(){
        try {
            Sheet sheet = workbook.getSheet(0);
            for(int j = 0; j < sheet.getColumns(); ++j) {
                for(int i = 1; i < sheet.getRows(); ++i) {
                    Cell cell = sheet.getCell(j, i);
                    if(j == 0) {
                        jazyk1.add(cell.getContents());
                    } else if(j == 1) {
                        jazyk2.add(cell.getContents());
                    }
                }
            }
        } catch (Exception e){
            System.out.println("Neo se nehorazne pokazilo");
        }
    }

    // funkce na import slovicek i s obsazenou vyslovnosti
    public void import_sVyslovnosti(){
        try {
            Sheet sheet = workbook.getSheet(0);
            for(int j = 0; j < sheet.getColumns(); ++j) {
                for(int i = 1; i < sheet.getRows(); ++i) {
                    Cell cell = sheet.getCell(j, i);
                    if(j == 0) {
                        jazyk1.add(cell.getContents());
                    } else if(j == 1) {
                        jazyk2.add(cell.getContents());
                    } else if (j == 2){
                        vyslovnost.add(cell.getContents());
                    }
                }
            }
        } catch (Exception e){
            System.out.println("Neo se nehorazne pokazilo");
        }
    }

    // funkce pro vytvoření dvojic slovicek do listView, vezme delku ceskeho import_sVyslovnosti, prida k nemu (50 - delka_cesky) mezeru a potom prida anglicke slovo
    public String parser(int i){
        StringBuilder stringBuffer = new StringBuilder();
        for(int j = 0; j < (50 - jazyk1.get(i).length()); ++j) {
            stringBuffer.append(" ");
        }
        return jazyk1.get(i) + stringBuffer + jazyk2.get(i);
    }

    public List<String> getJazyk1() {
        return jazyk1;
    }

    public List<String> getJazyk2() {
        return jazyk2;
    }

}
