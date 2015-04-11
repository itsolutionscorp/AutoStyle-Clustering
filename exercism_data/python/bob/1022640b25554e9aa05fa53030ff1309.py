def hey(statement): 
	if(len(statement.strip()) == 0):
		return "Fine. Be that way!"
	elif(statement.isupper()):
			return "Whoa, chill out!"
	elif(statement[len(statement)-1]=="?"):
		return "Sure."
	else:
		return "Whatever."
