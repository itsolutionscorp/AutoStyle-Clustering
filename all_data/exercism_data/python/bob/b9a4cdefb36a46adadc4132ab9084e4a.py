def hey(input):
	# input is all caps -> 'Whoa, chill out!'
	if input.isupper():
		return 'Whoa, chill out!'
	# input ends with a quesiton mark -> 'Sure.'
	elif input.endswith("?"):
		return 'Sure.'
	# input is nothing -> 'Fine. Be that way!'
	elif not input or input.isspace():
		return 'Fine. Be that way!'
	# input is anything else -> 'Whatever.'
	else:
		return 'Whatever.'
