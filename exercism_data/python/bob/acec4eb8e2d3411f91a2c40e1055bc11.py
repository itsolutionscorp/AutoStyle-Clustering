def hey(text):
	if not text or text.isspace():
		return "Fine. Be that way!"
	elif text.isupper():
		return "Whoa, chill out!"
	elif text[-1] == '?':
		return "Sure."
	else:
		return "Whatever."
