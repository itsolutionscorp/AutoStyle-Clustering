#
# Solution for the Python "Bob" exercise.
#

QUESTION_RESPONSE = "Sure."
SHOUT_RESPONSE = "Whoa, chill out!"
EMPTY_RESPONSE = "Fine. Be that way!"
OTHER_RESPONSE = "Whatever."

def hey(what):
	if not isinstance(what, str):
		raise TypeError("Expected a string for 'what' but got a(n) %s instead." % type(what))

	what = what.strip()

	if isEmpty(what):
		return EMPTY_RESPONSE

	if isShouting(what):
		return SHOUT_RESPONSE

	if isQuestion(what):
		return QUESTION_RESPONSE

	return OTHER_RESPONSE

def isEmpty(what):
	if not what:
		return True
	return False

def isShouting(what):
	if what.isupper():
		return True
	return False

def isQuestion(what):
	if what.endswith('?'):
		return True
	return False
