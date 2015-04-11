
def hey(string): 
	if string.isupper(): # Check if string is a shout
		return 'Woah, chill out!'
	elif string=='': # Check if string is void
		return 'Fine. Be that way!'
	elif string[-1]=='?': # Check for a question (ends with ?)
		return 'Sure.'
	elif vuota(string): # Check for prolonged silence
		return 'Fine. Be that way!'
	else: # Everything else
		return 'Whatever.'

def vuota(string):
	for c in string: 
		if c.isalpha() or c.isalnum(): # If any of the char of the string is a letter or a number,it's no silence
			return False
	return True

