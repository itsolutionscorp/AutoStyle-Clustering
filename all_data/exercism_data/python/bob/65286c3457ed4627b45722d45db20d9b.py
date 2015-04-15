#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	striped = what.strip()
	if len(striped) < 1:
		return "Fine. Be that way!"
	elif striped.isupper():
		return "Whoa, chill out!"
	elif striped.endswith("?"):
		return "Sure."	
	else:
		return "Whatever."
