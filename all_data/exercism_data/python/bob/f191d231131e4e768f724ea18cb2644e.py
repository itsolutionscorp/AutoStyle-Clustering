def hey(input):
	if input.strip() == "":
		return "Fine. Be that way!"

	elif input == input.upper():
		return "Whoa, chill out!"

	elif input[-1] == "?":
		return "Sure."

	return "Whatever."
