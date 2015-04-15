#
# Skeleton file for the Python "Bob" exercise.
#

def silence(what):
	return not len(what)

def shouting(what):
	return what.isupper()

def question(what):
	return what[-1] == '?'

def hey(what):
	what = what.strip()
	if silence(what):
		return 'Fine. Be that way!'

	if shouting(what):
		return 'Whoa, chill out!'

	if question(what):
		return 'Sure.'

	return "Whatever."
