#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):

	what = what.strip()
	
	if what == '':
		return 'Fine. Be that way!'
	elif what.isupper() and not what.islower():
		return 'Whoa, chill out!'
	elif what[-1] == '?':
		return 'Sure.'
	else:
		return 'Whatever.'
