__author__ = 'benlarue'


def yelling(inp):
# yelling gets a response of Whoa, chill out!
	return inp.isupper()


def questions(inp):
# questions get a response of Sure.
	inp.strip()
	return inp.endswith("?")


def nothing(inp):
# nothing gets a Fine. Be that way.
	return not inp or inp.strip() == ""


def hey(inp):

	if yelling(inp):
		return "Whoa, chill out!"
	elif questions(inp):
		return "Sure."
	elif nothing(inp):
		return "Fine. Be that way!"
	else:
		return "Whatever."
