def hey(inp = False):

	if len(inp.strip()) == 0: # Catches empty strings when stripped of whitespace
		response = 'Fine. Be that way!'

	elif inp.isupper(): # Catches yelling
		response = 'Whoa, chill out!'

	elif inp[-1] == '?': # Catches questions
		response = 'Sure.'

	else: # Catches anything else
		response = 'Whatever.'
	

	return response
		
