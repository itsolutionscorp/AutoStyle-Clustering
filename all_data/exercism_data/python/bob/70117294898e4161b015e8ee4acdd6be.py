#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	
	#check for yelling (Yelling == upper-case)
	if what.isupper():
		return "Whoa, chill out!"

	#check for a question 
	#	strip() takes out white space on both ends of string
	if what.strip().endswith('?') : 
		return "Sure."
		
	#check if nothing was said
	if not what.strip():
		return "Fine. Be that way!"
	
	#default answer
	return "Whatever."
