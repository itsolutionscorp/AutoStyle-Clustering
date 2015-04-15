subverse = {
	0 : "",
	1 : "malt\nthat lay in the ",
	2 : "rat\nthat ate the ",
	3 : "cat\nthat killed the ",
	4 : "dog\nthat worried the ",
	5 : "cow with the crumpled horn\nthat tossed the ",
	6 : "maiden all forlorn\nthat milked the ",
	7 : "man all tattered and torn\nthat kissed the ",
	8 : "priest all shaven and shorn\nthat married the ",
	9 : "rooster that crowed in the morn\nthat woke the ",
	10 : "farmer sowing his corn\nthat kept the ",
	11 : "horse and the hound and the horn\nthat belonged to the ",
}

def verse(nr):
	return "This is the " + "".join(subverse[i] for i in range(nr,-1,-1)) + "house that Jack built."

def rhyme():
	return "\n\n".join(verse(i) for i in range(12)) 

print rhyme()
