#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):

	if what.isupper():
		return "Whoa, chill out!"

	if what[-1:] == "?": #is last character a ?
		return "Sure."

	if what == "" :
		return "Fine. Be that way!"

	if isitnothing(what.strip(' ')): 
		return "Fine. Be that way!"

	return "Whatever."

def isitnothing(what):
	if not what[0].isalpha() and not what[0].isnumeric():
		if len(what) > 1:
			return isitnothing(what[1:])
		elif len(what) == 1:
			return 1
	else:
		return 0 
