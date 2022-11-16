package src.utils.entity;

import javax.swing.tree.DefaultMutableTreeNode;

import src.utils.TimeStamp;
import src.utils.UniqueID;

/**
 * Entity servers as "sysEntry" as your video says. I decided to make entity a defaultMutableTreenode because it makes working with the tree easier.
 * Entities also have a uniqueID and a timestamp.
 */
public abstract class Entity extends DefaultMutableTreeNode {
	private UniqueID id;
	private TimeStamp timeStamp;

	public Entity(String id) {
		super();
		this.id = new UniqueID(id);
		this.timeStamp = new TimeStamp();
	}

	/**
	 *
	 * @return full id
	 */
	public String getUniqueID() {
		return this.id.getID();
	}

	/**
	 * 
	 * @return just the # portion of the id
	 */
	public String getIdNum() {
		return this.id.getID().substring(this.getUniqueID().indexOf("-") + 1);
	}

	/**
	 * 
	 * @return just the name portion of the id
	 */
	public String getIdName() {
		return this.id.getID().substring(0, this.getUniqueID().indexOf("-"));
	}

	public void setUpdateStamp() {
		this.timeStamp.setUpdateStamp();
	}

	public long getUpdateStamp() {
		return this.timeStamp.getUpdateStamp();
	}

	public long getCreationStamp() {
		return this.timeStamp.getCreationStamp();
	}

	//TODO instead of using this to get node name figure out a better way
	@Override
	public String toString() {
		return this.getIdName();
	}
}
