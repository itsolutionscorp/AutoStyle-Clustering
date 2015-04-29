days = [
	"", "first", "second", "third", "fourth", "fifth", "sixth",
	"seventh", "eighth", "ninth", "tenth", "eleventh", "twelfth"
]

gifts = [
	"twelve Drummers Drumming",
	"eleven Pipers Piping",
	"ten Lords-a-Leaping",
	"nine Ladies Dancing",
	"eight Maids-a-Milking",
	"seven Swans-a-Swimming",
	"six Geese-a-Laying",
	"five Gold Rings",
	"four Calling Birds",
	"three French Hens",
	"two Turtle Doves",
	"a Partridge in a Pear Tree.\n"
]

def verse(n):

	res = ["On the %s day of Christmas my true love gave to me" % days[n]]
	res.extend(gifts[-n:])

	if n > 1:
		res[-1] = "and %s" % res[-1]

	return ', '.join(res)

def sing():
	return verses(1, 12)

def verses(lo, hi):
	return ''.join(
		verse(n) + '\n'
		for n in xrange(lo, hi + 1)
	)
