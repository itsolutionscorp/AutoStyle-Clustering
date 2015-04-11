#
# Skeleton file for the Python "Bob" exercise.
#

def hey(what):

	text = what.strip()

	if text.isupper():
		return 'Whoa, chill out!'

	elif text.endswith('?'):
		return 'Sure.'

	elif not text:
		return 'Fine. Be that way!'

	else:
		return 'Whatever.'
