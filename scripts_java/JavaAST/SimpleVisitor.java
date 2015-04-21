import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.BlockComment;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ConditionalExpression;
import org.eclipse.jdt.core.dom.EnhancedForStatement;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.Javadoc;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.PostfixExpression;
import org.eclipse.jdt.core.dom.PrefixExpression;
import org.eclipse.jdt.core.dom.SwitchCase;
import org.eclipse.jdt.core.dom.WhileStatement;
import org.eclipse.jdt.core.dom.LineComment;

public class SimpleVisitor extends ASTVisitor {
	private static final String LINE_DELIMITER = "::";
	
	private SimpleASTNode root;
	private SimpleASTNode current;
	private String methodOfInterest;
	private ASTNode methodNode;
	private CompilationUnit cu;
	private boolean addLines;
	private boolean inMethodOfInterest;
	private int startLineOfMethod;
	
	public SimpleVisitor(CompilationUnit cu, String methodName, boolean shouldAddLines){
		this.cu = cu;
		root = null;
		current = null;
		methodOfInterest = methodName;
		inMethodOfInterest = false;
		methodNode = null;
		addLines = shouldAddLines;
		startLineOfMethod = 0;
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
			methodNode = n;
			startLineOfMethod = getOriginalLineNumber(n.getBody()) - 1;
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
		return visitItem(n.getName().toString(), getLineNumberInMethod(n));
	}
	
	@Override
	public void endVisit(MethodInvocation n){
		/*System.out.println(n.getParent().getNodeType());
		System.out.println(n.getExpression());
		System.out.println(n.arguments());
		System.out.println(n.typeArguments());
		System.out.println();*/
		endVisitItem();
	}
	
	private void endVisitItem(){
		if (inMethodOfInterest && current!=null){
			current = current.getParent();
		}
	}
	
	@Override
	public boolean visit(BlockComment n){
		return true;
	}
	
	@Override
	public boolean visit(Javadoc n){
		n.delete();
		return true;
	}
	
	@Override
	public boolean visit(LineComment n){
		return true;
	}
	
	@Override
	public boolean visit(ForStatement n){
		return visitItem("iter", getLineNumberInMethod(n));
	}
	
	@Override
	public void endVisit(ForStatement n){
		endVisitItem();
	}
	
	@Override
	public boolean visit(InfixExpression n){
		return visitItem(n.getOperator().toString(), getLineNumberInMethod(n));
	}
	
	@Override
	public void endVisit(InfixExpression n){
		endVisitItem();
	}
	
	@Override
	public boolean visit(PrefixExpression n){
		return visitItem(n.getOperator().toString(), getLineNumberInMethod(n));
	}
	
	@Override
	public void endVisit(PrefixExpression n){
		endVisitItem();
	}
	
	@Override
	public boolean visit(PostfixExpression n){
		return visitItem(n.getOperator().toString(), getLineNumberInMethod(n));
	}
	
	@Override
	public void endVisit(PostfixExpression n){
		endVisitItem();
	}
	
	@Override
	public boolean visit(EnhancedForStatement n){
		return visitItem("iter", getLineNumberInMethod(n));
	}
	
	@Override
	public boolean visit(WhileStatement n){
		return visitItem("iter", getLineNumberInMethod(n));
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
		return visitItem("cond", getLineNumberInMethod(n));
	}
	
	@Override
	public void endVisit(SwitchCase n){
		endVisitItem();
	}
	
	@Override
	public boolean visit(IfStatement n){
		return visitItem("cond", getLineNumberInMethod(n));
	}
	
	@Override
	public boolean visit(ConditionalExpression n){
		return visitItem("cond", getLineNumberInMethod(n));
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
		if (root == null){
			return null;
		}
		return root.toString();
	}
	
	/**
	 * Returns the code of the method only.
	 */
	public String cleanCode(){
		if (methodNode == null){
			return null;
		} else {
			return methodNode.toString();
		}
	}
	
	private boolean visitItem(String item, int line){
		if (!inMethodOfInterest){
			return true;
		} else if (current != null){
			SimpleASTNode thisItem = null;
			if (addLines){
				thisItem = new SimpleASTNode(item + LINE_DELIMITER + line);
			} else {
				thisItem = new SimpleASTNode(item);				
			}
			current.addChild(thisItem);
			thisItem.setParent(current);
			current = thisItem;
			return true;
		} else {
			return true;
		}
	}
	
	private int getOriginalLineNumber(ASTNode n){
		return cu.getLineNumber(n.getStartPosition()) - 1;
	}
	
	private int getLineNumberInMethod(ASTNode n){
		return getOriginalLineNumber(n) - startLineOfMethod;
	}
}
