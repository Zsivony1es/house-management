import java.util.ArrayList;
import java.util.List;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class HausverwaltungClient {

	private static DecimalFormat decform = new DecimalFormat("0.00", new DecimalFormatSymbols(Locale.ENGLISH));

	public static void main(String[] args) {

		if (args.length == 0 || args.length == 1){
			System.out.println("Error: Parameter ungueltig.");
			return;
		}

		Hausverwaltung hvw = new Hausverwaltung( new HausverwaltungSerializationDAO(args[0]));
		List<Wohnung> wohnungList = hvw.getHausverwaltungDAO().getWohnungen();


		switch (args[1]){

			case "list":
				if (args.length == 2) {
					hvw.getHausverwaltungDAO().getWohnungen()
							.forEach(System.out::println);
				} else {
					Wohnung out = hvw.getHausverwaltungDAO().getWohnungbyId( Integer.parseInt(args[2]) );
					if (out == null)
						return;
					else
						System.out.println(out);
				}
				break;

			case "add":

				if (args.length != 14){
					System.out.println("Error: Parameter ungueltig.");
					return;
				}

				try {
					if (args[2].equalsIgnoreCase("EW"))
						hvw.getHausverwaltungDAO().saveWohnung(new EigentumsWohnung(Integer.parseInt(args[3]), Double.parseDouble(args[4]), Short.parseShort(args[5]),
															Short.parseShort(args[6]),  Short.parseShort(args[7]),  Short.parseShort(args[8]), args[9], Short.parseShort(args[10]),
															Short.parseShort(args[11]), Double.parseDouble(args[12]), Double.parseDouble(args[13])));
					else if (args[2].equalsIgnoreCase("MW"))
						hvw.getHausverwaltungDAO().saveWohnung(new MietWohnung(Integer.parseInt(args[3]), Double.parseDouble(args[4]), Short.parseShort(args[5]),
								Short.parseShort(args[6]),  Short.parseShort(args[7]),  Short.parseShort(args[8]), args[9], Short.parseShort(args[10]),
								Short.parseShort(args[11]), Double.parseDouble(args[12]), Short.parseShort(args[13])));
					else {
						System.out.println("Error: Ungueltige Parameter.");
						return;
					}
					break;
				} catch (IllegalArgumentException exc){
					if (!exc.getMessage().equals("Error: Parameter ungueltig.") && !exc.getMessage().equals("Error: Baujahr ungueltig."))
						System.out.println("Error: Parameter ungueltig.");
					else 
						System.out.println(exc.getMessage());
					return;
				}
				

			case "delete":
				hvw.getHausverwaltungDAO().deleteWohnung(Integer.parseInt(args[2]));
				break;

			case "count":
				if (args.length == 2)
					System.out.println(hvw.getHausverwaltungDAO().getWohnungen().size());
				else{
					int a = 0;
					for (Wohnung w : wohnungList)
						if (w instanceof MietWohnung)
							a++;
					System.out.println( (args[2].equalsIgnoreCase("MW")) ? a : wohnungList.size()-a);
				}
				break;

			case "meancosts":
				double kosten = 0;
				for (Wohnung w : wohnungList)
					kosten += w.gesamtKosten();
				System.out.println( decform.format( kosten / wohnungList.size() ));
				break;

			case "oldest":
				List<Wohnung> oldest = new ArrayList<>();
				for (Wohnung w : wohnungList){
					if (oldest.isEmpty())
						oldest.add(w);
					else {
						if (oldest.get(0).alter() < w.alter()) {
							oldest.clear();
							oldest.add(w);
						} else if (oldest.get(0).alter() == w.alter())
							oldest.add(w);
					}
				}
				for (Wohnung w : oldest)
					System.out.println("Id: " + w.getId());
				break;

			default:
				System.out.println("Error: Parameter ungueltig.");
		}
			
	}

	
}