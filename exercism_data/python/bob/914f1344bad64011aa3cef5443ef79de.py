def hey(phrase):
	if not isinstance(phrase, "".__class__):
		raise ValueError("phrase must be a string")
	
	if __is_empty_phrase(phrase):
		return "Fine. Be that way!" 
	elif __is_a_shout(phrase): 
		return "Whoa, chill out!"
	elif __is_a_question(phrase):
		return "Sure."
	else:
		return "Whatever."

def  __is_empty_phrase(phrase):
	return len(phrase.strip()) == 0
 
def __is_a_shout(phrase):
	is_a_shout = False
	for c in phrase:
		if c.isalpha():
			if c.upper() == c:
				is_a_shout = True
			else:
				is_a_shout = False
				break
	
	return is_a_shout
			
def __is_a_question(phrase):
	trimmed = phrase.strip();
	if len(trimmed) > 0 and trimmed[-1] == "?":
		return True
	else:
		return False
