def sing():
	return verses(1,12)
	
def verses(s,e):
	v = []
	for i in range(s,e+1):
		v.append(verse(i))
	return "\n".join(v)+"\n"
	
def verse(songnbr):
	ordinal = ['first','second','third','fourth','fifth','sixth','seventh','eighth','ninth','tenth','eleventh','twelfth']
	gifts = ['twelve Drummers Drumming, ', 'eleven Pipers Piping, ', 'ten Lords-a-Leaping, ', 'nine Ladies Dancing, ', 'eight Maids-a-Milking, ', 'seven Swans-a-Swimming, ', 'six Geese-a-Laying, ', 'five Gold Rings, ', 'four Calling Birds, ', 'three French Hens, ', 'two Turtle Doves, and ', 'a Partridge in a Pear Tree.\n']
	return "".join(["On the %s day of Christmas my true love gave to me, " % ordinal[songnbr-1]] + gifts[-songnbr:])
