def hey(t):
	if t.isupper():
		return "Whoa, chill out!"
	elif t.endswith("?"): 
		return "Sure." 
	elif t.isspace() or t == "":
		return "Fine. Be that way!"
	else:
		return "Whatever."
