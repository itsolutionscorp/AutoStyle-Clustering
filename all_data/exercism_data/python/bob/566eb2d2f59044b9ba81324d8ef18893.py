def hey(what):
	what = what.strip()
	response = {
		"question": "Sure.",
		"exclamation": "Whoa, chill out!",
		"blank": "Fine. Be that way!",
		"everything": "Whatever."
	}
	if not what: 
		return response["blank"]
	else:
		if what.isupper():
			return response["exclamation"]
		elif what.endswith('?'):
			return response["question"]
		else: 
			return response["everything"]
