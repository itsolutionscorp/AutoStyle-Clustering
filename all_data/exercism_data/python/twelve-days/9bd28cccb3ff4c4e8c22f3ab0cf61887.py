statement = "On the %s day of Christmas my true love gave to me, "

days = [
	'first',
	'second',
	'third',
	'fourth',
	'fifth',
	'sixth',
	'seventh',
	'eighth',
	'ninth',
	'tenth',
	'eleventh',
	'twelfth'
]

items = [
	'a Partridge in a Pear Tree.\n',
	'two Turtle Doves,',
	'three French Hens,',
	'four Calling Birds,',
	'five Gold Rings,',
	'six Geese-a-Laying,',
	'seven Swans-a-Swimming,',
	'eight Maids-a-Milking,',
	'nine Ladies Dancing,',
	'ten Lords-a-Leaping,',
	'eleven Pipers Piping,',
	'twelve Drummers Drumming,'
]

def sing():
	return verses(1,12)

def verse(verseno):
	if verseno == 1:
		return statement % (days[0],) + items[0]
	else:
		tail = ""
		for verse in range(verseno, 0, -1):
			if verse != 1:
				tail += items[verse-1] + " "
			else:
				tail += "and " + items[verse-1]
		return statement % (days[verseno-1],) + tail

def verses(startVerse, endVerse):
	output = ""
	for verseno in range(startVerse, endVerse+1):
		output += verse(verseno) + "\n"

	return output
