def _yelling(say):
	return say.isupper()
		
def _asking(say):
	return say.endswith('?')

def _hushing(say):
	return not say

def hey(say):
	say = say.strip()

	if _hushing(say):
		return 'Fine. Be that way!'
	
	if _yelling(say):
		return 'Whoa, chill out!'

	if _asking(say):
		return 'Sure.'

	return 'Whatever.'
