import re


class Bob():

	def hey(self, message):

		if self.nothing_special(message):
			return "Fine. Be that way!"
		elif self.yell_at_me(message):
			return "Woah, chill out!"
		elif self.question(message):
			return "Sure."
		else:
			 return "Whatever."
		

	def nothing_special(self, message):
		""" Returns the True if the stripped string is empty. """
		if message.strip()=="":
			return True
		else:
			return False



	def yell_at_me(self, message):
		""" 
			Returns True if the string sent is equal to its uppercase representation and 
			the string contains uppercase letters
		"""
		if message == message.upper() and self.contains_uppercase(message):
			return True
		else:
			return False



	def question(self, message):
		""" Returns true if the message contains a question mark at the end of the message. """
		# Search for a question mark at the end of the string
		match = re.search(r'\?\Z', message)
		if match:
			return True
		else:
			return False



	####################################################################
	# Utility functions
	####################################################################
	def contains_uppercase(self, message):
		""" Returns true if the str contains any uppercase character """

		match = re.search(r'[A-Z]', message)
		if match:
			return True
		else:
			return False
