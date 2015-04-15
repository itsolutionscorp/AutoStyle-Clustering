def hey(text):
	if not text.strip():
		return "Fine. Be that way!"
	elif text.isupper():
		return "Whoa, chill out!"
	elif text[-1] == '?':
		return "Sure."
	return "Whatever."
