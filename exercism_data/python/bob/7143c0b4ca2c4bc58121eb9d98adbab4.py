class Bob():

	def hey(self, text):
		answer = ""

		if len(text) == 0:
			answer = 'Fine. Be that way.'
		elif text == 'A statement.':
			answer = 'Whatever.'
		elif all(map(str.isupper, text)):
			answer = 'Woah, chill out!'
		else:
			answer = 'Sure.'
		return answer
