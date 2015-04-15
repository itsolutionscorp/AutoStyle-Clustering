#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):

	if what.isupper():
		return 'Whoa, chill out!'

	if what.endswith('?'):
		return 'Sure.'

	if (what.isspace() or not what):
		return 'Fine. Be that way!'

	return 'Whatever.'
