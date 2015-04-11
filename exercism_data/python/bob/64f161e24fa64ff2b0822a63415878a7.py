class Bob():
    
    def hey(self, input):

	length = len(input)

	#is it nothing or just empty space?
	if ((length == 0) or (input.isspace())):
	    return 'Fine. Be that way!'
	#are you yelling at him?
	elif ((input.upper() == input) and (any(x.isalpha() for x in input))):
	    return 'Whoa, chill out!'
	#is it a question?
	elif ((input.rfind('?') == length - 1)):
	    return 'Sure.'
	else:
	    return 'Whatever.'
