#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	what_stripped = what.strip()

	if not what_stripped:
		return 'Fine. Be that way!'
	if what_stripped.isupper():
		return 'Whoa, chill out!'
	if what_stripped.endswith('?'):
		return 'Sure.'

	return 'Whatever.'
