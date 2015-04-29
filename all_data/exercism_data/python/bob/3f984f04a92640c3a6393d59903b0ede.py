#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	#remove whitespace at end of sentence
	what_stripped = what.rstrip()

	if what_stripped == '':
		return 'Fine. Be that way!'

	elif what_stripped.isupper():
		return 'Whoa, chill out!'

	elif what_stripped[-1] == '?':
		return 'Sure.'
	
	else:
		return 'Whatever.'
