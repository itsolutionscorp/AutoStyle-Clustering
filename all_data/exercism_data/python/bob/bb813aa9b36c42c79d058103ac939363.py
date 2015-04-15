import re
p=re.compile('\w')
def hey(p1):
	result='Whatever.'
	if p1.isupper():
		result='Whoa, chill out!'
	elif not p.search(p1):
		result='Fine. Be that way!'
	elif p1.endswith('?'):
		result='Sure.'
	return result
