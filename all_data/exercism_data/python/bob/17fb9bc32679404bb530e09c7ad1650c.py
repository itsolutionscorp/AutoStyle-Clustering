def hey(text):
	question = "Sure."
	upper = "Whoa, chill out!"
	empty = "Fine. Be that way!"
	anything_else = "Whatever."

	if text.strip() == "":
		return empty
	elif text.isupper():
		return upper
	elif text[-1] == "?":
		return question
	else:
		return anything_else
