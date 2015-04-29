#
# Skeleton file for the Python "Bob" exercise.
#
import re

def removeSpaces(what):
	cleanWhat = what
	while ( len(cleanWhat) > 0 and ( cleanWhat[0] == ' ' or cleanWhat[0] == '\t' or cleanWhat[0] == '\r' or cleanWhat[0] == '\n' ) ):
		cleanWhat = cleanWhat[1:]
	while ( len(cleanWhat) > 0 and ( cleanWhat[-1] == ' ' or cleanWhat[-1] == '\t' or cleanWhat[-1] == '\r' or cleanWhat[-1] == '\n' ) ):
		cleanWhat = cleanWhat[:-1]
	return cleanWhat

def isAQuestion(what):
	return ( removeSpaces(what)[-1] == '?' )

def isAllCaps(what):
	return what == what.upper()

def onlyContainsNumbers(what):
	m = re.search('[a-zA-Z]', what)
	return m == None

def isEmpty(what):
	return removeSpaces(what) == ''

def hey(what):
	if not isEmpty(what):
		if isAllCaps(what) and isAQuestion(what) and not onlyContainsNumbers(what):
			return "Whoa, chill out!"
		elif isAQuestion(what):
			return "Sure."
		elif isAllCaps(what) and not onlyContainsNumbers(what):
			return "Whoa, chill out!"
		else:
			return "Whatever."
	else:
		return "Fine. Be that way!"
