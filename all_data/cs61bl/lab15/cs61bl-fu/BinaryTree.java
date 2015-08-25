import java.util.*;

public class BinaryTree {

    private TreeNode myRoot;
    private ArrayList<TreeNode> alreadySeen;

    public BinaryTree() {
        myRoot = null;
    }

    public BinaryTree(TreeNode t) {
        myRoot = t;
    }

    // Print the values in the tree in preorder: root value first,
    // then values in the left subtree (in preorder), then values
    // in the right subtree (in preorder).
    public void printPreorder() {
        if (myRoot == null) {
            System.out.println("(empty tree)");
        } else {
            myRoot.printPreorder();
            System.out.println();
        }
    }

    // Print the values in the tree in inorder: values in the left
    // subtree first (in inorder), then the root value, then values
    // in the right subtree (in inorder).
    public void printInorder() {
        if (myRoot == null) {
            System.out.println("(empty tree)");
        } else {
            myRoot.printInorder();
            System.out.println();
        }
    }

    public void fillSampleTree1() {
        myRoot = new TreeNode("a", new TreeNode("b"), new TreeNode("c"));
    }

    public void fillSampleTree2() {
        myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("d",
                    new TreeNode("e"), new TreeNode("f")), null), new TreeNode("c"));
    }

    public void fillSampleTree15() {
        myRoot = new TreeNode("a", new TreeNode("b", new TreeNode("c",
                    new TreeNode("d", null, null), new TreeNode("e", null, null)),
                new TreeNode("f", new TreeNode("g", null, null), new TreeNode("h", null, null))),
            new TreeNode("i", new TreeNode("j", new TreeNode("k", null, null), new TreeNode("l", null, null)),
                new TreeNode("m", new TreeNode("n", null, null), new TreeNode("o", null, null))));

    }

    public static void main(String[] args) {
        BinaryTree t;
        t = new BinaryTree();
        print(t, "the empty tree");
        System.out.println(t.check());
        t.fillSampleTree1();
        print(t, "sample tree 1");
        System.out.println(t.check());
        t.fillSampleTree2();
        print(t, "sample tree 2");
        System.out.println(t.check());
        System.out.println(t.height());
        System.out.println(t.isCompletelyBalanced() + " should be false");
        BinaryTree t1 = new BinaryTree();
        System.out.println(t1.isCompletelyBalanced() + " should be true");
        t.fillSampleTree15();
        System.out.println(t.isCompletelyBalanced() + " should be true");
        t.print();
        t.check();
        System.out.println(t.check());
        System.out.println("\n\n");
        BinaryTree fib; 
        for (int i = 0; i < 10; i ++) {
            fib = BinaryTree.fibTree(i);
            fib.print();
            System.out.println("\n");
        }
    }

    private static void print(BinaryTree t, String description) {
        System.out.println(description + " in preorder");
        t.printPreorder();
        System.out.println(description + " in inorder");
        t.printInorder();
        System.out.println();
    }

    public int height () {
        if (myRoot == null) return 0;
        return myRoot.height();
    }

    public boolean isCompletelyBalanced() {
        if (myRoot == null) return true;
        else return myRoot.isCompletelyBalanced();
    }

    public void print() {
        if (myRoot != null) {
            myRoot.print(0);
        }
    }

    public boolean check() { 
        alreadySeen = new ArrayList<TreeNode>(); 
        try { 
            isOK(myRoot); 
            return true; 
        } catch (IllegalStateException e) { 
            return false; 
        } 
    }

    private void isOK (TreeNode t) throws IllegalStateException {
        if (t == null) return;
        if (alreadySeen.contains(t)) throw new IllegalStateException("Duplicate Node!!!");
        alreadySeen.add(t);
        isOK(t.myLeft);
        isOK(t.myRight);
    }

    public static BinaryTree fibTree(int n) {
        BinaryTree result = new BinaryTree();
        result.myRoot = result.fibTreeHelper(n);
        return result;
    }

    private TreeNode fibTreeHelper (int n) {
        if (n == 0) return new TreeNode(new Integer(0));
        if (n == 1) return new TreeNode(new Integer(1));
        TreeNode previous = fibTreeHelper(n-1);
        TreeNode previous2 = fibTreeHelper(n-2);
        Integer fibNumber = ((Integer)previous.myItem).intValue() + ((Integer)previous2.myItem).intValue();
        return new TreeNode(fibNumber, previous, previous2);
    }

    public static BinaryTree exprTree(String s) {
        BinaryTree result = new BinaryTree();
        result.myRoot = result.exprTreeHelper(s);
        return result;
    }
    // Return the tree corresponding to the given arithmetic expression.
    // The expression is legal, fully parenthesized, contains no blanks, 
    // and involves only the operations + and *.
    // Dealing with exp before and after () is incorrect: req. addition + mult. precedence considerations
    private TreeNode exprTreeHelper(String expr) {
        //Remove White Space
        expr = removeSpaces(expr);
        int index = expr.indexOf("(");
        if (index == -1) {
            //No parentheses in the expression.
            index = expr.indexOf("*");
            if (index == -1) {
                //No multiplication in the expression.
                index = expr.indexOf("+");
                if (index == -1) {
                    //No addition in the expression.
                    return new TreeNode(expr,null,null);
                }
                else {
                    String left = expr.substring(0,index);
                    String right = expr.substring(index+1);
                    if (left.equals("") || right.equals("")) {
                        throw new IllegalArgumentException("Unbalanced arithmetic expression: " + expr);
                    }
                    return new TreeNode("+",exprTreeHelper(left),exprTreeHelper(right));
                }
            }
            else {
                String left = expr.substring(0,index);
                String right = expr.substring(index+1);
                if (left.equals("") || right.equals("")) {
                    throw new IllegalArgumentException("Unbalanced arithmetic expression: " + expr);
                }
                return new TreeNode("*",exprTreeHelper(left),exprTreeHelper(right));
            }
        }
        else {
            //Check if whole thing is parenthesized and unparenthesize.
            //THIS MEANS NO BALANCE UNTIL THE END.
            //Traverse expr look for unparenthesized operators. Evaluate those.
            int nesting = 0;
            boolean canStrip = true;
            if (expr.charAt(0) == '(' && expr.charAt(expr.length()-1) == ')') {
                //Check if parentheses are balanced. If so unparenthesize ends else throw error.
                for (int i = 0; i < expr.length(); i ++) {
                    if (expr.charAt(i) == '(') {
                        nesting ++;
                    }
                    else if (expr.charAt(i) == ')') {
                        nesting --;
                    }
                    if (nesting == 0 && i != expr.length() -1) {
                        //Not a candidate for stripping.
                        canStrip = false;
                        break;
                    }
                }
                if (nesting != 0) {
                    throw new IllegalArgumentException("Expression is unbalanced: " + expr);
                }
                else if (canStrip){
                    String toParse = expr.substring(1,expr.length() - 1);
                    return exprTreeHelper(toParse);
                }
            }

            //Search for unnested '+' operators.
            index = findUnNested('+',expr);
            if (index != -1) {
                String left = expr.substring(0,index);
                String right = expr.substring(index+1);
                if (left.equals("") || right.equals("")) {
                    throw new IllegalArgumentException("Unbalanced arithmetic expression: " + expr);
                }
                return new TreeNode("+",exprTreeHelper(left),exprTreeHelper(right));
            }

            //Search for unnested '*' operators.
            index = findUnNested('*',expr);
            if (index != -1) {
                String left = expr.substring(0,index);
                String right = expr.substring(index+1);
                if (left.equals("") || right.equals("")) {
                    throw new IllegalArgumentException("Unbalanced arithmetic expression: " + expr);
                }
                return new TreeNode("*",exprTreeHelper(left),exprTreeHelper(right));
            }
        }
        throw new IllegalArgumentException("Invalid arithmetic expression. Missing Operator.");
    }

    // Finds non-nested operators in an expression.
    private int findUnNested(char toFind, String expr) {
        int nesting = 0;
        for (int i = 0; i < expr.length(); i++) {
            if (expr.charAt(i) == '(') {
                nesting++;
            }
            else if (expr.charAt(i) == ')') {
                nesting --;
            }
            if (nesting == 0) {
                if (expr.charAt(i) == toFind) {
                    return i;
                }
            }
        }
        return -1;
    }

    // Returns the input cleared of spaces. 
    private String removeSpaces (String expr) {
        boolean isCleanFront = false;
        boolean isCleanBack = false;
        while (!isCleanFront || !isCleanBack) {
            if (expr.charAt(0) == ' ') {
                expr = expr.substring(1);
            }
            else {
                isCleanFront = true;
            }
            if (expr.charAt(expr.length() - 1) == ' ') {
                expr = expr.substring(0,expr.length()-1);
            }
            else {
                isCleanBack = true;
            }
        }
        return expr;
    }

    public void optimize () {
        if (myRoot == null) {
            return;
        }
        myRoot = optimizeHelper(myRoot, myRoot);
    }

    private TreeNode optimizeHelper (TreeNode root, TreeNode toOptimize) {
        if (toOptimize.myLeft != null && toOptimize.myRight != null) {
            if (isInteger(toOptimize.myLeft.myItem) && 
            isInteger(toOptimize.myRight.myItem)) {
                if (toOptimize.myItem.equals("+")) {
                    toOptimize.myItem = tryAdd(toOptimize.myLeft.myItem, toOptimize.myRight.myItem);
                }
                else if (toOptimize.myItem.equals("*")) {
                    toOptimize.myItem = tryMultiply(toOptimize.myLeft.myItem, toOptimize.myRight.myItem);
                }
                toOptimize.myLeft = null; toOptimize.myRight = null;
                return optimizeHelper(root,root);
            }
            else {
                if (toOptimize.myLeft != null)
                optimizeHelper (root, toOptimize.myLeft);
                if (toOptimize.myRight != null)
                optimizeHelper (root, toOptimize.myRight);
            }
        }
        return root;
    }

    private boolean isInteger (Object in) {
        if (in instanceof String) {
            try {
                Integer.parseInt((String)in);
                return true;
            }
            catch (NumberFormatException e) {
                return false;
            }
        }
        else if (in instanceof Integer) {
            return true;
        }
        return false;
    }

    private Object tryAdd (Object a, Object b) {
        if (a instanceof Integer && b instanceof Integer) {
            return new Integer(((Integer)a).intValue() + ((Integer)b).intValue());
        }
        else if (a instanceof String && b instanceof String) {
            Integer a1 = Integer.parseInt((String) a);
            Integer b1 = Integer.parseInt((String) b);
            return "" + (a1.intValue() + b1.intValue());
        }
        throw new IllegalArgumentException("The two types can't be added.");
    }

    private Object tryMultiply (Object a, Object b) {
        if (a instanceof Integer && b instanceof Integer) {
            return new Integer(((Integer)a).intValue() * ((Integer)b).intValue());
        }
        else if (a instanceof String && b instanceof String) {
            Integer a1 = Integer.parseInt((String) a);
            Integer b1 = Integer.parseInt((String) b);
            return "" + (a1.intValue() * b1.intValue());
        }
        throw new IllegalArgumentException("The two types can't be added.");
    }

    private static class TreeNode {
        public Object myItem;
        public TreeNode myLeft;
        public TreeNode myRight;

        public TreeNode(Object obj) {
            myItem = obj;
            myLeft = myRight = null;
        }

        public TreeNode(Object obj, TreeNode left, TreeNode right) {
            if (obj == null) {
                throw new IllegalArgumentException("Can't store null items.");
            }
            myItem = obj;
            myLeft = left;
            myRight = right;
        }

        private void printPreorder() {
            System.out.print(myItem + " ");
            if (myLeft != null) {
                myLeft.printPreorder();
            }
            if (myRight != null) {
                myRight.printPreorder();
            }
        }

        private void printInorder() {
            if (myLeft != null) {
                myLeft.printInorder();
            }
            System.out.print(myItem + " ");
            if (myRight != null) {
                myRight.printInorder();
            }
        }

        private int height () {
            if (myLeft == null && myRight == null) return 1;
            int left = 0;
            int right = 0;
            if (myLeft != null) left = myLeft.height();
            if (myRight != null) right = myRight.height();
            return 1 + Math.max(left,right);
        }

        private boolean isCompletelyBalanced() {
            if (myLeft == null && myRight == null) return true;
            else if (myLeft == null || myRight == null) return false;
            else return myLeft.isCompletelyBalanced() && myRight.isCompletelyBalanced();
        }

        private static final String indent1 = "    ";

        private void print(int indent) {
            // TODO your code here
            if (myRight != null) myRight.print(indent+1);
            println (myItem, indent);
            // TODO your code here
            if (myLeft != null) myLeft.print(indent+1);
        }

        private static void println(Object obj, int indent) {
            for (int k=0; k<indent; k++) {
                System.out.print(indent1);
            }
            System.out.println(obj);
        }

    }
}
