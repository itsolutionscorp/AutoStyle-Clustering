from string import punctuation

whiteSpace = " \t\n"
numbers = "1234567890"

def isCapsLock(stuff):
	strippedStuff = ""
	for i in stuff:
		if i.isalpha():
			strippedStuff += i

	if strippedStuff == "":
		return False
	elif strippedStuff.upper() == strippedStuff:
		return True
	else:
		return False


def isNotEnglish(stuff):
	toReturn = True
	for i in stuff:
		if i.isalpha() == False and i.isspace() == False and i in punctuation == False and i in numbers == False:
			toReturn = False
	return toReturn


def hey(stuff):
	if stuff == "" or stuff.isspace():
		return "Fine. Be that way!"
	elif isCapsLock(stuff):
		return "Whoa, chill out!"
	elif stuff[(len(stuff)-1)] == "?":
		return "Sure."
	else:
		return "Whatever."
