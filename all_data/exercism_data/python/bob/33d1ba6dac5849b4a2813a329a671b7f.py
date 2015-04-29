#
# strips the parameter passed and looks for either
#	1. nothing
#	2. uppercase
#	3. ending with question mark
#	4. other
#	And returns appropriate output based on README
def hey(what):
	stripped = what.strip()
	
	if(stripped is None or stripped == ''):
		return 'Fine. Be that way!'
	elif(stripped.isupper()):
		return 'Whoa, chill out!'
	elif(stripped.endswith('?')):
		return 'Sure.'
	else:
		return 'Whatever.'
