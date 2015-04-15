def hey(str):
	if str.isupper():
		return "Whoa, chill out!"

	if str.endswith("?"):
		return "Sure."
		
	if not str.strip():
		return "Fine. Be that way!"
		
	return "Whatever."

