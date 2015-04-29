#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	what = what.strip(' \t\n\r')

	if what.isupper():
		return 'Whoa, chill out!'
	
	if what.endswith('?'):
		return 'Sure.'
	
	if not what:
		return 'Fine. Be that way!'

	return 'Whatever.'
