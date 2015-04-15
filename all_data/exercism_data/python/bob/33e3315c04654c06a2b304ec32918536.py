# Hey function to give the responses of bob.
def hey(str):
	if(str.isupper()):
		return "Woah, chill out!"
	elif (str.endswith("?")):
		return "Sure."
	elif(str.isspace() or not str):
		return "Fine. Be that way!"
	else:
		return "Whatever."
