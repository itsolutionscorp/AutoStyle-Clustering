#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	reply = "Whatever."  #sets Bob's default reply to 'Whatever.'
	lowercase = "".join(c for c in what if c.islower()) #string of lowercase letters in what
	uppercase = "".join(c for c in what if c.isupper()) # string of uppercase letters in what
	all_caps = False
	#sets all_caps to True if there are no lower case letters and more than one uppercase
	if len(lowercase) == 0 and len(uppercase) > 0:
		all_caps = True
	if all_caps:
		reply = "Whoa, chill out!"
	#checks if what is empty (could contain spaces or \n, \t, etc..)
	elif what.strip() == "":
		reply = "Fine. Be that way!"
	#Checks if the last character in what is a "?"
	elif what[len(what)-1] == "?":
		reply = "Sure."
	return reply
