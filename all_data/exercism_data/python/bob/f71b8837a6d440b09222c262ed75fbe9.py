import re

def hey(arg):

	if re.search('[a-zA-Z]', arg) and arg.upper() == arg:
		return 'Woah, chill out!'
	elif arg.endswith('?'):
		return 'Sure.'
	elif not re.search('[a-zA-Z0-9]', arg):
		return 'Fine. Be that way!'
	else:
		return 'Whatever.'
