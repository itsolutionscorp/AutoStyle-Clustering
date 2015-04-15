#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	"""
	:type what: string
	"""

	response = ""
	if len(what.strip()) == 0:
		response = "Fine. Be that way!"
	elif what.isupper():
		response = "Whoa, chill out!"
	elif what.strip().endswith("?"):
		response = "Sure."
	else:
		response = "Whatever."
	return response
