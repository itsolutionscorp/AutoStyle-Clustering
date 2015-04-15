def hey(m):
	stripped = m.strip()
	if len(stripped) <= 0:
		return 'Fine. Be that way!'
	elif m.isupper():
		return "Woah, chill out!"
	elif m[len(m)-1] == '?':
		return "Sure."
	else: 
		return 'Whatever.'
