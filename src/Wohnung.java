import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.Year;
import java.util.Locale;

public abstract class Wohnung implements java.io.Serializable{

    private int Id;
    private double flaeche;
    private short zimmerAnzahl;
    private short stockwerk;
    private short bauJahr;

    private short plz;
    private String strasse;
    private short hausNr;
    private short top;

    private static DecimalFormat decForm = new DecimalFormat("0.00", new DecimalFormatSymbols(Locale.ENGLISH));

    //Static Methods
    public static DecimalFormat getDecimalFormat(){
        return decForm;
    }

    //Constructor
    public Wohnung(int Id, double flaeche, short zimmerAnzahl, short stockwerk, short bauJahr, short plz, String strasse, short hausNr, short top) throws IllegalArgumentException {

        if (flaeche < 0 || flaeche > 10000){ throw new IllegalArgumentException("Error: Parameter ungueltig."); }
        if (bauJahr > Year.now().getValue()) {throw new IllegalArgumentException("Error: Baujahr ungueltig.");}

        this.plz = plz;
        this.strasse = strasse;
        this.hausNr = hausNr;
        this.top = top;

        this.Id = Id;
        this.flaeche = flaeche;
        this.zimmerAnzahl = zimmerAnzahl;
        this.stockwerk = stockwerk;
        this.bauJahr = bauJahr;
    }

    //Getters
    public int getId() {
        return Id;
    }

    public double getFlaeche() {
        return flaeche;
    }

    public int getZimmerAnzahl() {
        return zimmerAnzahl;
    }

    public short getStockwerk() {
        return stockwerk;
    }

    public short getBauJahr() {
        return bauJahr;
    }

    //Setters only if needed

    //Member Methods
    public int alter(){ return Year.now().getValue() - bauJahr; }

    public abstract double gesamtKosten();

    //toString


    @Override
    public String toString() {
        String ausgabe = String.format("%-16s%s\n","Typ:", (this instanceof EigentumsWohnung) ? "EW" : "MW")
        + String.format("%-16s%d\n","Id:", this.Id)
        + String.format("%-16s%s\n","Flaeche:", Wohnung.getDecimalFormat().format(this.flaeche))
        + String.format("%-16s%d\n","Zimmer:", this.zimmerAnzahl)
        + String.format("%-16s%d\n","Stock:", this.stockwerk)
        + String.format("%-16s%d\n","Baujahr:", this.bauJahr)
        + String.format("%-16s%d\n","PLZ:", this.plz)
        + String.format("%-16s%s\n","Strasse:", this.strasse)
        + String.format("%-16s%d\n","Hausnummer:", this.hausNr)
        + String.format("%-16s%d\n","Top:", this.top);

        return ausgabe;
    }
}