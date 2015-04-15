#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):

	if (isNotASentence(what)):
		return "Fine. Be that way!"

	elif (exclamation(what)):
		return "Whoa, chill out!"

	elif isQuestion(what):
		return "Sure."

	else:
		return "Whatever."

	


def exclamation(what):
	theStrippedString = what.rstrip
	return what.isupper()

def isNotASentence(what):
	theString = what.rstrip()
	return len(theString) == 0

def isQuestion(what):
	theStrippedString = what.rstrip()
	return theStrippedString[-1] == '?'
