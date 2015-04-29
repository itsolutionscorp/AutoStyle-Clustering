#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	punct = what[-1]
	if punct == '!' or what.isupper():
		return 'Whoa, chill out!'
	elif punct == '?':
		return 'Sure.'
	elif what.strip() == '':
		return 'Fine. Be that way!'
	else:
		return 'Whatever.'
		
	
