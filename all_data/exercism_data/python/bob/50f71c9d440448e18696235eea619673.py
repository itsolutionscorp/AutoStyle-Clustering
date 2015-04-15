# Skeleton file for the Python "Bob" exercise.

def hey(what):
	input = what.strip()
	if not input:
		return 'Fine. Be that way!'
	elif input.isupper():
		return 'Whoa, chill out!'
	elif input.endswith('?'):
		return 'Sure.'
	else:
		return 'Whatever.'
