def hey(userInput):
	
	SURE = "Sure."
	FINE = "Fine. Be that way!"
	WHOA = "Whoa, chill out!"
	WHATEVER = "Whatever."
	
	userInput = userInput.strip()
	
	if userInput == "":
		return(FINE)

	if userInput.isupper():
		return(WHOA)
	
	lastLetter = userInput[-1]
	
	if lastLetter == "?":
		return(SURE)
	
	return(WHATEVER)
