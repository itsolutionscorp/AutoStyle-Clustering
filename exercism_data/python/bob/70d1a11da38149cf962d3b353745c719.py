import string

def hey (phrase):

	if phrase.isupper():
		return "Whoa, chill out!"	

	elif phrase.endswith("?"):
		return "Sure."

	elif (len(phrase) == 0) or (hasAlpha(phrase) == False) and (hasDigit(phrase) == False):
		return "Fine. Be that way!"

	else:
		return "Whatever."

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
