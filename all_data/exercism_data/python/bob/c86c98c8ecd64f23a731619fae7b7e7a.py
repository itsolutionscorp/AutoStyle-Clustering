class Bob(object):

	def hey(_,sentence):
		if sentence.isupper():
			return 'Whoa, chill out!'
		elif sentence.endswith('?'):
			return 'Sure.'
		elif not sentence.strip():
			return 'Fine. Be that way!'
		else:
			return 'Whatever.'
