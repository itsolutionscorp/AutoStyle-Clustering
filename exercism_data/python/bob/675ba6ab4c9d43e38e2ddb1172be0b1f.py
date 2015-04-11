class Bob(object):
	def hey(self, s):
		s = s.strip()
		if not s:
			return 'Fine. Be that way!'
		#if not self.anyLower(s) and self.anyLetter(s):
		if s.isupper():
			return 'Woah, chill out!'
		if s[-1] == '?':
			return 'Sure.'
		
		return 'Whatever.'
	
	#def anyLower(self, s):
	#	for c in s:
	#		if c.islower():
	#			return True
	#	return False
		
	#def anyLetter(self, s):
	#	for c in s:
	#		if c.isalpha():
	#			return True
	#	return False
