class Bob():

	def hey(self,message):
		if self._silence(message):  
			return 'Fine. Be that way.'
		if self._shouting(message): 
			return 'Woah, chill out!'
		if self._question(message): 
			return 'Sure.'
		return 'Whatever.'

	def _silence(self,message):  
		return not message 
	def _shouting(self,message): 
		return message == message.upper() 	
	def _question(self,message): 
		return message.endswith('?')	
