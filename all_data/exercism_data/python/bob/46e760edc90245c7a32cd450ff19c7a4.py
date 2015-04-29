def hey(what):
	answer = None
	if(what.isupper()):
		answer = 'Whoa, chill out!'
	elif(what.rstrip().endswith("?")):
		answer = 'Sure.'
	elif(not what.strip()):
		answer = 'Fine. Be that way!'
	else:
		answer = 'Whatever.'
	return answer
