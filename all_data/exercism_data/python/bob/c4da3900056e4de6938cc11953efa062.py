#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	if what.isupper() == True:
		return 'Whoa, chill out!'
	elif what == '' or what.find('\t') != -1:
		return 'Fine. Be that way!'
	elif what.find('?') != -1 and what.find(' ', what.find('?')-1, what.find('?')) == -1:
		return 'Sure.'
	else:
		return 'Whatever.'
    
