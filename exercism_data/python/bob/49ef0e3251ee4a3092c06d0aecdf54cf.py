class Bob(object):

	def hey(self, message):

		if self._nothing_special(message):
			return "Fine. Be that way!"
		elif self._yell_at_me(message):
			return "Woah, chill out!"
		elif self._question(message):
			return "Sure."
		else:
			 return "Whatever."
		

	def _nothing_special(self, message):
		""" Returns the True if the stripped string is empty. """
		return message.strip()==""


	def _yell_at_me(self, message):
		""" 
			Returns True if the string sent is equal to its uppercase representation and 
			the string contains uppercase letters
		"""
		return message.isupper()


	def _question(self, message):
		""" Returns true if the message contains a question mark at the end of the message. """
		# Search for a question mark at the end of the string
		return message.endswith('?')
