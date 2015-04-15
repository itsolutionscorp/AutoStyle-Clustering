def hey(saying):
	if isEmpty(saying):
		return "Fine. Be that way!"
	if isYelling(saying):
		return "Whoa, chill out!"
	if isQuestion(saying):
		return "Sure."
	return "Whatever."

def isEmpty(saying):
	if not saying.strip():
		return True
	return False

def isYelling(saying):
	if saying.isupper():
		return True
	return False

def isQuestion(saying):
	if saying.endswith("?"):
		return True
	return False
