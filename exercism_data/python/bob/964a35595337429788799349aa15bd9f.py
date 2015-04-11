def hey(a):
	if a.strip() == "":
		return "Fine. Be that way!"
	elif a.isupper() == True:
		return "Whoa, chill out!"
	elif a.endswith("?") == True:
		return ("Sure.")
	else:
		return ("Whatever.")
