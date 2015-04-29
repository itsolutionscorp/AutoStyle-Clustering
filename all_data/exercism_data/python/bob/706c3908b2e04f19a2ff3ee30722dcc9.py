class Bob:

	def hey(self, chat):
		if chat.isupper():
			return 'Woah, chill out!'
		elif chat.endswith('?'):
			return 'Sure.'
		elif len(chat) == 0 or chat.isspace():
			return 'Fine. Be that way!'
		else:
			return 'Whatever.'
