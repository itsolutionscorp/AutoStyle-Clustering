def hey(input):
	if isAllCaps(input):
		response = "Whoa, chill out!"
	elif input[-1:] == "?":
		response = "Sure."
	elif len(input.strip()) == 0 :
		response = "Fine. Be that way!"
	else:
		response = "Whatever."

	return response


def getAlphaNum(input):
	return [char for char in input if char.isalnum()]


def isAllCaps(input):
	return str(getAlphaNum(input)).isupper()
