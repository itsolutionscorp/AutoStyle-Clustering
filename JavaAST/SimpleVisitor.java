import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ConditionalExpression;
import org.eclipse.jdt.core.dom.EnhancedForStatement;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.SwitchCase;
import org.eclipse.jdt.core.dom.WhileStatement;

public class SimpleVisitor extends ASTVisitor {
	private SimpleASTNode root;
	private SimpleASTNode current;
	private String methodOfInterest;
	private boolean inMethodOfInterest;
	
	public SimpleVisitor(String methodName){
		root = null;
		current = null;
		methodOfInterest = methodName;
		inMethodOfInterest = false;
	}
	
	@Override
	public void preVisit(ASTNode n){
	}
	
	@Override
	public void postVisit(ASTNode n){
	}
	
	@Override
	public boolean visit(MethodDeclaration n){
		if (n.getName().toString().equals(methodOfInterest)){
			root = new SimpleASTNode(methodOfInterest);
			current = root;
			inMethodOfInterest = true;
		}
		return true;
	}
	
	@Override
	public void endVisit(MethodDeclaration n){
		if (n.getName().toString().equals(methodOfInterest)){
			inMethodOfInterest = false;
		}
	}
	
	@Override
	public boolean visit(MethodInvocation n){
		return visitItem(n.getName().toString());
	}
	
	private void endVisitItem(){
		if (inMethodOfInterest && current!=null){
			current = current.getParent();
		}
	}
	
	@Override
	public void endVisit(MethodInvocation n){
		endVisitItem();
	}
	
	@Override
	public boolean visit(ForStatement n){
		return visitItem("iter");
	}
	
	@Override
	public void endVisit(ForStatement n){
		endVisitItem();
	}
	
	@Override
	public boolean visit(EnhancedForStatement n){
		return visitItem("iter");
	}
	
	@Override
	public boolean visit(WhileStatement n){
		return visitItem("iter");
	}
	
	@Override
	public void endVisit(WhileStatement n){
		endVisitItem();
	}
	
	@Override
	public void endVisit(EnhancedForStatement n){
		endVisitItem();
	}
	
	@Override
	public boolean visit(SwitchCase n){
		return visitItem("cond");
	}
	
	@Override
	public void endVisit(SwitchCase n){
		endVisitItem();
	}
	
	@Override
	public boolean visit(IfStatement n){
		return visitItem("cond");
	}
	
	@Override
	public boolean visit(ConditionalExpression n){
		return visitItem("cond");
	}
	
	@Override
	public void endVisit(ConditionalExpression n){
		endVisitItem();
	}
	
	@Override
	public void endVisit(IfStatement n){
		endVisitItem();
	}
	
	@Override
	public String toString(){
		return root.toString();
	}
	
	private boolean visitItem(String item){
		if (!inMethodOfInterest){
			return true;
		} else if (current != null){
			SimpleASTNode thisItem = new SimpleASTNode(item);
			current.addChild(thisItem);
			thisItem.setParent(current);
			current = thisItem;
			return true;
		} else {
			return true;
		}
	}
}
