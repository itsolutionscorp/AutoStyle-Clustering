import re

def hey(what):
	# Prolonged silence is just whitespace
	if what == "" or re.search('^\s+$', what):
		return 'Fine. Be that way!'

	# Shouting consists of ALL CAPS
	if re.search('^[^a-z]+$', what) and re.search('[A-Z]+', what):
		# Python 2.x regexes don't work with unicode, so use islower()
		# to catch lower case unicode chars (such as umlats):
		for c in what:
			if c.islower():
				return 'Whatever.'
		return 'Whoa, chill out!'

	# Questions end with a '?'
	if re.search('\?$', what):
		return 'Sure.'

	# Default answer for everything else
	return 'Whatever.'
