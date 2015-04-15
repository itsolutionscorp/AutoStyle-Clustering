def hey(what):
	# Prolonged silence is just whitespace
	if what.strip() == "":
		return 'Fine. Be that way!'

	# Shouting consists of ALL CAPS
	if what.isupper():
		return 'Whoa, chill out!'

	# Questions end with a '?'
	if what.strip().endswith('?'):
		return 'Sure.'

	# Default answer for everything else
	return 'Whatever.'
