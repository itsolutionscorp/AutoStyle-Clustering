def hey(statement):
	"""
	Determine Bob's response to a given string input.

	@param statement : A string of what you say to Bob
	"""
	
	bobs_responses = ['Fine. Be that way!', 'Whoa, chill out!', 'Sure.', 'Whatever.'] 	

	# Strip any whitespace, then check for an empty string
	if not statement.strip():
		return bobs_responses[0]

	# If a statement is yelled (in all uppercase), it should match an uppercase version
	# of itself, but it should not match a lowercase version of itself
	elif statement.upper() == statement and statement.lower() != statement:
		return bobs_responses[1]

	# If the statement ends with a question mark
	elif statement[-1] == "?":
		return bobs_responses[2]

	# For all other cases
	else:
		return bobs_responses[3]
