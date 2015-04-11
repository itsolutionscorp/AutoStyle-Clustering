#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):

	# Get last non-whitespace character of a string, used for checking for a question
	tail = what.strip()[-1:]

	if what.isupper():
		return "Whoa, chill out!"
	if what.isspace() or len(what) == 0:
		return "Fine. Be that way!"
	if tail == "?":
		return "Sure."

	return "Whatever."
