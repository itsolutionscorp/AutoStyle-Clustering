def hey(instr):
	if instr.isupper():
		return "Whoa, chill out!"
	elif instr.endswith('?'):
		return 'Sure.'
	elif not instr.strip():
		return "Fine. Be that way!"
	return "Whatever."
