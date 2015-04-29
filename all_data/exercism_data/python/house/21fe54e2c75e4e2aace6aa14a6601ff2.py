_objects=["house that Jack built.\n", "malt", "rat", "cat", "dog", "cow with the crumpled horn", "maiden all forlorn", "man all tattered and torn", "priest all shaven and shorn", "rooster that crowed in the morn", "farmer sowing his corn", "horse and the hound and the horn"]
_actions=["lay in", "ate", "killed", "worried", "tossed", "milked", "kissed", "married", "woke", "kept", "belonged to"]

# creates line number final-n in a verse. first indicates that it is the first line in a verse.
def make_line(n,first=False):
	if first:
		return "This is the %s\n"%_objects[n]
	return "that %s the %s\n"%(_actions[n],_objects[n])

# constructs verse number n+1
def verse(n):
	answer=make_line(n,True)
	for i in range(n-1,-1,-1):
		answer+=make_line(i)
	return answer

# outputs the whole rhyme
def rhyme():
	answer=""
	for i in range(len(_objects)):
		answer+=verse(i)
	return answer.strip()
