def hey(what):
	what = what.strip()	#remove whitespace
	size = len(what)
	if size == 0:   #nothing said
		return 'Fine. Be that way!'
	elif what.isupper():  #all caps, shouting 
		return 'Whoa, chill out!'
	elif what[size-1] == '?':  #not shouting, and last character is a question mark 
		return 'Sure.'
	else:
		return 'Whatever.'
		
