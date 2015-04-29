#
# Skeleton file for the Python "Bob" exercise.
#
cap = set('ABCDEFGHIJKLMNOPQRSTUVWXYZ')

def hey(what):
	#print what
	if what == "":
		return "Fine. Be that way!"
	elif what[len(what)-1] == "!":
		return "Whoa, chill out!"
	elif what[len(what)-1] == "?":
		return "Sure."
	elif any((c in cap) for c in what):
		return "Whoa, chill out!"
	else:
		return "Whatever."
