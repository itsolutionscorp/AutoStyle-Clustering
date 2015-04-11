#
# Skeleton file for the Python "Bob" exercise.
#

def hey(what):
	# for a character next to a character is blank, check if the next char is blank
	# if so, strip. otherwise, keep
	what2 = what.strip()
	what2 = what2.rstrip()
	what2 = what2.lstrip()

	if what2.isupper():
		return "Whoa, chill out!"
	elif what2.endswith("?"):
		return "Sure."
	elif (len(what) == 0) or ('\t' in what) or ('\n' in what):
		return "Fine. Be that way!" 
	else:
		return "Whatever."
