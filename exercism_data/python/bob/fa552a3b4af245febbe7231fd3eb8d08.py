#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	if what.isupper():
		str = "Whoa, chill out!"
	elif not what.strip():
		str = "Fine. Be that way!"
	elif what.endswith('?'):
		str = 'Sure.'
	else:
		str = "Whatever."
	return str
