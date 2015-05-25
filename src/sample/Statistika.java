package sample;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * Created by david on 2.4.15.
 * TODO export a import statistiky
 */
class Statistika {

    // zavedeni potrebnych promen
    private long CELKOVY_CAS;
    private long zacatecni_cas;
    private long konecny_cas;
    private int POCET_ZKOUSENI;
    private int Spravne_odpovedi;
    private int Spatne_odpovedi;
    private int Celkove_odpovedi;

    // zavedeni trid
    private final Dialogy dialog = new Dialogy();

    // zavedeni potrebnych promennych
    private double Uspesnost = 0;

    // funkce na import dat ze souboru
    public void importDat(){
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader("statistika.txt"));                       // otevre soubor do ktereho se predtim ulozilo
            // precte se radek a jeho vysledek se zapise do promene, to same i u promennych nize
            Spravne_odpovedi = Integer.valueOf(bufferedReader.readLine());
            Spatne_odpovedi = Integer.valueOf(bufferedReader.readLine());
            Celkove_odpovedi = Integer.valueOf(bufferedReader.readLine());
            Uspesnost = Double.valueOf(bufferedReader.readLine());
            CELKOVY_CAS = Long.valueOf(bufferedReader.readLine());
            bufferedReader.close();                                                                                     // uzavreni readeru
        } catch (IOException e){                                                                                        // osetreni prvniho zapnuti aplikace
            System.out.println("Soubor se nepodarilo importovat, pravdepodobne z duvodu ze jde o prvni spusteni");
        }
    }

    // funkce na export dat za pouziti FileWriter
    public void exportDat(){
        System.out.println("blablabla");
        String zapis ="";
        zapis = zapis + Spravne_odpovedi + "\n";
        zapis = zapis + Spatne_odpovedi + "\n";
        zapis = zapis + Celkove_odpovedi + "\n";
        zapis = zapis + Uspesnost + "\n";
        zapis = zapis + CELKOVY_CAS + "\n";
        try {                                                               // osetreni vyjimky kdy se nepodari zapsat data do souboru
            FileWriter fileWriter = new FileWriter("statistika.txt");       // vytvoreni souboru statistika.txt
            fileWriter.write(zapis);                                        // zapis drive vytvoreneho stringu s vysledky
            fileWriter.close();                                             // uzavreni filereader
        } catch (IOException e){
            dialog.Error("Chyba", "Nepovedl se zapis databaze");
        }
    }

    // funkce na vypocet statistiky
    public double vypocetUspesnosti(){
        try {
            Uspesnost = ((double) Spravne_odpovedi / (double) Celkove_odpovedi) * 100;
        } catch (ArithmeticException e) {                                   // osetreni vyjimky kdy je nulovy pocet spravnych odpovedi
            System.out.println("Bohuzel nezijeme ve vesmiru kde lze delit nulou, bojim se ze v souboru bude Uspesnot jako Null, nebo 0");
        }
        return Uspesnost;
    }

    // funkce pro formatovani celkoveho casu do Controlleru pomoci tridy SimpleDateFormat
    public String celkovy_cas(){
        SimpleDateFormat smf = new SimpleDateFormat("m:ss");
        CELKOVY_CAS = CELKOVY_CAS + (konecny_cas - zacatecni_cas);
        return smf.format(CELKOVY_CAS);
    }

    // vymazani statistiky, nastaveni vsech hodnot na nulu
    public void smazatStatistiku(){
        Celkove_odpovedi = 0;
        Spravne_odpovedi = 0;
        Spatne_odpovedi = 0;
        POCET_ZKOUSENI = 0;
    }
    public double getUspesnost() {
        return Uspesnost;
    }

    public void setUspesnost(double uspesnost) {
        Uspesnost = uspesnost;
    }

    public long getCELKOVY_CAS() {
        return CELKOVY_CAS;
    }

    public void setCELKOVY_CAS(long CELKOVY_CAS) {
        this.CELKOVY_CAS = CELKOVY_CAS;
    }

    public long getZacatecni_cas() {
        return zacatecni_cas;
    }

    public void setZacatecni_cas(long zacatecni_cas) {
        this.zacatecni_cas = zacatecni_cas;
    }

    public long getKonecny_cas() {
        return konecny_cas;
    }

    public void setKonecny_cas(long konecny_cas) {
        this.konecny_cas = konecny_cas;
    }

    public int getPOCET_ZKOUSENI() {
        return POCET_ZKOUSENI;
    }

    public void setPOCET_ZKOUSENI(int POCET_ZKOUSENI) {
        this.POCET_ZKOUSENI = POCET_ZKOUSENI;
    }

    public int getSpravne_odpovedi() {
        return Spravne_odpovedi;
    }

    public void setSpravne_odpovedi(int spravne_odpovedi) {
        Spravne_odpovedi = spravne_odpovedi;
    }

    public int getSpatne_odpovedi() {
        return Spatne_odpovedi;
    }

    public void setSpatne_odpovedi(int spatne_odpovedi) {
        Spatne_odpovedi = spatne_odpovedi;
    }

    public int getCelkove_odpovedi() {
        return Celkove_odpovedi;
    }

    public void setCelkove_odpovedi(int celkove_odpovedi) {
        Celkove_odpovedi = celkove_odpovedi;
    }
}
