def hey(input):
	if input.isupper():
		response = "Whoa, chill out!"
	elif input[-1:] == "?":
		response = "Sure."
	elif len(input.strip()) == 0 :
		response = "Fine. Be that way!"
	else:
		response = "Whatever."

	return response
