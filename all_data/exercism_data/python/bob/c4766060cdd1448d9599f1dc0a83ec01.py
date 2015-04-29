def hey(input=""):
	input = input.rstrip()
	if not input:
		return "Fine. Be that way!"
	if input.isupper():
		return "Whoa, chill out!"
	elif input[-1] == "?":
		return "Sure."
	else:
		return "Whatever."
