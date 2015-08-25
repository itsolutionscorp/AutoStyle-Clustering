abstract public class AbstractListNode {

    abstract public Object item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size();
    abstract public Object get(int pos);
    abstract public String toString();
    abstract public boolean equals(AbstractListNode lst);

    // Every other list-processing method goes here.
}
