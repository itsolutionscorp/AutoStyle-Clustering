#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	if (what.endswith('?') or what.endswith(' ')) and not what.isupper():
		return 'Sure.'	
	if what.isupper():
		return 'Whoa, chill out!'
	if what is None or what.rstrip() == '':
		return 'Fine. Be that way!'
	return 'Whatever.'
