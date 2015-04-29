def hey(statement):
	if statement.isupper():
		return "Whoa, chill out!"
	elif len(statement.strip()) == 0:
		return "Fine. Be that way!"
	elif statement.endswith("?"):
		return "Sure."
	else:
		return "Whatever."
