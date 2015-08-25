import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

public class ResizableIntSequenceTest {
    @Test
    public void testBasic() {
        ResizableIntSequence n = new ResizableIntSequence(2);
        n.add(1);
        n.add(2);
        n.add(3);
        assertTrue(n.size() == 3);
    }

    @Test
    public void testInsert() {
        ResizableIntSequence i = new ResizableIntSequence(2);
        i.add(1);
        i.add(2);
        i.insert(3, 1);
        assertTrue(i.size() == 3);
        assertTrue(i.elementAt(1) == 3);
    }

    @Test
    public void testRemove() {
        ResizableIntSequence k = new ResizableIntSequence(2);
        k.add(1);
        k.add(2);
        k.insert(3, 1);
        k.remove(1);
        assertTrue(k.size() == 2);
        System.out.println(k.toString());
    }

    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(ResizableIntSequenceTest.class);
    }

}