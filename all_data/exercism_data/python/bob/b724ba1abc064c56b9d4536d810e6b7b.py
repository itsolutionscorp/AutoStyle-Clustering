def hey(inString):
	if (len(inString) == 0 or inString.isspace()):
		return 'Fine. Be that way!'
	elif ( inString.isupper()):
		return 'Whoa, chill out!'
	elif (inString[len(inString)-1] == '?'):
		return 'Sure.'
	else:
		return 'Whatever.'
