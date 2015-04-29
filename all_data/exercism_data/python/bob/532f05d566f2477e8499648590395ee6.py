class Bob(object):
	def hey(self, str):
		str = self._trim(str)
		if (not str):
			return 'Fine. Be that way!'
		elif (str.isupper()):
			return 'Woah, chill out!'
		elif (str.endswith('?')):
			return 'Sure.'
		else:
			return 'Whatever.'
			
	def _trim(self, str):
		# trim whitespace (or)
		# convert None to string	
		if (str is None):
			return ''
		return str.strip()
			
