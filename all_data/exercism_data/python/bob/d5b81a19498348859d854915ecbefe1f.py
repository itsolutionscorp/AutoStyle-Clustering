#Function to control Bob's immediate response
def hey(what):
	#.strip() deletes whitespace so conditions below can be met
	what = what.strip()
	#If a string is not return
	if not what:
		return "Fine. Be that way!"
	#If all uppercases are in a string
	if what.isupper():
		return "Whoa, chill out!"
	#If string ends with a question mark
	if what.endswith("?"):
		return "Sure."
	#If all if-statements are not satisfied then return
	return "Whatever."
	
