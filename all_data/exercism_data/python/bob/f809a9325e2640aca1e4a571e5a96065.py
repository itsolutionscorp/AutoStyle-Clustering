#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	# If you say nothing to Bob
	if len(what.rstrip()) == 0:
		return "Fine. Be that way!"

	# If you ask Bob a question
	elif what.rstrip()[-1] == "?" and not what.isupper():
		return "Sure."

	# If you shout at Bob in all caps
	elif what.isupper():
		return "Whoa, chill out!"

	# If you say anything else to Bob
	else:
		return "Whatever."
