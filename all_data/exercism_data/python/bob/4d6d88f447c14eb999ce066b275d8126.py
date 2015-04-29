import re

def hey(msg):
	if re.match(r"[A-Z]*!\Z", msg):
		return 'Whoa, chill out!'
	elif re.match(r".*!\Z", msg):
		return "Whatever."
	elif re.match(r".*\?\Z", msg):
		return 'Sure.'
	elif re.match(r".*\?.*\.\Z", msg):
		return 'Whatever.'
	elif re.match(r".*\..*!\Z", msg):
		return 'Fine.'
	elif re.match(r"\Z", msg):
		return 'Fine. Be that way!'
	elif re.match(r".*\..*!\Z", msg):
		return 'Whoa, chill out!';
	else:
		return 'Whatever.'
