def hey(said):
	response = ""
	if said == said.upper() and said.upper() != said.lower():
		response = "Woah, chill out!"
	elif said.strip() == "":
		response = 'Fine. Be that way!'
	elif said[-1] == "?":
		response = "Sure."
	else:
		response = "Whatever."
	return(response)
