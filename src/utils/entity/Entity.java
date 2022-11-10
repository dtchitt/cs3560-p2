package src.utils.entity;

import javax.swing.tree.DefaultMutableTreeNode;

import src.utils.TimeStamp;
import src.utils.UniqueID;

public abstract class Entity extends DefaultMutableTreeNode {
	private UniqueID id;
	private TimeStamp timeStamp;

	public Entity(String id) {
		super();
		this.id = new UniqueID(id);
		this.timeStamp = new TimeStamp();
	}

	public String getUniqueID() {
		return this.id.getID();
	}

	public String getIdNum() {
		return this.id.getID().substring(this.getUniqueID().indexOf("-") + 1);
	}

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
		return this.getUniqueID();
	}
}
