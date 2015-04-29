def is_last_char(char, message):
	return message[-1] == char

def hey(message):
	if not message.strip():
		return "Fine. Be that way!"
	elif message.isupper():
		return "Woah, chill out!"
	elif message.endswith('?'):
		return "Sure."
	else:
		return "Whatever."
