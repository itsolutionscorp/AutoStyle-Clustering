def hey(message):
	message = message.strip();
	if len(message) == 0:
		return "Fine. Be that way!"
	elif message.upper() == message and message.lower() != message:
		return "Whoa, chill out!"
	elif message[-1] == '?':
		return "Sure."
	else:
		return "Whatever."
