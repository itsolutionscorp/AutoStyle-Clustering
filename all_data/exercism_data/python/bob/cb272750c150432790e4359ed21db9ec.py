class Bob:
	def is_question(self,text):
		return text.strip().endswith('?')

	def is_yelling(self,text):
		return text.isupper()

	def is_silent_treatment(self,text):
		return len(text.strip())==0;
 
	def hey(__self__, text):
		if __self__.is_yelling(text):
			return 'Woah, chill out!'
		elif __self__.is_question(text):
			return 'Sure.'
		elif __self__.is_silent_treatment(text):
			return 'Fine. Be that way!'
		else: return 'Whatever.'
