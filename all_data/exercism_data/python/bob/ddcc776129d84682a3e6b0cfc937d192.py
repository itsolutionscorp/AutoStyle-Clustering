def hey(string):
	if _is_shouting(string):
		return "Woah, chill out!"

	if _is_silence(string):
		return "Fine. Be that way!"

	if _is_question(string):
		return "Sure."
		
	return "Whatever."

def _is_shouting(string):
	return string.isupper();

def _is_silence(string):
	return len(string) == 0 or string.isspace();

def _is_question(string):
	return string.endswith("?");
