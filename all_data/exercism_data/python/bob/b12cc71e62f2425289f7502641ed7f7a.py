def hey(message):
	if message.isupper():
		return "Woah, chill out!"
	elif message.endswith("?"):
		return "Sure."
	elif len(message.strip())==0:
		return "Fine. Be that way!"
	else:
		return "Whatever."
