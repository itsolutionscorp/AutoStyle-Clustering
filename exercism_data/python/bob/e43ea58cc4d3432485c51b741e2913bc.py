#
# Skeleton file for the Python "Bob" exercise.
#

def all_caps(what):
	return what.isupper()

def isQuestion(what):
	return what.strip().endswith('?')

def isNothing(what):
	return not what.strip(' \t')

def hey(what):
	if isQuestion(what) and not all_caps(what):
		return 'Sure.'
	elif isNothing(what):
		return 'Fine. Be that way!'
	elif all_caps(what):
		return 'Whoa, chill out!'
	
	return 'Whatever.'

