import string
def hey(talk):
	#If the input is empty, return "Fine, be that way!"
	if talk.isspace() or talk == "":
		return "Fine. Be that way!"

	#If the input is un uppercase and contains letters, return "Whoa, chill out!"
	elif talk == talk.upper():
		for x in range(0,len(talk)):
			if talk[x].isalpha():
				return "Whoa, chill out!"

	#If the input is a question, return "Sure."
	shorttalk = talk.strip(string.whitespace)
	if shorttalk[-1] == "?":
		return "Sure."

	#For anything else, return "Whatever."
	return "Whatever."
