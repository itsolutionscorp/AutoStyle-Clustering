def hey(statement):
	if statement.isupper():
		return "Whoa, chill out!"
	elif len(statement.strip('\n\t ')) == 0:
		return "Fine. Be that way!"
	elif statement[-1] == "?":
		return "Sure."
	else:
		return "Whatever."
