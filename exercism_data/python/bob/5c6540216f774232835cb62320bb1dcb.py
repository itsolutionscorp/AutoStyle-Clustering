def hey(stmt):
    
    #all caps
    if stmt.isupper():
        return 'Whoa, chill out!'
    
    #ends in ?
    elif stmt.endswith('?'):
        return 'Sure.'
		
    #only whitespace
    elif len(stmt.strip()) == 0:
        return 'Fine. Be that way!'
    
    #default response
    else:
        return 'Whatever.'
