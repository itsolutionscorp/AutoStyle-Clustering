def hey(what):
	""" Hey function returns a string in reply to inputs, 
	replies are governed by rules in brief """
	to 	var1=what
	var2=var1.strip()
	var_what=var2.strip( '/t' )
	# strips out whitespace and /t

	if len(var_what)<1:
		reply='Fine. Be that way!'
		
	elif var_what.isupper():
		reply='Whoa, chill out!'
	
	elif var_what[-1]=='?':
		reply= 'Sure.'
		
	else:
		reply='Whatever.'
		
	return reply
