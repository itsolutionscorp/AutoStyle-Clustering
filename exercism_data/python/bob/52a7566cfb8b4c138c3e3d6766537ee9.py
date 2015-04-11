def hey(str):
	if str.isspace() or not str:
		return 'Fine. Be that way!'
	elif str.isupper():
		return 'Woah, chill out!'
	elif str.endswith('?'):
		return 'Sure.'	 	
	else:
		return 'Whatever.'
