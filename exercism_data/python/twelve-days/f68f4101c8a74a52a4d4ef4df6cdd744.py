ordinals, gifts = zip(('',''),
		('first', 'a Partridge in a Pear Tree.'),
		('second', 'two Turtle Doves, and '),
		('third', 'three French Hens, '),
		('fourth', 'four Calling Birds, '),
		('fifth', 'five Gold Rings, '),
		('sixth', 'six Geese-a-Laying, '),
		('seventh', 'seven Swans-a-Swimming, '),
		('eighth', 'eight Maids-a-Milking, '),
		('ninth', 'nine Ladies Dancing, '),
		('tenth', 'ten Lords-a-Leaping, '),
		('eleventh', 'eleven Pipers Piping, '),
		('twelfth', 'twelve Drummers Drumming, '))

def verse(daynum):
	st = 'On the {0} day of Christmas my true love gave to me, '.format(ordinals[daynum])
	allgifts = [gifts[ii]  for ii in range(daynum,0,-1)]
	return st + ''.join(allgifts) + '\n'

def verses(daystart, dayend=None):
	if not dayend:
		dayend = daystart
	st = [verse(daynum) for daynum in range(daystart, (1+dayend))]
	return '\n'.join(st) + '\n'

def sing():
	return verses(1,12)
