#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	what = what.strip()

	if what == '':
		return "Fine. Be that way!"

	elif what.lower() != what and what.upper() == what:
		return "Whoa, chill out!"

	elif what[len(what)-1] == '?':
		return "Sure."

	else:
		return "Whatever."
