#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):

	# Checks for empty speech.
	if (what.strip() == ''):
		return "Fine. Be that way!"

	# Checks for a question without yelling.
	if (what.strip()[-1] == "?" and (what != what.upper() or what == what.lower())):
		return "Sure."

	# Checks for yelling.
	if (what == what.upper() and what != what.lower()):
		return "Whoa, chill out!"
	if (what[-1] == "!" and (what != what.lower() and what == what.upper())):
		return "Whoa, chill out!"

	return "Whatever."
