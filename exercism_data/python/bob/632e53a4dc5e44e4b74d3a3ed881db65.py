def hey (msg):
	response = "Whatever."
	length = len(msg)
	if length > 0 and msg[length-1] == "?":
		response = "Sure."
	if msg.isupper():
		response = "Whoa, chill out!"
	if not msg.strip():
		response = "Fine. Be that way!"
	
	return response
