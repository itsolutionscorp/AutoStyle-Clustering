import junit.framework.TestCase;

public class ResizableIntSequenceTest extends TestCase {
    public void testRemove() {
        IntSequence seq = new ResizableIntSequence(8);
        seq.add(99);
        seq.add(-3);
        seq.add(56);
        seq.add(1000);
        seq.add(8);
        seq.add(-333);
        seq.add(12345);
        seq.add(102);

        // Remove from middle - 99, 56, 1000, 8, -333, 12345, 102
        seq.remove(1);
        checkSequence(seq, new int[]{99, 56, 1000, 8, -333, 12345, 102});

        // Remove from end - 99, 56, 1000, 8, -333, 12345
        seq.remove(6);
        checkSequence(seq, new int[]{99, 56, 1000, 8, -333, 12345});

        // Remove from beginning - 56, 1000, 8, -333, 12345
        seq.remove(0);
        checkSequence(seq, new int[]{56, 1000, 8, -333, 12345});

        seq.remove(0);
        seq.remove(0);
        seq.remove(0);
        seq.remove(0);

        // Remove last element
        seq.remove(0);
        assertEquals(seq.size(), 0);

        // Try to remove from out of bounds
        try {
            seq.remove(0);
            fail("IndexOutOfException wasn't thrown!");
        } catch (IndexOutOfBoundsException ignored) {
            // success
        }
    }

    public void testInsert() {
        IntSequence seq = new ResizableIntSequence(7);

        // Insert into first position of empty array
        seq.insert(100, 0);
        checkSequence(seq, new int[]{100});

        seq.add(67);
        seq.add(120);
        seq.add(84);

        // Insert at the beginning - 9, 100, 67, 120, 84
        seq.insert(9, 0);
        checkSequence(seq, new int[]{9, 100, 67, 120, 84});

        // Insert at the end - 9, 100, 67, 120, 84, 23
        seq.insert(23, 5);
        checkSequence(seq, new int[]{9, 100, 67, 120, 84, 23});

        // Insert in the middle
        seq.insert(0, 3);
        checkSequence(seq, new int[]{9, 100, 67, 0, 120, 84, 23});
    }

    public void testContains() {
        IntSequence seq = new ResizableIntSequence(4);

        // an empty sequence contains nothing
        assertFalse(seq.contains(0));

        seq.add(0);
        seq.add(2);
        seq.add(4);
        seq.add(8);

        assertFalse(seq.contains(5));
        assertTrue(seq.contains(4));
    }

    public void testGrow() {
        // test growth from 0 to 1
        ResizableIntSequence seq = new ResizableIntSequence(0);
        seq.add(90);
        assertEquals(seq.capacity(), 1);

        // test growth from x to 1.5x
        seq = new ResizableIntSequence(50);
        for (int i = 0; i < 51; i++)
            seq.add(90);
        assertEquals(seq.capacity(), 75);

        // make sure growth doesn't happen when it shouldn't
        seq = new ResizableIntSequence(50);
        for (int i = 0; i < 50; i++)
            seq.add(90);
        assertEquals(seq.capacity(), 50);
    }

    public void testShrink() {
        // test _not_ shrinking when seq is small
        ResizableIntSequence seq = new ResizableIntSequence(2);
        seq.add(1);
        seq.add(2);
        seq.remove(0);
        assertEquals(seq.capacity(), 2);

        // test shrinking when seq is too large
        seq = new ResizableIntSequence(120);
        for (int i = 0; i < 61; i++)
            seq.add(1);
        seq.remove(0);
        assertEquals(seq.capacity(), 80);

        // make sure shrinkage doesn't happen when it shouldn't
        seq = new ResizableIntSequence(100);
        for (int i = 0; i < 80; i++) {
            seq.add(11);
        }
        seq.remove(0);
        assertEquals(seq.capacity(), 100);
    }

    private void checkSequence(IntSequence seq, int[] expected) {
        assertEquals(seq.size(), expected.length);
        for (int i = 0; i < expected.length; i++) {
            assertEquals(seq.elementAt(i), expected[i]);
        }
    }
}