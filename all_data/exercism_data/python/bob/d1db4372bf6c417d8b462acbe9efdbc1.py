def hey(what):

	if(what.isupper()):
		return 'Whoa, chill out!'

	if(what.strip().endswith('?')): #if what ends with a question mark
		return 'Sure.'

	elif(not isASCII(what)):
		return 'Whatever.'

	elif(what.strip().endswith('!') and not what.isupper): #if what ends with a question mark
		return 'Whoa, chill out!'

	elif(what.strip() == ''):
		return 'Fine. Be that way!'

	else:
		return 'Whatever.'

def isASCII(what):
	try:
		what.decode('ascii')
		return True
	except(SyntaxError, UnicodeDecodeError, UnicodeEncodeError):
		return False
