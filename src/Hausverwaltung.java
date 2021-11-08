public class Hausverwaltung {

    private HausverwaltungDAO hausverwaltungDAO;


    public Hausverwaltung(HausverwaltungDAO hvwDAO) {
        this.hausverwaltungDAO = hvwDAO;
    }

    public HausverwaltungDAO getHausverwaltungDAO() {
        return hausverwaltungDAO;
    }
}