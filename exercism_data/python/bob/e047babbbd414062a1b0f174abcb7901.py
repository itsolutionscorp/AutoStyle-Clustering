def hey(inp):
	print(inp)
	if inp.isupper():
		return "Whoa, chill out!"
	if inp.endswith("?"):
		return "Sure."
	if (not inp or inp.rstrip() == ''):
		return "Fine. Be that way!"
	return "Whatever."
