#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    if not what.strip():
        response = 'Fine. Be that way!'    
    elif what.isupper():
        response = 'Whoa, chill out!'
    elif what.endswith('?'):
        response = 'Sure.'
    else:
        response = 'Whatever.'

    return response
		
	
