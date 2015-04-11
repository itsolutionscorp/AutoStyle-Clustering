#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	if what.isupper(): #check if completely upper case
		return "Whoa, chill out!"
	elif what.strip() == "": #check if entirely spaces or space-like characters
		return "Fine. Be that way!"
	elif what[-1] == "?": #check if last character is question mark
		return "Sure."
	else:
		return "Whatever."
