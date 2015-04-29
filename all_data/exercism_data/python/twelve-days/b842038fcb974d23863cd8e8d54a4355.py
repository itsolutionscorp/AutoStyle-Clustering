dayWords = ["first", "second", "third", "fourth", "fifth", "sixth", "seventh", "eighth", "ninth", "tenth", "eleventh", "twelfth"]
countWords = ["a", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve"]

items = [
	"Partridge in a Pear Tree",
	"Turtle Doves",
	"French Hens",
	"Calling Birds",
	"Gold Rings",
	"Geese-a-Laying",
	"Swans-a-Swimming",
	"Maids-a-Milking",
	"Ladies Dancing",
	"Lords-a-Leaping",
	"Pipers Piping",
	"Drummers Drumming"
]

def verse(number):
	lyrics = "On the %s day of Christmas my true love gave to me, " % (dayWords[number - 1])
	
	for i in range(number)[:0:-1]:
		lyrics += "%s %s, " % (countWords[i], items[i])

	if (number > 1):
		lyrics += "and "

	lyrics += "%s %s.\n" % (countWords[0], items[0])
	return lyrics
	
def verses(start, end):
	return '\n'.join(map(verse, range(start, end + 1))) + "\n"
	
def sing():
	return verses(1, 12)
