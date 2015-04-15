def hey(x):

	x_length = len(x)

	if x.isupper():
                return 'Whoa, chill out!'
	elif x[x_length-1:x_length] == "?":
		return 'Sure.'
	elif not x.strip():
		return 'Fine. Be that way!'
	else:
		return 'Whatever.'
