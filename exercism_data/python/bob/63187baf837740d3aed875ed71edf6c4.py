#import regular expression library
import re

def hey(what):
	#strip trailing and leading whitespace
	test = what.strip(" ")
	#test for all uppercase string
	if test.isupper():
		response = 'Whoa, chill out!'
	#test for a question mark at the end of the string
	elif re.search("\?\Z",test):
		response = 'Sure.'
	#test to see if string is all whitespace or empty
	elif test.isspace() or not test:
		response = 'Fine. Be that way!'
	else:
		response = 'Whatever.'	
	return response
