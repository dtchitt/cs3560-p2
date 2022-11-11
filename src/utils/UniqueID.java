package src.utils;

/**
 * UniqueID is used with entities to give them a unique id along with their displayname
 * The unique id is composed of 2 parts the userName and the IdNum
 * A typical username would look like fred-710.
 */
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
