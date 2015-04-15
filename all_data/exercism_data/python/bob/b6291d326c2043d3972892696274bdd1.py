#
# Skeleton file for the Python "Bob" exercise.
#

def containsLetter(what):
	return any(c.isalpha() for c in what)

def all_caps(what):
	if containsLetter(what) and what.strip('').upper() == what:
		return True
	else:
		return False

def isQuestion(what):
	return what.strip().endswith('?')

def isNothing(what):
	return what.strip(' \t') == ''

def hey(what):
	if False:
		pass
	elif isQuestion(what) and not all_caps(what):
		return 'Sure.'
	elif isNothing(what):
		return 'Fine. Be that way!'
	elif all_caps(what):
		return 'Whoa, chill out!'
	else :
		return 'Whatever.'

