import re

class Bob:
	question = re.compile("\\?$")
	alpha = re.compile("[a-z]", re.IGNORECASE)
	silence = re.compile("^\\s*$")
	def hey(self, stmt):
		if Bob.alpha.search(stmt) and stmt.upper() == stmt:
			return "Woah, chill out!"
		elif Bob.silence.match(stmt):
			return "Fine. Be that way!"
		elif Bob.question.search(stmt):
			return "Sure."
		else:
			return "Whatever."
