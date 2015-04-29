import java.util.List;
import java.util.ArrayList;
public class SimpleASTNode {
	private String item;
	private List<SimpleASTNode> children;
	private SimpleASTNode parent;
	
	public SimpleASTNode(String i){
		item = i;
		children = new ArrayList<SimpleASTNode>();
		parent = null;
	}
	
	public void addChild(SimpleASTNode n){
		children.add(n);
	}
	
	public String getItem(){
		return item;
	}
	
	public List<SimpleASTNode> getChildren(){
		return children;
	}
	
	public SimpleASTNode getParent(){
		return parent;
	}
	
	public void setParent(SimpleASTNode n){
		parent = n;
	}
	
	/**
	 * Return an s-expression of this AST.
	 */
	@Override
	public String toString(){
		StringBuilder s = new StringBuilder("(");
		s.append(" ").append(item).append(" ");
		for (SimpleASTNode child: getChildren()){
			s.append(child.toString());
		}
		s.append(")");
		return s.toString();
	}
}
