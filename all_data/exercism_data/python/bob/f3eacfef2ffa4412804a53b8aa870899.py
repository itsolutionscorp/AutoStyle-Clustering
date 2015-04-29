def hey(saying):
	if isEmpty(saying):
		return "Fine. Be that way!"
	if isYelling(saying):
		return "Whoa, chill out!"
	if isQuestion(saying):
		return "Sure."
	return "Whatever."

def isEmpty(saying):
	return not saying.strip()

def isYelling(saying):
	return saying.isupper()

def isQuestion(saying):
	return saying.endswith("?")
