import re

ITEMS = """twelve Drummers Drumming
eleven Pipers Piping
ten Lords-a-Leaping
nine Ladies Dancing
eight Maids-a-Milking
seven Swans-a-Swimming
six Geese-a-Laying
five Gold Rings
four Calling Birds
three French Hens
two Turtle Doves
and a Partridge in a Pear Tree
""".split("\n")[::-1]

ORDINALS = "zeroth first second third fourth fifth sixth seventh eighth ninth tenth eleventh twelfth".split()

VERSE_TEMPLATE =  "On the {day} day of Christmas my true love gave to me, {what}.\n"
def sing():
	return verses(1,12)

def verse(n):
	day = ORDINALS[n]
	what = ", ".join(ITEMS[n:0:-1])
	verse = VERSE_TEMPLATE.format(what=what, day=day)
	if n == 1:
		return re.sub(r'and ', '', verse)
	return verse

def verses(first, last):
	return '\n'.join([verse(n) for n in range(first, last+1)]) + '\n'
