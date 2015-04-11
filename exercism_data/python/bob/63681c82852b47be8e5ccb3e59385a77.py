#
# Skeleton file for the Python "Bob" exercise.
#

def hey(what):
	what = what.strip()

	if what.endswith("?") == 1 and what != what.upper():
		return "Sure."
	elif (what.find("!") == 1) or what.isupper():
		return "Whoa, chill out!"
	elif what == "":
		return "Fine. Be that way!"
	return "Whatever."
