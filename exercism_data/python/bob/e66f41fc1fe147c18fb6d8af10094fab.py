def hey(msg):
	msg = msg.strip()
	if not msg():
		return "Fine. Be that way."
	elif msg.isupper():
		return "Whoa, chill out!"
	elif msg.endswith("?"):
		return "Sure."

	return "Whatever"

    
