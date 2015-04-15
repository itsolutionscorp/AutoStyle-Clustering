'''
File for Bob
'''


def hey(sentence):
	import re
	sentence=sentence.rstrip()

	if sentence == "":
		return "Fine. Be that way!"
	elif sentence == sentence.upper() and re.search("[A-Z]", sentence):
		return "Whoa, chill out!"
	elif sentence.endswith("?"):
		return "Sure."
	else:
		return "Whatever."
