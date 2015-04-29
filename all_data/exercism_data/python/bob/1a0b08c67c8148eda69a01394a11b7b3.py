#
# Skeleton file for the Python "Bob" exercise.
#


def hey(hey):
	if hey.isupper():
		return 'Whoa, chill out!'
	elif hey.endswith('?'):
		return 'Sure.'
	elif not hey.strip():
		return 'Fine. Be that way!'
	return 'Whatever.'
