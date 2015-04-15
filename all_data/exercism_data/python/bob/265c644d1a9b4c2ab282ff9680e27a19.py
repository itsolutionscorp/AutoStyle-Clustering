#
# Skeleton file for the Python "Bob" exercise.
# 
def hey(what):
	what=what.strip() #Strip off whitespace.
	if not what: #If an empty string
		response='Fine. Be that way!'
	elif what.isupper(): #If yelling (all upper case).
		response='Whoa, chill out!'
	elif what.endswith('?'): #If a question (ends in question mark; Note Could use `what[-1]=='?' but endswith() is more easily read)
		response='Sure.'
	else:
		response="Whatever."
	return response
