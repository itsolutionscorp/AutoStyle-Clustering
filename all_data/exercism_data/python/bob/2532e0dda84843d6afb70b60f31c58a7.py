def hey(message):
	if message.isupper():
		return "Whoa, chill out!"
	elif message[-1:] == "?": 
		return "Sure."
	elif message.isspace() or len(message) is 0:
		return "Fine. Be that way!"
	else:
		return "Whatever."
