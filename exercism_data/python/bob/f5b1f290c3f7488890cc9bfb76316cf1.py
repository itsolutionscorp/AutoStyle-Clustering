#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	for i in what:
		if i.isalpha():
			if what == what.upper():
				return "Whoa, chill out!"
	else:
		if len(what) > 0 and what[-1] == "?":
			return "Sure."
		else:
			for i in what:
				if i.isalnum():
					return "Whatever."
			else:
				return "Fine. Be that way!"
