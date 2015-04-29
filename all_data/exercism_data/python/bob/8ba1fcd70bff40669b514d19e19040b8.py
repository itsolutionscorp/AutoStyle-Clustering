#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	#I checked for upercase first since it had the most cases.
	if what.isupper():
		say = "Whoa, chill out!"
	#I originally check for a ? anywhere in the string, but I guess that doesn't count, seems ambiguous.
	elif what.endswith("?"):
		say = "Sure."
	#I checked this last since it has the most complexity. ispace checks for whitespace characters, while "" takes in strings that are empty, but declared.
	elif what.isspace() or what == "":
		say = "Fine. Be that way!"
	#Catchall
	else:
		say = "Whatever."
	return say
