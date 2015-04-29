# twelve_days.py
# Write a program that outputs the lyrics to 'The Twelve Days of Christmas'

"""
@ sing()		: function to output whole song
@ verse(n)		: functoon to get nth line
@ verses(a,b)	: function to get lines a to b
"""

h 	  = lambda x : "On the %s day of Christmas my true love gave to me, " %x
lines = "twelve Drummers Drumming, eleven Pipers Piping, ten Lords-a-Leaping, nine Ladies Dancing, eight Maids-a-Milking, seven Swans-a-Swimming, six Geese-a-Laying, five Gold Rings, four Calling Birds, three French Hens, two Turtle Doves, and a Partridge in a Pear Tree.".split(', ')

t = dict(zip(range(1,13),['first','second','third','fourth','fifth','sixth','seventh','eighth','ninth','tenth','eleventh','twelfth']))

def verse(n):
	if n == 1:
		return h(t[1])+ "a Partridge in a Pear Tree.\n"

	return h(t[n])+", ".join(lines[12-n:])+"\n"

verses = lambda a,b: "\n".join(verse(n) for n in range(a,b+1))+"\n"
sing   = lambda : verses(1,12)
