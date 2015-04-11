import re

def hey(call):
	if not call.strip():
		return "Fine. Be that way!"
	if call == call.upper():
		if re.search('[a-zA-Z]', call):
			return "Whoa, chill out!"
	if call[-1:] == '?':
		return 'Sure.'
	return 'Whatever.'
