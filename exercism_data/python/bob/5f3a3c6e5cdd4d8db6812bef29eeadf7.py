def hey(x):
	if (x.strip() == ''):
		return "Fine. Be that way!"
	if(x.isupper()):
		return "Woah, chill out!"
	if(x[-1] == "?"):
		return "Sure."
	return "Whatever."
