_ordinal = ['first',
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
			'twelfth']

_gifts = ['twelve Drummers Drumming, ',
		  'eleven Pipers Piping, ',
		  'ten Lords-a-Leaping, ',
		  'nine Ladies Dancing, ',
		  'eight Maids-a-Milking, ',
		  'seven Swans-a-Swimming, ',
		  'six Geese-a-Laying, ',
		  'five Gold Rings, ',
		  'four Calling Birds, ',
		  'three French Hens, ',
		  'two Turtle Doves, and ',
		  'a Partridge in a Pear Tree.\n']

_refrain = 'On the %s day of Christmas my true love gave to me, '

def verse(songnbr):
	return ''.join([_refrain % _ordinal[songnbr-1]] + _gifts[-songnbr:])

def verses(s, e):
	return '\n'.join(verse(n) for n in xrange(s, e + 1)) + '\n'

def sing():
	return verses(1, 12)
