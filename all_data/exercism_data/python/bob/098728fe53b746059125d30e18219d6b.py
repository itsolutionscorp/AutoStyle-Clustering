import re

def hey(what):
	has_letters = re.search(r"[a-z]", what, re.IGNORECASE)

	if (what.strip() == ""):
		return "Fine. Be that way!"
	elif (what.upper() == what and has_letters):
		return "Whoa, chill out!"
	elif (what.endswith("?")):
		return "Sure."

	return "Whatever."
