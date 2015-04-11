GIFTS = (
	('first', 'a Partridge in a Pear Tree.'),
	('second', 'two Turtle Doves'),
	('third', 'three French Hens'),
	('fourth', 'four Calling Birds'),
	('fifth', 'five Gold Rings'),
	('sixth', 'six Geese-a-Laying'),
	('seventh', 'seven Swans-a-Swimming'),
	('eighth', 'eight Maids-a-Milking'),
	('ninth', 'nine Ladies Dancing'),
	('tenth', 'ten Lords-a-Leaping'),
	('eleventh', 'eleven Pipers Piping'),
	('twelfth', 'twelve Drummers Drumming'),
)

def verse(num):
	parts = ['On the {} day of Christmas my true love gave to me'.format(GIFTS[num-1][0])]
	parts.extend(GIFTS[i-1][1] for i in xrange(num, 0, -1))
	if num > 1: parts[-1] = 'and ' + parts[-1]
	return ', '.join(parts) + "\n"

def verses(start, stop):
	return '\n'.join(verse(i) for i in xrange(start, stop + 1)) + '\n'

def sing():
	return verses(1, len(GIFTS))
