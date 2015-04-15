#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	if isYelling(what):
		ans = 'Whoa, chill out!'
	elif isQuestion(what):
		ans = 'Sure.'
	elif isSilence(what):
		ans = 'Fine. Be that way!'
	else:
		ans = 'Whatever.'
	return ans

def isQuestion(what):
	whatNoSpaces = what.replace(' ', '')
	if whatNoSpaces:
		return whatNoSpaces[-1] == '?'
	return False

def isYelling(what):
	return what.isupper()

def isSilence(what):
	return (not what) or what.isspace()
