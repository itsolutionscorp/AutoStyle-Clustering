def hey(convoStart):

	convoStart_length = len(convoStart)

	if convoStart.isupper():
                return 'Whoa, chill out!'
	elif convoStart[convoStart_length-1:convoStart_length] == "?":
		return 'Sure.'
	elif not convoStart.strip():
		return 'Fine. Be that way!'
	else:
		return 'Whatever.'
