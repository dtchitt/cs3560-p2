package src.utils.composite;

import javax.swing.tree.DefaultMutableTreeNode;

import src.utils.TimeStamp;
import src.utils.UniqueID;
import src.utils.visitor.Visitable;

/**
 * Entity is the component in the composite pattern.
 * It is a DefaultMutableTreeNode, which makes this entire project alot easier.
 * It allows me to manipulate the entire entity list and find users/groups with built in functionality
 */
public abstract class Entity extends DefaultMutableTreeNode implements Visitable {
	//I think a has-a relation is better for id and time stamp.
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
		return this.getUniqueID();
	}
}
