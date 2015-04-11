class Bob(object):

	def hey(self, bob_said):
		if bob_said is None or bob_said.strip() == '':
			return 'Fine. Be that way!'
		if bob_said.isupper():
			return 'Woah, chill out!'
		if bob_said.endswith('?'):
			return 'Sure.'
		return 'Whatever.'
