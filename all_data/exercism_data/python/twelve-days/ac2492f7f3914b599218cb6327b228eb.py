ordinal = ['first', 'second', 'third', 'fourth', 'fifth', 'sixth', 'seventh',
			'eighth', 'ninth', 'tenth', 'eleventh', 'twelfth']

gifts = ['a Partridge in a Pear Tree', 'two Turtle Doves', 'three French Hens', 'four Calling Birds', 'five Gold Rings',
'six Geese-a-Laying', 'seven Swans-a-Swimming', 'eight Maids-a-Milking', 'nine Ladies Dancing',
'ten Lords-a-Leaping', 'eleven Pipers Piping', 'twelve Drummers Drumming']

line = "On the {} day of Christmas my true love gave to me, {}.\n"

def verse(v):
	if v <2:
		return line.format(ordinal[v - 1], gifts[0])
	else:
		return line.format(ordinal[v - 1], ', '.join(g for g in reversed(gifts[1:v])) + ', and ' + gifts[0])

def verses(start, finish):
	return '\n'.join(verse(i) for i in range(start, finish + 1)) + '\n'

def sing():
	return verses(1, 12)
