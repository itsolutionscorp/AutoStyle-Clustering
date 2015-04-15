def containsLetters(m):
	return any(c.isalpha() for c in m)

def isShouting(m):
	return m.upper() == m and containsLetters(m)

def isQuestion(m):
	return m.endswith('?')

def hey(message):
	if message.strip() == '':
		return "Fine. Be that way!"
	if isShouting(message):
		return "Woah, chill out!"
	if isQuestion(message):
		return "Sure."
	return "Whatever."
