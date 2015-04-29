def containsLetters(m):
	return any(c.isalpha() for c in m)

def isShouting(m):
	if m.upper() == m and containsLetters(m):
		return True
	return False

def isQuestion(m):
	if m[len(m)-1] == '?':
		return True
	return False

def hey(message):
	if message.strip() == '':
		return "Fine. Be that way!"
	if isShouting(message):
		return "Woah, chill out!"
	if isQuestion(message):
		return "Sure."
	return "Whatever."
