#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	what = what.strip()
	if what.isupper():
		return 'Whoa, chill out!'
	if what.endswith("?"):
		return 'Sure.'
	if what == '':
		return 'Fine. Be that way!'
	return 'Whatever.'
