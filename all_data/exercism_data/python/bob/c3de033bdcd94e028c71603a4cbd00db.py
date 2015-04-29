#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):

	if len(what.strip()) == 0:
		return 'Fine. Be that way!'
	elif what.isupper():
		return 'Whoa, chill out!'
	elif what[-1] == '?':
		return 'Sure.'
	else:
	    return 'Whatever.'
