package sample;

/**
 * Created by david on 2.4.15.
 * TODO export a import statistiky
 */
class STatistika {

    private Double CELKOVY_CAS;
    private Double zacatecni_cas;
    private Double konecny_cas;
    private int POCET_ZKOUSENI;
    private int Spravne_odpovedi;
    private int Spatne_odpovedi;
    private int Celkove_odpovedi;

    public void importDat(){
    }

    public void exportDat(){

    }

    public Double getCELKOVY_CAS() {
        return CELKOVY_CAS;
    }

    public void setCELKOVY_CAS(Double CELKOVY_CAS) {
        this.CELKOVY_CAS = CELKOVY_CAS;
    }

    public Double getZacatecni_cas() {
        return zacatecni_cas;
    }

    public void setZacatecni_cas(Double zacatecni_cas) {
        this.zacatecni_cas = zacatecni_cas;
    }

    public Double getKonecny_cas() {
        return konecny_cas;
    }

    public void setKonecny_cas(Double konecny_cas) {
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
