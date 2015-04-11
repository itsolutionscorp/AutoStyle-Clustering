ordinals = '''
first
second
third
fourth
fifth
sixth
seventh
eighth
ninth
tenth
eleventh
twelfth'''.splitlines()


gifts = '''twelve Drummers Drumming
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
a Partridge in a Pear Tree

'''.splitlines()
gifts.reverse()

def verse(daynum):
	st = 'On the {0} day of Christmas my true love gave to me'.format(ordinals[daynum])
	allgifts = [', '+gifts[ii] for ii in range(daynum,1,-1)]
	if allgifts:
		st = st + ''.join(allgifts) + ', and ' + gifts[1] + '.\n'
	else:
		st = st + ', ' + gifts[1] + '.\n'
	return st

def verses(daystart, dayend=None):
	if not dayend:
		dayend = daystart
	st = [verse(daynum) for daynum in range(daystart, (1+dayend))]
	return '\n'.join(st) + '\n'

def sing():
	return verses(1,12)
