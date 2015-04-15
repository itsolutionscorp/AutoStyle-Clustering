import string

def hey (phrase):

	if phrase.isupper():
		return "Whoa, chill out!"	

	elif (isQuestion(phrase)):
		return "Sure."

	elif (isEmpty(phrase) == True) or (hasAlpha(phrase) == False) and (hasDigit(phrase) == False):
		return "Fine. Be that way!"

	else:
		return "Whatever."

def isQuestion (phrase):
	if phrase.endswith("?"):
		return True

def isEmpty (phrase):
	if len(phrase) == 0:
		return True
	else:
		return False

def hasAlpha (phrase):
	if len(phrase) == 0:
		return False
	for i in xrange(0,len(phrase)):
		if phrase[i] in string.letters:
			return True
	return False

def hasDigit (phrase):
	if len(phrase) == 0:
		return False
	for i in xrange(0,len(phrase)):
		if phrase[i] in string.digits:
			return True
	return False
