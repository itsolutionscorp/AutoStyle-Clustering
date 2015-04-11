#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	if shouting(what):
		return "Whoa, chill out!"
	if question(what):
		return "Sure."
	if nothing(what):
		return "Fine. Be that way!"
	else:
		return "Whatever."




def shouting(what):
	if what.isupper():
		return True

def question(what):
	if (len(what.strip()) > 0):
		ls = list(what.strip())
		if (ls[-1] == "?"):
			return True

def nothing(what):
	if len(what.strip()) == 0:
		return True
