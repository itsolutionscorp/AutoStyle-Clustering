#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	what = what.strip()
	if _is_shouting(what):
		return "Whoa, chill out!"
	elif _is_question(what):
		return "Sure."

	elif _is_silence(what):
		return "Fine. Be that way!"
	else:
		return "Whatever."

def _is_question(words):
	return words.endswith('?')
	
def _is_shouting(words):
	return words.isupper()
	
def _is_silence(words):
	return words == ""
