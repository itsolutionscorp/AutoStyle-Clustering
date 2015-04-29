def sing():
	return verses(1, 12)

def verses(start, finish):
	return "\n".join(verse(i) for i in range(start, finish+1)) + "\n"

def verse(i):
	return "On the {} day of Christmas my true love gave to me, {}.\n".format(_days[i], _join_presents(_presents[i:0:-1]))

def _join_presents(presents):
	if len(presents) > 1:
		return ", ".join(presents[:-1]) + ", and "+presents[-1]
	else:
		return presents[0]

_days, _presents = zip(
	('zeroth', 'a dustpan and a broom'),
	('first', 'a Partridge in a Pear Tree'),
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
	('twelfth', 'twelve Drummers Drumming')
)
