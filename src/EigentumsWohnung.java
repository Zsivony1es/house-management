public class EigentumsWohnung extends Wohnung {

    private double betriebsKosten;
    private double ruecklage;


    public EigentumsWohnung(int aptNumber, double flaeche, short zimmerAnzahl, short stockwerk, short baujahr, short plz, String strasse, short hausNr, short top, double betriebsKosten, double ruecklage) {
        super(aptNumber, flaeche, zimmerAnzahl, stockwerk, baujahr, plz, strasse, hausNr, top);
        this.betriebsKosten = betriebsKosten;
        this.ruecklage = ruecklage;
    }

    public double getBetriebsKosten() {
        return betriebsKosten;
    }

    public double getRuecklage() {
        return ruecklage;
    }

    @Override
    public double gesamtKosten() {
        double ohneZusatz = getFlaeche()*getBetriebsKosten() + getFlaeche()*getRuecklage();
        return ohneZusatz + ohneZusatz*0.02f*getStockwerk();
    }

    @Override
    public String toString() {

        String ausgabe = super.toString();

        ausgabe += String.format("%-16s%s\n","Betriebskosten:", Wohnung.getDecimalFormat().format(this.betriebsKosten))
                + String.format("%-16s%s\n","Ruecklage:", Wohnung.getDecimalFormat().format(this.ruecklage));

        return ausgabe;
    }
}