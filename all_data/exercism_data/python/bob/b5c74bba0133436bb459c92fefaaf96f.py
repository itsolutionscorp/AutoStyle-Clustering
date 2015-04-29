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
	return any(x.isalpha() for x in phrase)

def hasDigit (phrase):
	return any(x.isdigit() for x in phrase)
