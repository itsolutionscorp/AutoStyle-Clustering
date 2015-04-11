import re

def hey(strInput):
	"""
	Function for emulating Bob's responses
	"""
	# Removing empty characters before and after string
	strInput = strInput.strip()
	if strInput == '':
		return 'Fine. Be that way!'

	# Removing any numbers and non-word characters
	strOnlyLetters = re.sub('[0-9]', '', strInput, 0,  re.UNICODE)
	strOnlyLetters = re.sub('[^\w]', '', strOnlyLetters, 0,  re.UNICODE)
	# Creating upper case string
	strUpperLetters = strOnlyLetters.upper()

	# Obtaining last character
	if strInput != '':
		strLastChar = strInput[-1]
	else:
		strLastChar = ''

	# Core logic
	if strLastChar == '?' and strOnlyLetters == '':
		return 'Sure.'
	if strOnlyLetters == '' and strInput != '':
		return 'Whatever.'
	if  strOnlyLetters == strUpperLetters  :
		return 'Whoa, chill out!'
	if strLastChar == "?":
		return 'Sure.'

	return 'Whatever.'
