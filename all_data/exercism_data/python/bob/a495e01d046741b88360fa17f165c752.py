class Bob:
	def __init__(self):
		pass

	def hey(self, text):
		query = ['who', 'what', 'when', 'why', 'where', 'how']

		if len(text.strip()) == 0:
			return 'Fine. Be that way!'

		if text.isupper():
			return 'Woah, chill out!'

		lastChar = text.strip()[-1]
		
		
		if any(x in text for x in query) or lastChar == '?':
			return 'Sure.'
		else:
			return 'Whatever.'
