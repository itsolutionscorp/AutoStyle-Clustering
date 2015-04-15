ord = ["", "first", "second", "third", "fourth", "fifth", "sixth",
	"seventh", "eighth", "ninth", "tenth", "eleventh", "twelfth"]

gifts = [
	" twelve Drummers Drumming",
	" eleven Pipers Piping",
	" ten Lords-a-Leaping",
	" nine Ladies Dancing",
	" eight Maids-a-Milking",
	" seven Swans-a-Swimming",
	" six Geese-a-Laying",
	" five Gold Rings",
	" four Calling Birds",
	" three French Hens",
	" two Turtle Doves",
	" and a Partridge in a Pear Tree"]

def verse(n):
	return "On the " + ord[n] + " day of Christmas my true love gave to me," + \
		(gifts[11][4:] if n == 1 else ",".join(gifts[12-n:])) + ".\n"

def verses(first, last):
	return "".join(verse(n)+"\n" for n in range(first, last+1))

def sing():
	return verses(1, 12)
