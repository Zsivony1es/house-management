public class MietWohnung extends Wohnung {

    private double mietKosten;
    private short anzahlMieter;

    public MietWohnung(int aptNumber, double flaeche, short zimmerAnzahl, short stockwerk, short baujahr, short plz, String strasse, short hausNr, short top, double mietKosten, short anzahlMieter) {
        super(aptNumber, flaeche, zimmerAnzahl, stockwerk, baujahr, plz, strasse, hausNr, top);
        this.mietKosten = mietKosten;
        this.anzahlMieter = anzahlMieter;
    }

    public double getMietKosten() {
        return mietKosten;
    }

    public short getAnzahlMieter() {
        return anzahlMieter;
    }

    @Override
    public double gesamtKosten() {
        float zuschlag = 1f + (float) ((getAnzahlMieter() >= 5) ? 0.1f  : ((getAnzahlMieter()-1)*0.025f));
        return getMietKosten()*getFlaeche()*zuschlag;
    }

    @Override
    public String toString() {
        String ausgabe = super.toString();

        ausgabe += String.format("%-16s%s\n","Miete/m2:", Wohnung.getDecimalFormat().format(this.mietKosten))
                + String.format("%-16s%d\n","Anzahl Mieter:", this.anzahlMieter);

        return ausgabe;
    }
}