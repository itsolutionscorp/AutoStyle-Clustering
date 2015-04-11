# Bob
# Justin Ewasko

# RESPONSE_QUESTION is returned if string ends in '?' and is not all caps
RESPONSE_QUESTION = 'Sure.'

# RESPONSE_YELL is returned if string is all caps
RESPONSE_YELL = 'Whoa, chill out!'

# RESPONSE_EMPTY is returned if string contains no characters or is empty
RESPONSE_EMPTY = 'Fine. Be that way!'

# RESPONSE_DEFAULT is returned in all other cases
RESPONSE_DEFAULT = 'Whatever.'

def hey(inputString):
	if inputString.isupper():
		return RESPONSE_YELL
	elif inputString.isspace() or not inputString:
		return RESPONSE_EMPTY
	elif inputString.find('?') == len(inputString) - 1:
		return RESPONSE_QUESTION
	else:
		return RESPONSE_DEFAULT
