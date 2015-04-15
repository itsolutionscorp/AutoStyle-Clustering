def hey(statement):
	"""	Take a string and return bobs answer. """
	if statement == '' or statement.isspace(): # empty string or just white space (saying nothing)
		return "Fine. Be that way!"
	if statement.isupper(): # at least one letter and all in caps (yelling)
		return "Whoa, chill out!"
	if statement[-1] == '?': # Last char is '?' (question)
		return "Sure."
	else:
		return "Whatever."
