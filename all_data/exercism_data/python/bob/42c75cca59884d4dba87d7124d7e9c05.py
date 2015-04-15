import re

reflags = re.MULTILINE
question = re.compile(r"\?\s*$", reflags)
nocontent = re.compile(r"^\s*$", reflags)

def is_yelling(q):
	n_lower_chars = sum(1. for c in q if c.islower())
	n_upper_chars = sum(1. for c in q if c.isupper())
	return (n_lower_chars+n_upper_chars > 0. and n_upper_chars / (n_upper_chars + n_lower_chars) > 0.75)
		

def hey(q):
	if is_yelling(q):
		return "Whoa, chill out!"
	elif question.search(q) is not None:
		return "Sure."
	elif nocontent.search(q) is not None:
		return "Fine. Be that way!"
	else:
		return "Whatever."
		
