def is_shout(something):
	#Remove numbers and symbols
	words = [filter(lambda c: c.isalpha(), word) for word in something.split(' ')]

	#Remove empty strings	
	words = filter(lambda w: w != '', words)

	#Is only made up of symbols
	if len(words) == 0:
		return False

	for word in words:
		if not word.isupper():
			return False
	return True

def hey(what):
	if what.strip() == '':
		return "Fine. Be that way!"

	elif is_shout(what.strip()):
		return "Whoa, chill out!"

	elif what.strip()[-1] == '?':
		return "Sure."

	else:
		return "Whatever."
