#
# Solution for the Python "Bob" exercise.
#

QUESTION_RESPONSE = "Sure."
SHOUT_RESPONSE = "Whoa, chill out!"
EMPTY_RESPONSE = "Fine. Be that way!"
OTHER_RESPONSE = "Whatever."

def hey(what):
	what = what.strip()

	if not what:
		return EMPTY_RESPONSE

	if what.isupper():
		return SHOUT_RESPONSE

	if what.endswith('?'):
		return QUESTION_RESPONSE

	return OTHER_RESPONSE
