def hey(question):
	has_letters = any(c.isalpha() for c in question)
	is_shouting = all(c.isupper() for c in question if c.isalpha())
	if len(question) <= 0 or question.isspace():
		return "Fine. Be that way!"
	elif has_letters and is_shouting:
		return "Whoa, chill out!"
	elif question[-1] == '?':
		return "Sure."
	else:
		return "Whatever."
