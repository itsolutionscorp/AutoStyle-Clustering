RESPONSES = ['Whatever.', 
			'Whoa, chill out!', 
			'Sure.', 
			'Fine. Be that way!']

def hey(what):
	what = what.strip()
	if what.isupper():
		return RESPONSES[1]

	elif what[-1:] == '?':
		return RESPONSES[2]

	elif what == "":
		return RESPONSES[3]

	else: 
		return RESPONSES[0]
