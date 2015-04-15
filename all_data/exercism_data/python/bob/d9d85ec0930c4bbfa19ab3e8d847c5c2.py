#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	if (what.upper() == what) and (what.upper() != what.lower()):
		return 'Whoa, chill out!'
	elif len(what.split()) == 0:
		return 'Fine. Be that way!'
	elif what[-1] == '?':
		return 'Sure.'
	else:
		return 'Whatever.'
