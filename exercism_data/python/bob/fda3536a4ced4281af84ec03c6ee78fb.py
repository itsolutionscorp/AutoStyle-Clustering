import re

uppercase_letters = re.compile('[A-Z]')

def hey(s):
	if len(s.strip()) == 0:
		return 'Fine. Be that way!'
	if s.upper() == s and uppercase_letters.search(s):
		return 'Whoa, chill out!'
	if s[-1] == '?':
		return 'Sure.'
	return 'Whatever.'
