#
# Skeleton file for the Python "Bob" exercise.
#


def hey(what):
	answer = 'Whatever.'
	
	if(isQuestion(what)):
		answer = 'Sure.'
	if(isYelling(what)):
		answer = 'Whoa, chill out!'
	if(isNothing(what)):
		answer = 'Fine. Be that way!'
		
	return unicode(answer)

def hasNumbers(inputString):
    return any(char.isdigit() for char in inputString)
	
def hasAlpha(inputString):
	return any(char.isalpha() for char in inputString)
	
def isQuestion(inputString):
	isQ = False
	if(inputString.endswith('?')):
		isQ = True;
		if(hasAlpha(inputString) and inputString == inputString.upper()):
			isQ = False;
	
	return isQ;
	
def isYelling(inputString):
	isY = False
	if(inputString == inputString.upper() and hasAlpha(inputString)):
		isY = True

	return isY;

def isNothing(inputString):
	isN = False
	if(not hasAlpha(inputString) and not hasNumbers(inputString)):
		isN = True

	return isN
