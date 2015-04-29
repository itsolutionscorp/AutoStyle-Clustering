#
# Solution for the "bob"-exercise
#

import re

def isQuestion(prompt):
	if isShout(prompt):
		return False
	else:
		return prompt.endswith("?")

def isBlank(prompt):
	return not prompt.strip()

def isShout(prompt):
	return prompt.isupper()

def endsBlank(prompt):
	return prompt.endswith(" ")

def hey(what):
	if (endsBlank(what)) or (isQuestion(what)):
		return "Sure."
	elif (isBlank(what)):
		return "Fine. Be that way!"
	elif (isShout(what)):
		return "Whoa, chill out!"
	return "Whatever."
