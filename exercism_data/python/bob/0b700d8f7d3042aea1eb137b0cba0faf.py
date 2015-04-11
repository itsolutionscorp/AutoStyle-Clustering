#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):


	if what.isupper():
		return 'Whoa, chill out!'

	elif not what:
	 	return 'Fine. Be that way!'

	elif what.isspace():
		return 'Fine. Be that way!'

	elif what.strip().endswith('?'):
		return 'Sure.'

	else:
	    return 'Whatever.'
