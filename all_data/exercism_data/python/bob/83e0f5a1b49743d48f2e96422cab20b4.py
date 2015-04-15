import re

class Bob():

	def hey(self, str):

		nothingSpecial = NothingSpecial()
		return nothingSpecial.handle(str)


class NothingSpecial():
	""" 
		Handles the NothingSpecial response. 
		Returns the response if the stripped string is empty 
	"""

	def handle(self, str):
		if str.strip()=="":
			return "Fine. Be that way!"
		else:
			yellAtMe = YellAtMe()
			return yellAtMe.handle(str)



class YellAtMe:
	""" 
		Handles the yelling response. 
		Returns the yelling response if the string sent is equal to its uppercase representation and 
		the string contains uppercase letters
	"""

	def containsUpper(self, str):
		""" Returns true if the str contains any uppercase character """

		match = re.search(r'[A-Z]', str)
		if match:
			return True
		else:
			return False


	def handle(self, str):
		# Search for a exclamation as the last character of the string.
		if str == str.upper() and self.containsUpper(str):
			return "Woah, chill out!"
		else:
			question = Question()
			return question.handle(str)


class Question():
	""" 
		Handles the yelling response. 
		Returns the yelling response if the string sent is equal to its uppercase representation and 
		the string contains uppercase letters
	"""

	def handle(self, str):
		# Search for a question mark at the end of the string
		match = re.search(r'\?\Z', str)
		if match:
			return "Sure."
		else:
			whatEver = WhatEver()
			return whatEver.handle(str)
			

class WhatEver():
	""" 
		Handles the Whatever response. 
	"""

	def handle(self, str):
		return "Whatever."
