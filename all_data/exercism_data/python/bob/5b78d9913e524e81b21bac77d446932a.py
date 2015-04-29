def hey(x):
	if x.strip(' \t\r\n') == '':
		return 'Fine. Be that way!'
	elif x.isupper():
		return 'Whoa, chill out!'
	elif x[-1] == '?':
		return 'Sure.'
	else:
		return 'Whatever.'
