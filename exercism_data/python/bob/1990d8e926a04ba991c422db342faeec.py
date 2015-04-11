def hey(stmt):
	
    #all caps
    if stmt.isupper():
        return 'Whoa, chill out!'
    
    #ends in ?
    elif stmt.endswith('?'):
        return 'Sure.'
		
    #only whitespace
    elif not stmt.strip():
        return 'Fine. Be that way!'
    
    #default response
    else:
        return 'Whatever.'
