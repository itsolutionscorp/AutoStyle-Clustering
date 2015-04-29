def hey(userInput):
	
	SURE = "Sure."
	FINE = "Fine. Be that way!"
	WHOA = "Whoa, chill out!"
	WHATEVER = "Whatever."
	
	userInput = userInput.strip(" \t\n\r")
	
	if userInput == "":
		return(FINE)
	
	inputLength = len(userInput)
	lastLetter = userInput[inputLength-1]
	
	trimmedString = trim(userInput)

	if checkAllUpperCase(trimmedString):
		return(WHOA)
	
	if lastLetter == "?":
		return(SURE)
	else:
		return(WHATEVER)

def trim(inputString):
	trimmedString = ""
	for i in range(0, len(inputString)-1):
		asciiVal = ord(inputString[i])
		if asciiVal > 64 and asciiVal < 91 or asciiVal > 96 and asciiVal < 123 or asciiVal > 192 and asciiVal < 256: 
				trimmedString += inputString[i]
				
	return trimmedString
		
def checkAllUpperCase(inputString):
	for i in range(0, len(inputString)-1):
		asciiVal = ord(inputString[i])
		
		if asciiVal < 64 or asciiVal > 222:
			return False
			
		elif asciiVal > 91 and asciiVal < 191:
			return False
			
		elif i == len(inputString)-2:
			return(True)
