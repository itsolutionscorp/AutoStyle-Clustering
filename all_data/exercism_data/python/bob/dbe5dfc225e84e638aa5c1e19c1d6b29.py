#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	if (what.isupper()):
		return unicode("Whoa, chill out!")
	elif what.endswith('?'):
		return unicode("Sure.")
	elif not what.strip():
		return unicode("Fine. Be that way!")
	else:
		return unicode("Whatever.")
