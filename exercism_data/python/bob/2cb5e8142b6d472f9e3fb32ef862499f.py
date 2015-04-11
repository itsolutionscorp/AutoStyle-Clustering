class Bob:
	def hey(self, sentence):
		response = 'Whatever.'
		if not sentence.strip():
 			response = 'Fine. Be that way!'
 		elif sentence.isupper()	:
 			response = 'Woah, chill out!'
		elif sentence.endswith("?"):
			response = 'Sure.'
		return response
