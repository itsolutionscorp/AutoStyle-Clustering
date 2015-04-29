def hey(input):
	#if stripped out the string is empty
	if input.strip() == "":
		return "Fine. Be that way!"
	#this returns boolean value, used isupper instead of input == input.upper() because the comparison failed if input was all numbers
	elif input.isupper():
		return "Whoa, chill out!"
	#if the last character of the string is a question mark
	elif input[-1] == "?":
		return "Sure."
	#if none of the above are matched, return whatever
	return "Whatever."
