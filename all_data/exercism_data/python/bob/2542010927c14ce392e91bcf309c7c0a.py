#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	if isNothing(what):
		return "Fine. Be that way!"
	
	elif isYelling(what):
		return "Whoa, chill out!"

	elif isQuestion(what):
		return "Sure."

	else: return "Whatever."



def isYelling(what):
	if what.isupper():
		return True

def isQuestion(what):
	if what.strip(" ").endswith("?"):
		return True

def isNothing(what):
	if not what or what.isspace():
		return True
