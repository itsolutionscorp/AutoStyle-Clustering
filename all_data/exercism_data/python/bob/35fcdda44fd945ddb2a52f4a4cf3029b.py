def hey(statement):

	if(len(statement.strip()) == 0): # no input
		return "Fine. Be that way!"
	elif(statement.isupper()): # yelling
		return "Whoa, chill out!"
	elif(statement[-1] == '?'): # question
		return "Sure."
	else: # all else
		return "Whatever."
