def hey(message):
	
	# test for empty string
	# strip any whitespace characters first
    if message.strip() == '': 
    	return 'Fine. Be that way!'
    	
    # test for all uppercase characters
    elif message.isupper():
    	return 'Whoa, chill out!'
    
    # test for question mark at the end of the string
    # strip any whitespace characters first	
    elif message.rstrip()[-1] == "?":
    	return 'Sure.'
    	
    return 'Whatever.'
