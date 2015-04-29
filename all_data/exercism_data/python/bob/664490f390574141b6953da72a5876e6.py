#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	what = what.strip()
    
	
	if _is_silence(what):
		return 'Fine. Be that way!'
	
	elif _is_shouting(what):
		return 'Whoa, chill out!'
	
	elif _is_question(what):
		return 'Sure.'
	else:
		return 'Whatever.'
		
		
	
def _is_silence(what):
	return what == ''
	
def _is_shouting(what):
	return what.isupper()
	
def _is_question(what):
	return what.endswith('?')
