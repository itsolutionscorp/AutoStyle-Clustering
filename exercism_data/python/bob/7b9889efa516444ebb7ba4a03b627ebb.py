def hey(param=None):
	if (param.strip()==''):
		return 'Fine. Be that way!'
	elif (param.endswith('?') and not param.isupper()):
		return 'Sure.'
	elif (param.isupper()):
		return 'Whoa, chill out!'
	else:
		return 'Whatever.'
	
