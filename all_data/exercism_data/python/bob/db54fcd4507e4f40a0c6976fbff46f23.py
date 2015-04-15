import string

def only_numbers(message):
	return len(set(message) & set(string.letters)) == 0

def is_shouting(message):
	if message.upper() == message and not only_numbers(message):
		return True
	if message[-1] == "!" and message[0:5] != "Let's":
		return True
	return False

def saying_nothing(message):
	return message.strip() == ''

def is_question(message):
	return message[-1:] == '?' and not is_shouting(message) 

class Bob:
			
	def hey(this, message):
		if saying_nothing(message):
			return "Fine. Be that way!"
		elif is_question(message):
			return "Sure."
		elif is_shouting(message):
			return 'Woah, chill out!'
		else: 
			return "Whatever."
