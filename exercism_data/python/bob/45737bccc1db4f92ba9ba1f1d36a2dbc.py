#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	strippedString = what.strip()
	if (what.isspace() | (what == '')):
		return u'Fine. Be that way!'
	elif ((what == what.upper()) & (what != what.lower())):
		return u'Whoa, chill out!'
	elif (strippedString[-1] == "?"):
		return u'Sure.'
	else:
		return u'Whatever.'
	return
