def hey(input):
	# input is all caps -> 'Whoa, chill out!'
	if input.isupper():
		return 'Whoa, chill out!'
	# input ends with a quesiton mark -> 'Sure.'
	elif input[-1:] == "?":
		return 'Sure.'
	# input is nothing -> 'Fine. Be that way!'
	elif not any(c.isalpha() or c.isnumeric() for c in input):
		return 'Fine. Be that way!'
	# input is anything else -> 'Whatever.'
	else:
		return 'Whatever.'
