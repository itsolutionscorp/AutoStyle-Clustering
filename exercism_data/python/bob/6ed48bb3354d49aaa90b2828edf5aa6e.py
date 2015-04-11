def hey(what):

	#test if all characters that can be uppercase are
	if what.isupper():
	    return 'Whoa, chill out!'
		
	#test if there is nothing (except possibly whitespace)
	elif what.isspace() or what == '':
		return 'Fine. Be that way!'
	
	#empty strings are already filtered out, so there is a -1 index
	elif what[-1] == '?':
		return 'Sure.'
	
	else:
		return 'Whatever.'
		
