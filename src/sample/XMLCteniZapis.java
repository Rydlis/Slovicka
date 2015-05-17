package sample;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Trida pro spravu XML souboru
 *
 * TODO dodelat import a export
 */

@XmlRootElement (name = "statistika")
public class XMLCteniZapis {

    @XmlElement(name = "data")
    public void data (int sprav_odpovedi, int spatne_odpovedi, int celkove_odpovedi, double uspesnost, long celkovy_cas, int pocet_zkouseni){

    }

}
