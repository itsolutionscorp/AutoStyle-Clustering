def yelling(say):
	return say.isupper()
		
def asking(say):
	return say.endswith('?')

def hushing(say):
	return not say

def hey(say):
	say = say.strip()

	if hushing(say):
		return 'Fine. Be that way!'
	
	if yelling(say):
		return 'Whoa, chill out!'

	if asking(say):
		return 'Sure.'

	return 'Whatever.'
