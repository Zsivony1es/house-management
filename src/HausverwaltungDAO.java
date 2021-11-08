import java.util.List;

public interface HausverwaltungDAO {

	public abstract List<Wohnung> getWohnungen();
	public abstract Wohnung getWohnungbyId(int id);
	public abstract void saveWohnung(Wohnung wohnung);
	public abstract void deleteWohnung(int id);

}