# -- coding: utf-8 --
#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	speech = what
	yell = "Whoa, chill out!"
	if speech.isupper() == True:
		return yell
	elif speech.endswith('?') == True:
		return "Sure."
	elif speech.isspace() == True:
		return "Fine. Be that way!"
	elif speech == "":
		return "Fine. Be that way!"
	else:
		return "Whatever."
