#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	what = what.strip()
	yell = what.isupper()
	question = what.endswith('?') and not yell
	empty = len(what)==0
	if question:
		return 'Sure.'
	elif yell:
		return 'Whoa, chill out!'
	elif empty:
		return 'Fine. Be that way!'
	else:
		return 'Whatever.'
