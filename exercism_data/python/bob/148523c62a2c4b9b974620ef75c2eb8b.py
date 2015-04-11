def hey(text):
	question = "Sure."
	upper = "Whoa, chill out!"
	empty = "Fine. Be that way!"
	anything_else = "Whatever."

	text = text.strip()

	if not text:
		return empty
	elif text.isupper():
		return upper
	elif text.endswith("?"):
		return question
	else:
		return anything_else
