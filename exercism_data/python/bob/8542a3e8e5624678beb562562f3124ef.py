def hey(str):
	if str.isspace() or not str:
		return 'Fine. Be that way!'
	elif str.isupper():
		return 'Woah, chill out!'
	elif str[-1]=='?':
		return 'Sure.'	 	
	else:
		return 'Whatever.'
