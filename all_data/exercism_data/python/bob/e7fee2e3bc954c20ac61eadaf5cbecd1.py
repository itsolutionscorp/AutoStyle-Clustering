#
# Solution for the Python "Bob" exercise.
#

QUESTION_RESPONSE = "Sure."
SHOUT_RESPONSE = "Whoa, chill out!"
EMPTY_RESPONSE = "Fine. Be that way!"
OTHER_RESPONSE = "Whatever."

def hey(what):
	if not isinstance(what, str):
		return EMPTY_RESPONSE

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
	if what.isspace():
		return True
	return False

def isShouting(what):
	what_upper = what.upper()
	what_lower = what.lower()
	if what == what.upper() and what_upper != what_lower:
		return True
	return False

def isQuestion(what):
	if what[-1] == '?':
		return True
	return False
