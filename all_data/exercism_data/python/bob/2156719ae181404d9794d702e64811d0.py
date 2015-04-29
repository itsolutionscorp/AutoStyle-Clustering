#
# Skeleton file for the Python "Bob" exercise.
#


def hey(hey):
	if hey.endswith('?') and not hey.isupper():
		return 'Sure.'
	elif hey.isupper():
		return 'Whoa, chill out!'
	elif not hey.strip():
		return 'Fine. Be that way!'
	else:
		return 'Whatever.'
