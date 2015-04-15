def hey(text):
	if len(text) <= 0 or text.isspace():
		return "Fine. Be that way!"
	elif text == text.upper() and text.lower() != text:
		return "Whoa, chill out!"
	elif text[-1] == '?':
		return "Sure."
	else:
		return "Whatever."
