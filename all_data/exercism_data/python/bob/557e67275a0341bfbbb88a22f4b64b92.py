#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	what = what.strip()
	print what
	if what.isupper():
		return 'Whoa, chill out!'
	if what.endswith('?'):
		return 'Sure.'
	if what == '':
		return 'Fine. Be that way!'
	else:
		return 'Whatever.'
