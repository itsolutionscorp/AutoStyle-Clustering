#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	if what.strip().endswith('?') and not (what.isupper()):
		return 'Sure.'
	elif what.isspace() or len(what)==0 :
		return 'Fine. Be that way!'
	elif what.isupper():
		return 'Whoa, chill out!'
	else:
		return 'Whatever.'
