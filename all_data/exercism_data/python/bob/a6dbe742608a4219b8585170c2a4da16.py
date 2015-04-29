def hey(what):
	question	= 'Sure.'
	yelling		= 'Whoa, chill out!'
	nothing		= 'Fine. Be that way!'
	default		= 'Whatever.'

	response 	= what.rstrip().expandtabs()
	responseEnd = len(response) - 1
	
	hasCaps		= True
	hasLetters 	= False

	# After stripping the string, if it's empty, you have nothing. Return nothing.
	if not response:
		return nothing

	# Check if the response has lowercase letters, and has letters in the first place.
	for char in response:
		if isLower(char):
			hasCaps = False
			break
		if isLetter(char):
			hasLetters = True

	# If the string is just numbers and symbols, respond in turn.
	if not hasLetters:
		if response[responseEnd] == '!':
			return yelling
		elif response[responseEnd] == '?':
			return question
		else:
			return default

	# Yelling generally has all caps, then either of the qualifications:
	# 1. Ends with an exclamation point.
	# 2. Ends with a question mark, if no exclamation mark (no question mark and exclamation point? Or interrobang?)
	# 3. No punctuation, but still all caps.
	if caps == True and ((response[responseEnd] == '!') or (response.find("!") == -1 and response[responseEnd] == '?') or (isUpper(response[responseEnd]))):
		return yelling
	elif response[responseEnd] == '?':
		return question
	else:
		return default

def isLower(char):
	"""
	Input: One character, in the form of a string.
	Output: True if character is lowercase, False if it is number, symbol, uppercase, etc.
	"""
	if (ord(char) in range(97, 122)) or (ord(char) in range(224, 255)):
		return True
	else:
		return False

def isUpper(char):
	"""
	Input: One character, in the form of a string.
	Output: True if character is uppercase, False if it is number, symbol, uppercase, etc.
	"""
	if (ord(char) in range(65, 91)) or (ord(char) in range(192, 224)):
		return True
	else:
		return False

def isLetter(char):
	"""
	Input: One character, in the form of a string.
	Output: True if it is a letter, False if otherwise.
	"""
	if (isLower(char) or isUpper(char)):
		return True
	else:
		return False
