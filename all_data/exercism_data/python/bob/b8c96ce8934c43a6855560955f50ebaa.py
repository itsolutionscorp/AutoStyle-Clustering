#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):

	if what.strip() == "":
		answer = "Fine. Be that way!"
	elif what.isupper():
		answer = "Whoa, chill out!"
	elif what[-1] == "?":
		answer = "Sure."
	else:
		answer = "Whatever."

	return answer
