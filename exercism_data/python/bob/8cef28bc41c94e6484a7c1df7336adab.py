def hey(what=''):
	"""
	Simulates Bob, the lackadaisical teenager.
	:type what: string
	"""

	response = ""
	what = what.strip()
	if len(what) == 0:
		response = "Fine. Be that way!"
	elif what.isupper():
		response = "Whoa, chill out!"
	elif what.endswith("?"):
		response = "Sure."
	else:
		response = "Whatever."
	return response
