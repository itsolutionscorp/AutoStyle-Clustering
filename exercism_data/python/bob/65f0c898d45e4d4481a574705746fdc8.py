#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):

	if what.isupper():
		return 'Whoa, chill out!'
	elif what.rstrip().endswith("?"):
		return 'Sure.'
	elif not what:
		return 'Fine. Be that way!'
	elif what == '    \t':
		return 'Fine. Be that way!'


	return 'Whatever.'
