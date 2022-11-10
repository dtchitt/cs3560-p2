package src.utils;

public class UniqueID {
	private String id;
	private static int idModifier = 0;

	public UniqueID(String id) {
		this.id = id.trim();
		this.id += ("-" + idModifier);
		UniqueID.idModifier++;
	}

	public void setID(String id) {
		this.id = id;
	}

	public String getID() {
		return this.id;
	}
}
