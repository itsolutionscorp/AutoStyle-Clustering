#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	what = unicode(what)
	if not what or what.strip() == '':
		return "Fine. Be that way!"
	elif what.isupper():
		return "Whoa, chill out!"
	elif what.endswith('?'):
		return "Sure."
	else:
		return "Whatever."
