#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):

	what_stripped = what.strip()

	if len(what_stripped) == 0:
		return 'Fine. Be that way!'
	elif what_stripped.isupper():
		return 'Whoa, chill out!'
	elif what_stripped.endswith('?'):
		return 'Sure.'
	else:
		return 'Whatever.'
