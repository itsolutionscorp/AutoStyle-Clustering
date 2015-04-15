#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	answers = [ "Whatever.", "Sure.", 'Whoa, chill out!', 'Fine. Be that way!' ]

	what = what.strip()

	if len(what) == 0:
		return answers[3]
	elif what.isupper():
		return answers[2]
	elif what.endswith("?"):
		return answers[1]


	return answers[0]
