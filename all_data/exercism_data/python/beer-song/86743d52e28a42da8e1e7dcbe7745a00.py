def verse(current):
	if current == 0:
		return "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
	if current == 1:
		return "1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n"
	if current == 2:
		return  "2 bottles of beer on the wall, 2 bottles of beer.\nTake one down and pass it around, 1 bottle of beer on the wall.\n"
	return "%s bottles of beer on the wall, %s bottles of beer.\nTake one down and pass it around, %s bottles of beer on the wall.\n" % (current, current, current-1)

def song(start, end=0):
	return "\n".join([verse(x) for x in range(start,end-1,-1)])+"\n"
