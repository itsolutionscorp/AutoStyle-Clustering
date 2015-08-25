import static org.junit.Assert.*;



import org.junit.Test;



public class ResizableIntSequenceTest {



@Test



public void testAdd() {

ResizableIntSequence yellow = new ResizableIntSequence(3);

yellow.add(7);

yellow.add(9);

yellow.add(4);

for (int i = 0; i < yellow.size(); i++) {

System.out.println(yellow.elementAt(i));}

yellow.add(5);

assertEquals(yellow.myValues.length, 6);

for (int i = 0; i < yellow.size(); i++) {

System.out.println(yellow.elementAt(i));

}

}

@Test

public void testRemove() {

ResizableIntSequence blue = new ResizableIntSequence(6);

blue.add(2);

blue.add(4);

blue.add(6);

blue.add(8);

blue.remove(0); 

for (int i = 0; i < blue.size(); i++) {

System.out.println(blue.elementAt(i));}

assertEquals(blue.myValues.length, 6);

blue.remove(0);

for (int i = 0; i < blue.size(); i++) {

System.out.println(blue.elementAt(i));}

assertEquals(blue.myValues.length, 4);

}


@Test

public void testInsert() {

ResizableIntSequence blue = new ResizableIntSequence(5);

blue.add(2);

blue.add(4);

blue.add(6);

blue.add(8);

blue.insert(7, 1);

assertEquals("2 7 4 6 8", blue.toString());

blue.insert(3, 3);

assertEquals("2 7 4 3 6 8", blue.toString());

blue.remove(1);

blue.remove(1);

assertEquals("2 3 6", blue.toString());

blue.insert(1, 0);

assertEquals("1 2 3 6", blue.toString());

blue.insert(1, 4);

assertEquals("1 2 3 6 1", blue.toString());

blue.insert(2, 4);

assertEquals("1 2 3 6 2", blue.toString());



}


}