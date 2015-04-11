#regex
import re

def hey(what):
	what=what.strip()
	#what will be an empty string if there are only whitespace characters
	if not what:
		return 'Fine. Be that way!'
	#flags=re.U uses unicode library
	elif ''.join(re.findall('\w', what, flags=re.U)).isupper():
		return 'Whoa, chill out!'
	elif what[-1]=='?':
		return 'Sure.'
	else:
		return 'Whatever.'
