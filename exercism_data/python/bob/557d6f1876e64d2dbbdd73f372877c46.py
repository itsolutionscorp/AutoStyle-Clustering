import string

def hey(question):
	if len(question.strip()) == 0:
		return "Fine. Be that way!"
	if True in [char in question for char in string.ascii_letters] and question == question.upper():
		return "Whoa, chill out!"
	if question.endswith("?"):
		return "Sure."
	return "Whatever."
