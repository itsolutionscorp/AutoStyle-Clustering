def bottles(n):

	if n == 0:
		return "no more bottles"
	if n == 1:
		return "1 bottle"
	return "%d bottles" % n

def verse(n):
	res =  "%s of beer on the wall, %s of beer.\n" % (bottles(n).capitalize(), bottles(n))
	if n == 0:
		res += "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
	elif n == 1:
		res += "Take it down and pass it around, no more bottles of beer on the wall.\n"
	else:
		res += "Take one down and pass it around, %s of beer on the wall.\n" % bottles(n-1)
	return res

def song(hi, low = 0):
	return ''.join(
		verse(n) + '\n'
		for n in xrange(hi, low - 1, -1)
	)
