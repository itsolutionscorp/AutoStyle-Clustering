ord, gifts = zip(
	("twelfth",  "twelve Drummers Drumming,"),
	("eleventh", "eleven Pipers Piping,"),
	("tenth",    "ten Lords-a-Leaping,"),
	("ninth",    "nine Ladies Dancing,"),
	("eighth",   "eight Maids-a-Milking,"),
	("seventh",  "seven Swans-a-Swimming,"),
	("sixth",    "six Geese-a-Laying,"),
	("fifth",    "five Gold Rings,"),
	("fourth",   "four Calling Birds,"),
	("third",    "three French Hens,"),
	("second",   "two Turtle Doves, and"),
	("first",    "a Partridge in a Pear Tree")
)

def verse(n):
	return "On the {} day of Christmas my true love gave to me, {}.\n" \
		.format(ord[12-n], " ".join(gifts[12-n:]))

def verses(first, last):
	return "".join(verse(n)+"\n" for n in range(first, last+1))

def sing():
	return verses(1, 12)
