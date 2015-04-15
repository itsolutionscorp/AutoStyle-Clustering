def hey(statement):
	statement = statement.strip()
	if statement == statement.upper() and any(i.isalpha() for i in statement):
		return "Whoa, chill out!"
	elif statement == "":
		return "Fine. Be that way!"
	elif statement[-1] == "?":
		return "Sure."
	else:
		return "Whatever."
