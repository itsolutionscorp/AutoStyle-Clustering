#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	if len(what.strip()) == 0:
		return 'Fine. Be that way!'
	elif what == what.upper() and what != what.lower():
		return 'Whoa, chill out!'
	elif what.strip()[-1] == '?':
		return 'Sure.'
	else:
		return 'Whatever.'
