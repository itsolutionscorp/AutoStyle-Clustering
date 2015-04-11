def hey(txt):

	if txt.endswith("?") and txt.isupper() == False:
		return "Sure."
	elif txt.strip() == "":
		return "Fine. Be that way!"
	elif txt.isupper() == True:
		return "Whoa, chill out!"
	else:
		return "Whatever."
