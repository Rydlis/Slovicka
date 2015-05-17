package sample;

import java.text.SimpleDateFormat;

/**
 * Created by david on 2.4.15.
 * TODO export a import statistiky
 */
class Statistika {

    private long CELKOVY_CAS;
    private long zacatecni_cas;
    private long konecny_cas;
    private int POCET_ZKOUSENI;
    private int Spravne_odpovedi;
    private int Spatne_odpovedi;
    private int Celkove_odpovedi;
    private String cesta_db;

    XMLCteniZapis xml = new XMLCteniZapis();
    Dialogy dialog = new Dialogy();

    double Uspesnost = 0;

    public void importDat(){
        System.out.println("import dat");
    }


    public void exportDat(){
        xml.data(Spravne_odpovedi, Spatne_odpovedi, Celkove_odpovedi, Uspesnost, CELKOVY_CAS, POCET_ZKOUSENI);
    }

    public double vypocetStatistiky(){
        try {
            Uspesnost =  (Spravne_odpovedi / Celkove_odpovedi) * 100;
        } catch (ArithmeticException e) {
            System.out.println("Bohuzel nezijeme ve vesmiru kde lze delit nulou, bojim se ze v souboru bude Uspesnot jako Null, nebo 0");
        }
        return Uspesnost;
    }

    public String celkovy_cas(){
        SimpleDateFormat smf = new SimpleDateFormat("m:ss");
        CELKOVY_CAS = CELKOVY_CAS + (konecny_cas - zacatecni_cas);
        return smf.format(CELKOVY_CAS);
    }

    public void smazatStatistiku(){
        Celkove_odpovedi = 0;
        Spravne_odpovedi = 0;
        Spatne_odpovedi = 0;
        POCET_ZKOUSENI = 0;
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
