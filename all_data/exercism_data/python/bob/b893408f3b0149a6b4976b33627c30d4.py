#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):

	if len(what) == 0 or what.isspace():
		answer = "Fine. Be that way!"
	elif what.isupper():
		answer = "Whoa, chill out!"
	elif what[-1] == "?":
		answer = "Sure."
	else:
		answer = "Whatever."

	return answer
