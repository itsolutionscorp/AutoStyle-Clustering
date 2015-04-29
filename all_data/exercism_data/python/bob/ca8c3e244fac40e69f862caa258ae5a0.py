def shouting(message):
	return message.isupper()

def question(message):
	if shouting(message):
		return False
	else:
		return message.endswith('?')

def silence(message):
	if message == '' or message[0:].isspace():
		return True

def hey(message):
	if question(message):
		return 'Sure.'

	elif shouting(message):
		return 'Woah, chill out!'

	elif silence(message):
		return 'Fine. Be that way!'
	
	else:
		return 'Whatever.'
