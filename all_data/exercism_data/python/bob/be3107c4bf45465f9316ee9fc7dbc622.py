class Bob(object):
	def hey(_, arg):
		if arg.endswith('?') and (not arg.isupper()):
			return 'Sure.'
		elif arg.isupper():
			return 'Woah, chill out!'
		elif arg is None or arg.strip() == '':
			return 'Fine. Be that way!'
		else:
			return 'Whatever.'
	
