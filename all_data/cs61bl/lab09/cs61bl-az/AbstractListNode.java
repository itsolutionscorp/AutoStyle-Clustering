abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    // Every other list-processing method goes here.
    abstract public int size();
    abstract public AbstractListNode get(int pos);
    abstract public String toString();
    abstract public boolean equals(Object obj);
}

class NonemptyListNode extends AbstractListNode {

    private Object myItem;
    private AbstractListNode myNext;

    public NonemptyListNode (Object item, AbstractListNode next) {
        myItem = item;
        if (next == null) {
            myNext = new EmptyListNode();
        } else {
            myNext = next;
        }
    }

    public NonemptyListNode (Object item) {
        this (item, new EmptyListNode());
    }

    public Object item() {
        return myItem;
    }

    public AbstractListNode next() {
        return myNext;
    }
    
    public boolean isEmpty() {
        return false;
    }

    public int size() {
        return 1 + next().size();
    }

    public AbstractListNode get(int pos) {
        if (pos > size() - 1 || pos < 0) {
            throw new IllegalArgumentException("invalid position");
        }
        AbstractListNode node = this;
        int i = 0;
        while (i < pos) {
            node = node.next();
            i ++;
        }
        return node;
    }

    public String toString() {
        String s = "( ";
        AbstractListNode node = this;
        while (!node.isEmpty()) {
            s = s + node.item() + " ";
            node = node.next();
        }
        return s + ")";
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof NonemptyListNode)) {
            return false;
        }

        AbstractListNode node = (AbstractListNode) obj;
        if (node.size() != size()) {
            return false;
        }
        AbstractListNode baseNode = this;
        while(!node.isEmpty()) {
            if (!baseNode.item().equals(node.item())) {
                return false;
            }
            node = node.next();
            baseNode = baseNode.next();
        }

        return true;
    }
}

class EmptyListNode extends AbstractListNode {
    
    public EmptyListNode() {
        
    }
    
    public Object item() {
        throw new IllegalArgumentException ("There is no 'item' value stored in an EmptyListNode.");
    }
    
    public AbstractListNode next() {
        throw new IllegalArgumentException ("No elements follow an EmptyListNode.");
    }
    
    public boolean isEmpty() {
        return true;
    }

    public int size() {
        return 0;
    }

    public AbstractListNode get(int pos) {
        return null;
    }

    public String toString() {
        return "( )";
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        return(obj instanceof EmptyListNode);
    }
}
