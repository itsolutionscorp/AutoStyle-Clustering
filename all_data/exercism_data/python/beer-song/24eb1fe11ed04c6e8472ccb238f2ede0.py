def song(start, stop = 0):
	return "\n".join(verse(i) for i in xrange(start, stop-1, -1)) + "\n"

def verse(num):
	result = ''
	result += "{0} of beer on the wall, {1} of beer.\n".format(get_bottle(num).capitalize(), get_bottle(num))
	if num > 0:
		result += "Take {0} down and pass it around".format('one' if num != 1 else 'it')
	else:
		result += "Go to the store and buy some more"
		num = 100
	result += ", {0} of beer on the wall.\n".format(get_bottle(num-1))
		
	return result

def get_bottle(num):
	first = num if (num > 0) else 'no more'
	second = 'bottles' if (num != 1) else 'bottle'
	return "{0} {1}".format(first, second)
