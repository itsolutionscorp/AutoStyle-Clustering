import string

def checkForSilence(input):
	# Check to see if every character of input is classed as whitespace
	if all(c in string.whitespace for c in input):
		return True
	else:
		return False

def checkForQuestion(input):
	# The example file states 'Ending with ? is a question'.
	if(input.endswith('?')):
		return True
	else:
		return False

def checkForYelling(input):
	# First, check to see if any characters in the input could be classed as yelling.
	# Second, check to see if the input as uppercase matches the input.
	if(any(c in string.ascii_lowercase for c in input.lower()) == True):
		if(input.upper() == input):
			return True
	
	return False

def hey(input):
	yelling = checkForYelling(input)
	question = checkForQuestion(input)
	silence = checkForSilence(input)

	if(silence):
		return "Fine. Be that way!"

	if(yelling):
		return "Whoa, chill out!"

	if(question):
		return "Sure."

	return "Whatever."
