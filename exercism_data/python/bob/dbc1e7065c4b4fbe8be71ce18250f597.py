class Bob():
	def hey(self, request):
		if request.strip() == '':
			return 'Fine. Be that way!'
		elif request.isupper():
			return 'Woah, chill out!'
		elif request.endswith('?'):
			return 'Sure.'
		else:
			return 'Whatever.'
