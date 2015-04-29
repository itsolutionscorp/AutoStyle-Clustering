def hey(input):
	if input.isupper():
		response = "Whoa, chill out!"
	elif input.strip().endswith('?'):
		response = "Sure."
	elif len(input.strip()) == 0 :
		response = "Fine. Be that way!"
	else:
		response = "Whatever."

	return response
