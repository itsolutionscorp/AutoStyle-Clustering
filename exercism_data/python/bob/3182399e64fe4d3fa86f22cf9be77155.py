class Bob(object):
	def hey(self, words):
		if words.endswith("?") and words.isupper() == False:
			return 'Sure.'
		if words.isupper():
			return 'Woah, chill out!'
		if not words or words.isspace():
			return 'Fine. Be that way!'
		else:	
			return 'Whatever.'
