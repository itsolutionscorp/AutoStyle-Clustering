def hey(question):
	if len(question) <= 0 or question.isspace():
		return "Fine. Be that way!"
	elif question.isupper():
		return "Whoa, chill out!"
	elif question[-1] == '?':
		return "Sure."
	else:
		return "Whatever."
