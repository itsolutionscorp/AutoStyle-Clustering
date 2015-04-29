#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	if ((what.isupper() and "!" in what) or (what.isupper())):
		return "Whoa, chill out!"
	elif (what.endswith("?") or what.endswith(" ")) :
		return "Sure."
	elif len(what)==0 or "\t" in what:
		return "Fine. Be that way!"
	else:
		return "Whatever."
