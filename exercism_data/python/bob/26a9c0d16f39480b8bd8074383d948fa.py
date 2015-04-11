class Bob:
	def hey(self, statement):
		if not statement.strip():
			return 'Fine. Be that way!'
		elif statement.upper() == statement and statement.lower() != statement:
			return 'Whoa, chill out!'
		elif statement.endswith('?'):
			return 'Sure.'
		else:
			return 'Whatever.'
