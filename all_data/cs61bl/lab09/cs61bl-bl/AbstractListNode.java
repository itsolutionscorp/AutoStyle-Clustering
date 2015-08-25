abstract public class AbstractListNode {
	
    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size();
    abstract public Object get(int pos);
    abstract public String toString();
    abstract public boolean equals(AbstractListNode obj);
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
        NonemptyListNode temp = this;
        int count = 0;
        while (temp != null) {
            if (!temp.isEmpty()) {
                count += 1;
            }
            if (temp.myNext instanceof EmptyListNode) {
                break;
            }
            temp = (NonemptyListNode) temp.myNext;
        }
        return count;
    }

    public Object get(int pos) {
        NonemptyListNode temp = this;
        while (pos > 0) {
            if (temp == null || temp.myNext instanceof EmptyListNode) {
                throw new IllegalArgumentException("Position out of bounds.");
            }
            pos -= 1;
            temp = (NonemptyListNode) temp.myNext;
        }
        return temp.myItem;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("( ");
        int count = 0;
        while (count < this.size()) {
            sb.append(this.get(count));
            sb.append(" ");
            count += 1;
        }
        sb.append(")");
        return sb.toString();
    }

    public boolean equals(AbstractListNode obj) {
        NonemptyListNode temp = this;
        int count = 0;
        if (temp.size() == obj.size()) {
            while (count < temp.size()) {
                if (temp.get(count) == obj.get(count)) {
                    count += 1;
                } else {
                    return false;
                }
            }
            return true;
        }
        return false;
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

    public Object get(int pos) {
        return null;
    }

    public String toString() {
        return "( )";
    }

    public boolean equals(AbstractListNode obj) {
        return false;
    }


}
