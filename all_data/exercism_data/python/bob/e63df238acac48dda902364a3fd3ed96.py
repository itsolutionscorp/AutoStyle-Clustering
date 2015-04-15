#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	if what.isupper():
		return u'Whoa, chill out!'
	elif what.strip().endswith("?"):
		return u'Sure.'
	elif what.isspace() or len(what)==0:
		return u'Fine. Be that way!'
	else:
		return u'Whatever.'
