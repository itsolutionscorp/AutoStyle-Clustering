import re

#class Bob:
def hey(sentence):
	if sentence.isspace() or sentence == "":
		return "Fine. Be that way!"
	elif sentence == sentence.upper() and not re.match('\d, \d, \d$', sentence) and not re.match('\d+\?', sentence):
		return "Whoa, chill out!" 
	elif sentence.endswith("?") :
		return "Sure."		
	else:
		return "Whatever."
