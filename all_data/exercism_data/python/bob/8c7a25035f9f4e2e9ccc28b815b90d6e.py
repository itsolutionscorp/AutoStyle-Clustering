#
# Skeleton file for the Python "Bob" exercise.
#\
def hey(what):
	if not what or what.isspace():
		return 'Fine. Be that way!'
	elif what.rstrip('?!').isupper():
		return 'Whoa, chill out!'
	elif what.rstrip().endswith('?'):
		return 'Sure.'
	else:
		return 'Whatever.'
	return
