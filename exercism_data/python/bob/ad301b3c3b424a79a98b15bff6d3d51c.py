class Bob():
	answerQuestion = "Sure."
	answerYell = "Woah, chill out!"
	answerToAnything = "Fine. Be that way!"
	answerAnythingElse = "Whatever."


	def hey(self, phrase):
		if self.isAnything(phrase):
			return self.answerToAnything
		elif self.isQuestion(phrase):
			return self.answerQuestion
		elif self.isYell(phrase):
			return self.answerYell
		else:
			return self.answerAnythingElse


	def isAnything(self, string):
		if len(string) == 0 or string.isspace():
			return True
		else:
			return False

	def isYell(self, string):
		if string == string.upper() and not self.containsOnlyNumbers(string):
			return True
		else:
			return False

	def isQuestion(self, string):
		if string[len(string) - 1] == '?' and (string != string.upper() or self.containsOnlyNumbers(string)):
			return True
		else:
			return False

	def containsOnlyNumbers(self, string):
		for i in string:
			if (i != ' ' and i != ',' and i != '?' and i != '!') and not i.isdigit():
				return False
		return True
