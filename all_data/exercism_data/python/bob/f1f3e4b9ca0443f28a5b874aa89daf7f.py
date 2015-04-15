def _is_yelling(say):
	return say.isupper()
		
def _is_asking(say):
	return say.endswith('?')

def _is_hushing(say):
	return not say

def hey(say):
	say = say.strip()

	if _is_hushing(say):
		return 'Fine. Be that way!'
	
	if _is_yelling(say):
		return 'Whoa, chill out!'

	if _is_asking(say):
		return 'Sure.'

	return 'Whatever.'
