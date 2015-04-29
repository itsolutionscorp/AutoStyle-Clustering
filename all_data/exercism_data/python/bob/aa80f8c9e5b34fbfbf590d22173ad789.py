#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	answer = None
	if(what.isupper()):
		answer = 'Whoa, chill out!'
	elif(what.endswith("?") or what.endswith(" ")):
		answer = 'Sure.'
	elif(what.endswith('\t') or not what):
		answer = 'Fine. Be that way!'
	else:
		answer = 'Whatever.'
	return answer
