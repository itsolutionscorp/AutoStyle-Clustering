def hey(message):
	
	# test for empty string
	# strip any whitespace characters first
    if message.strip() == '': 
    	return 'Fine. Be that way!'
    	
    # test for all uppercase characters
    elif message.isupper() == True:
    	return 'Whoa, chill out!'
    
    # test for question mark at the end of the string
    # strip any whitespace characters first	
    elif message.rstrip()[-1] == "?":
    	return 'Sure.'
    
    # if none of the above apply
    else:
    	return 'Whatever.'
