import junit.framework.TestCase;

public class IntSequenceTest extends TestCase {
    public void testRemove() {
        IntSequence seq = new IntSequence(8);
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
        IntSequence seq = new IntSequence(7);

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
        IntSequence seq = new IntSequence(4);

        // an empty sequence contains nothing
        assertFalse(seq.contains(0));

        seq.add(0);
        seq.add(2);
        seq.add(4);
        seq.add(8);

        assertFalse(seq.contains(5));
        assertTrue(seq.contains(4));
    }

    private void checkSequence(IntSequence seq, int[] expected) {
        assertEquals(seq.size(), expected.length);
        for (int i = 0; i < expected.length; i++) {
            assertEquals(seq.elementAt(i), expected[i]);
        }
    }
}