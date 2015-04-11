# bob.py: Defines function "hey", which returns bob's default 
#		  responses to various inputs

def hey(input):
	if (input.strip() == ''):
		return "Fine. Be that way!"
	elif (input.isupper()):
		return "Woah, chill out!"
	elif (input.endswith('?')):
		return "Sure."
	else:
		return "Whatever."
