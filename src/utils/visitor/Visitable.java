package src.utils.visitor;

/** 
 * Interface for visitable part of visitor pattern
 */
public interface Visitable {
	public int accept(Visitor visitor);
}
