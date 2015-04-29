import re

def checkUpper(string):
	if re.search(r'[A-Za-z]', string):
        	for letter in string:
                	if letter != letter.upper():
                        	return False
        	return True

def hey(what):
    if re.search(r'\w', what):
	if checkUpper(what) == True:
		return 'Whoa, chill out!'
        elif what[-1] == "?":
	    return 'Sure.'
	else:
	    return 'Whatever.'
    else:
        return 'Fine. Be that way!'
