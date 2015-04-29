import re

class Bob:
	@staticmethod 
	def yelling(sentence):
		at_least_one_digit = re.compile('\d+')
		is_uppercase = sentence == sentence.upper()
		return (is_uppercase and not at_least_one_digit.match(sentence)) or (is_uppercase and sentence.endswith("!"))

	def hey(self, sentence):
		response = 'Whatever.'
		if not sentence.strip():
 			response = 'Fine. Be that way!'
 		elif Bob.yelling(sentence):
 			response = 'Woah, chill out!'
		elif sentence.endswith("?"):
			response = 'Sure.'
		return response
