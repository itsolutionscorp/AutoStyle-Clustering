__author__ = 'benlarue'


def yelling(inp):
# yelling gets a response of Whoa, chill out!
	if inp.isupper():
		return True
	else:
		return False


def questions(inp):
# questions get a response of Sure.
	inp.strip()
	if inp.endswith("?"):
		return True
	else:
		return False


def nothing(inp):
# nothing gets a Fine. Be that way.
	if not inp or inp.strip() == "":
		return True
	else:
		return False


def hey(inp):

	if yelling(inp):
		return "Whoa, chill out!"
	elif questions(inp):
		return "Sure."
	elif nothing(inp):
		return "Fine. Be that way!"
	else:
		return "Whatever."
