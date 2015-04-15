def hey(msg):
	if msg.isspace() == True or len(msg) == 0:
		return "Fine. Be that way!"
	if msg.upper() == msg and any(char.isalpha() for char in msg):
		return "Whoa, chill out!"
	if msg[len(msg)-1] == '?':
		return "Sure."
	return "Whatever."
