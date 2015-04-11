def verse(bottles):
	strophe = "{Some_bottles} of beer on the wall, {some_bottles} of beer.\n".format(
		Some_bottles=_bottles_str(bottles),
		some_bottles=_bottles_str(bottles).lower()
	)
	if bottles == 0:
		return strophe + "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
	elif bottles == 1:
		return strophe + "Take it down and pass it around, no more bottles of beer on the wall.\n"
	else:
		return strophe + "Take one down and pass it around, {one_less_bottle} of beer on the wall.\n".format(
			one_less_bottle=_bottles_str(bottles-1).lower()
		)

def song(start, stop=0):
	return "\n".join(verse(bottles) for bottles in range(start, stop-1, -1))+"\n"

def _bottles_str(bottles):
	if bottles <= 0:
		return "No more bottles"
	elif bottles == 1:
		return "1 bottle"
	else:
		return "{many} bottles".format(many=bottles)
