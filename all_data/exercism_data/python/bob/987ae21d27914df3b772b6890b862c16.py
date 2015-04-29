#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	what = what.strip()
	if what.isupper():
		return 'Whoa, chill out!'
	elif not what:
		return 'Fine. Be that way!'	
	if what[-1] == '?':
		return 'Sure.'
	else:
		return 'Whatever.'
