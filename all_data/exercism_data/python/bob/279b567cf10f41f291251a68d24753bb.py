import re

def hey(saying):
	# Test empty message
	if not saying.strip():
		return "Fine. Be that way!"

	# Test yelling
	if saying.isupper():
		return "Whoa, chill out!"

	# Test for question
	if saying.endswith("?"):
		return "Sure."

	# Whatever.
	return "Whatever."
