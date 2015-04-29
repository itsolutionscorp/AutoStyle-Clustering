day_ordinals = ['first',
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

_gifts = ['a Partridge in a Pear Tree',
		 'two Turtle Doves',
		 'three French Hens',
		 'four Calling Birds',
		 'five Gold Rings',
		 'six Geese-a-Laying',
		 'seven Swans-a-Swimming',
		 'eight Maids-a-Milking',
		 'nine Ladies Dancing',
		 'ten Lords-a-Leaping',
		 'eleven Pipers Piping',
		 'twelve Drummers Drumming']

_refrain = "On the %s day of Christmas my true love gave to me, "

def _gift_descr(num, add_conjunction=False):
	desc = _gifts[num-1]
	# Only append this if we have multiple verses
	if num > 1:
		desc += ', ' + _gift_descr(num-1, add_conjunction)
	conjunction = 'and ' if num == 1 and add_conjunction else ''
	return conjunction + desc

def verse(num):
	if num < 1:
		raise IndexError("Verse number must be greater than or equal to 1!")
	if 12 < num:
		raise IndexError("Verse number must be less than or equal to 12!")
	add_conjunction = num > 1
	return _refrain % day_ordinals[num-1] + _gift_descr(num, add_conjunction) + '.\n'

def verses(start, end):
	verse_string = ''
	for i in xrange(start, end+1):
		verse_string += verse(i) + '\n'
	return verse_string

def sing():
	return verses(1, 12)
