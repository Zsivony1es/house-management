import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HausverwaltungSerializationDAO implements HausverwaltungDAO {

    private String filename;

    public HausverwaltungSerializationDAO(String filename) {
        this.filename = filename;
    }

    @Override
    public List<Wohnung> getWohnungen() {
        FileInputStream inFile;
        try {

            // If source data doesn't exist, create one with an empty ArrayList
            try {
                inFile = new FileInputStream(filename);
            } catch (FileNotFoundException ex){
                FileOutputStream f = new FileOutputStream(filename);
                ObjectOutputStream outF = new ObjectOutputStream(f);
                outF.writeObject(new ArrayList<Wohnung>());
                outF.close();
                f.close();
                inFile = new FileInputStream(filename);
            }

            ObjectInputStream in = new ObjectInputStream(inFile);
            List<Wohnung> ret = (List<Wohnung>)in.readObject();
            inFile.close();
            in.close();
            return ret;

        } catch (Exception exc) {
            System.out.println("Fehler bei Deserialisierung:");
            return null;
        }
    }

    @Override
    public Wohnung getWohnungbyId(int id) {
        List<Wohnung> wohnungList = getWohnungen();
        for (Wohnung w : wohnungList)
            if (w.getId() == id) return w;
        return null;
    }

    @Override
    public void saveWohnung(Wohnung wohnung) {

        List<Wohnung> ls = getWohnungen();

        if (ls.stream().anyMatch( w -> {return w.getId() == wohnung.getId();})) { //ID already exists
            System.out.println("Error: Wohnung bereits vorhanden. (id=" + wohnung.getId() + ")");
            return;
        }

        try {

            FileOutputStream outFile = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(outFile);

            ls.add(wohnung);
            out.writeObject(ls);

            out.close();
            outFile.close();
            System.out.println("Info: Wohnung " + wohnung.getId() + " added.");
        } catch (IOException exc){
            System.out.println("Fehler bei Serialisierung:");
            return;
        }
    }

    @Override
    public void deleteWohnung(int id) {

        List<Wohnung> ls = getWohnungen();
        Wohnung toDelete = null;

       for (Wohnung w : ls){
           if (w.getId() == id)
               toDelete = w;
       }

       if (toDelete == null){
           System.out.println("Error: Wohnung nicht vorhanden. (id=" + id + ")");
           return;
       }

       ls.remove(toDelete);

        {
            try {

                FileOutputStream outFile = new FileOutputStream(filename);
                ObjectOutputStream out = new ObjectOutputStream(outFile);

                out.writeObject(ls);

                out.close();
                outFile.close();
                System.out.println("Info: Wohnung " + id + " deleted.");

            } catch (Exception exc) {
                System.out.println("Fehler bei Serialisierung:");
                return;
            }
        }

    }

}