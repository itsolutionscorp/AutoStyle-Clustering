#
# Skeleton file for the Python "Bob" exercise.
#
class Bob:
	def __init__(arg):
		return

	def hey(self, what):
		rtnResponse = "Whatever."

		if self._isWhitespace(what):
			rtnResponse = "Fine. Be that way!"
		elif self._isQuestion(what):
			rtnResponse = "Sure."
		elif self._isYelling(what):
			rtnResponse = "Woah, chill out!"

		return rtnResponse

	def _isWhitespace(self, what):
		rtnVal = len(what.strip()) == 0
		return rtnVal

	def _isQuestion(self, what):
		rtnVal = False
		lastChar = self._getLastCharacter(what)
		
		if lastChar == "?" and not self._isAllUppercase(what):
			rtnVal = True

		return rtnVal

	def _isYelling(self, what):
		rtnVal = False

		if self._isAllUppercase(what):
			rtnVal = True

		return rtnVal

	def _getLastCharacter(self, what):
		lastChar = what[len(what)-1]
		return lastChar

	def _isAllUppercase(self, what):
		#remove any characters from the string that aren't
		#letters to test if they're all uppercase
		test_string = filter(lambda c: c.isalpha(), what)
		
		if len(test_string) == 0:
			return False

		for c in test_string:
			if c.islower():
				return False

		return True
