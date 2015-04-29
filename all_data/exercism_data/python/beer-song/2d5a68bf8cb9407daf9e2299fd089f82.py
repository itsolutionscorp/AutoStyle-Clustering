def verse(v):
	bottle = lambda v: 'bottle' if v == 1 else 'bottles'
	if v > 1:
		return "{0} {1} of beer on the wall, {0} {1} of beer.\nTake one down and pass it around, {2} {3} of beer on the wall.\n".format(v, bottle(v), v-1, bottle(v-1))
	elif v > 0:
		return "1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n"
	else:
		return "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"


def song(start, finish=0):
	return '\n'.join(verse(i) for i in range(start, finish - 1, -1))+'\n'
