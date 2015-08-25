import junit.framework.TestCase;

public class PhoneBookTest extends TestCase {

	/*
	 * Tests that you can add a person,number pair to the book and later access
	 * the number for that person.
	 */
	public void testCanAddAndGet() {
		PhoneBook myBook = new PhoneBook();
		Person person1 = new Person("Sally");
		PhoneNumber num1 = new PhoneNumber("5105551234");

		myBook.addEntry(person1, num1);
		PhoneNumber numReturned = myBook.getNumber(person1);
		assertEquals("Stored Number Not Correct", num1, numReturned);
	}

	/*
	 * Tests that you can add a second phone number for the same person and that
	 * only the second phone number remains.
	 */
	public void testCanChangeNumber() {
		PhoneBook myBook = new PhoneBook();
		Person person1 = new Person("Sally");
		PhoneNumber num1 = new PhoneNumber("5105551234");
		PhoneNumber num2 = new PhoneNumber("5105551235");

		myBook.addEntry(person1, num1);
		myBook.addEntry(person1, num2);
		PhoneNumber numReturned = myBook.getNumber(person1);
		assertEquals("Replaced Number Not Found", numReturned, num2);
		assertNotSame("Old Phone number was found", numReturned, num1);
	}

	/*
	 * Tests that if you add a person, number pair then modify the person, you
	 * can't get the number out of the phone book again.
	 */
	public void testCantAccessNumIfChangePersonObj() {
		PhoneBook myBook = new PhoneBook();
		Person person1 = new Person("Jane");
		PhoneNumber num1 = new PhoneNumber("5105551234");
		Person person2 = new Person("Anne");
		PhoneNumber num2 = new PhoneNumber("5105551235");
		Person person3 = new Person("Zora");
		PhoneNumber num3 = new PhoneNumber("5105551236");

		myBook.addEntry(person1, num1);
		myBook.addEntry(person2, num2);
		myBook.addEntry(person3, num3);
		person3.changeName("Eungie");
		try {
			PhoneNumber numReturned = myBook.getNumber(person3);
			assertNull(numReturned);
		} catch (IllegalArgumentException e) {
		}
	}

	/*
	 * Also tests that if you add a person, number pair then modify the person,
	 * you can't get the number out of the phone book again.
	 */
	public void testCantAccessNumIfChangePersonObj2() {
		PhoneBook myBook = new PhoneBook();
		Person person1 = new Person("Jane");
		PhoneNumber num1 = new PhoneNumber("5105551234");

		myBook.addEntry(person1, num1);
		person1.changeName("Eungie");
		try {
			PhoneNumber numReturned = myBook.getNumber(person1);
			assertNull(numReturned);
		} catch (IllegalArgumentException e) {
		}
	}

	/*
	 * Tests that if you modify a PhoneNumber that is already in the Phone book
	 * that the change will be reflected the next time you look up that phone
	 * number.
	 */
	public void testCanModifyPhoneNumberAlreadyInBook() {
		PhoneBook myBook = new PhoneBook();
		Person person1 = new Person("Jane");
		PhoneNumber num1 = new PhoneNumber("5105551234");

		myBook.addEntry(person1, num1);
		num1.changeNumber("5105551235");
		PhoneNumber num2 = myBook.getNumber(person1);
		assertEquals("Changed PhoneNumber not reflected in PhoneBook", num1,
				num2);
	}

}
